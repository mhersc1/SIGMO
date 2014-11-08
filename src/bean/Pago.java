package bean;

import java.util.Date;

import extras.Constantes;
import extras.Util;

public class Pago {
	private Integer codigo;
	private Date fecha;
	private Cotizacion cotizacion;
	private Integer estado;
	
	public Pago() {
		cotizacion= new Cotizacion();
		// TODO Auto-generated constructor stub
	}
	public Pago(Integer codigo, Date fecha, Cotizacion cotizacion,
			Integer estado) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.cotizacion = cotizacion;
		this.estado = estado;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cotizacion getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	//Es similar al anterior incluso se deberia usar este =/
	public String getCodigoCorrelativo(){
		return Util.darFormatoNroCotizacion(codigo.toString());
	}
	public String getEstadoDesc(){
		String estadoDesc="";
		switch(estado){
		case 0:estadoDesc="Pago Borrador";break;
		case 1:estadoDesc="Pago Generado";break;
		case 2:estadoDesc="Pago Anulado";break;
		}
		return estadoDesc;
	}
}