package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.ClienteDAO;
import DAO.DocumentosclienteDAO;
import DAOIMPL.ClienteDAOImpl;
import DAOIMPL.DocumentosclienteDAOImpl;
import bean.Cliente;
import bean.Documentoscliente;

@ManagedBean(name = "clientMB")
@ViewScoped
public class ClienteMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<String> documentosCliente;
	/**
	 * Busqueda y filtros
	 */
	private List<Cliente> clientes;
	private List<Cliente> filteredClientes;
	
	@PostConstruct
	public void init() {
		// TODO Auto-generated constructor stub
		clean();
		// Loading ...		
		setClientes(cargarClientes());
	}
	
	public List<Cliente> cargarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		ClienteDAO clienteDAO = new ClienteDAOImpl();
		clientes = clienteDAO.findAll();
		return clientes;
	}	
	public void clean(){
		setCliente(new Cliente());
		setDocumentosCliente(new ArrayList<String>(){{
			add("");add("");			
		}});
	}
	
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setFilteredClientes(List<Cliente> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void setDocumentosCliente(List<String> documentosCliente) {
		this.documentosCliente = documentosCliente;
	}

	public List<String> getDocumentosCliente() {
		return documentosCliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Cliente> getFilteredClientes() {
		return filteredClientes;
	}
}

