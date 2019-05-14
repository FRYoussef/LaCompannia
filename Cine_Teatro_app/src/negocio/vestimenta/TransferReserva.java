package negocio.vestimenta;

import java.util.Date;
import java.util.ArrayList;
public class TransferReserva {
	
	private ArrayList<TransferVestimenta> ListaVestimenta;
	private int Codigo,Cantidad,Importe;
	private Date FechaIni,FechaFin;
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public int getImporte() {
		return Importe;
	}
	public void setImporte(int importe) {
		Importe = importe;
	}
	public Date getFechaIni() {
		return FechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		FechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return FechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}
	public ArrayList<TransferVestimenta> getListaVestimenta() {
		return ListaVestimenta;
	}
	public void setListaVestimenta(ArrayList<TransferVestimenta> listaVestimenta) {
		ListaVestimenta = listaVestimenta;
	}
	
	
	
	
	
	
}
