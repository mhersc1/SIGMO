package managedBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import DAO.UsuarioDAO;
import DAOIMPL.UsuarioDAOIMPL;
import bean.Usuario;
import extras.Util;
import form.UsuarioForm;


@ManagedBean
@SessionScoped
public class LoginMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage mensaje = null;
        boolean loggedIn = false;
		usuario=new Usuario();
		usuario=usuarioDAO.buscarUsuario(usuarioForm.getUsuario(), usuarioForm.getClave());
		String menu=devolverMenu();
		
		if(menu!=null){
			//Sesion
			usuarioForm.setRol(usuario.getRol().getNombre());
			loggedIn = true;
			HttpSession session = Util.getSession();
            session.setAttribute("username", usuario.getUsuario());
            session.setAttribute("rol", usuario.getRol().getNombre());
			return menu;
		}else{
			loggedIn = false;
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error Inicio Sesion", "Usuario y/o Clave incorrecto");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            context.addCallbackParam("loggedIn", loggedIn);
			return "";
		}
		
		
	}
	private String devolverMenu() {
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
				return null;
		}
		else
			return null;
		
	}
	public String cerrarSesion(){
		HttpSession session = Util.getSession();
	    session.invalidate();
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
