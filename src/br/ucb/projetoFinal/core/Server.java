package br.ucb.projetoFinal.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.ucb.projetoFinal.entidade.Cliente;
import br.ucb.projetoFinal.job.SiteHandlerJob;


public class Server {
	private static ServerSocket ss;

	public static Cliente cliente = new Cliente();

	public static void main(String[] args) throws IOException {
		System.out.println("Esperando cliente...");
		
		ss = new ServerSocket(9821);
		
		while (true) {
			Socket soc = ss.accept();
			System.out.println("Conexão estabelecida!");
			
			SiteHandlerJob handler = new SiteHandlerJob(soc);
			handler.start();
			
		}
	}

}
