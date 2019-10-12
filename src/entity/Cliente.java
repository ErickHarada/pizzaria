package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public void carregar(Connection connection) {
		String sql = "SELECT nome, telefone, cep, endereco FROM cliente WHERE id_cliente=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id_cliente);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				nome = rs.getString("nome");
				telefone = rs.getString("telefone");
				cep = rs.getString("cep");
				endereco = rs.getString("endereco");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Connection connection) {
		String sql = "update cliente set nome=?, telefone=?, cep=?, endereco=? where id_cliente=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, nome);
			pst.setString(2, telefone);
			pst.setString(3, cep);
			pst.setString(4, endereco);
			pst.setInt(5, id_cliente);

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Connection connection) {
		String sql = "delete from cliente where id_cliente=?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, id_cliente);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}