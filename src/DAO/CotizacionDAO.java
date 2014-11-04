package DAO;

import bean.Cotizacion;

public interface CotizacionDAO {
	public void saveOrUpdate(Cotizacion instance);
	public String generarCorrelativoNumeroCotizacion();
	public Cotizacion findById(int id);
}
