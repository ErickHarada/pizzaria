package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente {

	private int id_cliente;
	private String nome;
	private String telefone;
	private String cep;
	private String endereco;

	public Cliente(int id_cliente, String nome, String telefone, String cep, String endereco) {
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
	}

	public Cliente(String nome, String telefone, String cep, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.cep = cep;
		this.endereco = endereco;
	}

	public Cliente() {
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String toString() {
		return String.format("Cliente=[nome=%s, telefone=%s, cep=%s, endereco=%s]", nome, telefone, cep, endereco);
	}

	public void inserirCliente(Connection connection) {
		String sql = "INSERT INTO cliente (nome, telefone, cep, endereco) VALUES(?,?,?,?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, nome);
			pst.setString(2, telefone);
			pst.setString(3, cep);
			pst.setString(4, endereco);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Cliente> carregarCliente(Connection connection, String verifica) {
		String sql = "SELECT id_cliente, nome, telefone, cep, endereco FROM cliente WHERE telefone=?";
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, verifica);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("telefone"), rs.getString("cep"),
						rs.getString("endereco"));
				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void atualizarCliente(Connection connection, String tel) {
		String sql = "update cliente set nome=?, telefone=?, cep=?, endereco=? where telefone=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, nome);
			pst.setString(2, telefone);
			pst.setString(3, cep);
			pst.setString(4, endereco);
			pst.setString(5, tel);

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarCliente(Connection connection, String tel) {
		String sql = "delete from cliente where telefone=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, tel);
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}