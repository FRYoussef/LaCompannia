package presentacion.controladores.catering;

import java.util.List;

import negocio.FactoriaSA_GC;
import negocio.transfers.EstadoPedido;
import negocio.transfers.Producto;
import presentacion.vistas.GUIPedido_GC;

public class Controlador_GCImp extends Controlador_GC {
	
	public Controlador_GCImp() {}
	
	public List<String> productosDisponibles() {
		return FactoriaSA_GC.getInstancia().listaDeProductosDisponiblesToString();
	}
	
	public void anadirProducto(int CodProd) {
		FactoriaSA_GC.getInstancia().a�adirProducto(FactoriaSA_GC.getInstancia().buscaProductoPorCodigo(CodProd));
	}
	
	public int cantidadProductos() {
		return FactoriaSA_GC.getInstancia().cantidadProductos();
	}
	
	public List<Producto> listProductos(){
		return FactoriaSA_GC.getInstancia().listProductos();
	}
		
	public void borrarProducto(Producto prod) {
		FactoriaSA_GC.getInstancia().borrarProducto(prod);
	}
	
	public Producto buscarProductoEnPedido(int _codProd) {
		return FactoriaSA_GC.getInstancia().buscarProductoEnPedido(_codProd);
	}
	
	public void actualizarCantidadProd() {
		FactoriaSA_GC.getInstancia().actualizarCantidadProd();
	}
	
	public double calcularPrecioTotal() {
		return FactoriaSA_GC.getInstancia().calcularPrecioTotal();
	}
	
	public double aplicarOferta() {
		return FactoriaSA_GC.getInstancia().aplicarOferta();
	}
	
	public boolean pagar(String data) {
		return  FactoriaSA_GC.getInstancia().pagarPedido(null, data);
	}
	
	public int CodidoProducto() {
		return  FactoriaSA_GC.getInstancia().codigoProducto();
	}
	
	public void cambiarEstado(EstadoPedido estado) {
		FactoriaSA_GC.getInstancia().cambiarEstadoPedido(estado);
		if(estado == EstadoPedido.PAGADO) FactoriaSA_GC.getInstancia().guardarPedidoEnBBDD();
	}

	public void eliminarSA_GC() {
		GUIPedido_GC.eliminarInstancia();
		FactoriaSA_GC.borrarInstancia();
	}
}
