package managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ManagedBean(name = "solCotMB")
@ViewScoped
public class SolicitudCotizacionMB implements Serializable {

	/**
	 * 
	 */
	private String solicitud;	
	/**
	 * Log ***
	 */
	private static final Log log=LogFactory.getLog(SolicitudCotizacionMB.class);
	
	@ManagedProperty(value="#{clientMB}")
	private ClienteMB clienteMB;
	@ManagedProperty(value="#{message}")
	private MessageBean message;
	
	public void enviarSolicitudCotizacion(){
		List<String> parametros=new ArrayList<String>();
		//agjh ...
		String dni=(clienteMB.getDocumentosCliente().get(0)==null ||
				clienteMB.getDocumentosCliente().get(0).equals(""))?
						"---":clienteMB.getDocumentosCliente().get(0);
		String ruc=(clienteMB.getDocumentosCliente().get(1)==null ||
				clienteMB.getDocumentosCliente().get(1).equals(""))?
						"---":clienteMB.getDocumentosCliente().get(1);
		String direccion=(clienteMB.getCliente().getDireccion()==null ||
				clienteMB.getCliente().getDireccion().equals(""))
				?"---":clienteMB.getCliente().getDireccion();
		String body=
		"Datos del Cliente: \n\n"+
		"Nombres\t: "+clienteMB.getCliente().getNombre()+"\n"+
		"Apellidos\t:"+clienteMB.getCliente().getApellidos()+"\n"+		
		"Email\t: "+clienteMB.getCliente().getEmail()+"\n"+
		"DNI\t: "+dni+"\n"+
		"RUC\t: "+ruc+"\n"+
		"Direccion\t: "+direccion+"\n\n\n"+
		"Solicitud de Cotizacion: \n\n"+
		getSolicitud()+"\n\n"+
		"**** SIGMO MADERAS POR MONTON :v ****";
		System.out.println(body);
		parametros.add(0, leerResourceBundle("email"));//Emisor
		parametros.add(1, leerResourceBundle("email"));//Receptor
		parametros.add(2,"Envio Solicitud Cotizacion");//subject
		parametros.add(3,body);//body
		
		if(prepararEmail(parametros)){clean();message.showMessage(9);}
		else{message.showMessage(10);};
	}
	
	private void clean(){
		setSolicitud(new String());
		clienteMB.clean();		
	}
	
	@PreDestroy
	public void rip(){
		System.out.println("R.I.P");		
	}
	
	private boolean prepararEmail(List<String> parametros){
		return enviarEmail(parametros,getProperties());		
	}
	private Properties getProperties(){
		Properties props=System.getProperties();		
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", leerResourceBundle("host"));
        props.put("mail.smtp.user", leerResourceBundle("email"));
        props.put("mail.smtp.password", leerResourceBundle("password"));
        props.put("mail.smtp.port", leerResourceBundle("port"));
        props.put("mail.smtp.auth", "true");        
        return props;
	}
	public boolean enviarEmail(List<String> parametros,Properties props){
		Session session=Session.getDefaultInstance(props);
		MimeMessage message=new MimeMessage(session);
		InternetAddress address;
		try {
			address=new InternetAddress(parametros.get(1));
			message.setFrom(new InternetAddress(parametros.get(0)));//Emisor			
			message.addRecipient(Message.RecipientType.TO, address);//Receptor(s)
			message.setSubject(parametros.get(2));//Titulo
			message.setText(parametros.get(3));//Cuerpo del Mensaje
			
			Transport transport =session.getTransport("smtp");
			transport.connect(props.getProperty("mail.smtp.host"),props.getProperty("mail.smtp.user"),props.getProperty("mail.smtp.password"));			
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();			
			return true;
		}	catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	private String leerResourceBundle(String key){
		try {
			FacesContext context=FacesContext.getCurrentInstance();
			ResourceBundle bundle=context.getApplication().getResourceBundle(context,"param");
			String valor=bundle.getString(key);			
			return valor;
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			log.info("Ocurrio excepcion al leer el ResourceBundle");
			e.printStackTrace();
			throw e;
		}
	}
		
	
	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}
	
	public void setMessage(MessageBean message) {
		this.message = message;
	}
	
	public MessageBean getMessage() {
		return message;
	}
	
	public ClienteMB getClienteMB() {
		return clienteMB;
	}

	public void setClienteMB(ClienteMB clienteMB) {
		this.clienteMB = clienteMB;
	}	
	
}

