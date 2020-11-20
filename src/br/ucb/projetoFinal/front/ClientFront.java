package br.ucb.projetoFinal.front;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ucb.projetoFinal.core.Client;



public class ClientFront {
	
	public static JFrame site = new JFrame("Site de Produtos");
	public static JTextArea chatArea = new JTextArea(22, 45);
	static JLabel blankLabel = new JLabel();
	public static JButton cadProduto = new JButton("Cadastrar Produto");
	public static JButton listProduto = new JButton("Listar Produtos");
	public static JButton remProduto = new JButton("Remover Produto");
	public static JButton abreChat = new JButton("Entrar em Contato");
	
	static BufferedReader in;
	public static PrintWriter out;
	
	
	public void startFront(){
		
		
			site.setLayout(new FlowLayout());
			site.add(new JScrollPane(chatArea));
			site.add(blankLabel);
			site.add(cadProduto);
			site.add(listProduto);
			site.add(remProduto);
			site.add(abreChat);
			
			site.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			site.setSize(475, 500);
			site.setVisible(true);
			
			cadProduto.setVisible(false);
			listProduto.setVisible(false);
			remProduto.setVisible(false);
			
			chatArea.setEditable(false);
			
			
			
			ClientFront.cadProduto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {	
					Client.out.println("CADASTRARPROD");				
						
				}			
			});
			
			ClientFront.listProduto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					Client.out.println("LISTPRODUTOS");	
				}
			});
			
			ClientFront.remProduto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					Client.out.println("REMOVEPRODUTO");	
				}
			});
			
			ClientFront.abreChat.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					Client.out.println("ABRECHAT");
				}
			});		
	}
}
