package presentacion.vistas.reserva;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.transfers.Cliente;
import presentacion.controladores.reservas.ControladorReserva;

/***************************************************************************************************
 * Fichero		: FrameReservar.java
 *
 * Descripcion	: Clase JFrame que representa el caso de uso de reserva de lugar
 *
 * Autor		: Daniel Alfaro Miranda
 **************************************************************************************************/
public class FrameReservar extends JFrame implements CardLayoutInterface{
	private static final long serialVersionUID = 7233103508355797450L;
	//Paneles de control y etapas del caso de uso
	private PanelControl ctrPanel;
	private PanelListado listado;
	private PanelResumenReserva resumen;
	private PanelPago pago;
	
	private JPanel cards; //Panel con el cardLayout
	private String actualCard; //Panel actual del CardLayout
	public static final String cardNameListado = "LISTADO";
	public static final String cardNameResumen = "RESUMEN";
	public static final String cardNamePago = "PAGO";
	
	private Runnable onActionEnded; //Atributo que guarda la llamada al metodo onActioneEnded de GUIReserva
	
	public FrameReservar(GUIReserva father){
		super("Reservar Lugar");
		this.setAutoRequestFocus(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { father.exitAction(); }
		});
		onActionEnded = new Runnable() {
			@Override
			public void run() { father.onActionEnded(); }
		};
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 500));
		this.setResizable(false);
		
		ctrPanel = new PanelControl(this);
		ctrPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(ctrPanel, BorderLayout.PAGE_END);
		
		
		cards = new JPanel(new CardLayout());
		listado = new PanelListado(this);
		resumen = new PanelResumenReserva();
		pago = new PanelPago();
		cards.add(listado, cardNameListado);
		cards.add(resumen, cardNameResumen);
		cards.add(pago, cardNamePago);
		actualCard = cardNameListado;
		this.add(cards, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	//Metodo llamado desde los paneles de cada etapa del CU cuando quieren volver a la anterior
	@Override
	public void returnButtonAction(ActionEvent e) {
		if(e.getActionCommand().equals(menuButtonActionCommand) || 
				e.getActionCommand().equals(returnButtonActionCommand) && actualCard.equals(cardNameListado)) {
			onActionEnded.run();
		}
		else {
			CardLayout cly = (CardLayout) cards.getLayout();
			cly.previous(cards);
			if(actualCard.equals(cardNameResumen)) {
				actualCard = cardNameListado;
				ctrPanel.changeAdvanceButtonVisibility(false);
			}
			else if(actualCard.equals(cardNamePago)) actualCard = cardNameResumen;
		}		
	}

	//Metodo llamado desde los paneles de cada etapa del CU cuando quieren avanzar a la siguiente
	@Override
	public void advanceButtonAction(ActionEvent e) {
		if(e.getActionCommand().equals(advanceButtonActionCommand)) {
			CardLayout cly = (CardLayout) cards.getLayout();
			
			if(actualCard.equals(cardNameListado)) {
				actualCard = cardNameResumen;
				ctrPanel.changeAdvanceButtonVisibility(true);
				resumen.setLugarSeleccionado(listado.getLugarSeleccionado(), listado.getFechasReserva());
			}
			else if(actualCard.equals(cardNameResumen)) {
				actualCard = cardNamePago;
				
				//TODO SE SUSTITUYE DNI POR EL DEL CLIENTE LOGUEADO EN LA APLICACION
				pago.setCliente(ControladorReserva.getInstancia().obtenerDatosCliente("83423592x"));
			}
			else if(actualCard.equals(cardNamePago)) {
				Cliente tCli = pago.getDatos();
				if(tCli != null) {
					try {
						ControladorReserva.getInstancia().reservarLugar(listado.getLugarSeleccionado().getIdLugar(), tCli, 
								listado.getFechasReserva(), resumen.getTipoPagoSeleccionado(), pago.isPaypalSelected());
						
						String[] opciones = {"Menú", "Listado", "Descargar factura"};
						int sel = JOptionPane.showOptionDialog(this, "Reserva realizada con exito,\nse le enviara una notificacion al email", 
								"Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
						if(sel <= 0) onActionEnded.run(); //Vuelta al menu
						else if(sel == 1) actualCard = cardNameListado; //Vuelta al listado
						else {
							JOptionPane.showMessageDialog(this, "Funcionalidad sin implementar", "Información", JOptionPane.INFORMATION_MESSAGE);
							onActionEnded.run();
						}
					}catch(IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else JOptionPane.showMessageDialog(this, "Los datos de reserva tienen formato invalido", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
			cly.show(cards, actualCard);
		}		
	}
	
	
	
}
