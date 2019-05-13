package presentacion.vistas.obras;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import negocio.obras.ObrasSA;
import negocio.transfers.Obra;
import presentacion.vistas.GUI;

public class ObrasImpGUI implements GUI {
	public static final int CODE = 5;

	private JPanel mainPanel;// quitar
	private JButton addButton;
	private JButton searchButton;
	private ObrasSA gestor;
	private boolean hayObras;

	@Override
	public void ejecutar() {
		this.gestor = new ObrasSA();
		hayObras = false;
		JFrame pantIni = new JFrame();
		try {
			List<Obra> obras = gestor.loadObras();
			hayObras = true;
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(pantIni, "Aun no existen obras.", "Error 404",
			// 0);
		}

		pantIni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pantIni.setBounds(450, 200, 450, 600);
		Dimension d = new Dimension(300, 300);
		pantIni.setMinimumSize(d);
		JPanel globalPanel = new JPanel(new BorderLayout());
		pantIni.setContentPane(globalPanel);
		JPanel menu = panelPrincipal();
		pantIni.add(menu, BorderLayout.PAGE_START);
		pantIni.add(panelSecundario(), BorderLayout.PAGE_END);

		pantIni.setVisible(true);
		pantIni.pack();
	}

	protected JPanel panelPrincipal() {
		JPanel panel = new JPanel();
		this.searchButton = createSearchButton();
		panel.add(this.searchButton);
		this.addButton = createAddButton();
		panel.add(this.addButton);

		return panel;
	}

	protected JButton createSearchButton() {
		JButton search = new JButton();
		search.setBounds(5, 5, 5, 5);
		search.setToolTipText("Buscar Obras disponibles");
		search.setIcon(new ImageIcon("src/resources/search.png"));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearBuscador();
			}
		});
		return search;
	}

	protected JButton createAddButton() {
		JButton add = new JButton();
		add.setBounds(30, 5, 5, 5);
		add.setToolTipText("Añadir nueva Obra");
		add.setIcon(new ImageIcon("src/resources/+.png"));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnnadirObrasGUI pantallaAdd = new AnnadirObrasGUI(ObrasImpGUI.this.gestor);
			}
		});
		return add;
	}

	protected JOptionPane crearBuscador() {
		JOptionPane busca = new JOptionPane();
		String[] possibilities = null;// ObrasSA;
		int i = 0;
		try {
			//List<Obra> obras = this.gestor.getObras();
			for (Obra o : this.gestor.getObras()) {
				possibilities[i] = o.getTitulo();
				i++;
			}
			String n = (String) JOptionPane.showInputDialog(this.mainPanel, 
					"Selecciona una obra:",
					"Obras disponibles", 
					JOptionPane.INFORMATION_MESSAGE, 
					null, 
					possibilities, 
					possibilities[0]);
			JOptionPane.showInternalMessageDialog(busca, "Aun no se puede consultar la obra: " + n, "FUNCIONALIDAD NO IMPLEMENTADA", 1);
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(busca, "No existe ninguna obra en la base de datos actualmente", "AVISO", 2);
		}		
		return busca;
	}

	
	protected JPanel panelSecundario() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		if (this.hayObras) {
			for (Obra o : this.gestor.getObras()) {
				if (o.getDestacada()) {
					JLabel l = new JLabel(o.getTitulo());
					l.setAlignmentX(Component.CENTER_ALIGNMENT);
					panel.add(l);
				}
			}
		} else {
			JLabel no = new JLabel("No hay obras destacadas");
			Font auxFont=no.getFont();
			no.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
			
			no.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(no);
		}

		return panel;
	}
}
