package negocio;

import negocio.catering.SAPedido_GC;
import negocio.catering.SAPedido_GCImp;

public class FactoriaSAImp extends FactoriaSA {

	FactoriaSAImp() {}

	@Override
	public SAPedido_GC getSAPedido() {
		return new SAPedido_GCImp();
	}
}
