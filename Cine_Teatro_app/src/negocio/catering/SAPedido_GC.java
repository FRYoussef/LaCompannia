package negocio.catering;

import java.util.List;

import negocio.transfers.EstadoPedido;
import negocio.transfers.Persona;
import negocio.transfers.Producto;

public interface SAPedido_GC {
	
	public void crearPedido();
	public void borrarPedido();
	public double calcularPrecioTotal();
	public double aplicarOferta();
	public void cambiarEstadoPedido(EstadoPedido estado);
	public boolean pagarPedido(Persona persona, String datosIntroducidos);
	public List<Producto> listProductos();
	public void borrarProducto(Producto prod);
	public List<String> listaDeProductosDisponiblesToString();
	public void actualizarCantidadProd();
	public void anadirProducto(Producto prod);
	public Producto buscaProductoPorCodigo(int _codProd);
	public Producto buscarProductoEnPedido(int _codProd);
	public int cantidadProductos();
	public void guardarPedidoEnBBDD();
	public int codigoProducto();
}
