package integracion.catering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import negocio.catering.TransferPedido_GC;
import negocio.catering.TransferProducto_GC;
import negocio.transfers.EstadoPedido;
import presentacion.vistas.catering.GUIPedido_GCImp;

public class DAO_GCImp implements DAO_GC {
	String BBDD_productos = "src/integracion/catering/bbdd_productos.txt";
	String BBDD_pedidos = "src/integracion/catering/bbdd_pedidos.txt";
	public static int tam_Codigo_Producto = 4;
	
	File _filePed= new File(BBDD_pedidos);
	private String[][] prod; //ALMACENO LOS DATOS DE LA BASE DE DATOS;
	
	public DAO_GCImp() {
		leerProductosDeLaBBDD(); //CUANDO CREO EL DAO SE ACTUALIZA EL ARRAY CON LOS DATOS DEL FICHERO;
	}
	
	public TransferPedido_GC crearPedido(TransferPedido_GC ped) {
		ped = new TransferPedido_GC();
		return ped;
	}
	
	public void modificaPedido(TransferPedido_GC ped, EstadoPedido est, Number precio, List<TransferProducto_GC> productos, Number cantidad) {
		if(ped != null) {
			if(est != null) {
				ped.set_estado(est);
				if(est == EstadoPedido.CANCELADO) borrarTranferPedido(ped);
			}
			if(precio != null) ped.set_precioTotal((double) precio);
			if(productos != null) ped.setProductos(productos);
			if(cantidad != null) ped.set_cantidad((int) cantidad);
		}
	}
	
	public void borrarTranferPedido(TransferPedido_GC ped) {
		ped.set_estado(EstadoPedido.CANCELADO);
		guardarPedidoEnBBDD(ped);
	}
	
	public void guardarPedidoEnBBDD(TransferPedido_GC ped) {
		if(ped.get_estado() == EstadoPedido.PAGADO) { // SOLO SE ALMACENAN LOS PEDIDOS PAGADOS
			try(BufferedWriter wr = new BufferedWriter(new FileWriter(_filePed.getAbsoluteFile(), true))){
				wr.write("Codigo_Pedido: " + ped.get_codPed()+"\n");
				wr.write("Codigo_Mesa: " + ped.get_codMesa()+"\n");
				wr.write("Estado_Pedido: " + ped.get_estado() + "\n");
				wr.write("Cantidad: " + ped.get_cantidad() + "\n");
				wr.write("Precio_TOTAL: " + ped.get_precioTotal() + "\n");
				wr.write("Productos: " + "\n");
				for(TransferProducto_GC p: ped.getProductos()) {
					wr.write("  Codigo_Producto: " + p.get_codProd() + "\n");
					wr.write("  Nombre_Producto: " + p.get_nombre() + "\n");
				}
				wr.write("\n");
			} catch (IOException | IllegalArgumentException ex) {
				System.err.println(ex.getLocalizedMessage());
				System.exit(1);
			}
			
		}
	}
	
	@Override
	public TransferProducto_GC buscaProducto(int _codProd) { //SI NO LO ENCUENTRA NULL, SINO TRANSFERPRODUCTO.
		TransferProducto_GC tE= null;
		for(int i = 0; i < prod.length; i++) {
			if(_codProd == Integer.parseInt(prod[i][0])) {
				tE = new TransferProducto_GC();
				tE.set_codProd(_codProd);
				tE.set_nombre(prod[i][1]);
				tE.set_precio(Double.parseDouble(prod[i][2]));
				tE.set_descuento(Double.parseDouble(prod[i][3]));
			}
		}
		
		return tE;
	}

	@Override
	public List<String> listarTodosLosProductos() {
		leerProductosDeLaBBDD();
		List<String> listProd = new ArrayList<>();
		for(int i = 0; i < prod.length; i++) {
			listProd.add(GUIPedido_GCImp.codigoProd + prod[i][0] + GUIPedido_GCImp.nombreProd + prod[i][1]);
		}
		return listProd;
	}

	public void leerProductosDeLaBBDD() {
		String str;
		try(BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(BBDD_productos)))){
			prod = new String[Integer.parseInt(r.readLine())][4];
			int i = 0;
			str = r.readLine();
			while(str != null) {
				if(tam_Codigo_Producto != str.length() || Integer.parseInt(str) < 1000) throw new IOException("El fichero contiene un codigo de producto incorrecto");
				prod[i][0] = str;
				str = r.readLine();
				prod[i][1] = str;
				str = r.readLine();
				prod[i][2] = str;
				str = r.readLine();
				prod[i][3] = str;
				i++;
				str = r.readLine();
			}
			
		} catch (IOException | IllegalArgumentException ex) {
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
		
	}
}
