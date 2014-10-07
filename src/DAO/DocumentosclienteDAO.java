package DAO;

import java.util.List;

import bean.Cliente;
import bean.Documentoscliente;

public interface DocumentosclienteDAO {

	public List<Documentoscliente> cargarDocumentosCliente(Cliente cliente);
}
