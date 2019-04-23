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
import javax.swing.JPanel;

public class GUIReservaImp extends GUIReserva{
	
	private JFrame frameGui;
	private FrameReservar frameRes;
	@SuppressWarnings("unused")
	private JButton listarLug, listarRes, addLug, listarTodo;

	public GUIReservaImp() {
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
	
	@Override
	public void ejecutar() {
		frameGui.setLayout(new BorderLayout());
		frameGui.setMinimumSize(new Dimension(500, 500));
		frameGui.setResizable(false);
		
		
		JPanel center = new JPanel(new FlowLayout());
		JPanel grid = new JPanel(new GridLayout(2,2,20,20));
		grid.setPreferredSize(new Dimension(400, 400));
		listarLug = addListarLugButton(grid);
		listarRes = addListarResButton(grid);
		addLug = addAddLugButton(grid);
		listarTodo = addListarTodoButton(grid);
		center.add(grid);
		frameGui.add(center, BorderLayout.CENTER);
		
		JPanel north = new JPanel(new FlowLayout());
		north.setPreferredSize(new Dimension(400, 40));
		JLabel title = new JLabel("Menu Reserva Lugar");
		north.setBackground(new Color(124, 255, 203));
		north.setOpaque(true);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 25));
		north.add(title);
		frameGui.add(north, BorderLayout.NORTH);
		
		frameGui.setVisible(true);
		frameGui.pack();
	}
	
	private JButton addListarLugButton(JPanel panel) {
		JButton button = new JButton("<html><font size=5><b>Listar lugares</b>"
                + "<p>disponibles</html>");
		//button.setFont(new Font("", Font.BOLD, 20));
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
	
	private JButton addListarResButton(JPanel panel) {
		JButton button = new JButton("Listar Reservas");
		button.setFont(new Font("", Font.BOLD, 20));
		button.setToolTipText("Lista las reservas del usuario");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(button);
		return button;
	}
	
	private JButton addAddLugButton(JPanel panel) {
		JButton button = new JButton("Añadir Lugar");
		button.setFont(new Font("", Font.BOLD, 20));
		button.setToolTipText("Añadir nuevo lugar al sistema");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(button);
		return button;
	}
	
	private JButton addListarTodoButton(JPanel panel) {
		JButton button = new JButton("<html><font size=5><b>Listar todos</b>"
                + "<p>los lugares</html>");
		button.setToolTipText("Listar todos los lugares guardados");
		//button.setFont(new Font("", Font.BOLD, 20));
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(button);
		return button;
	}

	@Override
	public void onActionEnded() {
		frameGui.setVisible(true);
		frameRes.dispose();
		frameRes = null;
	}

	@Override
	public void exitAction() {
		GUIReservaImp.super.reset();
		//Se cambiara por volver al menu pricipal de subsistemas
		System.exit(0);
	}

}
