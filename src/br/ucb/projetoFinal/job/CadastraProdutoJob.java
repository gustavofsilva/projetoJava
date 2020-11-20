package br.ucb.projetoFinal.job;

import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import br.ucb.projetoFinal.core.Server;
import br.ucb.projetoFinal.entidade.Produto;
import br.ucb.projetoFinal.front.ClientFront;

public class CadastraProdutoJob extends Thread{
	protected Semaphore sem = new Semaphore(10, true);
	Random random = new Random();
	
	@Override
	public void run() {
		super.run();
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String nome = JOptionPane.showInputDialog(ClientFront.site, "Digite o nome do produto: ", 
				"nome obrigatório", JOptionPane.PLAIN_MESSAGE);
		if (nome == null) {
			return;
		} else {
			Produto produto = new Produto();
			produto.setNome(nome);
			try {
				produto.setQtd(Integer.parseInt(JOptionPane.showInputDialog(ClientFront.site, "Digite a quantidade disponível: ", 
						"quantidade obrigatória", JOptionPane.PLAIN_MESSAGE)));	
			} catch (NumberFormatException e) {
				return;
			}
			
			
			boolean existe = false;
			int id=0;
			
			do {
				id = random.nextInt(999);
				for (Produto produto2 : Server.cliente.getProdutos()) {
					if (produto2.getId() == id) {
						existe = true;
					}
				}
			} while (existe);
			
			produto.setId(id);
			Server.cliente.getProdutos().add(produto);;
		}	
		sem.release();
	}

}
