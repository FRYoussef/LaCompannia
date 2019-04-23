package negocio.reservas;

import java.util.ArrayList;

import negocio.transfers.Lugar;


public class FiltroBusquedaLugar {
	private String ciudad;
	private int dineroMin, dineroMax;
	private IntervaloTiempo intervalo;
	
	public FiltroBusquedaLugar() { //Filtro por defecto deja pasar todos los lugares
		ciudad = "";
		dineroMin = dineroMax = 0;
		intervalo = null;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getDineroMin() {
		return dineroMin;
	}

	public void setDineroMin(int dineroMin) throws IllegalArgumentException{
		if(dineroMin < 0 || dineroMin > this.dineroMax) throw new IllegalArgumentException("Dinero minimo invalido");
		this.dineroMin = dineroMin;
	}

	public int getDineroMax() {
		return dineroMax;
	}

	public void setDineroMax(int dineroMax) throws IllegalArgumentException{
		if(dineroMax < 0 || dineroMax < this.dineroMin) throw new IllegalArgumentException("Dinero maximo invalido");
		this.dineroMax = dineroMax;
	}

	public IntervaloTiempo getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(IntervaloTiempo intervalo) {
		this.intervalo = intervalo;
	}
	
	public boolean isInFilter(Lugar tLugar) {
		if(!ciudad.equals("") && !ciudad.equals(tLugar.getCiudad().toLowerCase())) return false;
		if(tLugar.getTarifa() < dineroMin || tLugar.getTarifa() > dineroMax) return false;
		if(intervalo != null) {
			ArrayList<IntervaloTiempo> ocupacion = tLugar.getOcupacion();
			for(IntervaloTiempo inter : ocupacion) {
				if(inter.compareTo(intervalo) == 0) return false;
			}
		}
		return true;
	}
}
