package negocio.catering;

public class TransferProducto_GC {

	private int _codProd;
	private String _nombre;
	private double _precio;
	private double _descuento;
	
	
	public int get_codProd() {
		return _codProd;
	}


	public void set_codProd(int _codProd) {
		this._codProd = _codProd;
	}


	public String get_nombre() {
		return _nombre;
	}


	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}


	public double get_precio() {
		return _precio;
	}


	public void set_precio(double _precio) {
		this._precio = _precio;
	}


	public double get_descuento() {
		return _descuento;
	}


	public void set_descuento(double _descuento) {
		this._descuento = _descuento;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferProducto_GC other = (TransferProducto_GC) obj;
		if (_codProd != other._codProd)
			return false;
		return true;
	}
}
