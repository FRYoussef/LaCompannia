package presentacion.vistas.reserva;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/***************************************************************************************************
 * Fichero		: GUIReservaImp.java
 *
 * Descripcion	: Clase de vista principal del subsitema que gestiona las interacciones entre el menu del subsistema (frameGui) y
 * 				  los JFrame de los casos de uso (frameRes)
 *
 * Autor		: Daniel Alfaro Miranda
 **************************************************************************************************/
public class GUIReservaImp extends GUIReserva{
	
	private JFrame frameGui;
	private FrameReservar frameRes;
	@SuppressWarnings("unused")
	private JButton listarLug, listarRes, addLug, listarTodo;

	protected GUIReservaImp() {
		frameGui = new JFrame("Menu Subsitema Reserva");
		frameGui.setAutoRequestFocus(true);
		frameGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRes = null;
		
		frameGui.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				GUIReservaImp.this.exitAction();
			}
		});
	}
	
	//Creacion de la pantalla del menu
	@Override
	public void ejecutar() {
		frameGui.setLayout(new BorderLayout());
		frameGui.setMinimumSize(new Dimension(500, 500));
		frameGui.setResizable(false);
		
		//Panel de botonera
		JPanel center = new JPanel(new FlowLayout());
		JPanel grid = new JPanel(new GridLayout(2,2,20,20));
		grid.setPreferredSize(new Dimension(400, 400));
		listarLug = addListarLugButton(grid);
		listarRes = addListarResButton(grid);
		addLug = addAddLugButton(grid);
		listarTodo = addListarTodoButton(grid);
		center.add(grid);
		frameGui.add(center, BorderLayout.CENTER);
		
		//Panel de titulo
		JPanel north = new JPanel(new FlowLayout());
		north.setPreferredSize(new Dimension(400, 40));
		JLabel title = new JLabel("Menu Reserva Lugar");
		north.setBackground(new Color(124, 255, 203));
		north.setOpaque(true);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 25));
		north.add(title);
		frameGui.add(north, BorderLayout.NORTH);
		
		frameGui.setLocationRelativeTo(null);
		frameGui.setVisible(true);
		frameGui.pack();
	}
	
	//CU Reserva general de lugar (SRES_ResLug)
	private JButton addListarLugButton(JPanel panel) {
		JButton button = new JButton("<html><font size=5><b>Listar lugares</b>"
                + "<p>disponibles</html>");
		button.setToolTipText("Lista los lugares de reserva disponibles");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameGui.setVisible(false);
				frameRes = new FrameReservar(GUIReservaImp.this);
			}
		});
		panel.add(button);
		return button;
	}
	
	//CU Listado y anulacion de reservas
	private JButton addListarResButton(JPanel panel) {
		JButton button = new JButton("Listar Reservas");
		button.setFont(new Font("", Font.BOLD, 20));
		button.setToolTipText("Lista las reservas del usuario");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frameGui, "Funcionalidad sin implementar", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(button);
		return button;
	}
	
	//CU Adicion de un nuevo lugar
	private JButton addAddLugButton(JPanel panel) {
		JButton button = new JButton("Añadir Lugar");
		button.setFont(new Font("", Font.BOLD, 20));
		button.setToolTipText("Añadir nuevo lugar al sistema");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frameGui, "Funcionalidad sin implementar", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(button);
		return button;
	}
	
	//CU Listado modificacion y borrado de lugares, solo admin
	private JButton addListarTodoButton(JPanel panel) {
		JButton button = new JButton("<html><font size=5><b>Listar todos</b>"
                + "<p>los lugares</html>");
		button.setToolTipText("Listar todos los lugares guardados");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frameGui, "Funcionalidad sin implementar", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(button);
		return button;
	}

	//Metodo llamado desde los Frame de caso de uso cuando quieren volver al menu
	@Override
	public void onActionEnded() {
		frameGui.setVisible(true);
		frameRes.dispose();
		frameRes = null;
	}

	//Metodo llamado cuando se quiere salir del subsistema
	@Override
	public void exitAction() {
		GUIReservaImp.super.reset();
		if(frameRes != null) {
			frameRes.dispose();
			frameRes = null;
		}
		frameGui.setVisible(false);
		frameGui.dispose();
	}

}
