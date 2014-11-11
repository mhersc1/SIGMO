package DAO;

import java.util.ArrayList;
import java.util.Date;

import bean.Pago;

public interface PagoDAO {
	public String generarCorrelativoCodigoPago();
	public boolean registrarPago(Pago pago);
	public ArrayList<Pago> obtenerListaPagosPorFecha(Date fechaDesde, Date fechaHasta);
}
