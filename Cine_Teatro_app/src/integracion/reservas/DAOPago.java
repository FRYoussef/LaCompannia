package integracion.reservas;

import negocio.transfers.Pago;

public interface DAOPago {
	
	public boolean altaPago(Pago tPago);
	
	public boolean modPago(Pago tPago);
	
	public boolean elimPago(String idCompra);
	
	public Pago consultaPago(String idCompra);
}
