package bean;
// Generated 30/10/2014 03:42:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Documento generated by hbm2java
 */
public class Documento implements java.io.Serializable {

	private int id;
	private String tipo;
	private Set documentosclientes = new HashSet(0);

	public Documento() {
	}

	public Documento(int id) {
		this.id = id;
	}

	public Documento(int id, String tipo, Set documentosclientes) {
		this.id = id;
		this.tipo = tipo;
		this.documentosclientes = documentosclientes;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Set getDocumentosclientes() {
		return this.documentosclientes;
	}

	public void setDocumentosclientes(Set documentosclientes) {
		this.documentosclientes = documentosclientes;
	}

}
