package integracion.vestimenta;

import java.util.Date;

import negocio.vestimenta.TransferReserva;

public interface DAO_BDV {

	public void crearReserva(TransferReserva reserva);
	public void AnularReserva (String ID);
	public void ModificarReserva (int Codigo,int Cantidad,int Importe, Date FechaIni,Date FechaFin);
	
}
