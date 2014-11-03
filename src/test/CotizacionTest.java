package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAO.CotizacionDAO;
import DAOIMPL.CotizacionDAOImpl;
import bean.Cotizacion;
import extras.Util;

public class CotizacionTest {
	@Test
	public void test() {
		CotizacionDAO cotDAO = new CotizacionDAOImpl();
		Cotizacion cotizacion;
		int nroCotizacion=Integer.parseInt(Util.obtenerNroCotizacionDeFormato("000002"));
		cotizacion=cotDAO.findById(nroCotizacion);
		//System.out.println("Apellidos del cliente:"+cotizacion.getCliente().getApellidos());
	}

}
