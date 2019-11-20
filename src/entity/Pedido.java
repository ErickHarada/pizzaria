package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Pedido {

	private int id_pedido;
	private int quantidade_produto;
	private double preco_total;
	private Date data_pedido;
	private boolean borda_pizza;
	private int fk_cliente_id_cliente;
	private int fk_produto_id_produto;

	public Pedido(int id_pedido, int quantidade_produto, double preco_total, Date data_pedido, boolean borda_pizza,
			int fk_cliente_id_cliente, int fk_produto_id_produto) {
		this.id_pedido = id_pedido;
		this.quantidade_produto = quantidade_produto;
		this.preco_total = preco_total;
		this.data_pedido = data_pedido;
		this.borda_pizza = borda_pizza;
		this.fk_cliente_id_cliente = fk_cliente_id_cliente;
		this.fk_produto_id_produto = fk_produto_id_produto;
	}
	
	public Pedido(int quantidade_produto, double preco_total, Date data_pedido, boolean borda_pizza,
			int fk_cliente_id_cliente, int fk_produto_id_produto) {
		this.quantidade_produto = quantidade_produto;
		this.preco_total = preco_total;
		this.data_pedido = data_pedido;
		this.borda_pizza = borda_pizza;
		this.fk_cliente_id_cliente = fk_cliente_id_cliente;
		this.fk_produto_id_produto = fk_produto_id_produto;
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
	public int getFk_cliente_id_cliente() {
		return fk_cliente_id_cliente;
	}

	public void setFk_cliente_id_cliente(int fk_cliente_id_cliente) {
		this.fk_cliente_id_cliente = fk_cliente_id_cliente;
	}

	public int getFk_produto_id_produto() {
		return fk_produto_id_produto;
	}

	public void setFk_produto_id_produto(int fk_produto_id_produto) {
		this.fk_produto_id_produto = fk_produto_id_produto;
	}
	
	public void inserirPedido(Connection connection) {
		String sql = "INSERT INTO pedido (fk_cliente_id_cliente, fk_produto_id_produto, quantidade_produto, borda_pizza, preco_total, data_pedido) VALUES(?,?,?,?,?,?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, fk_cliente_id_cliente);
			pst.setInt(2, fk_produto_id_produto);
			pst.setInt(3, quantidade_produto);
			pst.setBoolean(4, borda_pizza);
			pst.setDouble(5, preco_total);
			pst.setDate(6,data_pedido);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Pedido> carregarPedido(Connection connection, int verifica) {
		String sql = "SELECT id_pedido, fk_cliente_id_cliente, fk_produto_id_produto, quantidade_produto, borda_pizza, preco_total, data_pedido FROM pedido WHERE fk_cliente_id_cliente=?";
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, verifica);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido(rs.getInt("id_pedido"), rs.getInt("quantidade_produto"), rs.getDouble("preco_total"), rs.getDate("data_pedido"), rs.getBoolean("borda_pizza"), 
						rs.getInt("fk_cliente_id_cliente"), rs.getInt("fk_produto_id_produto"));
				lista.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void deletarCliente(Connection connection, int num) {
		String sql = "delete from pedido where id_pedido=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {			
			pst.setInt(1, num);
			pst.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
