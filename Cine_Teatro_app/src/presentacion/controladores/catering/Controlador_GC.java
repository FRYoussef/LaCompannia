package presentacion.controladores.catering;

import java.util.List;

import negocio.catering.TransferProducto_GC;
import negocio.transfers.EstadoPedido;

public abstract class Controlador_GC {
	
	static Controlador_GC instancia= null;
	
	static public Controlador_GC getInstancia(){
		if (instancia == null) instancia= new Controlador_GCImp();
		return instancia;
	}
	
	public abstract List<String> productosDisponibles();
	public abstract void addProducto(int CodProd);
	public abstract int cantidadProductos();
	public abstract List<TransferProducto_GC> listProductos();
	public abstract void borrarProducto(TransferProducto_GC prod);
	public abstract TransferProducto_GC buscarProductoEnPedido(int _codProd);
	public abstract void actualizarCantidadProd();
	public abstract double calcularPrecioTotal();
	public abstract double aplicarOferta();
	public abstract boolean pagar(String n);
	public abstract int CodidoProducto();
	public abstract void cambiarEstado(EstadoPedido estado);
	public abstract void eliminarGUIPedido_GC();
}
