package presentacion.vistas.reserva;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.transfers.Lugar;

@SuppressWarnings("serial")
public class PanelLugar extends JPanel{
	
	private JTextField nombre, descripcion, direccion;
	private JButton masInfo, verDisp, reservar;
	private Lugar lugarAsociado;
	
	public PanelLugar(PanelListado listado, Lugar lugar) {
		lugarAsociado = lugar;
		initGUI();
		reservar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listado.setLugarSeleccionado(lugarAsociado);
				listado.reservarButtonAction(arg0);
			}
		});
		
	}
	
	public PanelLugar(Lugar lugar) {
		lugarAsociado = lugar;
		initGUI();
		reservar.setVisible(false);	
	}
	
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setPreferredSize(new Dimension(485, 60));
		this.setMaximumSize(new Dimension(485, 260));
		
		JPanel pNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pNombre.add(new JLabel("Nombre: "));
		nombre = new JTextField(lugarAsociado.getNombre());
		nombre.setEditable(false);
		nombre.setPreferredSize(new Dimension(285, 20));
		pNombre.add(nombre);
		
		JPanel pDescrip = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pDescrip.add(new JLabel("Descripcion: "));
		String strDesc = lugarAsociado.getDescripcion();
		if(strDesc.length() > 65) strDesc = strDesc.substring(0, 60) + "...";
		descripcion = new JTextField(strDesc);
		descripcion.setEditable(false);
		descripcion.setPreferredSize(new Dimension(360, 20));
		pDescrip.add(descripcion);
		
		JPanel pDir = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pDir.add(new JLabel("Direccion: "));
		direccion = new JTextField(lugarAsociado.getCiudad() + "-" + lugarAsociado.getCalle());
		direccion.setEditable(false);
		direccion.setPreferredSize(new Dimension(278, 20));
		pDir.add(direccion);
		pDir.add(new JLabel("Tarifa: "));
		pDir.add(new JLabel(new DecimalFormat("#,##0.00").format(lugarAsociado.getTarifa()) + "€/dia"));
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.X_AXIS));
		masInfo = new JButton("Mas Info");
		masInfo.setAlignmentX(JButton.LEFT_ALIGNMENT);
		botonera.add(masInfo);
		botonera.add(Box.createHorizontalGlue());
		verDisp = new JButton("Ver Disponibilidad");
		verDisp.setAlignmentX(JButton.CENTER_ALIGNMENT);
		botonera.add(verDisp);
		botonera.add(Box.createHorizontalGlue());
		reservar = new JButton("Reservar");
		reservar.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		reservar.setActionCommand(PanelListado.reserveButtonActionCommand);
		
		botonera.add(reservar);
		
		this.add(pNombre);
		this.add(pDescrip);
		this.add(pDir);
		this.add(botonera);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public Lugar getTransferLugar() {
		return lugarAsociado;
	}
	
}
