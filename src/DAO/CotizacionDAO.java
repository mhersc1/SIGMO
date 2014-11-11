package DAO;

import java.util.Date;
import java.util.List;

import bean.Cotizacion;

public interface CotizacionDAO {
	public void saveOrUpdate(Cotizacion instance);
	public String generarCorrelativoNumeroCotizacion();
	public Cotizacion findById(int id);
	public List<Cotizacion> obtenerListaCotizacionesPorFecha(Date fechaDesde, Date fechaHasta);
}
