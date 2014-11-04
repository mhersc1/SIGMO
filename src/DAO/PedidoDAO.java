package DAO;

import bean.Pedido;


public interface PedidoDAO {
	public void saveOrUpdate(Pedido instance);
	public String generarCorrelativoNumeroPedido();
	public Pedido findByNro(int id);
}
