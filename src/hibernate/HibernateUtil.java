package hibernate;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import extras.Util;

/**
 * Hibernate Utility Resource with a convenient method to get Session Factory
 * object.
 *
 * @author mher
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
    	Properties props;
        try {
        	props=getProperties();
            //Programmatic Configuration
        	Configuration cfg = new Configuration()
        	//Configuration Mapping Files        	
        	.addResource("hibernate/mapping/Rol.hbm.xml")
        	.addResource("hibernate/mapping/Usuario.hbm.xml")
        	.addResource("hibernate/mapping/Cliente.hbm.xml")
        	.addResource("hibernate/mapping/Cotizacion.hbm.xml")
        	.addResource("hibernate/mapping/Pedido.hbm.xml")
        	.addResource("hibernate/mapping/Detallecotizacion.hbm.xml")
        	.addResource("hibernate/mapping/Documento.hbm.xml")
        	.addResource("hibernate/mapping/Documentoscliente.hbm.xml")
        	.addResource("hibernate/mapping/Pago.hbm.xml")
        	.addResource("hibernate/mapping/Producto.hbm.xml")
        	.addResource("hibernate/mapping/Unidad.hbm.xml")
        	.addResource("hibernate/mapping/Moneda.hbm.xml")
        	//Configuration Hibernate Properties
        	.addProperties(props);      
        	
        	sessionFactory =cfg.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
		}
    }
    private static Properties getProperties(){
    	/**
    	 * Desarrollo
    	 */
    	Properties props=Util.getProperties("hibernate.properties");
    	/**
    	 * Produccion
    	 *   
    	String host=System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    	String port=System.getenv("OPENSHIFT_MYSQL_DB_PORT");    	
    	String username=System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    	String password=System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    	String url="jdbc:mysql://"+host+":"+port+"/";
    	props.setProperty("hibernate.connection.url", url);
    	props.setProperty("hibernate.connection.username", username);
    	props.setProperty("hibernate.connection.password", password);
    	 */ 
    	return props;
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
