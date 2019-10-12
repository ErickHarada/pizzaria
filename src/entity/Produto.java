package entity;

public class Produto {
	
	private int id_produto;
	private String nome;
	private int quantidade_produto;
	private double preco;
	
	public Produto(int id_produto, String nome, int quantidade_produto, double preco) {
		this.id_produto = id_produto;
		this.nome = nome;
		this.quantidade_produto = quantidade_produto;
		this.preco = preco;
	}
	
	public Produto() {}
	
	public int getId_produto() {
		return id_produto;
	}
	
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString() {
		return String.format("Produto=[nome=%s, quantidade_produto=%d, preco=%f]", nome, quantidade_produto, preco);
	}

}