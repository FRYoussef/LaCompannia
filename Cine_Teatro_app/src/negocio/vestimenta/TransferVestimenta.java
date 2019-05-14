package negocio.vestimenta;

public class TransferVestimenta {
	private String ID,NombrPropietario,localizacion,descripcion,disponible;
	
	private  int tarifa;
	private double CosteFabricacion;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNombrPropietario() {
		return NombrPropietario;
	}
	public void setNombrPropietario(String nombrPropietario) {
		NombrPropietario = nombrPropietario;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String isDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	public int getTarifa() {
		return tarifa;
	}
	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}
	public double getCosteFabricacion() {
		return CosteFabricacion;
	}
	public void setCosteFabricacion(double costeFabricacion) {
		CosteFabricacion = costeFabricacion;
	}
	
}
