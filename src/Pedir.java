import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import TableModels.TabelaProdutos;
import entity.Pedido;
import entity.Produto;
import factory.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Pedir extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scroll;
	private JTable tablePedido;
	private JScrollPane scrollPedido;
	private JTextField textCliente;
	private JTextField textTelefone;
	private JComboBox<String> comboBox;
	ArrayList<Produto> estoque;
	ArrayList<Pedido> editarPedido;
	private JTextField textQuantidade;
	private JButton btnCancelar;
	private JButton btnFinalizar;

	public Pedir(int id, String nome, String tel, int numeroPedido) {
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
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setFont(new Font("Arial", Font.BOLD, 15));
		lblCliente.setBounds(38, 54, 56, 16);
		contentPane.add(lblCliente);

		textCliente = new JTextField(nome);
		textCliente.setFont(new Font("Arial", Font.BOLD, 15));
		textCliente.setBounds(106, 47, 173, 30);
		textCliente.setEditable(false);
		contentPane.add(textCliente);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		lblTelefone.setBounds(23, 98, 71, 16);
		contentPane.add(lblTelefone);

		textTelefone = new JTextField(tel);
		textTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		textTelefone.setBounds(106, 91, 173, 30);
		textTelefone.setEditable(false);
		contentPane.add(textTelefone);

		tabelaPedido(numeroPedido);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Inicial inicial = new Inicial(tel);
				inicial.setVisible(true);
				inicial.setLocationRelativeTo(null); // centraliza a janela
			}
		});
		btnCancelar.setBounds(154, 384, 97, 30);
		contentPane.add(btnCancelar);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.showConfirmDialog(null, pedidoTotal(), "Deseja concluir o pedido?",
						JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					try {
						Connection connection = ConnectionFactory.getConnection();
						connection.setAutoCommit(false);
						Pedido pedido = new Pedido();
						pedido.setFk_cliente_id_cliente(id);
						long millis = System.currentTimeMillis();
						Date data = new Date(millis);
						pedido.setData_pedido(data);
						int idPedido = 0;
						if (numeroPedido != 0) {
							idPedido = numeroPedido;
							pedido.deletarPedidoProduto(connection, idPedido);
						} else {
							pedido.inserirPedido(connection);
							idPedido = pedido.carregarIdPedido(connection);
						}
						connection.commit();
						connection.close();

						for (int i = 0; i < tablePedido.getRowCount(); i++) {
							Connection connectionPPR = ConnectionFactory.getConnection();
							connectionPPR.setAutoCommit(false);
							Pedido pedidoPPR = new Pedido();
							boolean checked = Boolean.valueOf(tablePedido.getValueAt(i, 0).toString());
							if (checked) {
								pedidoPPR.setFk_produto_id_produto(estoque.get(i).getId_produto());
								int qnt = Integer.parseInt(String.valueOf(tablePedido.getValueAt(i, 3).toString()));
								pedidoPPR.setQuantidade_produto(qnt);
								boolean borda = Boolean.valueOf(tablePedido.getValueAt(i, 2).toString());
								pedidoPPR.setBorda_pizza(borda);
								pedidoPPR.setPreco_total(somaPreco());
								pedidoPPR.setFk_pedido_id_pedido(idPedido);
								pedidoPPR.inserirPedidoProduto(connectionPPR);
								System.out.println("entrei" + i);
							}
							connectionPPR.commit();
							connectionPPR.close();
						}
						dispose();
						Inicial inicial = new Inicial(tel);
						inicial.setVisible(true);
						inicial.setLocationRelativeTo(null); // centraliza a janela

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					remove(dialogButton);
				}
			}
		});
		btnFinalizar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnFinalizar.setBounds(386, 384, 97, 30);
		contentPane.add(btnFinalizar);

	}

	public void tabelaPedido(int numeroPedido) { // monta a tabela para fazer o pedido

		tablePedido = new JTable();
		tablePedido.setFont(new Font("Arial", Font.PLAIN, 15));
		tablePedido.setBounds(12, 13, 614, 45);

		scrollPedido = new JScrollPane();
		scrollPedido.setLocation(38, 167);
		scrollPedido.setSize(567, 186);
		scrollPedido.setViewportView(tablePedido);
		contentPane.add(scrollPedido);

		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return Boolean.class;
				case 3:
					return String.class;
				default:
					return String.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				if (column == 1)
					return false;
				else
					return true;
			}
		};

		tablePedido.setModel(model);

		model.addColumn("");
		model.addColumn("Sabor");
		model.addColumn("Borda");
		model.addColumn("Quantidade");

		if (numeroPedido == 0) {
			for (int i = 0; i < estoque.size(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(false, i, 0);
				model.setValueAt(estoque.get(i).getNome(), i, 1);
				model.setValueAt(false, i, 2);
				model.setValueAt("", i, 3);
			}
		} else {
			try {
				Pedido editPedido = new Pedido();
				Connection connection = ConnectionFactory.getConnection();
				connection.setAutoCommit(false);
				editarPedido = editPedido.editarPedido(connection, numeroPedido);
				connection.commit();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i = 0; i < estoque.size(); i++) {
				model.addRow(new Object[0]);
				for (int j = 0; j < editarPedido.size(); j++) {
					if (editarPedido.get(j).getFk_produto_id_produto() == estoque.get(i).getId_produto()) {
						model.setValueAt(true, i, 0);
						model.setValueAt(estoque.get(i).getNome(), i, 1);
						model.setValueAt(editarPedido.get(j).isBorda_pizza(), i, 2);
						model.setValueAt(editarPedido.get(j).getQuantidade_produto(), i, 3);
						break;
					} else {
						model.setValueAt(false, i, 0);
						model.setValueAt(estoque.get(i).getNome(), i, 1);
						model.setValueAt(false, i, 2);
						model.setValueAt("", i, 3);
					}
				}
			}
		}

	}

	public void mostraProduto() { // mostra os produtos na tabela
		try {
			Connection connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			Produto produto = new Produto();
			estoque = produto.carregarProduto(connection);
			TabelaProdutos tabela = new TabelaProdutos(estoque);
			table.setModel(tabela);
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double somaPreco() { // Pega o preço Total do pedido
		double somaTotal = 0;
		for (int j = 0; j < tablePedido.getRowCount(); j++) {
			boolean precoChecked = Boolean.valueOf(tablePedido.getValueAt(j, 0).toString());
			if (precoChecked) {
				somaTotal += (estoque.get(j).getPreco() * Integer.parseInt(tablePedido.getValueAt(j, 3).toString()));
			}
		}
		return somaTotal;
	}

	public String pedidoTotal() { // mostra todos os dados do pedido
		String compraTotal = "";
		for (int j = 0; j < tablePedido.getRowCount(); j++) {
			// Pedido pedido = new Pedido();
			boolean checked = Boolean.valueOf(tablePedido.getValueAt(j, 0).toString());
			if (checked) {
				// pedido.setFk_produto_id_produto(estoque.get(j).getId_produto());
				// int qnt = Integer.parseInt(String.valueOf(tablePedido.getValueAt(j,
				// 3).toString()));
				// pedido.setQuantidade_produto(qnt);
				String valorBorda;
				boolean borda = Boolean.valueOf(tablePedido.getValueAt(j, 2).toString());
				if(borda) {
					valorBorda = "Com borda";
				}else {
					valorBorda = "Sem borda";
				}
				// pedido.setBorda_pizza(borda);
				compraTotal += "\n\nSabor: " + estoque.get(j).getNome() + "\nQuantidade: "
						+ Integer.parseInt(String.valueOf(tablePedido.getValueAt(j, 3).toString())) + "\n" + valorBorda + "\n";
			}
		}
		if (compraTotal == "") {
			JOptionPane.showMessageDialog(null, "Pedido Vazio");
			throw new IllegalArgumentException("Pedido Vazio");
		}
		return compraTotal + "\n" + "Preço Total: " + String.format("%.2f", somaPreco());
	}
}