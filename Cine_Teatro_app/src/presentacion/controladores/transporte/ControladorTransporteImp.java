package presentacion.controladores.transporte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import presentacion.vistas.transporte.GUITransporte;

import negocio.FactoriaSA;
import negocio.transfers.DatosBancarios;
import negocio.transfers.Persona;
import negocio.transfers.ReservaBillete;
import negocio.transfers.Viaje;
import negocio.transporte.SATransporte;

public class ControladorTransporteImp extends ControladorTransporte {

	//Comprobacion algun campo vacio
	public ArrayList<Viaje> accionCampo(boolean vacio, Viaje v){
		if(vacio){
			JOptionPane.showMessageDialog(null, "Algún campo está vacio");
		}
		else{
			return FactoriaSA.getInstancia().getSATransporte().buscarViajes(v);
		}
		return null;
	}
	
	//Comprobacion fecha ida/vuelta
	public boolean accionFecha(Viaje v) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date start = sdf.parse(v.getFechaIda());
		Date end = sdf.parse(v.getFechaVuelta());
		
		if (!start.before(end)){
			JOptionPane.showMessageDialog(null, "La fecha de vuelta no puede ser anterior o igual que la de ida");
			return true;
		}
		else {
			return false;
		}
	}
	
	public Viaje nuevoViaje(){
		return FactoriaSA.getInstancia().getSATransporte().crearViaje();
	}

	@Override
	public Persona nuevaPersona(String dni) {
		return FactoriaSA.getInstancia().getSATransporte().buscarPersona(dni);
	}
	
	public int importeTotal(boolean s, int e, int p, int npersonas){
		int seguro= FactoriaSA.getInstancia().getSATransporte().importeSeguro(s);
		int equipaje = FactoriaSA.getInstancia().getSATransporte().importeEquipaje(e);
		int precio = FactoriaSA.getInstancia().getSATransporte().importeNPersonas(p, npersonas);
		
		return FactoriaSA.getInstancia().getSATransporte().calcularTotal(seguro, equipaje, precio);
	}

	@Override
	public ReservaBillete nuevaReserva(Viaje viaje, int total, int equipaje, boolean seguro) {
		return FactoriaSA.getInstancia().getSATransporte().crearReserva(viaje, total, equipaje, seguro);
	}
	
	public void guardarReserva(ReservaBillete reserva, Viaje v){
		FactoriaSA.getInstancia().getSATransporte().guardarR(reserva, v);
	}
	
	public DatosBancarios datosBancarios(){
		return FactoriaSA.getInstancia().getSATransporte().datosB();
	}
	
	public void guardarPersona(Persona nuevaP){
		FactoriaSA.getInstancia().getSATransporte().guardarP(nuevaP);
	}

	@Override
	public void viajes(int evento, Object campos) {
		switch(evento){
			case(Eventos.VIAJE_UPDATE): {
				HashMap<String, String>ids = (HashMap<String, String>) campos;
				String id1 = new String(ids.get("origen"));
				String id2 = new String(ids.get("destino"));
				
				//Creamos el servicio de aplicacion
				SATransporte saTransporte = FactoriaSA.getInstancia().getSATransporte();
				
				Viaje viaje = saTransporte.nuevoViaje(id1, id2);
				viajeCreado(viaje);
				
				//GUITransporte.getInstancia().actualizar(Eventos.VIAJE_UPDATE_GUI, viaje);
			}
		}
		
	}
	
	public Viaje viajeCreado(Viaje viaje){
		return viaje;
	}
}
