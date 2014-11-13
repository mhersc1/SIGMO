package extras;

import java.io.InputStream;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
 
public class Util {
	  private static Log log = LogFactory.getLog(Util.class);	  
	  
      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
      }
       
      public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
      }
      public static String darFormatoNroCotizacion(String correlativo){
  		/**
  		 * @formatNumber 	<RUC><FechaActual><correlativo>
  		 * 					10471113321814121991000001
  		 */
  		/**
  		 * @formatNumber 	<correlativo> //Hasta 6 digitos
  		 * 					000001
  		 */
  		String correl="";
  		int tam=(""+correlativo).length();
  		for (int i = 0; i < (6-tam); i++) {
  			correl += "0";
  		}
  		return correl+correlativo;
  	}
      
  	public static Properties getProperties(String file){
		Properties props=new Properties();
		InputStream input=null;
  		try {			
			input=FacesContext.class.getClassLoader().getResourceAsStream(file);			
			props.load(input);				
			return props;							
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			log.info("Ocurrio excepcion al leer el Properties :"+ file);
			e.printStackTrace();
			return null;
		}finally{
			if(input==null){
				System.out.println("No se pudo encontrar "+ file);
				
			}
		}
	}
    
     
     public static String obtenerNroCotizacionDeFormato(String nroFormateado){
    		
    		int contadorCeros=0;
    		int tam=(nroFormateado).length();
    		for (int i = 0; i < (6-tam); i++) {
    			if(nroFormateado.charAt(i)=='0')
    				contadorCeros++;
    		}
    		return nroFormateado.substring(contadorCeros);
    	}
}
