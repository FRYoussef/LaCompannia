package integracion;

import integracion.reservas.DAOCliente;
import integracion.reservas.DAOClienteImp;
import integracion.reservas.DAOLugar;
import integracion.reservas.DAOLugarImp;
import integracion.reservas.DAOPago;
import integracion.reservas.DAOPagoImp;
import integracion.reservas.DAOReservaLugar;
import integracion.reservas.DAOReservaLugarImp;

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
