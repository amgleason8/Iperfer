import java.io.*;
import java.net.*;

public class Client {
	
	String name;
	int port;
	int time;
	OutputStream output = null;
	byte[] outputBytes = new byte[1000];
	int kiloBytes = 0;
	
	Client(String name, int port, int time){
		this.name = name;
		this.port = port;
		this.time = time;
	}
	
	public void start() {
		if(port > 65535 || port < 1024) {
			System.out.println("Error: port number must be in the range 1024 to 65535");
			System.exit(1);
		}
		if(this.time <= 0) {
			System.out.println("Error: time should be a positive integer");
			System.exit(1);
		}
		Socket client = null;
		long endTime = Integer.toUnsignedLong(time) * 1000000000;
		
		
		try {
			client = new Socket(this.name, this.port);
			output = client.getOutputStream();
		} catch (IOException e ) {
			System.out.println("Client socket failure");
			System.exit(1);
		}
		
		// Now we try sending the data
		long start = System.nanoTime();
		while(System.nanoTime() - start < endTime) {
			try {
				output.write(outputBytes);
			}
			catch (IOException e) {
				System.out.println("Sending data failed");
				System.exit(1);
			}
			kiloBytes++;
		}
		System.out.println("time elapsed: " + (System.nanoTime() - start)/1000000000);
		// if done sending the data close the socket and output
		
		try {
			output.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("time elapsed: " + time);
		double kbs = kiloBytes/time;
		System.out.println("sent=" + kiloBytes + " KB rate = " + kbs/1000 + " Mbps");
		
	}
}
