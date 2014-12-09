package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bean.Producto;

import DAO.ProductoDAO;
import DAOIMPL.ProductoDAOImpl;

@ViewScoped
@ManagedBean(name="consultarStockMB")
public class ConsultarStockMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;
	private List<Producto> productos;
	private List<Producto> productosFiltrados;
	@PostConstruct
	public void init(){
		productoDAO=new ProductoDAOImpl();
		buscarProductos();
	}
	private void buscarProductos() {
		productos=productoDAO.findAll();
	}
	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}
	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public List<Producto> getProductosFiltrados() {
		return productosFiltrados;
	}
	public void setProductosFiltrados(List<Producto> productosFiltrados) {
		this.productosFiltrados = productosFiltrados;
	}
	
}
