package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

	public ChatServer() {
		ServerSocket server;
		try {
			server = new ServerSocket(5002);
			while(true) {
				Socket socket = server.accept();
				new Thread(new EscutaClient(socket)).start();
			}
		} catch (IOException e) {}
		
	}
	
	private class EscutaClient implements Runnable {
		
		Scanner reader;
		
		public EscutaClient(Socket socket) {
			try {
				reader = new Scanner(socket.getInputStream());
			} catch (IOException e) {}
		}
		
		@Override
		public void run() {
			try {
				String txt;
				while((txt = reader.nextLine()) != null) {
					System.out.println(txt);
				}
			}catch(Exception e) {}
			
		}
		
	}
	
	public static void main(String[] args) {
		new ChatServer();

	}


}
