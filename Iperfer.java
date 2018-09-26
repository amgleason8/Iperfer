import java.io.*;
//import java.net.*;


public class Iperfer {
	public static void main(String[] args) throws IOException {
		
		String hostname;
		int port;
		int time;
		
		if(args.length == 7) {
			if(args[0].compareTo("-c") == 0 && 
					args[1].compareTo("-h") == 0 && 
					args[3].compareTo("-p") == 0 &&
					args[5].compareTo("-t") == 0) 
			{	
				// TODO start client mode
				hostname = args[2];
				port = Integer.parseInt(args[4]);
				time = Integer.parseInt(args[6]);
				
				Client client = new Client(hostname, port, time);
				client.start();
			}
			else {
				System.out.println("Error: missing or additional arguments");
				System.exit(1);
			}
		}
		else if(args.length == 3) {
			if(args[0].compareTo("-s") == 0 && args[1].compareTo("-p") == 0) {
				// TODO star server mode
				port = Integer.parseInt(args[2]);
				
				Server server = new Server(port);
				server.start();
			}
			else {
				System.out.println("Error: missing or additional arguments");
				System.exit(1);
			}
		}
		else {
			System.out.println("Error: missing or additional arguments");
			System.exit(1);
		}
	}
	}

