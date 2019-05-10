package negocio.transporte;

import java.util.ArrayList;

import negocio.transfers.DatosBancarios;
import negocio.transfers.Persona;
import negocio.transfers.ReservaBillete;
import negocio.transfers.Viaje;

public interface SATransporte {

	public abstract ArrayList<Viaje> mostrarViajes();
	
	public abstract ArrayList<Viaje> buscarViajes(Viaje nuevo);
	
//	public Persona buscarPersona(Persona nuevo);
	public Persona buscarPersona(String dni);
	public Viaje crearViaje();
	public ReservaBillete crearReserva(Viaje viaje, int total, int equipaje, boolean seguro);
	public int importeNPersonas(int precio, int npersonas);
	public int importeSeguro(boolean seguro);
	public int importeEquipaje(int equipaje);
	public int calcularTotal(int seguro, int equipaje, int precio);
	public void guardarR(ReservaBillete reserva, Viaje v);
	public DatosBancarios datosB();
	public void guardarP(Persona nuevaP);

	public abstract Viaje nuevoViaje(String id1, String id2);

}
