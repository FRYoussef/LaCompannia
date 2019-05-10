package negocio.transfers;

import java.util.ArrayList;

public class ReservaBillete extends Reserva {
	private String id; //Id de la reserva
	private boolean seguro; //Numero de asiento
	private int equipaje; //Numero de equipaje
	private int total; //importe total del viaje
	private String idViaje;
	private int idPersona;

	
	public ReservaBillete()	{	}
	
	public String getIdReserva()	{
		return id;
	}
	
	public void setIdReserva(String idP)	{
		id= idP;
	}
	
	public boolean getSeguro()	{
		return seguro;
	}
	
	public void setSeguro(boolean seguroR)	{
		seguro = seguroR;
	}
	
	public int getEquipaje()	{
		return equipaje;
	}
	
	public void setEquipaje(int equipajeR)	{
		equipaje= equipajeR;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	public String getidViaje(){
		return idViaje;
	}
	
	public void setidViaje(String idViaje){
		this.idViaje=idViaje;
	}
	
	public int getidPersona(){
		return idPersona;
	}
	
	public void setidPersona(int idPersona){
		this.idPersona=idPersona;
	}
	
}
