package br.ucb.projetoFinal.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ucb.projetoFinal.core.ClientChat;

public class ChatListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		ClientChat.out.println(ClientChat.textField.getText());
		ClientChat.textField.setText("");
	}

}
