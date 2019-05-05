package integracion.catering;

import java.util.List;

import negocio.catering.TransferPedido_GC;
import negocio.catering.TransferProducto_GC;
import negocio.transfers.EstadoPedido;

public interface DAO_GC {
	
	public TransferPedido_GC crearPedido(TransferPedido_GC ped); //CREAR PEDIDO
	public void modificaPedido(TransferPedido_GC ped, EstadoPedido est, Number d, List<TransferProducto_GC> productos, Number cantidad); //MODIFICA EL PEDIDO
	public void borrarTranferPedido(TransferPedido_GC ped); //BORRAR PEDIDO
	public void guardarPedidoEnBBDD(TransferPedido_GC ped); //ANNADIR PEDIDO AL FICHERO
	public TransferProducto_GC buscaProducto(int _codProd); // BUSCA UN PRODUCTO EN EL FICHERO
	public List<String> listarTodosLosProductos(); //LISTA LOS PRODUCTOS DISPONIBLES DEL FICHERO PARA MOSTRARLOS POR LA GUI
	public void leerProductosDeLaBBDD(); //LEE LOS PRODUCTOS DEL FICHERO
	

}
