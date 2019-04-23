package integracion.reservas;

import negocio.transfers.Cliente;


public interface DAOCliente {
	
	public boolean altaCliente(Cliente tCli);
	
	public boolean modCliente(Cliente tCli);
	
	public boolean elimCliente(String dniCliente);
	
	public Cliente consultaCliente(String dniCliente);
}
