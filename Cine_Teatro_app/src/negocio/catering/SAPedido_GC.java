package negocio.catering;

import java.util.List;

import negocio.transfers.EstadoPedido;
import negocio.transfers.Persona;

public interface SAPedido_GC {
	
	public void crearPedido();
	public void borrarPedido();
	public double calcularPrecioTotal();
	public double aplicarOferta();
	public void cambiarEstadoPedido(EstadoPedido estado);
	public boolean pagarPedido(Persona persona, String datosIntroducidos);
	public List<TransferProducto_GC> listProductos();
	public void borrarProducto(TransferProducto_GC prod);
	public List<String> listaDeProductosDisponiblesToString();
	public void actualizarCantidadProd();
	public void addProducto(TransferProducto_GC prod);
	public TransferProducto_GC buscaProductoPorCodigo(int _codProd);
	public TransferProducto_GC buscarProductoEnPedido(int _codProd);
	public int cantidadProductos();
	public void guardarPedidoEnBBDD();
	public int codigoProducto();
}
