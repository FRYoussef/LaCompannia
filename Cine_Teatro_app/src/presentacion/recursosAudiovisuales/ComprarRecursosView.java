package presentacion.recursosAudiovisuales;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import negocio.recursosAudiovisuales.*;

public class ComprarRecursosView extends JPanel implements View {

	private ComprarRecursosModel model;
	
	public Event btnComprarPressed = new Event();
	public Event btnDetallesPressed = new Event();
	public Event btnOcultarAgotadosPressed = new Event();
	public Event selectedItemIndexChanged = new Event();
	
	private static final long serialVersionUID = 1L;
	private JButton btnComprar;
	private JButton btnDetalles;
	private JTable tablaRecursos;
	private JScrollPane scrollpaneRecursos;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea taDescripcion;
	private boolean detallesEnabled;

	private JButton btnOcultarAgotados;

	public ComprarRecursosView(ComprarRecursosModel model) {
		this.model = model;
		
		setBorder(BorderFactory.createTitledBorder("COMPRAR RECURSOS"));
		
		btnComprar = new JButton("comprar");
		btnDetalles = new JButton("detalles");
		btnOcultarAgotados = new JButton("ocultar agotados");
		
		setLayout(new BorderLayout());
		
		tablaRecursos = new JTable(this.model);
		scrollpaneRecursos = new JScrollPane(tablaRecursos);
		tablaRecursos.setFillsViewportHeight(true);
		
		taDescripcion = new JTextArea();
		scrollPaneDescripcion = new JScrollPane(taDescripcion);
		scrollPaneDescripcion.setBorder(BorderFactory.createTitledBorder("descripcion"));
		scrollPaneDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneDescripcion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scrollpaneRecursos,BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.add(btnComprar);
		panelBotones.add(btnDetalles);
		panelBotones.add(btnOcultarAgotados);
			
		add(panelBotones, BorderLayout.SOUTH);
		
		btnComprar.addActionListener(e -> btnComprarPressed.notifyAllObservers());
		btnDetalles.addActionListener(e -> btnDetallesPressed.notifyAllObservers());
		btnOcultarAgotados.addActionListener(e -> btnOcultarAgotadosPressed.notifyAllObservers());
		tablaRecursos.getSelectionModel().addListSelectionListener((ListSelectionEvent e)-> cambiaBtnComprar(tablaRecursos.getSelectedRow()));
		
		personalizarUI();
	}

	private void cambiaBtnComprar(int index) {
		if(index > -1 && index <= model.getRowCount()) btnComprar.setEnabled(true);
		else btnComprar.setEnabled(false);
	}

	private void personalizarUI() {
		btnComprar.setEnabled(false);
		taDescripcion.setWrapStyleWord(true);
		taDescripcion.setEditable(false);
		taDescripcion.setFont(getFont());
		taDescripcion.setLineWrap(true);
		taDescripcion.setBackground(getBackground());
		tablaRecursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
	}

	@Override
	public void updateView() {
		cambiaBtnComprar(tablaRecursos.getSelectedRow());
		if(model.isFiltroQuitarAgotados()) btnOcultarAgotados.setText("mostrar todos");
		else btnOcultarAgotados.setText("ocultar agotados");
		revalidate();
		repaint();
	}
	
	public void mostrarDetalles() {
		//tablaRecursos.clearSelection();
		if(tablaRecursos.getSelectedRow() < 0) return;
		setDetallesEnabled(true);
		remove(scrollpaneRecursos);
		add(scrollPaneDescripcion);
		taDescripcion.setText("");
		taDescripcion.setText(this.model.getListaRecursos().get(getSelectedItemIndex()).getDescripcion());
		btnDetalles.setText("ocultar detalles");
		revalidate();
		repaint();
	}
	
	public void ocultarDetalles() {
		setDetallesEnabled(false);
		remove(scrollPaneDescripcion);
		add(scrollpaneRecursos);
		btnDetalles.setText("detalles");
		revalidate();
		repaint();
	}

	public boolean isDetallesEnabled() {
		return detallesEnabled;
	}

	public void setDetallesEnabled(boolean detallesEnabled) {
		this.detallesEnabled = detallesEnabled;
	}

	public int getSelectedItemIndex() {
		return tablaRecursos.getSelectedRow();
	}

	public void mostrarMensajeCompra() {
		JOptionPane.showMessageDialog(this, "Comprado con exito, puedes ver el recurso en tus recursos \nse suponen datos bancarios correctos");
	}

	public void mostrarMensajeAgotado() {
		JOptionPane.showMessageDialog(this, "no existen mas unidades de este recurso audiovisual", "ERROR" , JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensajeNoSeleccion() {
		JOptionPane.showMessageDialog(this, "selecciona un recurso primero", "ERROR" , JOptionPane.ERROR_MESSAGE);
	}
}
