package bean;

import java.util.Date;

public class Pago {
	private Integer codigo;
	private String codigoCorrelativo;
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
	
	public String getCodigoCorrelativo() {
		return codigoCorrelativo;
	}
	public void setCodigoCorrelativo(String codigoCorrelativo) {
		this.codigoCorrelativo = codigoCorrelativo;
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
	
}