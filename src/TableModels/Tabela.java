package TableModels;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entity.Cliente;

public class Tabela extends AbstractTableModel {
	String[] colunaNome = { "nome", "telefone", "cep", "endereço"};
	private ArrayList<Cliente> lista;
	
	public Tabela(ArrayList<Cliente> lista) {
		this.lista = lista;
	}

	public int getRowCount() {
		return lista.size();
	}

	public int getColumnCount() {
		return colunaNome.length;
	}
	
	public String getColumnName(int coluna) {
        return colunaNome[coluna];
    }

	public Object getValueAt(int linha, int coluna) {
		Cliente cliente = lista.get(linha);
		switch (coluna) {
		case 0:
			return cliente.getNome();
		case 1:
			return cliente.getTelefone();
		case 2:
			return cliente.getCep();
		case 3:
			return cliente.getEndereco();
		}
		return "";
	}

}
