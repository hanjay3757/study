package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
	private static final String SERVER_ADDRESS = "localhost";
	private static final int SERVER_PORT = 12345;

	public static void main(String[] args) {
		try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
			new ReadThread(socket).start();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

			String message;
			while ((message = console.readLine()) != null) {
				out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ReadThread extends Thread {
		private BufferedReader in;

		public ReadThread(Socket socket) {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			String message;
			try {
				while ((message = in.readLine()) != null) {
					System.out.println("Server: " + message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
