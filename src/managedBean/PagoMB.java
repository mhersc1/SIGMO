package managedBean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

import bean.Cotizacion;
import extras.Constantes;
import extras.Util;
import form.PagoForm;
import DAO.CotizacionDAO;
import DAO.DetallecotizacionDAO;
import DAO.PagoDAO;
import DAOIMPL.CotizacionDAOImpl;
import DAOIMPL.DetallecotizacionDAOImpl;
import DAOIMPL.PagoDAOImpl;

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
	private PagoForm pagoForm;
	
	@PostConstruct
	public void init() {
		pagoForm=new PagoForm();
		String codCorrelativo=generarCodigoPago();
		pagoForm.getPago().setCodigo(Integer.parseInt(codCorrelativo));
		pagoForm.getPago().setEstado(Constantes.PAGO_BORRADOR);
		pagoForm.setFechaActual(new Date());
		pagoForm.setTieneRuc(false);
	}
	public String generarCodigoPago() {

		String correlativo = pagoDAO.generarCorrelativoCodigoPago();
		
		return (correlativo == null ? "000001" : correlativo);
	}
	public void limpiar() {
		pagoForm=new PagoForm();
		init();
	}

	public void registrarPago() throws Exception {
		//limpiar();
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage mensaje = null;
		pagoForm.getPago().setEstado(Constantes.PAGO_GENERADO);
		pagoForm.getPago().setFecha(pagoForm.getFechaActual());
        pagoForm.getPago().setCotizacion(buscarCotizacionPorCodigo(pagoForm.getCodCotFormateado()));
        if(pagoDAO.registrarPago(pagoForm.getPago())){
        	mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "REGISTRO PAGOS", "ORDEN DE PAGO CON NRO: "+pagoForm.getPago().getCodigoCorrelativo()+" GENERADA  CORRECTAMENTE");
        	FacesContext.getCurrentInstance().addMessage(null, mensaje);
        	}
        else{
        	mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "REGISTRO PAGOS", "ORDEN DE PAGO CON NRO: "+pagoForm.getPago().getCodigoCorrelativo()+" NO GENERADA");
        	FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
	}
	
	public void buscarCotizacion(){
		
		int nroCotizacion=Integer.parseInt(Util.obtenerNroCotizacionDeFormato(pagoForm.getCodCotFormateado()));
		Cotizacion cotBuscada=cotDAO.findById(nroCotizacion);
		if(cotBuscada!=null){
			pagoForm.setCotizacion(cotBuscada);
			System.out.println("codigo de detalle:"+pagoForm.getCotizacion().getDetallecotizacions().iterator().next().getCodigo());
			}
		else
			lanzarMensaje("COTIZACION NO ENCONTRADA","LA COTIZACION CON DICHO CODIGO DE COTIZACION"+
					pagoForm.getCodCotFormateado()+" NO EXISTE");
	}
	public void lanzarMensaje(String titulo,String detalle){
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage mensaje = null;
		mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        //context.addCallbackParam("ordenPago", pagoForm.getPago().getCodigoCorrelativo());
	}
	public Cotizacion buscarCotizacionPorCodigo(String codigo){
		Cotizacion cotizacion;
		int nroCotizacion=Integer.parseInt(Util.obtenerNroCotizacionDeFormato(codigo));
		cotizacion=cotDAO.findById(nroCotizacion);
		return cotizacion;
	}
	
	public void cambiarTipoPago(){
		System.out.println("Hello!!");
		System.out.println("omarx");
		if(pagoForm.getTipoPago().equalsIgnoreCase("Factura"))
			pagoForm.setTieneRuc(true);
		else
			pagoForm.setTieneRuc(false);
	}
	
	//Getters and Setters
	public PagoForm getPagoForm() {
		return pagoForm;
	}
	public void setPagoForm(PagoForm pagoForm) {
		this.pagoForm = pagoForm;
	}


	
}
