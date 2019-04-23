package presentacion.controladores.reservas;

import java.util.ArrayList;
import java.util.Date;

import negocio.reservas.FiltroBusquedaLugar;
import negocio.reservas.IntervaloTiempo;
import negocio.reservas.TipoPago;
import negocio.transfers.Cliente;
import negocio.transfers.Lugar;


public abstract class ControladorReserva {
	private static ControladorReserva instancia = null;
	
	public static ControladorReserva getInstancia() {
		if(instancia == null) instancia = new ControladorReservaImp();
		return instancia;
	}
	
	public abstract ArrayList<Lugar> listarLugaresDisponibles(FiltroBusquedaLugar filter);
	
	public abstract String getFactura(Lugar lug, IntervaloTiempo fechas, TipoPago tPago, Date fechaIniPago);
	
	public abstract Cliente obtenerDatosCliente(String dniCliente);
	
	public abstract boolean actualizarDatosCliente(Cliente tCli);
	
	public abstract void reservarLugar(String idLugar, Cliente tCli, IntervaloTiempo fechasReserva, 
			TipoPago tPago,  boolean paypal) throws IllegalArgumentException;
}
