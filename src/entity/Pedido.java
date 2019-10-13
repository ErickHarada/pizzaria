package entity;

import java.util.Date;

public class Pedido {

	private int id_pedido;
	private int quantidade_produto;
	private double preco_total;
	private Date data_pedido;
	private boolean borda_pizza;

	public Pedido(int id_pedido, int quantidade_produto, double preco_total, Date data_pedido, boolean borda_pizza) {
		this.id_pedido = id_pedido;
		this.quantidade_produto = quantidade_produto;
		this.preco_total = preco_total;
		this.data_pedido = data_pedido;
		this.borda_pizza = borda_pizza;
	}

	public Pedido() {
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}

	public double getPreco_total() {
		return preco_total;
	}

	public void setPreco_total(double preco_total) {
		this.preco_total = preco_total;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public boolean isBorda_pizza() {
		return borda_pizza;
	}

	public void setBorda_pizza(boolean borda_pizza) {
		this.borda_pizza = borda_pizza;
	}
}
