package managedBean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.CotizacionDAO;
import DAO.PedidoDAO;
import DAOIMPL.CotizacionDAOImpl;
import DAOIMPL.PedidoDAOImpl;
import bean.Cotizacion;
import bean.Pedido;



@ManagedBean(name = "pedMB")
@ViewScoped
public class PedidoMB implements Serializable {
	/**
	 * 
	 */
	private String codigoPed;
	private Date fechaRegistro;	
	/**
	 * Filtros ***
	 */
	private String nroCotBuscado;
	Cotizacion cotizacion;//V.G.
	/**
	 * DAO's
	 */
	CotizacionDAO cotDAO;
	PedidoDAO pedDAO;
	/**
	 * Log *** 
	 */
	private static final Log log=LogFactory.getLog(PedidoMB.class);
	
	@ManagedProperty(value="#{clientMB}")
	private ClienteMB clientMB;
	@ManagedProperty(value="#{message}")
	private MessageBean message;
	
	@PostConstruct
	public void init() {
		// TODO Auto-generated constructor stub
		//Create DAO's
		cotDAO=new CotizacionDAOImpl();
		pedDAO=new PedidoDAOImpl();
		clean();
	}
	
	private void clean(){		
		setCodigoPed(generarNumeroDePedido());
		setFechaRegistro(new Date());
		setNroCotBuscado(null);
		cotizacion=new Cotizacion();
		clientMB.clean();
	}
	
	@PreDestroy
	public void rip(){
		System.out.println("R.I.P");		
	}
	public String generarNumeroDePedido() {		
		String correlativo = pedDAO.generarCorrelativoNumeroPedido();
		System.out.println("Este es:"+correlativo);
		return (correlativo == null ? "000001" : correlativo);
	}	
	public void buscarCotizacion(){
		cotizacion=cotDAO.findById(Integer.parseInt(nroCotBuscado));
		setNroCotBuscado(nroCotBuscado);
		if(cotizacion != null){
		clientMB.asignarCliente(cotizacion.getCliente());
		}else{
		clientMB.clean();
		message.showMessage(6);
		log.info("No se encontro la cotizacion");
		}
	}
	
	public void registrarPedido(){
		try {
			boolean flagP = false;
			Pedido pedido = new Pedido();
			if(cotizacion!=null && cotizacion.getCliente()!=null){
				if (getFechaRegistro()!=null) {
					pedido.setCotizacion(cotizacion);
					pedido.setFecharegistro(getFechaRegistro());
					flagP = true;
				}else{
					log.error("No se ingreso la fecha de registro. Is null");
					message.showMessage(1);				
				}
			}else{
				message.showMessage(6);
				log.error("No se encontro la cotizacion ");
			}
			
			if(flagP){
				pedDAO.saveOrUpdate(pedido);
				message.showMessage(7);
				log.info("Se registro con exito el Pedido N°"+codigoPed);
				clean();
			}else{
				log.info("No se pudo registrar el Pedido N°"+codigoPed);
				message.showMessage(8);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Fail!!");
			e.printStackTrace();
		}
	}
	public String getNroCotizacion(){
		if(cotizacion == null || cotizacion.getCliente()==null || cotizacion.getCodigo()==null){
			return "";
		}
		else{
			return "Se cargó los datos del cliente de la cotización #"+String.valueOf(cotizacion.getCodigo());
		}
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getCodigoPed() {
		return codigoPed;
	}
	public void setCodigoPed(String codigoPed) {
		this.codigoPed = codigoPed;
	}
	public String getNroCotBuscado() {
		return nroCotBuscado;
	}
	public void setNroCotBuscado(String nroCotizacion) {
		this.nroCotBuscado = nroCotizacion;
	}
	public void setClientMB(ClienteMB clientMB) {
		this.clientMB = clientMB;
	}
	public void setMessage(MessageBean message) {
		this.message = message;
	}
}

