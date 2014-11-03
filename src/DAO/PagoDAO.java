package DAO;

import bean.Pago;

public interface PagoDAO {
	public String generarCorrelativoCodigoPago();
	public void registrarPago(Pago pago);
}
