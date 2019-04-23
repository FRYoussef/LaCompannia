package presentacion.vistas.catering;

public abstract class GUIPedido_GC {

	static GUIPedido_GC instancia= null;
	
	public static GUIPedido_GC getInstancia()	{
		if(instancia == null) instancia= new GUIPedido_GCImp();
		return instancia;
	}
	
	static public void eliminarInstancia() {
		instancia = null;
	}
	
	public abstract void confirmarPedido();
}
