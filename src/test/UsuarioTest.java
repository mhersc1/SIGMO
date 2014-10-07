package test;


import hibernate.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import bean.Rol;
import bean.Usuario;

public class UsuarioTest {
	@Test
	public void test() {
		Session sesion=HibernateUtil.getSessionFactory().openSession();
		String usuario="maycol";
		String clave="espinoza";
		Rol rol=new Rol();
		rol.setIdRol(1);
		Query query=sesion.createQuery("from Usuario u where u.usuario='"+usuario+"' and u.clave='"+clave+"'");
		Usuario encontrado=(Usuario)query.list().get(0);
		System.out.println("El usuario es :"+encontrado.getUsuario());
	}
}
