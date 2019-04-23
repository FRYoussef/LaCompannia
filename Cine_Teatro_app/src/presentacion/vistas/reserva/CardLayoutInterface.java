package presentacion.vistas.reserva;

import java.awt.event.ActionEvent;

public interface CardLayoutInterface {
	public static final String returnButtonActionCommand = "RETURN";
	public static final String advanceButtonActionCommand = "ADVANCE";
	public static final String menuButtonActionCommand = "MENU";
	
	public abstract void returnButtonAction(ActionEvent e);
	
	public abstract void advanceButtonAction(ActionEvent e);
}
