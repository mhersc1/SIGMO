package form;

public class UsuarioForm {
	private String usuario;
	private String clave;
	
	
	public UsuarioForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsuarioForm(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
}
