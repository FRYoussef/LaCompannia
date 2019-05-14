package presentacion.vistas.vestimenta;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import negocio.vestimenta.SAReservaVestimentaImp;
import negocio.vestimenta.TransferVestimenta;

public class ReservaVestimentaImp extends VestimentaGUIImp {

	private JFrame mainWindow;
	private JPanel mainPanel,CenterPanel;
	private JLabel title;
	private JButton busqueda;
	private JTextField campobusqueda;
	private String ID;
	
	public ReservaVestimentaImp(){
		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setSize(1000, 500);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainWindow.add(mainPanel);
		
		title = new JLabel("Busqueda y Reserva de Vestimenta");
		mainPanel.add(new JSeparator());
		mainPanel.add(title,BorderLayout.NORTH);
		CenterPanel =new JPanel();
		CenterPanel.setLayout(new BorderLayout());
		/*mainPanel.add(CenterPanel,BorderLayout.CENTER);*/
		
		
		busqueda = new JButton("Buscar Ropa por Código:");	
		busqueda.setMinimumSize(new Dimension(200,100));
		busqueda.setMaximumSize(new Dimension(200,100));
		mainPanel.add(busqueda,BorderLayout.EAST);
		
		campobusqueda=new JTextField();
		campobusqueda.setMinimumSize(new Dimension(500,100));
		campobusqueda.setMaximumSize(new Dimension(500,100));
		mainPanel.add(campobusqueda,BorderLayout.WEST);
		
		/*busqueda.addActionListener(new ActionListener()
			{
				
				public void actionPerformed(ActionEvent e)
				{
					
					ID=campobusqueda.getText();
					SAReservaVestimentaImp SAR = null;
					SAR.printTransferVestimenta(SAR.BuscarProducto(ID));
					
					
					
				}
			});*/
		
	
		mainPanel.setVisible(true);
		mainWindow.setVisible(true);
	}
	
	@Override
	public void ejecutar() {

	}
}
