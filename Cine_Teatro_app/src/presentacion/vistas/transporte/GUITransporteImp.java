package presentacion.vistas.transporte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javafx.scene.control.ComboBox;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import presentacion.controladores.transporte.ControladorTransporte;
import presentacion.controladores.transporte.Eventos;
import presentacion.vistas.publicidad.PublicidadGUIImp;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import negocio.transfers.*;

public class GUITransporteImp extends GUITransporte implements ActionListener {
	ControladorTransporte ctrl;
	Clase clase;
	Viaje viaje;
	ReservaBillete reserva;
	JFrame nuevaReserva;
	private JButton nuevo, verdisp, volver, seleccionar, pagar, confirmar, pagprincipal;
	JTextField origenr, destinor, equipajer, codigoreserva;
	SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
	JSpinner npersonasr;
	private JComboBox<String> claser;
	private JComboBox<String> medior;
	private JRadioButton botida, botvuelta;
	JCheckBox seguro;
	private JDateChooser dateida, datevuelta;
	ArrayList<Viaje>listado = null;
	JPanel paneltable, center, inferior;
	private ResTableModel modelo;
	JTable table;
	String [] valores;
	JScrollPane scroll;
	JLabel paginaconf;
	
	Border b = BorderFactory.createLineBorder(Color.black, 1);
			
	public GUITransporteImp()	{
		ejecutar();
	}

	private void ejecutar() {
		nuevaReserva = new JFrame("Menu Nueva Reserva de Billete");
		nuevaReserva.setPreferredSize(new Dimension(600, 600));
		nuevaReserva.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					JOptionPane.showMessageDialog(GUITransporteImp.this.nuevaReserva, "Saliendo Subsistema Transporte", "Subsistema Transporte", JOptionPane.INFORMATION_MESSAGE);
					GUITransporteImp.this.nuevaReserva.dispose();
					deleteInstance();
				}
			}
		);
		//nuevaReserva.setLocationRelativeTo(null);
		
		//*********PANEL DE TITULO
		JPanel panel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("NUEVA RESERVA DE BILLETE");
		//title.setFont(new Font(getWarningString(), getDefaultCloseOperation(), 20));
		//title.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(title, BorderLayout.NORTH);
		
		//*********PANEL CENTRAL DE DATOS
		center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		//INFORMACION DE VIAJE
		JPanel content = new JPanel(new GridLayout(7,1));
		content.setBorder((BorderFactory.createTitledBorder(b, "Datos de Viaje")));
		
		//Número de personas
		JPanel varios = new JPanel();
		varios.setLayout(new BoxLayout(varios, BoxLayout.X_AXIS));
		JLabel npersonas = new JLabel("Nº personas: ");
		varios.add(npersonas, varios);
		npersonasr = new JSpinner(model);
		npersonasr.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				/*if(pagar.isEnabled()){
					int cambio = Integer.parseInt(npersonasr.getValue().toString());
					viaje.setNPersonas(cambio);
				}*/
			}
			
		});
		varios.add(npersonasr, varios);
		JLabel clase = new JLabel("Clase: ");
		varios.add(clase, varios);
		claser = new JComboBox<String>(Clase.getString());
		varios.add(claser, varios);
		JLabel medio = new JLabel("Medio: ");
		varios.add(medio, varios);
		medior = new JComboBox<String>(MedioTransporte.getString());
		varios.add(medior, varios);
		
		JPanel radiobutton = new JPanel();
		radiobutton.setLayout(new BoxLayout(radiobutton, BoxLayout.X_AXIS));
		botida = new JRadioButton("Solo ida");
		botida.setSelected(true);
		botvuelta = new JRadioButton("Ida y vuelta");
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(botida);
		btnGroup.add(botvuelta);
		radiobutton.add(botida);
		radiobutton.add(botvuelta);
		
		botida.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				datevuelta.setEnabled(false);
				verdisp.setEnabled(true);
				seleccionar.setVisible(false);
				pagar.setVisible(false);
			}
			
		});
		botvuelta.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				datevuelta.setEnabled(true);
				verdisp.setEnabled(true);
				seleccionar.setVisible(false);
				pagar.setVisible(false);
			}
			
		});
		
		//Fechas ida/vuelta
		JPanel fechas = new JPanel();
		fechas.setLayout(new BoxLayout(fechas, BoxLayout.X_AXIS));
		JLabel ida = new JLabel("Fecha ida: ");
		fechas.add(ida, fechas);
		dateida = new JDateChooser();
		fechas.add(dateida);
		JLabel vuelta = new JLabel("Fecha vuelta: ");
		fechas.add(vuelta, fechas);
		datevuelta = new JDateChooser();
		datevuelta.setEnabled(false);
		fechas.add(datevuelta);
		
		//Lugares origen/destino
		JPanel lugares = new JPanel();
		lugares.setLayout(new BoxLayout(lugares, BoxLayout.X_AXIS));
		JLabel origen = new JLabel("Origen: ");
		lugares.add(origen, lugares);
		origenr = new JTextField(2);
		lugares.add(origenr, lugares);
		JLabel destino = new JLabel("Destino: ");
		lugares.add(destino, lugares);
		destinor = new JTextField(2);
		lugares.add(destinor, lugares);
		
		//Espaciado
		JPanel varios2 = new JPanel();
		varios2.setLayout(new BoxLayout(varios2, BoxLayout.X_AXIS));
		JPanel fechas2 = new JPanel();
		fechas2.setLayout(new BoxLayout(fechas2, BoxLayout.X_AXIS));
		JPanel lugares2 = new JPanel();
		lugares2.setLayout(new BoxLayout(lugares2, BoxLayout.X_AXIS));
		
		content.add(varios);
		content.add(varios2);
		content.add(radiobutton);
		content.add(fechas);
		content.add(fechas2);
		content.add(lugares);
		content.add(lugares2);
		center.add(content);
		
		//INFORMACION ADICIONAL
		JPanel info = new JPanel();
		info.setLayout(new FlowLayout(FlowLayout.LEFT));
		info.setBorder((BorderFactory.createTitledBorder(b, "Información Adicional")));
		seguro = new JCheckBox("Seguro de Viaje  ");
		seguro.setSelected(true);
		seguro.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		info.add(seguro, info);
		JLabel equipaje = new JLabel("  Nº de Equipaje: ");
		info.add(equipaje, info);
		equipajer = new JTextField(3);
		equipajer.setText("1");
		equipajer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!equipajer.getText().equals(1)){
					//Incrementar precio por defecto de 1 equipaje
				}
			}
		});
		info.add(equipajer);
		seguro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!seguro.isSelected()){
					//Restar el dinero del seguro
				}
			}
		});
		//Incluir panel central
		center.add(info);
		
		panel.add(center, BorderLayout.CENTER);
		
		//********PANEL INFERIOR DE BOTONES
		inferior = new JPanel(new FlowLayout());
		//volver = new JButton("Volver");
		verdisp = new JButton("Ver disponibilidad");
		//inferior.add(volver);
		/*volver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nuevaReserva.setEnabled(false);
				new GUITransporteImp();
			}
		});*/
		
		inferior.add(verdisp);
		verdisp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(dateida.getDate() == null || origenr.getText().equals("") || destinor.getText().equals("") || (datevuelta.getDate() == null && datevuelta.isEnabled()) || (equipajer.getText().equals("0")) || (equipajer.getText().equals(""))){
						ControladorTransporte.getInstancia().accionCampo(true, viaje);
				}
				//Hacer comprobacion de fechas
				/*if (ControladorTransporte.getInstancia().accionFecha(v)){
				}*/
				else {
					pagar.setVisible(true);
					/*HashMap<String, String>campos = new HashMap<String, String>();
					campos.put("origen", origenr.getText());
					campos.put("destino", destinor.getText());
					/*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					campos.put("dateida", dateFormat.format(dateida.getDate()));*/
					/*ControladorTransporte.getInstancia().viajes(Eventos.VIAJE_UPDATE, campos);
					
					viaje.getLugarDestino();
					viaje.getLugarOrigen();
					*/
					
					ArrayList<Viaje>listado;
					viaje = ControladorTransporte.getInstancia().nuevoViaje();
					//Se actualizan algunos de los valores del viaje, el resto de valores se actualizaran cuando el usuario decida el horario y demás información del viaje.
					int campo0 = Integer.parseInt(npersonasr.getValue().toString());
					viaje.setNPersonas(campo0);
					String campo3 = origenr.getText();
					viaje.setLugarOrigen(campo3);
					String campo4 = destinor.getText();
					viaje.setLugarDestino(campo4);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String campo1 = dateFormat.format(dateida.getDate());
					viaje.setFechaIda(campo1);
					if(datevuelta.isEnabled()){
						String campo2 = dateFormat.format(datevuelta.getDate());
						viaje.setFechaVuelta(campo2);
					}
						listado = ControladorTransporte.getInstancia().accionCampo(false, viaje);
						
					paneltable = new JPanel(new BorderLayout());
					paneltable.setBorder((BorderFactory.createTitledBorder(b, "Datos de Viaje")));
					
					//Creacion de la tabla
					modelo = new ResTableModel(ctrl, listado);
					table= new JTable(modelo);
					table.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e) {
								int fila = table.getSelectedRow();
								int col = table.getColumnCount();
								int i;
								valores = new String[col];
								for (i = 0; i < col-2; i++) {
									String valor = (String) table.getValueAt(fila, i);
									valores[i]=valor;
								}
								JOptionPane.showMessageDialog(null, "VIAJE SELECCIONADO \n" + "Hora salida: "  + valores[2] + "\n Hora llegada:" + valores[3]); //Cambiar por mensaje YES/NO
								//Una vez reconocidos los valores de la tabla se actualiza el resto de la informacion del viaje que se ha creado.
								viaje.sethoraSalida((String) table.getValueAt(fila, 2));
								viaje.sethoraLlegada((String) table.getValueAt(fila, 3));
								viaje.setPrecio((String) table.getValueAt(fila, 4));
						}
					});
					table.setPreferredScrollableViewportSize(new Dimension(500,0));
					table.setBackground(Color.WHITE);
					//table.setFillsViewportHeight(true);
					scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					paneltable.add(table, BorderLayout.CENTER);
					//paneltable.add(scroll);
					
					center.add(paneltable);
					verdisp.setEnabled(false);
					pagar.setVisible(true);
					
					nuevaReserva.getContentPane();
					nuevaReserva.repaint();
					nuevaReserva.pack();
					/*nuevaReserva.getContentPane().removeAll();
					nuevaReserva.repaint();
					JPanel datos = new DatosReserva(ctrl, listado);
					nuevaReserva.add(datos);
					nuevaReserva.pack();*/
				}
			}
		});
		seleccionar = new JButton("Seleccionar");
		seleccionar.setVisible(false);
		seleccionar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pagar.setVisible(true);
			}
		});
		inferior.add(seleccionar);
		
		pagar = new JButton("Pagar");
		pagar.setVisible(false);
		pagar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(viaje.gethoraSalida().equals("") || viaje.gethoraSalida().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un viaje entre los disponibles"); 
				}
				else {				
					boolean seg = seguro.isSelected();
					int equip = Integer.parseInt(equipajer.getText());
					String prec = viaje.getPrecio();
					int result = Integer.parseInt(prec);
					int nper = Integer.parseInt(npersonasr.getValue().toString());
					
					//Para calcular el total del importe
					int total = ControladorTransporte.getInstancia().importeTotal(seg, equip, result, nper);
					//Se procede a crear una nueva reserva
					reserva = ControladorTransporte.getInstancia().nuevaReserva(viaje, total, equip, seg);
					
					new PagarReserva(viaje,reserva);
					pagar.setEnabled(false);
					confirmar.setVisible(true);
				}
				
			}
		});
		inferior.add(pagar);
		
		confirmar = new JButton("Confirmar");
		confirmar.setVisible(false);
		confirmar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(reserva.getidPersona()==0){ //Si no hay datos personales / pago introducidos
					JOptionPane.showMessageDialog(null, "No se puede confirmar el viaje porque no ha introducido los datos personales y de pago"); 
					pagar.setEnabled(true);
				}
				else {
					ControladorTransporte.getInstancia().guardarReserva(reserva, viaje); //Se guarda la reserva en el fichero de reservas
					nuevaReserva.getContentPane().removeAll();
					nuevaReserva.repaint();
					JPanel confirmacion = new JPanel();
					confirmacion.setLayout(new FlowLayout());
					paginaconf = new JLabel("Su reserva ha sido confirmada con éxito. El código asignado es: ");
					confirmacion.add(paginaconf);
					codigoreserva = new JTextField();
					codigoreserva.setText(reserva.getIdReserva());
					codigoreserva.setEditable(false);
					confirmacion.add(codigoreserva);
					
					
					pagprincipal = new JButton("Página Principal");
					pagprincipal.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							nuevaReserva.setVisible(false);
							new GUITransporteImp();
						}
					});
					
					confirmacion.add(pagprincipal);
					//Se añade todo al panel principal
					nuevaReserva.add(confirmacion);
					nuevaReserva.pack();
				}
			}
		});
		inferior.add(confirmar);
		
		panel.add(inferior, BorderLayout.SOUTH);
		nuevaReserva.add(panel);
		nuevaReserva.setVisible(true);
		nuevaReserva.pack();
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

	public void actionPerformed(ActionEvent e) {
		
	}
	
	/*public void actualizar(int evento, Viaje viaje){
		switch (evento) {
			case (Eventos.VIAJE_UPDATE_GUI): {
				origenr.setText(viaje.get)
			}
		}
	}*/
	
}
