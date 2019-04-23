package presentacion.controladores.reservas;

import java.util.ArrayList;
import java.util.Date;

import negocio.FactoriaSA;
import negocio.reservas.FiltroBusquedaLugar;
import negocio.reservas.IntervaloTiempo;
import negocio.reservas.TipoPago;
import negocio.transfers.Cliente;
import negocio.transfers.Lugar;

public class ControladorReservaImp extends ControladorReserva {

	@Override
	public ArrayList<Lugar> listarLugaresDisponibles(FiltroBusquedaLugar filter) {
		return FactoriaSA.getInstancia().getSAReserva().listarLugaresDisponibles(filter);
	}

	@Override
	public String getFactura(Lugar lug, IntervaloTiempo fechas, TipoPago tPago, Date fechaIniPago) {
		return FactoriaSA.getInstancia().getSAReserva().getFactura(lug, fechas, tPago, fechaIniPago);
	}

	@Override
	public Cliente obtenerDatosCliente(String dniCliente) {
		return FactoriaSA.getInstancia().getSAReserva().obtenerDatosCliente(dniCliente);
	}
	
	@Override
	public boolean actualizarDatosCliente(Cliente tCli) {
		return FactoriaSA.getInstancia().getSAReserva().actualizarDatosCliente(tCli);
	}

	@Override
	public void reservarLugar(String idLugar, Cliente tCli, IntervaloTiempo fechasReserva, TipoPago tPago,
			boolean paypal) throws IllegalArgumentException {
		FactoriaSA.getInstancia().getSAReserva().reservarLugar(idLugar, tCli, fechasReserva, tPago, paypal);
	}
	
	
}
