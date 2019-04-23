package integracion.catering;

import java.util.List;

import negocio.EstadoPedido;
import negocio.transfers.TransferPedido_GC;
import negocio.transfers.TransferProducto;

public interface DAO_GC {
	
	public TransferPedido_GC crearPedido(TransferPedido_GC ped); //CREAR PEDIDO
	public void modificaPedido(TransferPedido_GC ped, EstadoPedido est, Number d, List<TransferProducto> productos, Number cantidad); //MODIFICA EL PEDIDO
	public void borrarTranferPedido(TransferPedido_GC ped); //BORRAR PEDIDO
	public void guardarPedidoEnBBDD(TransferPedido_GC ped); //AÑADIR PEDIDO AL FICHERO
	public TransferProducto buscaProducto(int _codProd); // BUSCA UN PRODUCTO EN EL FICHERO
	public List<String> listarTodosLosProductos(); //LISTA LOS PRODUCTOS DISPONIBLES DEL FICHERO PARA MOSTRARLOS POR LA GUI
	public void leerProductosDeLaBBDD(); //LEE LOS PRODUCTOS DEL FICHERO
	

}
