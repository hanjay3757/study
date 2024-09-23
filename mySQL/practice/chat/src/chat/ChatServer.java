package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
	private static final int PORT = 12345;
	private static Set<PrintWriter> clientWriters = new HashSet<>();

	public static void main(String[] args) {
		System.out.println("Chat server is running...");
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				new ClientHandler(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				synchronized (clientWriters) {
					clientWriters.add(out);
				}

				String message;
				while ((message = in.readLine()) != null) {
					System.out.println("Received: " + message);
					broadcast(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				synchronized (clientWriters) {
					clientWriters.remove(out);
				}
			}
		}

		private void broadcast(String message) {
			synchronized (clientWriters) {
				for (PrintWriter writer : clientWriters) {
					writer.println(message);
				}
			}
		}
	}
}
