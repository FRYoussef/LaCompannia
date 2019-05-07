package presentacion.vistas.publicidad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BotonesBusqueda extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JButton bEmpresa, bServicio;

	public BotonesBusqueda()
	{
		this.setPreferredSize(new Dimension(1000, 150));
		this.setMinimumSize(new Dimension(1000, 150));
		this.setMaximumSize(new Dimension(1000, 150));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(null, "Búsqueda", TitledBorder.CENTER , TitledBorder.TOP, new Font(Font.MONOSPACED, Font.ITALIC, 20), Color.black));
		
		bEmpresa = new JButton("<html><center>Busqueda<p>empresa</center></html>", null);
		bEmpresa.setMaximumSize(new Dimension(250, 150));
		bEmpresa.setPreferredSize(new Dimension(250, 150));
		bEmpresa.setMinimumSize(new Dimension(250, 150));
		
		bServicio = new JButton("<html><center>Busqueda<p>servicio</center></html>", null);
		bServicio.setMaximumSize(new Dimension(250, 150));
		bServicio.setPreferredSize(new Dimension(250, 150));
		bServicio.setMinimumSize(new Dimension(250, 150));
		
		initGUI();
	}
	
	private void initGUI()
	{
		this.add(Box.createGlue());
		this.add(bEmpresa);
		this.add(Box.createGlue());
		this.add(bServicio);
		this.add(Box.createGlue());
		this.bEmpresa.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(BotonesBusqueda.this, "Busqueda Empresa", "__Temporal__", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		this.bServicio.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(BotonesBusqueda.this, "Busqueda Servicio", "__Temporal__", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		this.setVisible(true);
	}
}
