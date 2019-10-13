import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import TableModels.Tabela;
import entity.Cliente;
import factory.ConnectionFactory;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;

public class Inicial extends JFrame {

	private JPanel inicialPane;
	private JTable table;
	private JTextField textVerifica;
	private JScrollPane scroll;
	private JButton btnEditar;
	private JButton btnNewButton;

	public Inicial() {
		setTitle("Pizzaria");
		setLocationRelativeTo(null); // centraliza a janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 496); // tamanho
		inicialPane = new JPanel();
		inicialPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(inicialPane);
		inicialPane.setLayout(null);

		table = new JTable();
		table.setBounds(12, 13, 614, 45);

		textVerifica = new JTextField();
		textVerifica.setBounds(41, 234, 138, 30);
		inicialPane.add(textVerifica);
		textVerifica.setColumns(10);

		scroll = new JScrollPane();
		scroll.setLocation(12, 13);
		scroll.setSize(614, 190);
		scroll.setViewportView(table);
		inicialPane.add(scroll);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEADING);
		btnCadastrar.setBounds(41, 332, 97, 30);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro cadastro = new Cadastro();
				cadastro.setVisible(true);
				cadastro.setLocationRelativeTo(null); // centraliza a janela
			}
		});

		inicialPane.add(btnCadastrar);

		JButton btnVerifica = new JButton("Verificar");
		btnVerifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = ConnectionFactory.getConnection();
					connection.setAutoCommit(false);
					Cliente cliente = new Cliente();
					ArrayList<Cliente> lista = cliente.carregarCliente(connection, textVerifica.getText());
					Tabela tabela = new Tabela(lista);
					table.setModel(tabela);
					connection.commit();
					connection.close();
					if (lista.size() > 1) {
						btnEditar.setEnabled(true);
						btnNewButton.setEnabled(true);
					} else {
						btnEditar.setEnabled(false);
						btnNewButton.setEnabled(false);
					}

				} catch (SQLException s) {
					s.printStackTrace();
				}
			}
		});
		btnVerifica.setBounds(191, 234, 97, 30);
		inicialPane.add(btnVerifica);

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro cadastro = new Cadastro();
				cadastro.setVisible(true);
				cadastro.setLocationRelativeTo(null); // centraliza a janela
			}
		});
		btnEditar.setBounds(150, 332, 97, 30);
		inicialPane.add(btnEditar);
		
		btnNewButton = new JButton("Pedir");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pedido pedido = new Pedido();
				pedido.setVisible(true);
				pedido.setLocationRelativeTo(null); // centraliza a janela
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(479, 289, 147, 73);
		inicialPane.add(btnNewButton);

	}
}
