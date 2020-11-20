package br.ucb.projetoFinal.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class SiteHandlerJob extends Thread{

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public SiteHandlerJob(Socket soc) {
		setSocket(soc);
	}
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
	@Override
	public void run() {
		super.run();
		try {
			setIn(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
			
			setOut(new PrintWriter(getSocket().getOutputStream(), true));
			
			getOut().println("NAMEREQUIRED");
					
			
			while (true) {
				String clientMessage = in.readLine();
				if (clientMessage.contentEquals("CADASTRARPROD")) {
					getOut().println("CADPRODUTO");
					
				} else if (clientMessage.contentEquals("LISTPRODUTOS")) {	
					getOut().println("LISTPRODUTOSOK");
					
				} else if (clientMessage.contentEquals("REMOVEPRODUTO")) {
					getOut().println("REMOVEPRODUTO");
					
				} else if (clientMessage.contentEquals("ABRECHAT")) {
					getOut().println("ABRECHAT");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


