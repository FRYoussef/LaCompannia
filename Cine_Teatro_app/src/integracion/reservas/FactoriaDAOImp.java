package integracion.reservas;

public class FactoriaDAOImp extends FactoriaDAO {

	@Override
	public DAOCliente nuevoDAOCliente() {
		return new DAOClienteImp();
	}

	@Override
	public DAOLugar nuevoDAOLugar() {
		return new DAOLugarImp();
	}

	@Override
	public DAOPago nuevoDAOPago() {
		return new DAOPagoImp();
	}

	@Override
	public DAOReservaLugar nuevoDAOReservaLugar() {
		return new DAOReservaLugarImp();
	}

}
