package TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entity.Cliente;
import entity.Pedido;
import entity.Produto;

public class TabelaPedido extends AbstractTableModel {

	String[] colunaNome = { "N.Pedido", "Cliente", "telefone", "cep", "endereço", "Sabor", "Quantidade", "Borda", "Preço Total",
			"Data" };
	private ArrayList<Cliente> lista;
	private ArrayList<Pedido> pedido;
	private ArrayList<Produto> produto;

	public TabelaPedido(ArrayList<Cliente> lista, ArrayList<Pedido> pedido, ArrayList<Produto> produto) {
		this.lista = lista;
		this.pedido = pedido;
		this.produto = produto;
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

	public Object getValueAt(int linha, int coluna) {
		Pedido pedir = pedido.get(linha);
		Cliente cliente = lista.get(0);
		Produto sabor = produto.get(linha);
		switch (coluna) {
		case 0:
			return pedir.getId_pedido();
		case 1:
			return cliente.getNome();
		case 2:
			return cliente.getTelefone();
		case 3:
			return cliente.getCep();
		case 4:
			return cliente.getEndereco();
		case 5:
			return sabor.getNome();
		case 6:
			return pedir.getQuantidade_produto();
		case 7:
			return pedir.isBorda_pizza();
		case 8:
			return pedir.getPreco_total();
		case 9:
			return pedir.getData_pedido();
		}
		return "";
	}

}
