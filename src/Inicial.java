import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class Inicial extends JFrame {

	private JPanel inicialPane;


	public Inicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 496); //tamanho
		inicialPane = new JPanel();
		inicialPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(inicialPane);
		inicialPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Cadastro cadastro = new Cadastro();
				cadastro.setVisible(true);
				cadastro.setLocationRelativeTo(null); //centraliza a janela
			}
		});
		inicialPane.add(btnCadastrar);
	}

}
