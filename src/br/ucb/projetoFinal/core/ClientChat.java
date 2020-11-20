package br.ucb.projetoFinal.core;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import br.ucb.projetoFinal.listener.ChatListener;

public class ClientChat {
	
	static JFrame chatWindow = new JFrame("Chat de Internet");
	static JTextArea chatArea = new JTextArea(22, 45);
	public static JTextField textField = new JTextField(40);
	static JLabel blankLabel = new JLabel();
	static JButton sendButton = new JButton("Enviar!");
	
	ClientChat(){
		chatWindow.setLayout(new FlowLayout());
		chatWindow.add(new JScrollPane(chatArea));
		chatWindow.add(blankLabel);
		chatWindow.add(textField);
		chatWindow.add(sendButton);
		
		chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatWindow.setSize(475, 500);
		chatWindow.setVisible(true);
		
		textField.setEditable(false);
		chatArea.setEditable(false);
		
		ChatListener chatListener = new ChatListener();
		
		sendButton.addActionListener(chatListener);
		textField.addActionListener(chatListener);
	}

	public static void main(String[] args) {
		ClientChat client = new ClientChat();
		
		try {
			client.startChat();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	static BufferedReader in;
	public static PrintWriter out;
	
	public void startChat () throws UnknownHostException, IOException {
		String ipAdress = JOptionPane.showInputDialog(chatWindow, "Digite o endereco de IP do Servidor: ", 
				"endereco de ip obrigatório", JOptionPane.PLAIN_MESSAGE);
		Socket soc = new Socket(ipAdress, 9801);
		in = new BufferedReader (new InputStreamReader(soc.getInputStream()));
		out = new PrintWriter(soc.getOutputStream(), true);
		
		while (true) {
			
			String serverMsg = in.readLine();
			if (serverMsg.contentEquals("NAMEREQUIRED")) {
				String userName = JOptionPane.showInputDialog(chatWindow, "Digite um nome: ", 
						"Nome é obrigatório!", JOptionPane.PLAIN_MESSAGE);
				out.println(userName);
			} else if (serverMsg.contentEquals("NAMEACCEPTED")) {
				textField.setEditable(true);
			} else {
				chatArea.append(serverMsg);
			}	
			
		}
		
		
	}

}


















