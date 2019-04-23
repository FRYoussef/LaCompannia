package integracion.reservas;

import java.util.ArrayList;

import negocio.reservas.FiltroBusquedaLugar;
import negocio.transfers.Lugar;

public interface DAOLugar {
	
	public boolean altaLugar(Lugar tLugar);
	
	public boolean modLugar(Lugar tLugar);
	
	public boolean elimLugar(String idLugar);
	
	public ArrayList<Lugar> consultaLugar(FiltroBusquedaLugar filtro);
	
	public Lugar consultaLugar(String idLugar);
}
