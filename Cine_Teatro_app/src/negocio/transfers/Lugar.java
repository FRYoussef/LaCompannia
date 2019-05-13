package negocio.transfers;

import java.util.ArrayList;

import negocio.reservas.IntervaloTiempo;

public class Lugar {
	private String idLugar, dniPropietario, nombre, descripcion, comAutonoma, ciudad, calle;
	private int aforo;
	private float tarifa;
	private ArrayList<IntervaloTiempo> ocupacion; //Array ordenado con numero par de fechas indicando intervalos de tiempo
	private String pathFoto;
	
	public String getIdLugar() {
		return idLugar;
	}
	
	public void setIdLugar(String idLugar) {
		this.idLugar = idLugar;
	}
	
	public String getDniPropietario() {
		return dniPropietario;
	}
	
	public void setDniPropietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public float getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
	}
	
	public ArrayList<IntervaloTiempo> getOcupacion() {
		return ocupacion;
	}
	
	public void setOcupacion(ArrayList<IntervaloTiempo> ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getComAutonoma() {
		return comAutonoma;
	}

	public void setComAutonoma(String comAutonoma) {
		this.comAutonoma = comAutonoma;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	} 
	
	
	
	
}
