import java.net.*;
import java.io.*;

public class Server extends Thread{
	
	public Socket socket;
	
	public Server(Socket socket) {
		this.socket = socket;
	}
	
	public static void main(String args[]) throws IOException {
		ServerSocket ss = new ServerSocket(777);
		System.out.println("Listening on port: 777");

		//using threads so we can connect as multiple clients
		while(true) {			
			Socket s = ss.accept();
			Server server = new Server(s);
			server.start();
		}
		
	}
	public void run() {
		try {			
			while(true) {
				//get input from client
				ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
				Message msg = (Message) ois.readObject();
				
				//process send output/response
				System.out.println("Processing String: "+msg.getText());
				Message response = new Message(Server.checkPalindrome(msg.getText()));
				ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
				oos.writeObject(response);
			}
		}catch(IOException | ClassNotFoundException e) {
			//nothing here	
		}
	}
	
	public static String checkPalindrome(String str) {
		
		String reverse = new StringBuffer(str).reverse().toString();
		
		String res = str.equals(reverse) ? str + " is a palindrome" : str + " is not a palindrome";
		
		return res;
	}
}
