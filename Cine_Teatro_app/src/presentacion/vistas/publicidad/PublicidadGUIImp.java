package presentacion.vistas.publicidad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PublicidadGUIImp extends PublicidadGUI
{
	private JFrame mainWindow;
	private JPanel mainPanel;
	private JLabel title;
	private JButton updateDatos, addEmpresa;
	private BotonesBusqueda search;
	public PublicidadGUIImp()
	{
		/*JFrame*/
		mainWindow = new JFrame();
		/*JPanels*/
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		search = new BotonesBusqueda();		
		/*JButtons*/
		updateDatos = new JButton("<html>Actualizar<p>datos</html>", null);
		addEmpresa = new JButton("<html>Añadir<p>empresa</html>", null);
		/*JLabels*/
		title = new JLabel("Contratacion de Publicidad");
	}
	
	public void ejecutar()
	{
		mainWindow.setResizable(false);
		mainWindow.setSize(1000, 300);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					JOptionPane.showMessageDialog(PublicidadGUIImp.this.mainWindow, "Saliendo del subsistema de publicidad", "Saliendo...", JOptionPane.INFORMATION_MESSAGE);
					PublicidadGUIImp.this.mainWindow.dispose();
					deleteInstance();
				}
			}
		);
		mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		title.setSize(new Dimension(500, 200));
		title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
		
		mainPanel.add(title);
		
		mainPanel.add(search);
		
		mainPanel.add(updateDatos);
		mainPanel.add(addEmpresa);
		
		mainWindow.add(mainPanel);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}
}
