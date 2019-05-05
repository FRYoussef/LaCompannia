package negocio;

import negocio.catering.SAPedido_GC;
import negocio.reservas.SAReserva;

public abstract class FactoriaSA {
	
	private static FactoriaSA instancia = null;
	
	public static FactoriaSA getInstancia() {
		if(instancia == null)
			instancia = new FactoriaSAImp();
		return instancia;
	}
	
	public abstract SAReserva getSAReserva();
	public abstract SAPedido_GC getSACatering();
}
