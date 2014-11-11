package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bean.Pago;

import extras.Constantes;
import form.PagoForm;

import DAO.PagoDAO;
import DAOIMPL.PagoDAOImpl;

@ManagedBean(name = "listarPagoMB")
@ViewScoped
public class ListarPagoMB implements Serializable{
	private PagoDAO pagoDAO = new PagoDAOImpl();
	private Date fechaDesde;
	private Date fechaHasta;
	private ArrayList<Pago> pagos;
	private ArrayList<Pago> pagosFiltro;
	@PostConstruct
	public void init() {
		obtenerFechas();
		buscarPagosPorFechas();
		
	}

	public void buscarPagosPorFechas() {
		pagos = pagoDAO.obtenerListaPagosPorFecha(fechaDesde, fechaHasta);
		
	}

	private void obtenerFechas() {
		// TODO Auto-generated method stub
		fechaHasta = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaHasta);
		cal.add(Calendar.MONTH, -3);
		fechaDesde = cal.getTime();
	}

	public PagoDAO getPagoDAO() {
		return pagoDAO;
	}

	public void setPagoDAO(PagoDAO pagoDAO) {
		this.pagoDAO = pagoDAO;
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

	public ArrayList<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(ArrayList<Pago> pagos) {
		this.pagos = pagos;
	}

	public ArrayList<Pago> getPagosFiltro() {
		return pagosFiltro;
	}

	public void setPagosFiltro(ArrayList<Pago> pagosFiltro) {
		this.pagosFiltro = pagosFiltro;
	}
	
}
