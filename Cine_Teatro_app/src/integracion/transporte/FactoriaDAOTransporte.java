package integracion.transporte;


public abstract class FactoriaDAOTransporte {

private static FactoriaDAOTransporte instancia= null;
	
	static public FactoriaDAOTransporte getInstancia(){
		if (instancia == null) instancia= new FactoriaDAOTransporteImp();
		return instancia;
	}
	
	public abstract DAOTransporte nuevoViaje();

}
