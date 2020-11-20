package br.ucb.projetoFinal.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.ucb.projetoFinal.job.ChatConversationHandlerJob;

public class ServerChat {
	
	public static ArrayList<String> userNames = new ArrayList<String>();
	public static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();
	private static ServerSocket ss;

	public static void main(String[] args) throws IOException {
		System.out.println("Esperando cliente...");
		
		ss = new ServerSocket(9801);
		
		while (true) {
			Socket soc = ss.accept();
			System.out.println("Conexão estabelecida!");
			
			ChatConversationHandlerJob handler = new ChatConversationHandlerJob(soc);
			handler.start();
			
		}

	}

}
