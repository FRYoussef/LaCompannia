package negocio;

import negocio.catering.FactoriaSA_GC;
import negocio.catering.SAPedido_GC;
import negocio.reservas.SAReserva;
import negocio.reservas.SAReservaImp;
import negocio.transporte.SATransporte;
import negocio.transporte.SATransporteImp;

public class FactoriaSAImp extends FactoriaSA {

	FactoriaSAImp() {}

	@Override
	public SAReserva getSAReserva() {
		return new SAReservaImp();
	}

	@Override
	public SAPedido_GC getSACatering() {
		return FactoriaSA_GC.getInstancia();
	}
	
	public SATransporte getSATransporte() {
		return new SATransporteImp();
	}
}
