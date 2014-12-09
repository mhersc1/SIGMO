package managedBean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import bean.Producto;

import DAO.ProductoDAO;
import DAOIMPL.ProductoDAOImpl;

@ManagedBean
@ViewScoped
public class ProductoMB implements Serializable {
	private ProductoDAO productoDAO;
	private String codigoABuscar="";
	private Producto productoEncontrado;
	//Constantes
	private final static String CADENA_VACIA="";
	@PostConstruct
	public void init(){
		productoDAO=new ProductoDAOImpl();
		limpiar();
	}
	
	
	public void buscarProducto(){
			if(!codigoABuscar.equals(CADENA_VACIA)){
				int codProducto=Integer.parseInt(codigoABuscar);
				productoEncontrado=productoDAO.findById(codProducto);
				if(productoEncontrado==null)
					lanzarMensaje("No existe el codigo","No hay ningun producto con dicho codigo");
			}
			else
				lanzarMensaje("Ingrese Codigo!","No ha Ingresado un codigo para buscar el producto");
	}
	
	public ArrayList<Producto> buscarListaProductos(){
		return null;
	}
	
	public void limpiar(){
		codigoABuscar=CADENA_VACIA;
		productoEncontrado=new Producto();
	}
	public void actualizar(){
		if(!codigoABuscar.equals(CADENA_VACIA)){
			if(productoEncontrado!=null){
				productoDAO.saveOrUpdate(productoEncontrado);
				lanzarMensaje("Producto Actualizado!","Se actualizo el producto correctamente");
			}
			else
				lanzarMensaje("Falta Buscar Codigo de Producto","No hay ningun producto");
		}else
			lanzarMensaje("Ingrese Codigo!","No ha Ingresado un codigo para buscar el producto");

	}
	public void lanzarMensaje(String titulo,String detalle){
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage mensaje = null;
		mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        //context.addCallbackParam("ordenPago", pagoForm.getPago().getCodigoCorrelativo());
	}
	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}
	public String getCodigoABuscar() {
		return codigoABuscar;
	}
	public void setCodigoABuscar(String codigoABuscar) {
		this.codigoABuscar = codigoABuscar;
	}
	public Producto getProductoEncontrado() {
		return productoEncontrado;
	}
	public void setProductoEncontrado(Producto productoEncontrado) {
		this.productoEncontrado = productoEncontrado;
	}
	
}
