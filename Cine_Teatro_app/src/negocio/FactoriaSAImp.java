package negocio;

import negocio.catering.SAPedido_GC;
import negocio.catering.SAPedido_GCImp;
import negocio.reservas.SAReserva;
import negocio.reservas.SAReservaImp;

public class FactoriaSAImp extends FactoriaSA {

	FactoriaSAImp() {}

	@Override
	public SAPedido_GC getSAPedido() {
		return new SAPedido_GCImp();
	}

	@Override
	public SAReserva getSAReserva() {
		return new SAReservaImp();
	}
}
