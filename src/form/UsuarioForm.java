package form;

public class UsuarioForm {
	private String usuario;
	private String clave;
	private String rol;
	
	public UsuarioForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioForm(String usuario, String clave, String rol) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.rol = rol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
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
