package integracion.transporte;

public class FactoriaDAOTransporteImp extends FactoriaDAOTransporte {

	@Override
	public DAOTransporte nuevoViaje() {
		return new DAOTransporteImp();
	}

}
