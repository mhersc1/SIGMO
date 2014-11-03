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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.ClienteDAO;
import DAO.CotizacionDAO;
import DAO.DetallecotizacionDAO;
import DAO.DocumentosclienteDAO;
import DAO.ProductoDAO;
import DAOIMPL.ClienteDAOImpl;
import DAOIMPL.CotizacionDAOImpl;
import DAOIMPL.DetallecotizacionDAOImpl;
import DAOIMPL.DocumentosclienteDAOImpl;
import DAOIMPL.ProductoDAOImpl;
import bean.Cliente;
import bean.Cotizacion;
import bean.Detallecotizacion;
import bean.Documentoscliente;
import bean.Producto;

@ManagedBean(name = "cotMB")
@ViewScoped
public class CotizacionMB implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String codigoCot;
	private Date fechaRegistro;
	private Cliente cliente;
	private List<String> documentosCliente;
	private List<Detallecotizacion> detallesCotizacion;
	private BigDecimal importeTotal;
	/**
	 * Filtros ***
	 */
	private List<Cliente> clientes;
	private List<Cliente> filteredClientes;

	private List<Producto> productosL;
	private List<Producto> filteredProductos;
	/**
	 * Log ***
	 */
	private static final Log log = LogFactory.getLog(CotizacionMB.class);
	
	@ManagedProperty(value="#{message}")
	private MessageBean message;
	@PostConstruct
	public void init() {
		// TODO Auto-generated constructor stub
		cleanup();
		// Loading ...		
		setClientes(cargarClientes());
		setProductosL(cargarProductos());
	}
	
	private void cleanup(){		
		setCodigoCot(generarNumeroDeCotizacion());
		setFechaRegistro(new Date());		
		setImporteTotal(new BigDecimal("0.0"));
		setDetallesCotizacion(new ArrayList<Detallecotizacion>());
		cleanCliente();
	}
	public void cleanCliente(){
		setCliente(new Cliente());
		setDocumentosCliente(new ArrayList<String>(){{
			add("");add("");			
		}});
	}
	
	@PreDestroy
	public void rip(){
		System.out.println("R.I.P");		
	}
	
	
	/**
	 * Functions CRUD
	 */
	public void registrarCotizacion() {
		// Solo para los reportes se le agrega el DNI y la fecha actual al
		// numero
		// Cotizacion:
		boolean flagC = false;
		boolean flagP = false;
		try {
			CotizacionDAO cotDAO = new CotizacionDAOImpl();
			DetallecotizacionDAO detCotDAO = new DetallecotizacionDAOImpl();
			List<Detallecotizacion> detalles=new ArrayList<Detallecotizacion>();
			Cotizacion cot = new Cotizacion();

			if (getCliente() != null && getCliente().getId() != null) {
				if (getFechaRegistro() != null) {
					cot.setCliente(getCliente());
					cot.setFecharegistro(getFechaRegistro());
					cot.setImportetotal(getImporteTotal());
					flagC = true;
				} else {
					log.error("No se ingreso la fecha de registro. Is null");
					message.showMessage(1);
				}
			} else {
				log.error("No se encontro el cliente. Is null");
				message.showMessage(2);
			}
			
			if (getDetallesCotizacion() != null) {
				for (Detallecotizacion det : getDetallesCotizacion()) {
					if (det != null && det.getCodigo() != null && flagC) {
						det.setCotizacion(cot);
						detalles.add(det);//Seran los detalles a grabar
						flagP = true;
					}
				}
				if(flagP==false){
					 log.error("El detalle a registrar es nulo o vacio");
					 message.showMessage(3);
				}
			} else {
				log.error("Los detalles de la cotizacion es vacía");
				message.showMessage(3);
			}
			
			if (flagC && flagP) {//Registro de la cotizacion
				cotDAO.saveOrUpdate(cot);
				detCotDAO.saveOrUpdateList(detalles);
				log.info("Se registro con exito la cotizacion.");
				message.showMessage(4);
				cleanup();				
			} else {
				log.info("No se registro la cotizacion.");
				message.showMessage(5);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Fail!!");
			e.printStackTrace();			
		}
	}

	/**
	 * Basic Functions
	 */	
	public void asignarCliente(Cliente cliente) {		
		this.setCliente(cliente);
		cargarDocumentosCliente(cliente);
	}

	private void cargarDocumentosCliente(Cliente cliente) {
		DocumentosclienteDAO documentosDAO = new DocumentosclienteDAOImpl();
		List<String> lista = new ArrayList<String>();
		for (Documentoscliente doc : documentosDAO
				.cargarDocumentosCliente(cliente)) {
			lista.add(doc.getIdentificador());
		}
		this.setDocumentosCliente(lista);
	}

	public void asignarProducto(Producto producto) {
		Detallecotizacion det = new Detallecotizacion();
		det.setCodigo(String.valueOf(producto.getCodigo()));
		det.setDescripcion(producto.getDescripcion());
		det.setNrodepiezas(1);
		det.setPrecio(producto.getPreciounit());
		det.setImporte(det.getPrecio());
		this.detallesCotizacion.add(det);				
	}

	public BigDecimal calcularImporteTotal() {
		BigDecimal importeTotal = new BigDecimal("0.0");
		if (detallesCotizacion != null && detallesCotizacion.size() > 0) {
			for (Detallecotizacion detalle : detallesCotizacion) {
				if (detalle != null && detalle.getImporte() != null) {
					importeTotal = detalle.getImporte().add(importeTotal);
				}
			}
		}
		this.setImporteTotal(importeTotal);
		return importeTotal;
	}

	public void quitarDetalle(Detallecotizacion detallecotizacion) {
		this.getDetallesCotizacion().remove(detallecotizacion);
	}

	public String generarNumeroDeCotizacion() {

		CotizacionDAO cotDAO = new CotizacionDAOImpl();
		String correlativo = cotDAO.generarCorrelativoNumeroCotizacion();
		System.out.println("Este es:"+correlativo);
		return (correlativo == null ? "000001" : correlativo);
	}

	public List<Cliente> cargarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		ClienteDAO clienteDAO = new ClienteDAOImpl();
		clientes = clienteDAO.findAll();
		return clientes;
	}

	public List<Producto> cargarProductos() {
		List<Producto> productos = new ArrayList<Producto>();
		ProductoDAO productoDAO = new ProductoDAOImpl();
		productos = productoDAO.findAll();
		return productos;
	}

	public List<Cliente> getFilteredClientes() {
		return filteredClientes;
	}

	public void setFilteredClientes(List<Cliente> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Producto> getProductosL() {
		return productosL;
	}

	public void setProductosL(List<Producto> productosL) {
		this.productosL = productosL;
	}

	public List<Producto> getFilteredProductos() {
		return filteredProductos;
	}

	public void setFilteredProductos(List<Producto> filteredProductos) {
		this.filteredProductos = filteredProductos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Detallecotizacion> getDetallesCotizacion() {
		return detallesCotizacion;
	}

	public void setDetallesCotizacion(List<Detallecotizacion> detallesCotizacion) {
		this.detallesCotizacion = detallesCotizacion;
	}

	public List<String> getDocumentosCliente() {
		return documentosCliente;
	}

	public void setDocumentosCliente(List<String> documentosCliente) {
		this.documentosCliente = documentosCliente;
	}

	public String getCodigoCot() {
		return codigoCot;
	}

	public void setCodigoCot(String codigoCot) {
		this.codigoCot = codigoCot;
	}
	
	public void setMessage(MessageBean message) {
		this.message = message;
	}	
}

