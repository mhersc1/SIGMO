 package DAO;

import bean.Usuario;

public interface UsuarioDAO {
	Usuario buscarUsuario(String usuario,String clave);
}
