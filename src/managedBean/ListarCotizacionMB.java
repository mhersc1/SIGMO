package managedBean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.CotizacionDAO;
import DAOIMPL.CotizacionDAOImpl;
import bean.Cotizacion;

@ManagedBean(name = "listarCotizacionMB")
@ViewScoped
public class ListarCotizacionMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<Cotizacion> cotizaciones;
	private List<Cotizacion> cotizacionesFiltro;
	/**
	 * DAO's
	 */
	CotizacionDAO cotizacionDAO;
	
	@PostConstruct
	public void init() {
		cotizacionDAO = new CotizacionDAOImpl();
		obtenerFechas();
		buscarCotizacionesPorFechas();
		
	}

	public void buscarCotizacionesPorFechas() {
		setCotizaciones(cotizacionDAO.obtenerListaCotizacionesPorFecha(fechaDesde, fechaHasta));
		
	}

	private void obtenerFechas() {
		// TODO Auto-generated method stub
		fechaHasta = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaHasta);
		cal.add(Calendar.MONTH, -3);
		fechaDesde = cal.getTime();
	}

	public CotizacionDAO getCotizacionDAO() {
		return cotizacionDAO;
	}

	public void setCotizacionDAO(CotizacionDAO cotizacionDAO) {
		this.cotizacionDAO = cotizacionDAO;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<Cotizacion> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(List<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public List<Cotizacion> getCotizacionesFiltro() {
		return cotizacionesFiltro;
	}

	public void setCotizacionesFiltro(List<Cotizacion> cotizacionesFiltro) {
		this.cotizacionesFiltro = cotizacionesFiltro;
	}


	}
