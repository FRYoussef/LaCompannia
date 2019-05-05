package integracion.catering;

public abstract class FactoriaDAO_GC {
	
	static FactoriaDAO_GC instancia= null;
	
	static public FactoriaDAO_GC getInstancia()
	{
		if (instancia == null) instancia= new FactoriaDAO_GCImp();
	
		return instancia;
	}
	
	public abstract DAO_GC nuevoDAOPedido();

}
