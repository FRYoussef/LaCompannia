package integracion.transporte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import negocio.transfers.*;

public class DAOTransporteImp implements DAOTransporte {
	//BBDD de viajes disponibles
	String rutaviaje = new File("src/integracion/transporte/bbdd_transporte.txt").getAbsolutePath();
	
	//BBDD de personas y viajes ya asignados a personas
	String rutapersonas = new File("src/integracion/transporte/bbdd_persona.txt").getAbsolutePath();
	String viajesnuevos = new File("src/integracion/transporte/viajes.txt").getAbsolutePath();
	
	//BBDD de reservas (tienen un viaje y una persona asociada)
	File reservasnuevas = new File("src/integracion/transporte/reservas.txt").getAbsoluteFile();
	
	Viaje nuevo;
	Persona nuevoP;
	ReservaBillete nuevoR;
	DatosBancarios datos;
	
	//Metodos de Viaje
	@Override
	public Viaje crear() {
		return new Viaje();
	}
	
	public ArrayList<Viaje> loadFile() {
		try{
			BufferedReader br;
			br = new BufferedReader(new FileReader(rutaviaje));
			ArrayList<Viaje> listviaje = new ArrayList<Viaje>();
			String contenido;
			while ((contenido=br.readLine()) != null) {
				listviaje.add(loadViajes(br));
			}
			br.close();
			return listviaje;
		}
		catch (IOException e){
		}
		return null;
	}
	
	public Viaje loadViajes(BufferedReader br) {
		nuevo = new Viaje();
		try {
			nuevo.setNPersonas(Integer.parseInt(br.readLine()));
			nuevo.setMedioTrans(MedioTransporte.valueOfLabel(br.readLine()));
			nuevo.setClase(Clase.valueOfLabel(br.readLine()));
			nuevo.setLugarOrigen(br.readLine());
			nuevo.setLugarDestino(br.readLine());
			nuevo.sethoraSalida(br.readLine());
			nuevo.sethoraLlegada(br.readLine());
			nuevo.setPrecio(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nuevo;
	}

	public Viaje buscarViaje(String origen, String destino, ArrayList<Viaje> viajes){
		ArrayList<Viaje> allViajes = viajes;
		for(Viaje v : allViajes){
			if(v.getLugarOrigen().equals(origen) && v.getLugarDestino().equals(destino))
				return v;
		}
		return null;
	}
	
	public void guardarV(Viaje nuevo){
		try(BufferedWriter wr = new BufferedWriter(new FileWriter(viajesnuevos, true))){
			wr.newLine();
			wr.write(nuevo.getIdViaje() + System.lineSeparator());
			wr.write(nuevo.getLugarOrigen() + System.lineSeparator());
			wr.write(nuevo.getLugarDestino() + System.lineSeparator());
			wr.write(nuevo.getFechaIda() + System.lineSeparator());
			wr.write(nuevo.getFechaVuelta() + System.lineSeparator());
			wr.write(nuevo.gethoraSalida() + System.lineSeparator());
			wr.write(nuevo.gethoraLlegada() + System.lineSeparator());
			wr.write(nuevo.getNPersonas() + System.lineSeparator());
			wr.write(nuevo.getClase() + System.lineSeparator());
			wr.write(nuevo.getMedioTrans() + System.lineSeparator());
			wr.write(nuevo.getPrecio() + System.lineSeparator());
		} catch (IOException | IllegalArgumentException ex) {
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
	}

	//Metodos de Persona
	@Override
	public Persona crearP() {
		DatosBancarios datos = new DatosBancarios();
		Persona p = new Persona();
		//p.setId(id);
		p.setDatosBancarios(datos);
		return p;
	}
	
	@Override
	public ArrayList<Persona> loadFilePersona() {
		ArrayList<Persona> listpersonas = new ArrayList<Persona>();
		try{
			BufferedReader br;
			br = new BufferedReader(new FileReader(rutapersonas));
			String contenido;
			while ((contenido=br.readLine()) != null) {
				listpersonas.add(loadPersonas(br));
			}
			br.close();
		}
		catch (IOException e){
		}
		return listpersonas;
	}

	@Override
	//Lectura fichero de persona
	public Persona loadPersonas(BufferedReader br) {
		nuevoP = new Persona();
		datos = new DatosBancarios();
		nuevoP.setDatosBancarios(datos);
		
		try {
			nuevoP.setId(Integer.parseInt(br.readLine()));
			nuevoP.setDni(br.readLine());
			nuevoP.setNombre(br.readLine());
			nuevoP.setApellidos(br.readLine());
			nuevoP.setPais(br.readLine());
			nuevoP.setCiudad(br.readLine());
			nuevoP.setCalle(br.readLine());
			nuevoP.setCodPostal(br.readLine());
			nuevoP.setTelefono(Integer.parseInt(br.readLine()));
			nuevoP.setEmail(br.readLine());
			//Para cargar los datos de la cuenta.
			datos.setNombreCuenta(br.readLine());
			datos.setNumCuenta(br.readLine());
			datos.setPaisCuenta(br.readLine());
			nuevoP.setDatosBancarios(datos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevoP;
	}

	public Persona existePersona(String dni) {
		ArrayList<Persona> todas = new ArrayList<Persona>();
		todas = loadFilePersona();
		if(todas != null) {
			for(Persona p : todas) {
				if(p.getDni().equalsIgnoreCase(dni)){
					return p;
				}
			}
		}
		return null;
	}
	
	public void guardarP(Persona nuevoP){
		try(BufferedWriter wr = new BufferedWriter(new FileWriter(rutapersonas, true))){
			wr.newLine();
			wr.write(nuevoP.getId() + System.lineSeparator());
			wr.write(nuevoP.getDni() + System.lineSeparator());
			wr.write(nuevoP.getNombre() + System.lineSeparator());
			wr.write(nuevoP.getApellidos() + System.lineSeparator());
			wr.write(nuevoP.getPais() + System.lineSeparator());
			wr.write(nuevoP.getCiudad() + System.lineSeparator());
			wr.write(nuevoP.getCalle() + System.lineSeparator());
			wr.write(nuevoP.getCodPostal() + System.lineSeparator());
			wr.write(nuevoP.getTelefono() + System.lineSeparator());
			wr.write(nuevoP.getEmail() + System.lineSeparator());
			wr.write(nuevoP.getDatosBancarios().getNombreCuenta() + System.lineSeparator());
			wr.write(nuevoP.getDatosBancarios().getNumCuenta() + System.lineSeparator());
			wr.write(nuevoP.getDatosBancarios().getPaisCuenta() + System.lineSeparator());
		} catch (IOException | IllegalArgumentException ex) {
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
	}

	//Metodos de Reserva
	@Override
	public ReservaBillete crearR(Viaje viaje) {
		nuevoR = new ReservaBillete();
		return nuevoR;
	}

	public ArrayList<ReservaBillete> loadFileR() {
		try{
			BufferedReader br;
			br = new BufferedReader(new FileReader(reservasnuevas));
			ArrayList<ReservaBillete> listreservas = new ArrayList<ReservaBillete>();
			String contenido;
			while ((contenido=br.readLine()) != null) {
				listreservas.add(loadReservas(br));
			}
			br.close();
			return listreservas;
		}
		catch (IOException e){
		}
		return null;
	}
	
	public ReservaBillete loadReservas(BufferedReader br) {
		nuevoR = new ReservaBillete();
		try {
			nuevoR.setIdReserva(br.readLine());
			nuevoR.setEquipaje(Integer.parseInt(br.readLine()));
			nuevoR.setSeguro(Boolean.parseBoolean(br.readLine()));
			nuevoR.setidViaje(br.readLine());
			nuevoR.setidPersona(Integer.parseInt(br.readLine()));
			nuevoR.setTotal(Integer.parseInt(br.readLine()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nuevoR;
	}
	
	public ReservaBillete buscarReserva(String codigo, ArrayList<ReservaBillete> reservas){
		ArrayList<ReservaBillete> allReservas = reservas;
		for(ReservaBillete r : allReservas){
			if(r.getIdReserva().equals(codigo))
				return r;
		}
		return null;
	}
	
	public void guardarR(ReservaBillete nuevoR){
		try(BufferedWriter wr = new BufferedWriter(new FileWriter(reservasnuevas, true))){
			wr.newLine();
			wr.write(nuevoR.getIdReserva() + System.lineSeparator());
			wr.write(nuevoR.getEquipaje() + System.lineSeparator());
			wr.write(nuevoR.getSeguro() + System.lineSeparator());
			wr.write(nuevoR.getidViaje() + System.lineSeparator());
			wr.write(nuevoR.getidPersona() + System.lineSeparator());
			wr.write(nuevoR.getTotal() + System.lineSeparator());
		} catch (IOException | IllegalArgumentException ex) {
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
	}

	@Override
	public DatosBancarios datosB() {
		return new DatosBancarios();
	}
	
}

