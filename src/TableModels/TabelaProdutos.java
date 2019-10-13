package TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entity.Produto;

public class TabelaProdutos extends AbstractTableModel {
	String[] colunaNome = { "Sabor", "Quantidade restante", "Preço"};
	private ArrayList<Produto> lista;
	
	public TabelaProdutos(ArrayList<Produto> lista) {
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
		Produto produto = lista.get(linha);
		switch (coluna) {
		case 0:
			return produto.getNome();
		case 1:
			return produto.getQuantidade_produto();
		case 2:
			return String.format("%.2f", produto.getPreco());
		}
		return "";
	}

}
