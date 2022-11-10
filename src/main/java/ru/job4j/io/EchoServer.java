package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
	
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(9000)) {
			while (!server.isClosed()) {
				Socket socket = server.accept();
				try (OutputStream out = socket.getOutputStream();
					 BufferedReader in = new BufferedReader(
							 new InputStreamReader(socket.getInputStream()))) {
					out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
					String line = in.readLine();
					if (line != null && !line.isEmpty() && line.matches("^.+[?]msg=Exit.+$")) {
						server.close();
					}
					if (line != null && !line.isEmpty() && line.matches("^.+[?]msg=Hello.+$")) {
						out.write("Hello".getBytes());
					}
					if (line != null && !line.isEmpty()
						&& !line.matches("^.+[?]msg=Exit.+$") && !line.matches("^.+[?]msg=Hello.+$")) {
						out.write("What".getBytes());
					}
					out.flush();
				}
			}
		} catch (IOException e) {
			LOG.error("job4j Exception", e);
		}
	}
}
