package form;

import java.util.Date;
import java.util.List;

import bean.Cotizacion;
import bean.Pago;

public class PagoForm {
	private String tipoPago;
	private Boolean tieneRuc;
	private Date fechaActual;
	private Cotizacion cotizacion;
	private Pago pago;
	private String cliente;
	private String ruc;
	private Date fechaCotizacion;
	private String codCotFormateado;
	public String getCodCotFormateado() {
		return codCotFormateado;
	}

	public void setCodCotFormateado(String codCotFormateado) {
		this.codCotFormateado = codCotFormateado;
	}
	private List productosCotizacion;
	private List productosFiltrados;
	public PagoForm(){
		pago=new Pago();
		cotizacion=new Cotizacion();
	}
	
	public PagoForm(String tipoPago, Boolean tieneRuc, Date fechaActual,
			Cotizacion cotizacion, Pago pago, String cliente, String ruc,
			Date fechaCotizacion, List productosCotizacion,
			List productosFiltrados) {
		super();
		this.tipoPago = tipoPago;
		this.tieneRuc = tieneRuc;
		this.fechaActual = fechaActual;
		this.cotizacion = cotizacion;
		this.pago = pago;
		this.cliente = cliente;
		this.ruc = ruc;
		this.fechaCotizacion = fechaCotizacion;
		this.productosCotizacion = productosCotizacion;
		this.productosFiltrados = productosFiltrados;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public Boolean getTieneRuc() {
		return tieneRuc;
	}
	public void setTieneRuc(Boolean tieneRuc) {
		this.tieneRuc = tieneRuc;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Date getFechaCotizacion() {
		return fechaCotizacion;
	}
	public void setFechaCotizacion(Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}
	public List getProductosCotizacion() {
		return productosCotizacion;
	}
	public void setProductosCotizacion(List productosCotizacion) {
		this.productosCotizacion = productosCotizacion;
	}
	public List getProductosFiltrados() {
		return productosFiltrados;
	}
	public void setProductosFiltrados(List productosFiltrados) {
		this.productosFiltrados = productosFiltrados;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
}
