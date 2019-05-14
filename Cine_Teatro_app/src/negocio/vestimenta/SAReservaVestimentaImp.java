package negocio.vestimenta;

import integracion.vestimenta.DAO_BDVImp;



public class SAReservaVestimentaImp implements SAReservaVestimenta {

	DAO_BDVImp Dao;
	public SAReservaVestimentaImp(DAO_BDVImp Dao) {
		this.Dao=Dao;
	}
	
	public void BuscarProducto(String ID) {
		TransferVestimenta vestimenta= null;
		vestimenta= Dao.buscarVestimenta(ID);
	}
	public void printTransferVestimenta(TransferVestimenta vestimenta) {
		
	}

}
