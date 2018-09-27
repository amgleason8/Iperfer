import java.net.*;
import java.io.*;

public class server {
	
	int port;
	
	server(int port){
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
		
		double time = System.nanoTime();
		while(input > -1) {
			try {
				input = server.getInputStream().read(b);
				//System.out.println("read: " + input + " bytes");
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
		count = count + 2;


		count = count/1000;
		
		// get time back in seconds
		timeElapsed = timeElapsed * (0.000000001);
//		System.out.println("time elapsed: " + timeElapsed);
		double kbs = count/timeElapsed;
		
		System.out.println("received=" + count + " KB rate=" + kbs/1000 + " Mbps");
	}

}  
