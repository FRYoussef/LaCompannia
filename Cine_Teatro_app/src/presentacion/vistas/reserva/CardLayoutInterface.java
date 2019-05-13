package presentacion.vistas.reserva;

import java.awt.event.ActionEvent;

/***************************************************************************************************
 * Fichero		: CardLayoutInterface.java
 *
 * Descripcion	: Interfaz que define una interaccion entre paneles pensados para usarse en un cardLayout.
 * 				  Permite reutilizar clases comunes a esta estructura en otros JFrame representantes de casos de uso (EJ PanelControl)
 *
 * Autor		: Daniel Alfaro Miranda
 **************************************************************************************************/
public interface CardLayoutInterface {
	public static final String returnButtonActionCommand = "RETURN";
	public static final String advanceButtonActionCommand = "ADVANCE";
	public static final String menuButtonActionCommand = "MENU";
	
	public abstract void returnButtonAction(ActionEvent e);
	
	public abstract void advanceButtonAction(ActionEvent e);
}
