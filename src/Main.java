import java.sql.Connection;
import java.sql.SQLException;

import entity.Cliente;
import factory.ConnectionFactory;

public class Main {
	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Inicial inicial = new Inicial();
			inicial.setVisible(true);
			inicial.setLocationRelativeTo(null); //centraliza a janela
			
			/*
			 * connection.setAutoCommit(false); Cliente cliente = new Cliente();
			 * cliente.setNome("Joaquim da Silva 123"); cliente.setTelefone("11903842");
			 * cliente.setCep("0228045"); cliente.setEndereco("Rua Marechal Deodoro");
			 * cliente.inserirCliente(connection); connection.commit();
			 * cliente.carregar(connection); System.out.println(cliente);
			 * connection.close();
			 */
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
