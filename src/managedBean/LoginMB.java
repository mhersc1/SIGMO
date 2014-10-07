package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.UsuarioDAO;
import DAOIMPL.UsuarioDAOIMPL;
import bean.Usuario;
import form.UsuarioForm;


@SessionScoped
@ManagedBean
public class LoginMB {
	private UsuarioForm usuarioForm;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	public LoginMB(){
		usuarioForm=new UsuarioForm();
		usuarioDAO=new UsuarioDAOIMPL();
	}
	public LoginMB(UsuarioForm usuarioForm) {
		super();
		this.usuarioForm = usuarioForm;
		usuarioDAO=new UsuarioDAOIMPL();
	}

	//Metodos de managed bean
	public String iniciarSesion(){
		usuario=new Usuario();
		usuario=usuarioDAO.buscarUsuario(usuarioForm.getUsuario(), usuarioForm.getClave());
		if(usuario!=null){
			if(usuario.getRol().getNombre().equals("secretaria"))
				return "menuSecretaria";
			else if(usuario.getRol().getNombre().equals("gerente"))
				return "menuGerente";
			else if(usuario.getRol().getNombre().equals("cajera"))
				return "menuCajera";
			else if(usuario.getRol().getNombre().equals("encargadoVentas"))
				return "menuVentas";
			else if(usuario.getRol().getNombre().equals("encargadoAlmacen"))
				return "menuAlmacen";
			else
				return "usuarioNoExiste";
		}
		else
			return "usuarioNoExiste";
	}
	public String cerrarSesion(){
		return "iniciarSesion";
	}
	
	//Metodos Basicos
	public UsuarioForm getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(UsuarioForm usuarioForm) {
		this.usuarioForm = usuarioForm;
	}
	
}
