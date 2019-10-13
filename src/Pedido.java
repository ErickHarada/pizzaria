import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Pedido extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scroll;

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
		table.setBounds(12, 13, 614, 45);

		scroll = new JScrollPane();
		scroll.setLocation(12, 13);
		scroll.setSize(614, 119);
		scroll.setViewportView(table);
		contentPane.add(scroll);
	}

}
