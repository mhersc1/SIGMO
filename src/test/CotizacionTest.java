package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import DAO.CotizacionDAO;
import DAOIMPL.CotizacionDAOImpl;
import bean.Cotizacion;
import extras.Util;

public class CotizacionTest {
	//@Test
	public void test() {
		CotizacionDAO cotDAO = new CotizacionDAOImpl();
		Cotizacion cotizacion;
		int nroCotizacion=Integer.parseInt(Util.obtenerNroCotizacionDeFormato("000002"));
		cotizacion=cotDAO.findById(nroCotizacion);
		//System.out.println("Apellidos del cliente:"+cotizacion.getCliente().getApellidos());
	}
	
	@Test
	public void test1() {
		CotizacionDAO cotDAO = new CotizacionDAOImpl();
		Date date1=new Date();
		Date date2=new Date();
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			date1=sdf.parse("14/12/1991 12:00:00 a. m.");
			date2=sdf.parse("10/11/2014 12:00:00 a. m.");
			System.out.println("La cantidad de cotizaciones es:"+cotDAO.obtenerListaCotizacionesPorFecha(date1, date2).size());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ocurrio excepcion al parsear fecha");
		}
	}
}
