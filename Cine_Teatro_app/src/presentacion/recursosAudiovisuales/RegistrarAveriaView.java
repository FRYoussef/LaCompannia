package presentacion.recursosAudiovisuales;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import integracion.recursosAudiovisuales.*;
import negocio.recursosAudiovisuales.*;

public class RegistrarAveriaView extends JPanel implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6506197357944836022L;
	private JTextField tfIdRecurso;
	private JTextArea taDescripcion;
	private JComboBox<TipoAveria> cbTipo;
	private JLabel lblIdRecurso;
	private JButton btnRegistrar;
	private JLabel lblDescripcion;
	private JPanel vbrecurso;
	private JPanel rootPanel;
	private RegistrarAveriaModel model;

	public Event btnRegistarPressed = new Event();

	public RegistrarAveriaView(RegistrarAveriaModel registrarAveriaModel) {
		this.model = registrarAveriaModel;
		rootPanel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		lblIdRecurso = new JLabel("id recurso");
		tfIdRecurso = new JTextField(10);
		lblDescripcion = new JLabel("Descripcion");
		taDescripcion = new JTextArea();
		btnRegistrar = new JButton("registrar");
		cbTipo = new JComboBox<TipoAveria>(TipoAveria.values());
		vbrecurso = new JPanel(new FlowLayout(FlowLayout.LEFT));
		vbrecurso.add(lblIdRecurso);
		vbrecurso.add(tfIdRecurso);
		rootPanel.add(vbrecurso);
		rootPanel.add(lblDescripcion);
		rootPanel.add(taDescripcion);
		rootPanel.add(cbTipo);
		rootPanel.add(btnRegistrar);
		personalizarUI();
		add(rootPanel);

		setupHandlers();

		updateView();
	}

	private void setupHandlers() {
		btnRegistrar.addActionListener((e) -> btnRegistarPressed.notifyAllObservers());
	}

	private void personalizarUI() {
		rootPanel.setBorder(BorderFactory.createTitledBorder("Registrar Averia"));
		lblIdRecurso.setAlignmentX(Component.LEFT_ALIGNMENT);
		tfIdRecurso.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblDescripcion.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		lblDescripcion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		taDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnRegistrar.setAlignmentX(Component.LEFT_ALIGNMENT);
		cbTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
		vbrecurso.setAlignmentX(Component.LEFT_ALIGNMENT);
	}

	@Override
	public void updateView() {
		tfIdRecurso.setText(String.valueOf(model.getIdRecurso()));
	}

	public Integer getIdRecurso() {
		int ret = 0;
		try {
			ret = Integer.parseInt(tfIdRecurso.getText());
		} catch (NumberFormatException ex) {
			System.out.println("err");
		}
		return ret;
	}

	public String getDescripcion() {
		return taDescripcion.getText();
	}

	public TipoAveria getTipoAveria() {
		return (TipoAveria) cbTipo.getSelectedItem();
	}

}
