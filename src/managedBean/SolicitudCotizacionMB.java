package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

import extras.Util;

@ManagedBean(name = "solCotMB")
@ViewScoped
public class SolicitudCotizacionMB implements Serializable {

	/**
	 * 
	 */
	private Properties props;
	private String solicitud;	
	/**
	 * Log ***
	 */
	private static final Log log=LogFactory.getLog(SolicitudCotizacionMB.class);
	
	@ManagedProperty(value="#{clientMB}")
	private ClienteMB clienteMB;
	@ManagedProperty(value="#{message}")
	private MessageBean message;
	
	@PostConstruct
	public void init(){
		props = Util.getProperties("parameters.properties");
	}
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
		
		parametros.add(0, props.getProperty("mail.smtp.user"));//Emisor
		parametros.add(1, props.getProperty("mail.smtp.user"));//Receptor
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
		return enviarEmail(parametros);		
	}

	public boolean enviarEmail(List<String> parametros){
		/**
		 * Descomentar si ejecuta SolicitudCotizacionTest.
		 */
		//Properties props=Util.getProperties("parameters.properties");
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

