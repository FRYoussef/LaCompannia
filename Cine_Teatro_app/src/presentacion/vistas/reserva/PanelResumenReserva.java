package presentacion.vistas.reserva;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import negocio.reservas.IntervaloTiempo;
import negocio.transfers.Lugar;
import negocio.transfers.TipoPago;
import presentacion.controladores.reservas.ControladorReserva;

@SuppressWarnings("serial")
public class PanelResumenReserva extends JPanel{
	
	private JComboBox<String> cBoxTipoPago;
	private JTextArea txAreaResumen;
	private PanelLugar pLugar;
	private IntervaloTiempo fechasReserva;
	
	public PanelResumenReserva() {
		initGUI();
		cBoxTipoPago.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				setFactura(pLugar.getTransferLugar(), fechasReserva);
			}
		});
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		this.add(new JPanel(), BorderLayout.PAGE_START);
		
		JPanel pResumen = new JPanel();
		pResumen.setLayout(new FlowLayout());
		
		JPanel pTipoPago = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cBoxTipoPago = new JComboBox<String>(TipoPago.getStringValues());
		pTipoPago.add(new JLabel("Metodo de pago: "));
		pTipoPago.add(cBoxTipoPago);
		pTipoPago.setPreferredSize(new Dimension(480,33));
		
		JPanel pTextArea = new JPanel(new FlowLayout());
		txAreaResumen = new JTextArea(15, 43);
		txAreaResumen.setEditable(false);
		txAreaResumen.setLineWrap(true);
		txAreaResumen.setAlignmentX(JTextArea.RIGHT_ALIGNMENT);
		txAreaResumen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pTextArea.add(txAreaResumen);
		JScrollPane scroll = new JScrollPane(pTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll, BorderLayout.CENTER);
	
		
		pResumen.add(pTipoPago);
		pResumen.add(scroll);

		this.add(pResumen, BorderLayout.CENTER);
	}
	
	public void setLugarSeleccionado(Lugar lugar, IntervaloTiempo fechasReserva) {
		if(pLugar != null) pLugar.setVisible(false);
		BorderLayout layout = (BorderLayout) this.getLayout();
		this.remove(layout.getLayoutComponent(BorderLayout.PAGE_START));
		pLugar = new PanelLugar(lugar);
		this.add(pLugar, BorderLayout.PAGE_START);
		this.fechasReserva = fechasReserva;
		setFactura(lugar, fechasReserva);
	}
	
	private void setFactura(Lugar lugar, IntervaloTiempo fechasReserva) {
		txAreaResumen.setText(ControladorReserva.getInstancia().
				getFactura(lugar, fechasReserva, TipoPago.getValue((String) cBoxTipoPago.getSelectedItem()), new Date()));
	}
	
	public TipoPago getTipoPagoSeleccionado() {
		return TipoPago.getValue((String) cBoxTipoPago.getSelectedItem());
	}
	
}
