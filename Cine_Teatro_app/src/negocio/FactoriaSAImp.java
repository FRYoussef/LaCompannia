package negocio;

import negocio.reservas.SAReserva;
import negocio.reservas.SAReservaImp;

public class FactoriaSAImp extends FactoriaSA {

	FactoriaSAImp() {}

	@Override
	public SAReserva getSAReserva() {
		return new SAReservaImp();
	}
}
