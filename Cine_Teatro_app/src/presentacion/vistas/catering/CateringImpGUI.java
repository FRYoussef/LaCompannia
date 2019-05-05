package presentacion.vistas.catering;

import presentacion.vistas.GUI;

public abstract class CateringImpGUI implements GUI {
	public static final int CODE = 3;
	static CateringImpGUI instancia = null;
	
	public static CateringImpGUI getInstancia()	{
		if(instancia == null) instancia = new GUIPedido_GCImp();
		return instancia;
	}
	
	static public void eliminarInstancia() {
		instancia = null;
	}
	
	@Override
	public void ejecutar() {
		CateringImpGUI.getInstancia();
	}
	
	public abstract void confirmarPedido();
}
