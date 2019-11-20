package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public Produto(String nome, int quantidade_produto, double preco) {
		this.nome = nome;
		this.quantidade_produto = quantidade_produto;
		this.preco = preco;
	}

	public Produto() {
	}

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

	public ArrayList<Produto> carregarProduto(Connection connection) {
		String sql = "select id_produto, nome, quantidade_produto, preco from produto";
		ArrayList<Produto> lista = new ArrayList<Produto>();
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto(rs.getInt("id_produto"), rs.getString("nome"),
						rs.getInt("quantidade_produto"), rs.getDouble("preco"));
				lista.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public Produto carregarProdutoEscolhidos(Connection connection, int verifica) {
		String sql = "select nome from produto where id_produto=?";
		Produto produto = new Produto();
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, verifica);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				produto.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

}