package presentacion.recursosAudiovisuales;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.recursosAudiovisuales.RecursosAudiovisualesModel;

public class RecursosAudiovisualesView extends JFrame implements View {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField tfTitle;
	private JButton btnCambiar;
	
	public Event btnCambiarPressed = new Event();
	public Event btnConsultarPressed = new Event();
	public Event btnComprarPressed = new Event();
	public Event btnVolverPressed = new Event();
	public Event btnAveriasPressed = new Event();
	
	private RecursosAudiovisualesModel model;
	private JButton btnComprar;
	private JButton btnConsultar;
	private JButton btnAsignar;
	private JButton btnSolicitar;
	private JButton btnAverias;

	private JPanel mainPanel;

	private JButton btnVolverPrincipal;
	
	public RecursosAudiovisualesView(RecursosAudiovisualesModel mainModel){
		this.model = mainModel;
		
		setupMainPanelComponents();
		((JComponent)getContentPane()).setBorder(BorderFactory.createEmptyBorder());
		setTitle("pagina principal del subsistema");
		setPreferredSize(new Dimension(700,400));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		
		setupHandlers();
		
		personalizarUI();
		
		setVisible(true);
	}
	
	private void personalizarUI() {
		try {
			setIconImage(ImageIO.read(ClassLoader.getSystemResource("resources/icon.png")));
		} catch (Exception e) { e.printStackTrace(); }
	}

	void setupMainPanelComponents(){
		JPanel mainViewOperationsPanel = new JPanel();
		mainViewOperationsPanel.setLayout(new BoxLayout(mainViewOperationsPanel, BoxLayout.X_AXIS));
		mainViewOperationsPanel.setBorder(BorderFactory.createTitledBorder("Operaciones de vista"));
		
		btnComprar = new JButton("comprar recurso");
		btnConsultar = new JButton("consultar recurso");
		btnAsignar = new JButton("asignar a un proyecto");
		btnSolicitar = new JButton("solicitar un recurso");
		btnAverias = new JButton("historial de averias");
		btnVolverPrincipal = new JButton("volver");
		personalizarBotonesOperaciones();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainViewOperationsPanel.add(btnComprar); 
		mainViewOperationsPanel.add(btnConsultar);
		mainViewOperationsPanel.add(btnAverias);
		mainViewOperationsPanel.add(btnAsignar); 
		mainViewOperationsPanel.add(btnSolicitar); 
		
		/* panel de customizacion y interno */
		JPanel customizationPanel = new JPanel();
		customizationPanel.setBorder(BorderFactory.createTitledBorder("Operaciones de personalizacion"));
		btnCambiar = new JButton("cambiar titulo");
		tfTitle = new JTextField(10);
		customizationPanel.add(btnCambiar);
		customizationPanel.add(tfTitle);
		
		mainViewOperationsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		mainPanel.add(mainViewOperationsPanel);
		mainPanel.add(customizationPanel);
		
		mainPanel.setBorder(BorderFactory.createTitledBorder("SUBSISTEMA DE GESTION DE REC AUDIOVISUALES"));
		
		add(mainPanel);
	}

	private void personalizarBotonesOperaciones() {
		/* expandir automaticamente horizontalmente */
		btnComprar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		btnConsultar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		btnAsignar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		btnSolicitar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		btnAverias.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		/* para que salgan centrados en el eje x hay que ponerles un alto minimo */
		btnComprar.setMinimumSize(new Dimension(0, 100));
		btnConsultar.setMinimumSize(new Dimension(0, 100));
		btnAsignar.setMinimumSize(new Dimension(0, 100));
		btnSolicitar.setMinimumSize(new Dimension(0, 100));
		btnAverias.setMinimumSize(new Dimension(0, 100));
		/* para que se repartan igual espacio horizontal */
		btnComprar.setPreferredSize(new Dimension(10,100));
		btnConsultar.setPreferredSize(new Dimension(10,100));
		btnAsignar.setPreferredSize(new Dimension(10,100));
		btnSolicitar.setPreferredSize(new Dimension(10,100));
		btnAverias.setPreferredSize(new Dimension(10,100));
	}

	private void setupHandlers() {
		//adaptador de ActionListener a Event
		btnCambiar.addActionListener(e -> btnCambiarPressed.notifyAllObservers());		
		btnConsultar.addActionListener(e -> btnConsultarPressed.notifyAllObservers());
		btnComprar.addActionListener(e -> btnComprarPressed.notifyAllObservers());
		btnVolverPrincipal.addActionListener(e -> btnVolverPressed.notifyAllObservers());
		btnAverias.addActionListener(e -> btnAveriasPressed.notifyAllObservers());
	}

	@Override
	public void updateView() {
		setTitle(model.getTitle());		
	}

	public String getTextTitle() {
		return tfTitle.getText();		
	}
	
	public void mostrarUI() {
		getContentPane().removeAll();
		add(mainPanel);
		revalidate();
		repaint();
	}
	
	public void ocultarUI() {
		getContentPane().removeAll();
		revalidate();
		repaint();
	}

	public void showView(View view) {
		getContentPane().removeAll();
		add((JComponent)view);
		JPanel tmpPanel = new JPanel();
		tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));
		tmpPanel.add(btnVolverPrincipal);
		btnVolverPrincipal.setAlignmentX(1.0f);
		add(tmpPanel, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

}
