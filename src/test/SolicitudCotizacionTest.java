package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import managedBean.SolicitudCotizacionMB;

import org.junit.Test;

public class SolicitudCotizacionTest {
	@Test
	public void test() {
		SolicitudCotizacionMB solCot=new SolicitudCotizacionMB();
		List<String> parametros=new ArrayList<String>();		
		parametros.add(0, "sigmotp2@gmail.com");
		parametros.add(1, "sigmotp2@gmail.com");
		parametros.add(2,"Envio Solicitud Cotizacion");//subject
		parametros.add(3,"Hola que aze?");//body		
     
        
		if(solCot.enviarEmail(parametros)){
			System.out.println("Se envio correctamente");
		}else{
			System.out.println("Hubo fallas");
		}
		
	}

}
