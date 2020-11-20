package br.ucb.projetoFinal.core;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import br.ucb.projetoFinal.entidade.Produto;
import br.ucb.projetoFinal.front.ClientFront;
import br.ucb.projetoFinal.job.CadastraClientJob;
import br.ucb.projetoFinal.job.CadastraProdutoJob;
import br.ucb.projetoFinal.job.DeletaProdutoJob;

public class Client {

	static BufferedReader in;
	public static PrintWriter out;
	public static ClientFront front = new ClientFront();
	
	
	
	public static BufferedReader getIn() {
		return in;
	}



	public static void setIn(BufferedReader in) {
		Client.in = in;
	}



	public static PrintWriter getOut() {
		return out;
	}



	public static void setOut(PrintWriter out) {
		Client.out = out;
	}



	public static void main(String[] args) throws UnknownHostException, IOException {
		
		
		
		
		front.startFront();
		Socket soc = new Socket("localhost", 9821);
		in = new BufferedReader (new InputStreamReader(soc.getInputStream()));
		out = new PrintWriter(soc.getOutputStream(), true);
		
		
		while (true) {
			String serverMsg = in.readLine();
			if (serverMsg.contentEquals("NAMEREQUIRED")) {
				CadastraClientJob cadastraJob = new CadastraClientJob();
				cadastraJob.start();
								
				
			} else if (serverMsg.contentEquals("LISTPRODUTOSOK")) {
				ClientFront.chatArea.setText("");
				for (Produto produto : Server.cliente.getProdutos()) {
					ClientFront.chatArea.append(("Nome: " + produto.getNome() + " ID: " + produto.getId() + " Quantidade: " + produto.getQtd() + "\n"));
				
				}
			} else if (serverMsg.contentEquals("CADPRODUTO")) {
				CadastraProdutoJob cadastraProduto = new CadastraProdutoJob();
				cadastraProduto.start();
			} else if (serverMsg.contentEquals("REMOVEPRODUTO")) {
				DeletaProdutoJob deletaProduto = new DeletaProdutoJob();
				deletaProduto.start();
				getOut().println("LISTPRODUTOS");
			} else if (serverMsg.contentEquals("ABRECHAT")) {
				ClientChat chat = new ClientChat();
				chat.startChat();
				
			}
			
		}
		
		
	}

}

