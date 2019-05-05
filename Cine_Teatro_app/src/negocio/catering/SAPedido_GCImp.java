package negocio.catering;

import java.util.List;

import integracion.catering.FactoriaDAO_GC;
import negocio.transfers.EstadoPedido;
import negocio.transfers.Persona;

public class SAPedido_GCImp implements SAPedido_GC{
	private TransferPedido_GC ped = null;
	
	public SAPedido_GCImp() {
		crearPedido();
		/*
		 * COMO ME HE ASEGURADO POR LA "FactoriaSA_GC" QUE SOLO VA A VER UNA INSTANCIA,
		 * PUEDO CREAR EL PEDIDO CUANDO SE CREE EL SISTEMA DE APLICACION QUE SE ENCARGA
		 * DE GESTIONARLO, DE ESTA MANERA ME ASEGURO QUE SOLO SE GESTIONA UN UNICO PEDIDO
		 * DURANTE EL TRANSCURSO DE LA EJECUCION.
		 */
	}
	
	@Override
	public void crearPedido() {
		ped = FactoriaDAO_GC.getInstancia().nuevoDAOPedido().crearPedido(ped);
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().modificaPedido(ped, EstadoPedido.PROCESO, null, null, null);
	}

	@Override
	public void borrarPedido() {
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().borrarTranferPedido(ped);
	}
	
	@Override
	public double calcularPrecioTotal() {
		double tot = 0;
		List<TransferProducto_GC> list = ped.getProductos();
		for(TransferProducto_GC p : list) {
			if(FactoriaDAO_GC.getInstancia().nuevoDAOPedido().buscaProducto(p.get_codProd()) != null) //SI EXISTE
				tot += p.get_precio();
		}
		ped.set_precioTotal(tot);
		return tot;
	}

	@Override
	public double aplicarOferta() {
		double desc = 0;
		List<TransferProducto_GC> list = ped.getProductos();
		for(int i = 0; i < list.size(); i++) {
			desc += list.get(i).get_descuento();
		}
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().modificaPedido(ped, null, ped.get_precioTotal() - desc, null, null);
		return ped.get_precioTotal();
	}

	@Override
	public void cambiarEstadoPedido(EstadoPedido estado) {
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().modificaPedido(ped, estado, null, null, null);
	}

	@Override
	public boolean pagarPedido(Persona persona, String datosIntroducidos) {
		/*	
		 * 	HABRIA QUE COMPORBAR QUE LOS "datosIntroducidos" SON CORRECTOS Y SE PROCEDERIA A PAGAR.
		 * 	UNA VEZ OBTENIDA LA INFORMACION DEL BANCO SE DEVOLVERIA:
		 * 		- TRUE -> SI SE HA REALIZADO CORRECTAMENTE EL PAGO NOTIFICADO POR EL BANCO.
		 * 		- FALSE -> EN CASO CONTRARIO.
		 */
		return true; //SUPONGO SIEMPRE QUE SE HA REALIZADO EL PAGO CORRECTAMENTE.
	}

	@Override
	public List<TransferProducto_GC> listProductos() {
		return ped.getProductos();
	}
	
	@Override
	public void borrarProducto(TransferProducto_GC prod) {
		List<TransferProducto_GC> list = ped.getProductos();
		if(list.contains(prod)) {
			list.remove(prod);
		}
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().modificaPedido(ped, null,null,list, null);
	}
	
	@Override
	public List<String> listaDeProductosDisponiblesToString(){
		return FactoriaDAO_GC.getInstancia().nuevoDAOPedido().listarTodosLosProductos();
	}

	@Override
	public void actualizarCantidadProd() {
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().modificaPedido(ped, null,null,null, ped.getProductos().size());
	}

	@Override
	public void addProducto(TransferProducto_GC prod) {
		if(prod != null && ped != null) {
			List<TransferProducto_GC>list = ped.getProductos();
			list.add(prod);
			FactoriaDAO_GC.getInstancia().nuevoDAOPedido().modificaPedido(ped, null,null,list, null);
		}
		else throw new IllegalArgumentException("No se ha podido a�adir correctamente el producto");
	}

	@Override
	public TransferProducto_GC buscaProductoPorCodigo(int _codProd) {
		return FactoriaDAO_GC.getInstancia().nuevoDAOPedido().buscaProducto(_codProd);
	}
	
	@Override
	public TransferProducto_GC buscarProductoEnPedido(int _codProd) {
		List<TransferProducto_GC> list = ped.getProductos();
		for(int i = 0; i < list.size(); i++) {
			if(_codProd == list.get(i).get_codProd()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	@Override
	public int cantidadProductos() {
		return ped.getProductos().size();
	}
	
	@Override
	public void guardarPedidoEnBBDD() {
		FactoriaDAO_GC.getInstancia().nuevoDAOPedido().guardarPedidoEnBBDD(ped);
	}
	
	@Override
	public int codigoProducto() {
		return ped.get_codPed();
	}
}
