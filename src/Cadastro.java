import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Cliente;
import factory.ConnectionFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro extends JFrame {

	private JPanel cadastroPane;
	private JTextField textNome; 
	private JTextField textTelefone;
	private JTextField textCep;
	private JTextField textEndereco;
	private JButton btnCadastrar;
	private JButton btnCancelar;

	public Cadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 496);
		cadastroPane = new JPanel();
		cadastroPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastroPane);
		cadastroPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(120, 106, 41, 18);
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));
		cadastroPane.add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(189, 100, 238, 30);
		textNome.setColumns(10);
		cadastroPane.add(textNome);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(97, 156, 64, 18);
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		cadastroPane.add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setBounds(189, 150, 238, 30);
		textTelefone.setColumns(10);
		cadastroPane.add(textTelefone);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Arial", Font.BOLD, 15));
		lblCep.setBounds(130, 206, 31, 16);
		cadastroPane.add(lblCep);

		textCep = new JTextField();
		textCep.setBounds(189, 200, 238, 30);
		cadastroPane.add(textCep);
		textCep.setColumns(10);

		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 15));
		lblEndereco.setBounds(92, 256, 69, 16);
		cadastroPane.add(lblEndereco);

		textEndereco = new JTextField();
		textEndereco.setBounds(189, 250, 238, 30);
		cadastroPane.add(textEndereco);
		textEndereco.setColumns(10);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = textNome.getText();
				String telefone = textTelefone.getText();
				String cep = textCep.getText();
				String endereco = textEndereco.getText();
				if(textNome.getText().trim().isEmpty() || textTelefone.getText().trim().isEmpty()
				|| textCep.getText().trim().isEmpty() || textEndereco.getText().trim().isEmpty()) { //valida os campos
					JOptionPane.showMessageDialog(null, "Os campos não podem estar vazio");
				}else {
					try {
						Connection connection = ConnectionFactory.getConnection();
						connection.setAutoCommit(false);
						Cliente cliente = new Cliente();
						cliente.setNome(nome);
						cliente.setTelefone(telefone);
						cliente.setCep(cep);
						cliente.setEndereco(endereco);
						cliente.inserirCliente(connection);
						connection.commit();
						connection.close();
						dispose();
						Inicial inicial = new Inicial();
						inicial.setVisible(true);
						inicial.setLocationRelativeTo(null); // centraliza a janela

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
		});
		btnCadastrar.setBounds(189, 350, 97, 30);
		cadastroPane.add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Inicial inicial = new Inicial();
				inicial.setVisible(true);
				inicial.setLocationRelativeTo(null); // centraliza a janela
			}
		});
		btnCancelar.setBounds(328, 350, 97, 30);
		cadastroPane.add(btnCancelar);

	}

}
