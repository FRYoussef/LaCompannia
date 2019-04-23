package integracion.reservas;


import negocio.transfers.ReservaLugar;

public interface DAOReservaLugar {
	
	public boolean altaReserva(ReservaLugar tRes);
	
	public boolean elimReserva(String idCompra);
	
	public ReservaLugar consultaReserva(String idCompra);
}
