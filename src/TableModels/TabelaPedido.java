package TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entity.Produto;

public class TabelaPedido extends AbstractTableModel {
	String[] colunaNome = { "", "Sabor", "Borda", "Quantidade" };
	private ArrayList<Produto> pedido;
	
	public TabelaPedido(ArrayList<Produto> pedido) {
		this.pedido = pedido;
	}
	
	public int getRowCount() {
		return pedido.size();
	}

	public int getColumnCount() {
		return colunaNome.length;
	}
	
	public String getColumnName(int coluna) {
        return colunaNome[coluna];
    }
	
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

	public Object getValueAt(int linha, int coluna) {
		Produto produto = pedido.get(linha);
		switch (coluna) {
		case 0:
			return Boolean.class;
		case 1:
			return produto.getNome();
		case 2:
			return false;
		case 3:
			return "";
		}
		return "";
	}

}
