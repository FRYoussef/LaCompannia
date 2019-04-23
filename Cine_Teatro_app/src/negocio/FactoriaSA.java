package negocio;

import negocio.catering.SAPedido_GC;

public abstract class FactoriaSA {
	
	private static FactoriaSA instancia = null;
	
	public static FactoriaSA getInstancia(int codigo) {
		if(instancia == null)
			instancia = new FactoriaSAImp();
		return instancia;
	}
	
	
	
	public abstract SAPedido_GC getSAPedido(); 
}
