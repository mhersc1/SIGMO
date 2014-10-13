package managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import form.PagoForm;

import DAO.ClienteDAO;
import DAO.CotizacionDAO;
import DAO.DetallecotizacionDAO;
import DAO.DocumentosclienteDAO;
import DAO.PagoDAO;
import DAO.ProductoDAO;
import DAOIMPL.ClienteDAOImpl;
import DAOIMPL.CotizacionDAOImpl;
import DAOIMPL.DetallecotizacionDAOImpl;
import DAOIMPL.DocumentosclienteDAOImpl;
import DAOIMPL.PagoDAOImpl;
import DAOIMPL.ProductoDAOImpl;
import bean.Cliente;
import bean.Cotizacion;
import bean.Detallecotizacion;
import bean.Documentoscliente;
import bean.Producto;

@ManagedBean(name = "pagoMB")
@ViewScoped
public class PagoMB implements Serializable {
	private static final Log log = LogFactory.getLog(PagoMB.class);
	
	// DAO
	CotizacionDAO cotDAO = new CotizacionDAOImpl();
	DetallecotizacionDAO detCotDAO = new DetallecotizacionDAOImpl();
	PagoDAO pagoDAO = new PagoDAOImpl();
	//Variables
	private static final long serialVersionUID = 1L;
	PagoForm pagoForm;
	Date fechaActual;
	

	@PostConstruct
	public void init() {
		pagoForm=new PagoForm();
		fechaActual=new Date();
	}

	private void limpiar() {
	}

	public void registrarPago() throws Exception {
		try {
			log.info("Es correcta la transaccion :EXITO:");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("No pudo registrar pago");
			e.printStackTrace();
			throw e;
		}
	}
	//Getters and Setters

	public PagoForm getPagoForm() {
		return pagoForm;
	}

	public void setPagoForm(PagoForm pagoForm) {
		this.pagoForm = pagoForm;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	
}
