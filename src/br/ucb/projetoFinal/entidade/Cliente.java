package br.ucb.projetoFinal.entidade;

import java.util.ArrayList;

public class Cliente {
	private String name;
	private int id;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
