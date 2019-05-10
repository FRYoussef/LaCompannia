package negocio.transfers;

import java.io.Serializable;

public class Viaje implements Serializable {
	private String idViaje; //Id viaje
	private int numero; //Numero de personas
	private String fechaIda; //Fecha de ida
	private String fechaVuelta; //Fecha de vuelta
	private String lugarOrigen; //Lugar de origen
	private String lugarDestino; //Lugar de destino
	private String clase2;
	private Clase clase;
	private MedioTransporte medio;
	private String horaSalida;
	private String horaLlegada;
	private String precio;
	
	public Viaje()	{	}
	
	public String getIdViaje()	{
		return idViaje;
	}
	
	public void setIdViaje(String idP)	{
		idViaje= idP;
	}
	
	public int getNPersonas()	{
		return numero;
	}
	
	public void setNPersonas(int numeroP)	{
		numero= numeroP;
	}
	
	public void restaPersonas(int numeroP){
		if(getNPersonas()>=numeroP){
			this.setNPersonas(getNPersonas()-numeroP);
		}
	}
	
	public MedioTransporte getMedioTrans() {
		return medio;
	}
	
	public MedioTransporte setMedioTrans(MedioTransporte medioMod) {
		return medio = medioMod;
	}
	
	public Clase getClase() {
		return clase;
	}
	
	public Clase setClase(Clase claseM) {
		return clase = claseM;
	}
	
	public String getFechaIda() {
		return fechaIda;
	}
	
	public void setFechaIda(String fechaIdaV) {
		fechaIda = fechaIdaV;
	}
	
	public String getFechaVuelta() {
		return fechaVuelta;
	}
	
	public void setFechaVuelta(String fechaVueltaV) {
		fechaVuelta = fechaVueltaV;
	}
	
	public String getLugarOrigen() {
		return lugarOrigen;
	}
	
	public void setLugarOrigen(String lugarOrigenV) {
		lugarOrigen = lugarOrigenV;
	}
	
	public String getLugarDestino() {
		return lugarDestino;
	}
	
	public void setLugarDestino(String lugarDestinoV) {
		lugarDestino = lugarDestinoV;
	}
	
	public String gethoraSalida(){
		return horaSalida;
	}
	
	public void sethoraSalida(String horaSalida){
		this.horaSalida=horaSalida;
	}
	
	public String gethoraLlegada(){
		return horaLlegada;
	}
	
	public void sethoraLlegada(String horaLlegada){
		this.horaLlegada=horaLlegada;
	}
	
	public String getPrecio(){
		return precio;
	}
	
	public void setPrecio(String i){
		this.precio=i;
	}

}
