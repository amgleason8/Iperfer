import java.net.*;
import java.io.*;

public class Server {
	
	int port;
	
	Server(int port){
		this.port = port;
	}

	public void start() {
		// TODO
		
		if(this.port > 65535 || this.port < 1024 ){
		    System.out.println("Error: port number must be in the range 1024 to 65535");
		    System.exit(1);
		}
		
		ServerSocket serve = null;
		Socket server = null;
		byte[] b = new byte[1000];
		int input = 0;
		int count = 0;
		
		try {
			serve = new ServerSocket(this.port);
			server = serve.accept();
		}
		catch(IOException e) {
			System.out.println("Server socket error");
			System.exit(1);
		}
		
		long time = System.nanoTime();
		while(input > -1) {
			try {
				input = server.getInputStream().read(b);
				count += input;
			}
			catch (IOException e) {
				System.out.println("Server failure");
				System.exit(1);
			}
		} 
		double timeElapsed = System.nanoTime() - time;
		
		try {
			server.close();
			serve.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		count /= 1000;
		
		// get time back in seconds
		timeElapsed = timeElapsed * 0.000000001;
		double kbs = count/timeElapsed;
		
		System.out.println("received=" + count + " KB rate = " + kbs/1000 + " Mbps");
	}

}  