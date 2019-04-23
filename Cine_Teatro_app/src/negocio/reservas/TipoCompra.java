package negocio.reservas;

public enum TipoCompra {
	RESERVA;
	
	public static TipoCompra getValue(String value) {
		for(TipoCompra tCompra : TipoCompra.values())
			if(tCompra.name().toLowerCase().equals(value.toLowerCase())) return tCompra;
		return null;
	}
}
