package DAOIMPL;

import java.io.Serializable;
import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

import bean.Usuario;
import DAO.UsuarioDAO;

public class UsuarioDAOIMPL implements Serializable,UsuarioDAO{
		Session sesion=HibernateUtil.getSessionFactory().openSession();
	@Override
	public Usuario buscarUsuario(String usuario, String clave) {
		// TODO Auto-generated method stub
		Session sesion=HibernateUtil.getSessionFactory().openSession();
		Query query=sesion.createQuery("from Usuario u where u.usuario='"+usuario+"' and u.clave='"+clave+"'");
		Usuario usuEncontrado;
		if(query.list().size()!=0)
			usuEncontrado=(Usuario)query.list().get(0);
		else
			usuEncontrado=null;
		return usuEncontrado;
	}
	
}
