package negocio.reservas;

public enum TipoPago {
	COMPLETO(1), TRIMESTRAL(3), SEMESTRAL(6);
	
	private int mesesDuracion;
	
	private TipoPago(int mesesDuracion) {
		this.mesesDuracion = mesesDuracion;
	}
	
	public int getMesesDuracion() {
		return mesesDuracion;
	}
	
	public static String[] getStringValues() {
		String[] values = new String[TipoPago.values().length];
		int i = 0;
		for(TipoPago tPago : TipoPago.values())
			values[i++] = tPago.name().substring(0, 1).toUpperCase() + tPago.name().substring(1).toLowerCase();
		return values;
	}
	
	public static TipoPago getValue(String value) {
		for(TipoPago tPago : TipoPago.values())
			if(tPago.name().toLowerCase().equals(value.toLowerCase())) return tPago;
		return null;
	}
}
