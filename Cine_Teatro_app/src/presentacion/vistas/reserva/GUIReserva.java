package presentacion.vistas.reserva;

import presentacion.vistas.GUI;

public abstract class GUIReserva implements GUI{
	private static GUIReserva instancia = null;
	
	public static final int CODE = 0;
	
	public static GUIReserva getInstancia() {
		if(instancia == null) instancia = new GUIReservaImp();
		return instancia;
	}
	
	protected void reset() {
		instancia = null;
	}
	
	public abstract void onActionEnded(); //Funcion que representa el retorno al menu de CU,s del subsistema reserva
	
	public abstract void exitAction(); //Funcion que representa el retorno al menu principal de subsistemas
	
}
