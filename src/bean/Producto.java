package bean;
// Generated 30/10/2014 03:42:50 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * Producto generated by hbm2java
 */
public class Producto implements java.io.Serializable {

	private int codigo;
	private Unidad unidad;
	private Moneda moneda;
	private String descripcion;
	private BigDecimal preciounit;
	private int stockDisponible;
	public Producto() {
	}

	public Producto(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	

	public Producto(int codigo, Unidad unidad, Moneda moneda,
			String descripcion, BigDecimal preciounit, int stockDisponible) {
		super();
		this.codigo = codigo;
		this.unidad = unidad;
		this.moneda = moneda;
		this.descripcion = descripcion;
		this.preciounit = preciounit;
		this.stockDisponible = stockDisponible;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPreciounit() {
		if(preciounit==null)
			preciounit=new BigDecimal(0);
		return this.preciounit;
	}

	public void setPreciounit(BigDecimal preciounit) {
		this.preciounit = preciounit;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
	
}
