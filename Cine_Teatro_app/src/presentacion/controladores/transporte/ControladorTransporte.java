package presentacion.controladores.transporte;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import negocio.transfers.DatosBancarios;
import negocio.transfers.Persona;
import negocio.transfers.ReservaBillete;
import negocio.transfers.Viaje;

public abstract class ControladorTransporte {
	
	private static ControladorTransporte instancia = null;
	
	public static ControladorTransporte getInstancia() {
		if(instancia == null) instancia = new ControladorTransporteImp();
		return instancia;
	}
	
	public abstract ArrayList<Viaje> accionCampo(boolean vacio, Viaje viaje);
	public abstract boolean accionFecha(Viaje v) throws ParseException;
	public abstract Viaje nuevoViaje();
	//public abstract Persona nuevaPersona(Persona persona);
	public abstract Persona nuevaPersona(String dni);
	public abstract ReservaBillete nuevaReserva(Viaje viaje, int total, int equipaje, boolean seguro);
	public abstract void guardarReserva(ReservaBillete reserva, Viaje v);
	public abstract int importeTotal(boolean seguro, int equipaje, int precio, int npersonas);
	public abstract DatosBancarios datosBancarios();
	public abstract void guardarPersona(Persona nuevaP);

	public void datosVacios(boolean vacio) {
		if(vacio){
			JOptionPane.showMessageDialog(null, "No se puede aceptar la operación porque algún campo está vacío");
		}
	}
	
	public abstract void viajes(int evento, Object campos);
	public abstract Viaje viajeCreado(Viaje viaje);
}
