package br.ucb.projetoFinal.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.ucb.projetoFinal.core.ServerChat;

public class ChatConversationHandlerJob extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String userName;
	
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	
	public ChatConversationHandlerJob (Socket socket) {
		setSocket(socket);
	}
		
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public void run() {
		super.run();
		try {
			setIn(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
			
			setOut(new PrintWriter(getSocket().getOutputStream(), true));
			
			getOut().println("NAMEREQUIRED");
			setUserName(getIn().readLine());
			
			if (getUserName() == null) {	
				return;
			}
			if (!ServerChat.userNames.contains(getUserName())) {
				ServerChat.userNames.add(getUserName());
				getOut().println("NAMEACCEPTED");
				ServerChat.printWriters.add(getOut());
			}
			
			while (true) {
				String clientMessage = in.readLine();
				if (clientMessage == null) {
					return;
				}
				for (PrintWriter writer : ServerChat.printWriters) {
					writer.println(getUserName() + ": " + clientMessage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}










