import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import TableModels.TabelaProdutos;
import entity.Produto;
import factory.ConnectionFactory;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pedido extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scroll;
	private JTextField textCliente;
	private JTextField textTelefone;

	public Pedido() {
		setTitle("Pedido");
		setLocationRelativeTo(null); // centraliza a janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 496); // tamanho
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setBounds(12, 13, 614, 45);
		mostraProduto();

		scroll = new JScrollPane();
		scroll.setLocation(291, 24);
		scroll.setSize(335, 119);
		scroll.setViewportView(table);
		contentPane.add(scroll);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Arial", Font.BOLD, 15));
		lblCliente.setBounds(27, 32, 56, 16);
		contentPane.add(lblCliente);

		textCliente = new JTextField();
		textCliente.setFont(new Font("Arial", Font.BOLD, 15));
		textCliente.setBounds(95, 25, 173, 30);
		textCliente.setText("Cliente");
		textCliente.setEditable(false);
		contentPane.add(textCliente);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		lblTelefone.setBounds(12, 74, 71, 16);
		contentPane.add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		textTelefone.setBounds(95, 67, 173, 30);
		textTelefone.setText("99508657");
		textTelefone.setEditable(false);
		contentPane.add(textTelefone);
	}

	public void mostraProduto() {
		try {
			Connection connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			Produto produto = new Produto();
			ArrayList<Produto> estoque = produto.carregarProduto(connection);
			TabelaProdutos tabela = new TabelaProdutos(estoque);
			table.setModel(tabela);
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
