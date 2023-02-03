import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	
	private Socket socket = null;
	private String name = "Client of Makye Daniels";
	
	
	public Client (String host, int port) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number from 1 to 100 ");
		String number = scan.nextLine();
		
		//Establish connection w/server
		try {
				//initialize socket with IP and port
				socket = new Socket(host, port);
				System.out.println("Connection with server established");
				
				//Open output stream
				DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
				
				//Reads input stream
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//Write string to output stream
				dataOut.writeUTF(name + " " + number);
				System.out.println("Sending name and number to server...");
				
				//Print string from input stream
				System.out.println("Incoming message from server...");
				System.out.println(br.readLine());
				
				try {
					
					dataOut.close();
					scan.close();
					socket.close();
					System.out.println("Closed output stream, scanner object, and socket");
			
				}catch(IOException i) { 
					
					System.out.println(i);
				}
				
				return;
		
		}catch (UnknownHostException e){
			
			e.printStackTrace();
			scan.close();
			return;
			
		}catch (IOException i){
			
			System.out.println(i);
			scan.close();
			return;
		}
	}
	
	public static void main(String args[]) throws UnknownHostException {
		
		Client client = new Client("127.0.0.1", 5000);
	}

}
