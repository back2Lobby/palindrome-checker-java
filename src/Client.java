
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException {
		//making socket connection on localhost + port 777
		Socket s = new Socket("localhost",777);
		
		try {
			while(true) {
				// getting input from client				
				System.out.print("Client: ");
				Scanner scn = new Scanner(System.in);
				String str = scn.nextLine();
				
				// making serializable object and sending to server
				Message msg = new Message(str);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(msg);
				
				// receiving response and showing it on console 
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message response = (Message) ois.readObject();
				System.out.println("Server: "+response.getText());
			}
		}catch(NoSuchElementException e) {
			//nothing here
		}
	}
}
