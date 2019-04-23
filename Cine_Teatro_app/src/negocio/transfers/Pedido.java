
package negocio.transfers;

import java.util.ArrayList;
import java.util.List;

import negocio.transfers.EstadoPedido;

public class Pedido {
	static int numberPed = 100254;
	static int numMesa = 0;
	
	private EstadoPedido _estado;
	private int _codPed;
	private double _precioTotal;
	private int _codMesa;
	private List<Producto> productos;
	private int _cantidad;
	
	public Pedido()	{
		productos = new ArrayList<>();
		numberPed += 13; //VOY GENERANDO UN CODIGO DE PEDIDO
		_codPed = numberPed;
		numMesa = (numMesa + 1)%50; //VOY GENERANDO UN CODIGO PARA LA MESA
		_codMesa = numMesa;
	}

	public EstadoPedido get_estado() {
		return _estado;
	}

	public void set_estado(EstadoPedido _estado) {
		this._estado = _estado;
	}

	public int get_codPed() {
		return _codPed;
	}

	public void set_codPed(int _codPed) {
		this._codPed = _codPed;
	}

	public double get_precioTotal() {
		return _precioTotal;
	}

	public void set_precioTotal(double _precioTotal) {
		this._precioTotal = _precioTotal;
	}

	public int get_codMesa() {
		return _codMesa;
	}

	public void set_codMesa(int _codMesa) {
		this._codMesa = _codMesa;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public int get_cantidad() {
		return _cantidad;
	}

	public void set_cantidad(int _cantidad) {
		this._cantidad = _cantidad;
	}
}
