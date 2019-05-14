package presentacion.vistas.transporte;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import negocio.transfers.Viaje;

import presentacion.controladores.transporte.ControladorTransporte;
import presentacion.controladores.transporte.Eventos;

public class GUITransporteImp extends GUITransporte {
	
	private JFrame principal;
	private JButton nuevo;
	private JButton nue;
	
	public GUITransporteImp()	{
		principal = new JFrame("Menu Subsistema Transporte");
		principal.setPreferredSize(new Dimension(600,600));
		principal.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					JOptionPane.showMessageDialog(GUITransporteImp.this.principal, "Saliendo Subsistema Transporte", "Subsistema Transporte", JOptionPane.INFORMATION_MESSAGE);
					GUITransporteImp.this.principal.dispose();
					deleteInstance();
				}
			}
		);
		//principal.setBounds(600,600,900,500);
		ejecutar();
	}

	private void ejecutar() {
		//principal.setLocationRelativeTo(null);
		JPanel buttons = new JPanel(new GridLayout(2,2));
		nuevo = new JButton("Nueva Reserva");
		nuevo.setToolTipText("Nueva reserva de viaje");
		buttons.add(nuevo);
		
		nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				nuevaReserva();
			}
		});
		
		//Para el resto de botones que no implementan caso de uso
		buttons.add(createButton("Consultar Reserva", "Consultar una reserva ya creada"));
		buttons.add(createButton("Modificar Reserva", "Modificar un viaje"));
		buttons.add(createButton("Borrar Reserva", "Eliminar un viaje"));
		principal.add(buttons);
		
		principal.setVisible(true);
		principal.pack();
	}
	
	private void nuevaReserva() {
		principal.setVisible(false);
		new NuevaReserva();
		
	}
	
	private JButton createButton(String texto, String tooltip){
		JButton button = new JButton(texto);
		button.setToolTipText(tooltip);
		return button;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUITransporteImp();
			}
		});
	}
	

}
