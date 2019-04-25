package presentacion.recursosAudiovisuales;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import negocio.recursosAudiovisuales.*;

public class ConsultarRecursosView extends JPanel implements View {

	public Event btnDetallesPressed = new Event();
	public Event btnAveriaPressed = new Event();
	public Event btnMontajePressed = new Event();
	public Event btnRetirarPressed = new Event();
	
	private static final long serialVersionUID = 1L;
	private JButton btnDetalles;
	private JButton btnAveria;
	private JButton btnMontaje;
	private JButton btnRetirar;
	private JTable tablaRecursos;
	private JScrollPane scrollpaneRecursos;
	private ConsultarRecursosModel model;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea taDescripcion;

	public ConsultarRecursosView(ConsultarRecursosModel consultarRecursosModel) {
		
		this.model = consultarRecursosModel;
		
		btnDetalles = new JButton("detalles");
		btnAveria = new JButton("averia");
		btnMontaje = new JButton("montaje");
		btnRetirar = new JButton("retirar");
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("CONSULTAR TUS RECURSOS"));
		
		tablaRecursos = new JTable(consultarRecursosModel);
		scrollpaneRecursos = new JScrollPane(tablaRecursos);
		
		taDescripcion = new JTextArea();
		scrollPaneDescripcion = new JScrollPane(taDescripcion);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.add(btnDetalles);
		panelBotones.add(btnAveria);
		panelBotones.add(btnMontaje);
		panelBotones.add(btnRetirar);
		
		add(scrollpaneRecursos,BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		btnDetalles.addActionListener(e -> btnDetallesPressed.notifyAllObservers());
		btnAveria.addActionListener(e -> btnAveriaPressed.notifyAllObservers());
		btnMontaje.addActionListener(e -> btnMontajePressed.notifyAllObservers());
		btnRetirar.addActionListener(e -> btnRetirarPressed.notifyAllObservers());
		tablaRecursos.getSelectionModel().addListSelectionListener((e)->cambiarBotones());
		
		personalizarUI();
		
	}
	
	private void personalizarUI() {
		tablaRecursos.setFillsViewportHeight(true);
		taDescripcion.setWrapStyleWord(true);
		taDescripcion.setEditable(false);
		taDescripcion.setFont(getFont());
		taDescripcion.setLineWrap(true);
		taDescripcion.setBackground(getBackground());
		scrollPaneDescripcion.setBorder(BorderFactory.createTitledBorder("descripcion"));
		scrollPaneDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneDescripcion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		btnDetalles.setEnabled(false);
		btnAveria.setEnabled(false);
		btnMontaje.setEnabled(false);
		btnRetirar.setEnabled(false);	

		personalizarBotonesOperaciones();
	}

	@Override
	public void updateView() {
		cambiarBotones();
		if(model.getDetailsState()) mostrarDetalles();
		else ocultarDetalles();
	}
	
	private void cambiarBotones() {
		int selectedRow = tablaRecursos.getSelectedRow();
		boolean state = (selectedRow  >= 0 && selectedRow < model.getRowCount());
		btnDetalles.setEnabled(state);
		btnAveria.setEnabled(state);
		btnMontaje.setEnabled(state);
		btnRetirar.setEnabled(state);
	}

	private void personalizarBotonesOperaciones() {
		/* expandir automaticamente horizontalmente */
		btnDetalles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		btnAveria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		btnMontaje.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		btnRetirar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		btnAveria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		/* para que salgan centrados en el eje x hay que ponerles un alto minimo */
		btnDetalles.setMinimumSize(new Dimension(0, 20));
		btnAveria.setMinimumSize(new Dimension(0, 20));
		btnMontaje.setMinimumSize(new Dimension(0, 20));
		btnRetirar.setMinimumSize(new Dimension(0, 20));
		btnAveria.setMinimumSize(new Dimension(0, 20));
		/* para que se repartan igual espacio horizontal */
		btnDetalles.setPreferredSize(new Dimension(10,40));
		btnAveria.setPreferredSize(new Dimension(10,40));
		btnMontaje.setPreferredSize(new Dimension(10,40));
		btnRetirar.setPreferredSize(new Dimension(10,40));
		btnAveria.setPreferredSize(new Dimension(10,40));
	}

	public void mostrarDetalles() {
		if(tablaRecursos.getSelectedRow() < 0) return;
		remove(scrollpaneRecursos);
		add(scrollPaneDescripcion);
		taDescripcion.setText("");
		taDescripcion.setText(model.getListaRecursos().get(getSelectedItemIndex()).getDescripcion());
		btnDetalles.setText("ocultar detalles");
		revalidate();
		repaint();
	}
	
	public void ocultarDetalles() {
		remove(scrollPaneDescripcion);
		add(scrollpaneRecursos);
		btnDetalles.setText("detalles");
		revalidate();
		repaint();
	}

	public int getSelectedItemIndex() {
		return tablaRecursos.getSelectedRow();
	}
	
}
