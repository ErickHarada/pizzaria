import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

public class Cadastro extends JFrame {

	private JPanel cadastroPane;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textField;

	public Cadastro() {
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
		textNome.setBounds(189, 104, 238, 22);
		textNome.setColumns(10);
		cadastroPane.add(textNome);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(97, 156, 64, 18);
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 15));
		cadastroPane.add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(189, 154, 238, 22);
		textTelefone.setColumns(10);
		cadastroPane.add(textTelefone);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Arial", Font.BOLD, 15));
		lblCep.setBounds(105, 207, 56, 16);
		cadastroPane.add(lblCep);
		
		textField = new JTextField();
		textField.setBounds(189, 204, 116, 22);
		cadastroPane.add(textField);
		textField.setColumns(10);
	}
}
