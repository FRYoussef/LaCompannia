package negocio.transporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import integracion.transporte.DAOTransporte;
import integracion.transporte.FactoriaDAOTransporte;
import negocio.transfers.DatosBancarios;
import negocio.transfers.Persona;
import negocio.transfers.ReservaBillete;
import negocio.transfers.Viaje;

public class SATransporteImp implements SATransporte {
	private Viaje viaje;
	ReservaBillete reserva;
	List<Integer> numbers = new ArrayList<>(200);
	int numeroGenerado=0;
	
	public SATransporteImp(){
		for (int i=1;i<201;i++){ //Para generar numeros aleatorios para el codigo de viaje
			   numbers.add(i);
		}
	}

	public ArrayList<Viaje> mostrarViajes() {
		return FactoriaDAOTransporte.getInstancia().nuevoViaje().loadFile();
	}
	
	public ArrayList<Viaje> buscarViajes(Viaje nuevo){
		ArrayList<Viaje> todos = FactoriaDAOTransporte.getInstancia().nuevoViaje().loadFile();
		ArrayList<Viaje> disponibles = new ArrayList();
		int i=0;
		for(Viaje v: todos){
			if(todos.get(i).getLugarOrigen().equalsIgnoreCase(nuevo.getLugarOrigen()) && (todos.get(i).getLugarDestino().equalsIgnoreCase(nuevo.getLugarDestino()))){
				if(todos.get(i).getNPersonas() >= nuevo.getNPersonas()){
					//if(todos.get(i).getClase().equals(nuevo.getClase()) && todos.get(i).getMedioTrans().equals(nuevo.getMedioTrans())){
						disponibles.add(v); //Se añade el viaje a la lista de disponibles
					}
				//	else {
					//	JOptionPane.showMessageDialog(null, "Hay disponibilidad pero en otra clase o medio de transporte distinto al seleccionado");
				//	}
			//	}
				else{
					JOptionPane.showMessageDialog(null, "El número de personas disponibles para este viaje es inferior al seleccionado");
				}
			}
			i++;
		}
		if(disponibles.size()==0) {
			//No se debe imprimir la tabla
			JOptionPane.showMessageDialog(null, "No se han encontrado viajes disponibles con ese origen o destino");
			return null;
		}
		return disponibles;
	}
	

	public Persona buscarPersona(String dni){
		//Si existe la persona devuelve todos sus datos, si no crea una nueva.
		if(FactoriaDAOTransporte.getInstancia().nuevoViaje().existePersona(dni) != null){
			return FactoriaDAOTransporte.getInstancia().nuevoViaje().existePersona(dni);
		}
		else{
			Persona p = FactoriaDAOTransporte.getInstancia().nuevoViaje().crearP();
			int c = generarCodigoReserva();
			return p;
		}		
	}

	@Override
	public Viaje crearViaje() {
		Viaje crear = FactoriaDAOTransporte.getInstancia().nuevoViaje().crear();
		int c = generarCodigoReserva();
		String codigo = Integer.toString(c) + "VV";
		crear.setIdViaje(codigo);
		return crear;
	}
	
	public int importeNPersonas(int precio, int npersonas){
		int importe=precio;
		if(npersonas!=1){
			importe=importe*npersonas;
		}
		return importe;
	}
	
	public int importeSeguro(boolean seguro){
		int importe=30;
		if(!seguro){
			importe=0;
		}
		return importe;
	}
	
	public int importeEquipaje(int equipaje){
		int importe=50;
		if(equipaje!=1){
			importe=importe*equipaje;
		}
		return importe;
	}

	public int calcularTotal(int seguro, int equipaje, int precio){
		return seguro+equipaje+precio;
	}

	@Override
	public ReservaBillete crearReserva(Viaje viaje, int total, int equipaje, boolean seguro) {
		reserva = new ReservaBillete();
		int c = generarCodigoReserva(); //Generar codigo aleatorio
		String codigo = Integer.toString(c) + "RRRR";
		reserva.setIdReserva(codigo);
		reserva.setTotal(total);
		reserva.setEquipaje(equipaje);
		reserva.setSeguro(seguro);
		reserva.setidViaje(viaje.getIdViaje());
		return reserva;
		
	//	return FactoriaDAOTransporte.getInstancia().nuevoViaje().crearR(viaje);
	}

	//Se genera un numero aleatorio entre 1-200. Una vez usado uno, no es posible repetirlo
	public int generarCodigoReserva(){
		Random random = new Random();
		if(numbers.size()>1 ){
		   int randomIndex = random.nextInt(numbers.size());
		   numeroGenerado=numbers.get(randomIndex);
		   numbers.remove(randomIndex);
		}
		return numeroGenerado;
	}
	
	public void guardarR(ReservaBillete r, Viaje v) {
		reserva=r;
		FactoriaDAOTransporte.getInstancia().nuevoViaje().guardarV(v);
		FactoriaDAOTransporte.getInstancia().nuevoViaje().guardarR(reserva);
	}

	@Override
	public DatosBancarios datosB() {
		return FactoriaDAOTransporte.getInstancia().nuevoViaje().datosB();
	}

	@Override
	public void guardarP(Persona p) {
		FactoriaDAOTransporte.getInstancia().nuevoViaje().guardarP(p);
	}

	@Override
	public Viaje nuevoViaje(String id1, String id2) {
		DAOTransporte daoTransporte = FactoriaDAOTransporte.getInstancia().nuevoViaje();
		Viaje crearViaje = daoTransporte.crear(); //Se crea un nuevo viaje
		int c = generarCodigoReserva();
		String codigo = Integer.toString(c) + "VV";
		crearViaje.setIdViaje(codigo);
		crearViaje.setLugarOrigen(id1);
		crearViaje.setLugarDestino(id2);
		
		return crearViaje;
	}
}
