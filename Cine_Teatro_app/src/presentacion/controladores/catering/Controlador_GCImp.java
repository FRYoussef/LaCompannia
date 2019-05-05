package presentacion.controladores.catering;

import java.util.List;

import negocio.catering.FactoriaSA_GC;
import negocio.catering.TransferProducto_GC;
import negocio.transfers.EstadoPedido;
import presentacion.vistas.catering.CateringImpGUI;


public class Controlador_GCImp extends Controlador_GC {
	
	public Controlador_GCImp() {}
	
	public List<String> productosDisponibles() {
		return FactoriaSA_GC.getInstancia().listaDeProductosDisponiblesToString();
	}
	
	public void addProducto(int CodProd) {
		FactoriaSA_GC.getInstancia().addProducto(FactoriaSA_GC.getInstancia().buscaProductoPorCodigo(CodProd));
	}
	
	public int cantidadProductos() {
		return FactoriaSA_GC.getInstancia().cantidadProductos();
	}
	
	public List<TransferProducto_GC> listProductos(){
		return FactoriaSA_GC.getInstancia().listProductos();
	}
		
	public void borrarProducto(TransferProducto_GC prod) {
		FactoriaSA_GC.getInstancia().borrarProducto(prod);
	}
	
	public TransferProducto_GC buscarProductoEnPedido(int _codProd) {
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

	public void eliminarGUIPedido_GC() {
		CateringImpGUI.eliminarInstancia();
		FactoriaSA_GC.eliminarInstancia();
	}
}
