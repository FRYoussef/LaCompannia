package integracion.transporte;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import negocio.transfers.DatosBancarios;
import negocio.transfers.Persona;
import negocio.transfers.ReservaBillete;
import negocio.transfers.Viaje;

public interface DAOTransporte {
	
	//Metodos de Viaje
	public Viaje crear();
	public ArrayList<Viaje> loadFile();
	public Viaje loadViajes(BufferedReader br);
	public Viaje buscarViaje(String origen, String destino, ArrayList<Viaje> viajes);
	public void guardarV(Viaje nuevo);
	
	//Metodos de Persona
	public Persona crearP();
	public ArrayList<Persona> loadFilePersona();
	public Persona loadPersonas(BufferedReader br);
	public Persona existePersona(String dni);
	public void guardarP(Persona nuevoP);

	//Metodos de reserva
	public ReservaBillete crearR(Viaje viaje);
	public ArrayList<ReservaBillete> loadFileR();
	public ReservaBillete loadReservas(BufferedReader br);
	public ReservaBillete buscarReserva(String codigo, ArrayList<ReservaBillete> reservas);
	public void guardarR(ReservaBillete nuevoR);
	
	public DatosBancarios datosB();
	
}
