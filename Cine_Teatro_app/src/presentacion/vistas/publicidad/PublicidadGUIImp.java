package presentacion.vistas.publicidad;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PublicidadGUIImp extends PublicidadGUI
{
	private JFrame mainWindow;
	private JPanel mainPanel;
	private JLabel title, busqueda, modDatos;
	private JButton bEmpresa, bServicio, updateDatos, addEmpresa, inicio;
	public PublicidadGUIImp()
	{
		/*JFrame*/
		mainWindow = new JFrame();
		/*JPanel*/
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		/*JButtons*/
		bEmpresa = new JButton("<html>Busqueda<p>empresa</html>", null);
		bServicio = new JButton("<html>Busqueda<p>servicio</html>", null);
		updateDatos = new JButton("<html>Actualizar<p>datos</html>", null);
		addEmpresa = new JButton("<html>Añadir<p>empresa</html>", null);
		inicio = new JButton("<html>Menu<p>Principal</html>", null);
		/*JLabels*/
		title = new JLabel("Contratacion de Publicidad");
		busqueda = new JLabel("Acciones Usuario");
		modDatos = new JLabel("Acciones de gestion (admin)");
	}
	
	public void ejecutar()
	{
		mainWindow.setAlwaysOnTop(true);
		mainWindow.setResizable(false);
		mainWindow.setSize(1000, 900);
		mainPanel.add(title);
		title.setSize(new Dimension(1000, 200));
		title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		mainPanel.add(busqueda);
		busqueda.setSize(new Dimension(1000, 200));
		busqueda.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		mainPanel.add(bEmpresa);
		mainPanel.add(bServicio);
		mainPanel.add(modDatos);
		modDatos.setSize(new Dimension(1000, 200));
		modDatos.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		mainPanel.add(updateDatos);
		mainPanel.add(addEmpresa);
		mainPanel.add(inicio);
		mainWindow.add(mainPanel);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}
}
