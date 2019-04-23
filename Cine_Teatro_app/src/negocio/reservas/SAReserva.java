package negocio.reservas;

import java.util.ArrayList;
import java.util.Date;

import negocio.transfers.Cliente;
import negocio.transfers.Lugar;
import negocio.transfers.TipoPago;

public interface SAReserva {
	
	public abstract ArrayList<Lugar> listarLugaresDisponibles(FiltroBusquedaLugar filter);
	
	public abstract String getFactura(Lugar lug, IntervaloTiempo fechas, TipoPago tPago, Date fechaIniPago);
	
	public abstract Cliente obtenerDatosCliente(String dniCliente);
	
	public abstract boolean actualizarDatosCliente(Cliente tCli);
	
	public abstract void reservarLugar(String idLugar, Cliente tCli, IntervaloTiempo fechasReserva, 
			TipoPago tPago,  boolean paypal) throws IllegalArgumentException;
}
