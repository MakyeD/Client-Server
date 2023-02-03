import java.net.*;
import java.io.*;

public class Server {
	
	private Socket socket = null;
	private String name = "Server of Makye Daniels";
	private int num = 23;
	private int sum;
	private String message = null;

	public Server(int port) {
		
		while(true) {
		
			try {
				
				//Open server's socket
				ServerSocket server = new ServerSocket(port);
				System.out.println();
				System.out.println("Server ready and waiting...");
				socket = server.accept();
				System.out.println("Client accepted");
				
				//Open data input stream
				DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				
					try {
						
						//Prints socket output from client
						PrintStream ps = new PrintStream(socket.getOutputStream());
						
						//client's message
						System.out.println("Reading in client message...");
						message = input.readUTF();
						System.out.println("Client Name: "  + message.substring(10,23));
						System.out.println("Server of: " + name.substring(10));
						Integer toInt = Integer.parseInt(message.substring(24));
						
						//Verifying number is between 0 and 101
						System.out.println("Verifying number is valid...");
						if(toInt < 0 || toInt > 100) {
						
							System.out.println("Number out of range");
							ps.print("Client side number out of range");
							socket.close();
							server.close();
							System.out.println("Terminating...");
							continue;
						}
						
						//Print client's number and server's number
						System.out.println("Client Number: " + message.substring(24));
						System.out.println("Server Number: " + num);
						
						//Sum of client and server number
						sum = toInt + num;
						System.out.println("Sum:" + sum );
						
						//Send message to client
						System.out.println("Sending message to client...");
						ps.print("From Server: " + name.substring(10) + " " + num);
						
						
						System.out.println("Closing printStream, inputStream, socket, and serverSocket");
						ps.close();
						input.close();
						socket.close();
						server.close();
						
					
					}catch(IOException i) {
						
						System.out.println(i);
					}
					
				
				
			}catch(IOException i){
				
				System.out.println(i);
			}
		
		}
		
	}
	
	public static void main(String args[]) {
		
		Server server = new Server(5000);
	}
}
