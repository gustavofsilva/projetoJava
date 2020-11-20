package br.ucb.projetoFinal.job;

import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import br.ucb.projetoFinal.core.Server;
import br.ucb.projetoFinal.entidade.Produto;
import br.ucb.projetoFinal.front.ClientFront;

public class DeletaProdutoJob extends Thread{
	protected Semaphore sem = new Semaphore(10, true);
	
	@Override
	public void run() {
		super.run();
		
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int idDel =0;
		
		try {
			idDel = Integer.parseInt(JOptionPane.showInputDialog(ClientFront.site, "Digite o id do produto: ", 
					"id obrigatório", JOptionPane.PLAIN_MESSAGE));
		} catch (NumberFormatException e) {
			System.out.println("Erro de input!");
		}
		
		int aux=0;
		boolean existe=false;
		
		for (Produto produto : Server.cliente.getProdutos()) {			
			if (produto.getId() == idDel) {
				existe = true;
				break;
			}
			aux++;
		}
		if (existe) {
			Server.cliente.getProdutos().remove(aux);
		}		
		sem.release();
		
	}
}
