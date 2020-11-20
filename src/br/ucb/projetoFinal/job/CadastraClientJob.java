package br.ucb.projetoFinal.job;

import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import br.ucb.projetoFinal.core.Server;
import br.ucb.projetoFinal.front.ClientFront;

public class CadastraClientJob extends Thread {
	protected Semaphore sem = new Semaphore(10, true);
	Random random = new Random();
	
	@Override
	public void run() {
		super.run();
		try {
			sem.acquire();
			Thread.sleep(3000);
			String nomeCliente = JOptionPane.showInputDialog(ClientFront.site, "Digite o nome do usuário: ", 
					"nome obrigatório", JOptionPane.PLAIN_MESSAGE);
			if (nomeCliente == null) {
				return;
			}

			
			Server.cliente.setName(nomeCliente);
			Server.cliente.setId(random.nextInt(999));
			
			ClientFront.cadProduto.setVisible(true);
			ClientFront.listProduto.setVisible(true);
			ClientFront.remProduto.setVisible(true);
			
			sem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
