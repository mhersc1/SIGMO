package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

@ManagedBean(name="message")
@ViewScoped
public class MessageBean {
private String message;

public MessageBean() {
	// TODO Auto-generated constructor stub
	
}
public String getMessage() {
	return message;	
}

public void setMessage(String message) {
	this.message = message;
}
public void showMessage(int option){	
	FacesContext context=FacesContext.getCurrentInstance();
	switch (option) {
	case 1://No hay fecha
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Fecha no encontrada","Seleccione fecha"));
		break;
	case 2://No hay cliente
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Cliente no encontrado","Ingrese Cliente"));
		break;
	case 3://No hay ningun producto
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Producto no encontrado","Ingrese al menos algun producto"));		
		break;
	case 4://Cotizacion registrada
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cotizacion registrada","La cotización se registro con exito."));		
		break;
	case 5://Cotizacion no registrada
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cotizacion no registrada","La cotización no se pudo registrar."));		
		break;
	default:
		break;
	}
}
}
