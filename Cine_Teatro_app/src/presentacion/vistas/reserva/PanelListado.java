package presentacion.vistas.reserva;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.reservas.FiltroBusquedaLugar;
import negocio.reservas.IntervaloTiempo;
import negocio.transfers.Lugar;
import presentacion.controladores.reservas.ControladorReserva;

@SuppressWarnings("serial")
public class PanelListado extends JPanel{
	
	private JTextField tfFechaIni, tfFechaFin, tfPrecioMin, tfPrecioMax, tfCiudad; 
	private JButton butBuscar;
	private JPanel pLugares;
	private Consumer<ActionEvent> reserveButtonAction;
	public static final String reserveButtonActionCommand = CardLayoutInterface.advanceButtonActionCommand;
	private Lugar lugarSeleccionado;
	
	public PanelListado(CardLayoutInterface father) {
		lugarSeleccionado = null;
		initGUI();
		butBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateLugares(pLugares);
			}
		});
		reserveButtonAction = new Consumer<ActionEvent>() {
			@Override
			public void accept(ActionEvent arg0) {
				father.advanceButtonAction(arg0);
			}
		};
	}
	
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		this.add(createFilterPanel(new Color(124, 255, 203)), BorderLayout.PAGE_START);
		
		pLugares = new JPanel();
		pLugares.setLayout(new BoxLayout(pLugares, BoxLayout.Y_AXIS));
		updateLugares(pLugares);

		JScrollPane scroll = new JScrollPane(pLugares, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll, BorderLayout.CENTER);
		
	}
	
	private void updateLugares(JPanel container) {
		try {
			ArrayList<Lugar> lugares = ControladorReserva.getInstancia().listarLugaresDisponibles(getFiltro());
			if(lugares == null) 
				JOptionPane.showMessageDialog(this, "Ocurrio un error en la carga de lugares", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				for(Component comp : container.getComponents()) 
					comp.setVisible(false);
				container.removeAll();
				if(lugares.size() > 0) {
					for(Lugar lugar : lugares) 
						container.add(new PanelLugar(this, lugar));
				}
				else container.add(createPanelInfo("No hay lugares disponibles para los filtros indicados, pruebe otra consulta"));
				container.add(Box.createVerticalGlue());
			}
		}
		catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	private JPanel createFilterPanel(Color color) {
		JPanel pFiltrado = new JPanel();
		pFiltrado.setLayout(new BoxLayout(pFiltrado, BoxLayout.X_AXIS));
		
		//Panel con las fechas
		JPanel pFechas = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Date actualDate = new Date();
		tfFechaIni = new JTextField(IntervaloTiempo.formatDate(actualDate));
		tfFechaIni.setEditable(true);
		tfFechaFin = new JTextField(IntervaloTiempo.formatDate(IntervaloTiempo.addDayToDate(actualDate)));
		tfFechaFin.setEditable(true);
		pFechas.add(new JLabel("Fechas: "));
		pFechas.add(tfFechaIni);
		pFechas.add(new JLabel(" hasta "));
		pFechas.add(tfFechaFin);
		pFechas.setBackground(color);
		
		//Panel con los precios
		JPanel pDinero = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfPrecioMin = new JTextField("0");
		tfPrecioMin.setEditable(true);
		tfPrecioMin.setPreferredSize(new Dimension(70, 20));
		tfPrecioMax = new JTextField("1000");
		tfPrecioMax.setEditable(true);
		tfPrecioMax.setPreferredSize(new Dimension(70, 20));
		pDinero.add(new JLabel("Precio:  "));
		pDinero.add(tfPrecioMin);
		pDinero.add(new JLabel("hasta "));
		pDinero.add(tfPrecioMax);
		pDinero.setBackground(color);
		
		//Panel con la ciudad
		JPanel pCiudad = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfCiudad = new JTextField("");
		tfCiudad.setEditable(true);
		tfCiudad.setPreferredSize(new Dimension(175, 20));
		pCiudad.add(new JLabel("Ciudad: "));
		pCiudad.add(tfCiudad);
		pCiudad.setBackground(color);
		
		//Panel con el boton buscar
		JPanel pBuscar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		butBuscar = new JButton("Buscar");
		pBuscar.add(butBuscar);
		pBuscar.setBackground(color);
		
		//Panel derecho con el de ciudad y el del boton buscar
		JPanel pFiltradoDer = new JPanel();
		pFiltradoDer.setLayout(new BoxLayout(pFiltradoDer, BoxLayout.Y_AXIS));
		pFiltradoDer.add(pCiudad);
		pFiltradoDer.add(pBuscar);
		
		//Panel Izquierdo con el de fechas y el de precios
		JPanel pFiltradoIzqu = new JPanel();
		pFiltradoIzqu.setLayout(new BoxLayout(pFiltradoIzqu, BoxLayout.Y_AXIS));
		pFiltradoIzqu.add(pFechas);
		pFiltradoIzqu.add(pDinero);
		
		pFiltrado.add(pFiltradoIzqu);
		pFiltrado.add(pFiltradoDer);
		return pFiltrado;
	}
	
	private JPanel createPanelInfo(String info) {
		JPanel pInfo = new JPanel(new FlowLayout());
		JTextArea tAreaInfo = new JTextArea(20, 39);
		tAreaInfo.setEditable(false);
		tAreaInfo.setLineWrap(true);
		tAreaInfo.setText(info);
		tAreaInfo.setFont(new Font("", Font.BOLD, 12));
		tAreaInfo.setBackground(this.getBackground());
		pInfo.add(tAreaInfo);
		return pInfo;
	}
	
	
	
	private FiltroBusquedaLugar getFiltro() throws IllegalArgumentException{
		FiltroBusquedaLugar filtro = new FiltroBusquedaLugar();
		IntervaloTiempo interval = IntervaloTiempo.parseInterval(tfFechaIni.getText().trim(), tfFechaFin.getText().trim());
		if(interval == null || interval.getDaysInBetween() == 0) throw new IllegalArgumentException("Fechas invalidas.");
		filtro.setIntervalo(interval);
		filtro.setCiudad(tfCiudad.getText().trim().toLowerCase());
		try {
			filtro.setDineroMax(Integer.parseInt(tfPrecioMax.getText()));
			filtro.setDineroMin(Integer.parseInt(tfPrecioMin.getText()));
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException("Formato de precio invalido.", e);
		}
		return filtro;
	}
	
	public void reservarButtonAction(ActionEvent e) {
		if(IntervaloTiempo.parseInterval(tfFechaIni.getText().trim(), tfFechaFin.getText().trim()) == null) 
			JOptionPane.showMessageDialog(this, "Las fechas de reserva seleccionadas son incorrectas", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		else reserveButtonAction.accept(e);
	}
	
	public void setLugarSeleccionado(Lugar lugar) {
		lugarSeleccionado = lugar;
	}
	
	public Lugar getLugarSeleccionado() {
		return lugarSeleccionado;
	}
	
	public IntervaloTiempo getFechasReserva() {
		return IntervaloTiempo.parseInterval(tfFechaIni.getText().trim(), tfFechaFin.getText().trim());
	}

	
}
