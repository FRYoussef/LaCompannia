package negocio.transfers;

import negocio.reservas.TipoCompra;

public abstract class Compra {
	
	protected String idCompra;
	protected TipoCompra tipoCompra;
	
	public String getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}
	public TipoCompra getTipoCompra() {
		return tipoCompra;
	}
	public void setTipoCompra(TipoCompra tipoCompra) {
		this.tipoCompra = tipoCompra;
	}
	
}
