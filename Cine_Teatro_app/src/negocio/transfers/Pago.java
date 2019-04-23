package negocio.transfers;

import java.util.ArrayList;
import java.util.Date;

import negocio.reservas.TipoCompra;
import negocio.reservas.TipoPago;

public class Pago {
	private String idCompra, dniCliente, descripcion;
	private float dineroCobrado, dineroAdeudado;
	private TipoPago tipoPago;
	private TipoCompra tipoCompra;
	private Date fechaIni;
	private ArrayList<Date> fechasCobro;
	
	public String getIdCompra() {
		return idCompra;
	}
	
	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}
	
	public String getDniCliente() {
		return dniCliente;
	}
	
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public float getDineroCobrado() {
		return dineroCobrado;
	}
	
	public void setDineroCobrado(float dineroCobrado) {
		this.dineroCobrado = dineroCobrado;
	}
	
	public float getDineroAdeudado() {
		return dineroAdeudado;
	}
	
	public void setDineroAdeudado(float dineroAdeudado) {
		this.dineroAdeudado = dineroAdeudado;
	}
	
	public TipoPago getTipoPago() {
		return tipoPago;
	}
	
	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	public Date getFechaIni() {
		return fechaIni;
	}
	
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	
	public ArrayList<Date> getFechasCobro() {
		return fechasCobro;
	}
	
	public void setFechasCobro(ArrayList<Date> fechasCobro) {
		this.fechasCobro = fechasCobro;
	}

	public TipoCompra getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(TipoCompra tipoCompra) {
		this.tipoCompra = tipoCompra;
	}
	
}
