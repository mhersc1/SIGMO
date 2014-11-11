package managedBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="message")
@ViewScoped
public class MessageBean implements Serializable{
private String message;

public String getMessage() {
	return message;	
}

public void setMessage(String message) {
	this.message = message;
}
public void showMessage(int option){	
	FacesContext context=FacesContext.getCurrentInstance();
	switch (option) {
	//********************	Common		******************************
	case 1://No hay fecha
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Fecha no encontrada","Seleccione fecha"));
		break;
	case 2://No hay cliente
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Cliente no encontrado","Ingrese Cliente"));
		break;
	case 3://No hay ningun producto
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Producto no encontrado","Ingrese al menos algun producto"));		
		break;
	//********************	Cotizacion	******************************
	case 4://Cotizacion registrada
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cotizacion registrada","La cotización se registro con exito."));		
		break;
	case 5://Cotizacion no registrada
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cotizacion no registrada","La cotización no se pudo registrar."));		
		break;
	case 6://Cotizacion no encontrada
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cotizacion no encontrada", "Ingrese un numero de cotizacion valida."));
		break;
	//********************	Pedido		**********************************
	case 7://Pedido registrado
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido registrado", "El pedido se registro con exito"));
		break;
	case 8://Pedido no registrado
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pedido no registrado", "El pedido no se pudo registrar."));
		break;
	//********************	Solicitud Cotizacion		**********************************
	case 9://Solicitud Enviada
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Cotizacion enviada", "Envio exitoso."));
		break;
	case 10://Solicitud No Enviada
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Envio de solicitud defectuosa", "No se pudo enviar la solicitud. We're sorry!"));
		break;
	default:
		break;
	}
}
}
