package negocio.catering;

public abstract class FactoriaSA_GC {
	
	static SAPedido_GC instancia = null;
	
	static public SAPedido_GC getInstancia(){
		if (instancia == null) instancia = new SAPedido_GCImp();
		return instancia;
	}	
	
	static public void eliminarInstancia() {
		instancia = null;
	}
}
