import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import TableModels.TabelaPedido;
import entity.Cliente;
import entity.Pedido;
import entity.Produto;
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
import javax.swing.JLabel;

public class Inicial extends JFrame {

	private JPanel inicialPane;
	private JTable table;
	private JLabel lblTelefone;
	private JTextField textVerifica;
	private JScrollPane scroll;
	private JButton btnEditar;
	private JButton btnPedir;
	private JButton btnDeletar;
	ArrayList<Cliente> lista;
	ArrayList<Pedido> pedido;
	ArrayList<Produto> produto = new ArrayList<Produto>();
	private JTextField textNumeroPedido;
	private JLabel lblNmeroDoPedido;
	private JButton btnDeletarPedido;

	public Inicial(String num) {
		setTitle("Pizzaria");
		setLocationRelativeTo(null); // centraliza a janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 496); // tamanho
		inicialPane = new JPanel();
		inicialPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(inicialPane);
		inicialPane.setLayout(null);

		table = new JTable();
		table.setBounds(12, 13, 614, 45);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		lblTelefone.setBounds(12, 280, 72, 16);
		inicialPane.add(lblTelefone);

		textVerifica = new JTextField();
		textVerifica.setToolTipText("");
		textVerifica.setBounds(82, 273, 206, 30);
		inicialPane.add(textVerifica);
		textVerifica.setColumns(10);

		
		if(num != null) {
			textVerifica.setText(num);
		}

		scroll = new JScrollPane();
		scroll.setLocation(12, 13);
		scroll.setSize(767, 190);
		scroll.setViewportView(table);
		inicialPane.add(scroll);

		JButton btnCadastrar = new JButton("Cadastrar"); // Cadastra um novo cliente
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEADING);
		btnCadastrar.setBounds(41, 332, 97, 30);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro cadastro = new Cadastro(null);
				cadastro.setVisible(true);
				cadastro.setLocationRelativeTo(null); // centraliza a janela
			}
		});
		inicialPane.add(btnCadastrar);

		JButton btnVerifica = new JButton("Verificar"); // Verifica se o telefone informado ja está cadastrado
		btnVerifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = ConnectionFactory.getConnection();
					connection.setAutoCommit(false);
					Cliente cliente = new Cliente();
					lista = cliente.carregarCliente(connection, textVerifica.getText());
					if (lista.size() >= 1) {
						btnEditar.setEnabled(true);
						btnPedir.setEnabled(true);
						btnDeletar.setEnabled(true);
						Pedido pedidoRealizado = new Pedido();
						pedido = pedidoRealizado.carregarPedido(connection, lista.get(0).getId_cliente());
						if (pedido.size() < 1) {
							JOptionPane.showMessageDialog(null, "Cliente já cadastrado");
							return;
						}
						btnDeletarPedido.setEnabled(true);
						for (int i = 0; i < pedido.size(); i++) {
							Produto sabor = new Produto();
							produto.add(sabor.carregarProdutoEscolhidos(connection,
									pedido.get(i).getFk_produto_id_produto()));
						}
						TabelaPedido tabela = new TabelaPedido(lista, pedido, produto);
						table.setModel(tabela);
						connection.commit();
						connection.close();

					} else {
						JOptionPane.showMessageDialog(null, "Sem cadastro");
						btnEditar.setEnabled(false);
						btnPedir.setEnabled(false);
						btnDeletar.setEnabled(false);
					}

				} catch (SQLException s) {
					System.out.println(s.getMessage());
					s.printStackTrace();

				}
			}
		});
		btnVerifica.setBounds(297, 273, 97, 30);
		inicialPane.add(btnVerifica);

		btnEditar = new JButton("Editar"); // Editar dados do Cliente
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = ConnectionFactory.getConnection();
					connection.setAutoCommit(false);
					Cliente cliente = new Cliente();
					ArrayList<Cliente> lista = cliente.carregarCliente(connection, textVerifica.getText());
					dispose();
					Cadastro cadastro = new Cadastro(lista);
					cadastro.setVisible(true);
					cadastro.setLocationRelativeTo(null); // centraliza a janela
				} catch (SQLException s) {
					s.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(150, 332, 97, 30);
		inicialPane.add(btnEditar);

		btnPedir = new JButton("Pedir"); // Fazer Pedido
		btnPedir.setEnabled(false);
		btnPedir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cliente cliente = lista.get(0);
				Pedir pedir = new Pedir(cliente.getId_cliente(), cliente.getNome(), cliente.getTelefone());
				pedir.setVisible(true);
				pedir.setLocationRelativeTo(null); // centraliza a janela
			}
		});
		btnPedir.setFont(new Font("Arial", Font.BOLD, 20));
		btnPedir.setBounds(569, 273, 176, 89);
		inicialPane.add(btnPedir);

		btnDeletar = new JButton("DELETAR CLIENTE"); // Deletar Cliente
		btnDeletar.setEnabled(false);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = ConnectionFactory.getConnection();
					connection.setAutoCommit(false);
					Cliente cliente = new Cliente();
					cliente.deletarCliente(connection, textVerifica.getText());
					connection.commit();
					connection.close();
					JOptionPane.showMessageDialog(null, "Cliente deletado");
					dispose();
					Inicial inicial = new Inicial(null);
					inicial.setVisible(true);
					inicial.setLocationRelativeTo(null); // centraliza a janela
				} catch (SQLException s) {
					JOptionPane.showMessageDialog(null, s.getMessage());
					s.printStackTrace();
				}
			}
		});
		btnDeletar.setBounds(259, 332, 145, 30);
		inicialPane.add(btnDeletar);
		
		lblNmeroDoPedido = new JLabel("N\u00FAmero do Pedido");
		lblNmeroDoPedido.setFont(new Font("Arial", Font.BOLD, 15));
		lblNmeroDoPedido.setBounds(377, 219, 137, 16);
		inicialPane.add(lblNmeroDoPedido);
		
		textNumeroPedido = new JTextField();
		textNumeroPedido.setFont(new Font("Arial", Font.PLAIN, 15));
		textNumeroPedido.setBounds(526, 216, 79, 22);
		inicialPane.add(textNumeroPedido);
		textNumeroPedido.setColumns(10);
		
		btnDeletarPedido = new JButton("DELETAR PEDIDO");
		btnDeletarPedido.setEnabled(false);
		btnDeletarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection connection = ConnectionFactory.getConnection();
					connection.setAutoCommit(false);
					Pedido pedido = new Pedido();
					pedido.deletarCliente(connection, Integer.parseInt(textNumeroPedido.getText()));
					connection.commit();
					connection.close();
					JOptionPane.showMessageDialog(null, "Pedido deletado");
					dispose();
					Inicial inicial = new Inicial(null);
					inicial.setVisible(true);
					inicial.setLocationRelativeTo(null); // centraliza a janela
				} catch (SQLException s) {
					s.printStackTrace();
				}
			}
		});
		btnDeletarPedido.setFont(new Font("Arial", Font.PLAIN, 13));
		btnDeletarPedido.setBounds(617, 212, 159, 30);
		inicialPane.add(btnDeletarPedido);
		
	}
}
