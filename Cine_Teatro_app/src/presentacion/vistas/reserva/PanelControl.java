package presentacion.vistas.reserva;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelControl extends JPanel {

	JButton returnButton, advanceButton, menuButton;
	
	
	public PanelControl(CardLayoutInterface father) {
		initGUI();
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				father.returnButtonAction(e);
			}
		});
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				father.returnButtonAction(e);
			}
		});
		advanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				father.advanceButtonAction(e);
			}
		});
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		returnButton = new JButton("Volver");
		returnButton.setMinimumSize(new Dimension(20, 40));
		returnButton.setToolTipText("Retornar a la pantalla anterior");
		returnButton.setActionCommand(CardLayoutInterface.returnButtonActionCommand);
		
		advanceButton = new JButton("Pagar");
		advanceButton.setMinimumSize(new Dimension(20, 40));
		advanceButton.setToolTipText("Avanzar a la pantalla siguiente");
		advanceButton.setActionCommand(CardLayoutInterface.advanceButtonActionCommand);
		advanceButton.setVisible(false);
		
		menuButton = new JButton("Menú");
		menuButton.setMinimumSize(new Dimension(20, 40));
		menuButton.setToolTipText("Volver al menu del subsistema");
		menuButton.setActionCommand(CardLayoutInterface.menuButtonActionCommand);
		
		this.add(menuButton);
		this.add(returnButton);
		this.add(Box.createHorizontalGlue());
		this.add(advanceButton);
	}
	
	public void changeReturnButtonCaption(String caption) {
		returnButton.setText(caption);
	}
	
	public void changeAdvanceButtonCaption(String caption) {
		advanceButton.setText(caption);
	}
	
	public void changeReturnButtonVisibility(boolean b) {
		returnButton.setVisible(b);
	}
	
	public void changeAdvanceButtonVisibility(boolean b) {
		advanceButton.setVisible(b);
	}
	
	public void changeMenuButtonVisibility(boolean b) {
		menuButton.setVisible(b);
	}
	
}
