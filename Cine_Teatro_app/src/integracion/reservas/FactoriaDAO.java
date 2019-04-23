package integracion.reservas;

public abstract class FactoriaDAO {
	
	private static FactoriaDAO instancia= null;
	
	static public FactoriaDAO getInstancia(){
		if (instancia == null) instancia= new FactoriaDAOImp();
		return instancia;
	}
	
	public abstract DAOCliente nuevoDAOCliente();
	
	public abstract DAOLugar nuevoDAOLugar();
	
	public abstract DAOPago nuevoDAOPago();
	
	public abstract DAOReservaLugar nuevoDAOReservaLugar();
}
