package presentacion.vistas.obras;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.obras.ObrasSA;
import negocio.transfers.Obra;
import negocio.transfers.ObraGenero;


public class AnnadirObrasGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField titulo;
	private JTextField anno;
	private JTextField pais;
	private JTextField genero;
	private JTextArea sinopsis;
	private JCheckBox destaca;
	private ObrasSA obras;
	
	public AnnadirObrasGUI(ObrasSA o) {
		this.obras = o;
		this.Init();
	}
	
	public void Init() {
		//JFrame pantAdd = new JFrame();		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(450, 200, 450, 600);
		Dimension size = new Dimension(600, 600);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setTitle("A�adir Obra");
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		//Titulo
		JPanel tPanel = new JPanel();
		panel.add(tPanel);
		JLabel titulo = new JLabel("Titulo: ");
		this.titulo = new JTextField(20);
		tPanel.add(titulo); tPanel.add(this.titulo);
		
		//A�o		
		JPanel aPanel = new JPanel();
		panel.add(aPanel);
		JLabel anno = new JLabel("A�o: ");
		this.anno = new JTextField(4);
		aPanel.add(anno); aPanel.add(this.anno);
		
		//Pais
		JPanel pPanel = new JPanel();
		panel.add(pPanel);
		JLabel pais= new JLabel("Pa�s: ");
		this.pais = new JTextField(15);
		pPanel.add(pais); pPanel.add(this.pais);
		
		//Genero
		JPanel gPanel = new JPanel();
		panel.add(gPanel);
		JLabel genero = new JLabel("G�nero: ");
		this.genero = new JTextField(10);
		gPanel.add(genero); gPanel.add(this.genero);///////////////////////////////////////////////////////////////////////////
		
		//Destacada		
		JPanel dPanel = new JPanel();
		panel.add(dPanel);
		JLabel destacada = new JLabel("Destacada");
		this.destaca = new JCheckBox();
		dPanel.add(destacada); dPanel.add(this.destaca);
		
		//Sinopsis
		panel.add(addSinopsis());
		
		//Botones
		panel.add(addButtons());
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel addSinopsis() {
		JPanel sPanel = new JPanel();		
		JLabel sinopsis = new JLabel("Sinopsis: ");
		this.sinopsis = new JTextArea(5, 25);		
		this.sinopsis.setLineWrap(true); 
		this.sinopsis.setWrapStyleWord(true);				
		sPanel.add(sinopsis); 
		sPanel.add(new JScrollPane(this.sinopsis));		
		return sPanel;
	}
	
	private JPanel addButtons() {
		JPanel bPanel = new JPanel();
		bPanel.add(botonAceptar());
		bPanel.add(botonCancelar());		
		return bPanel;
	}
	
	private JButton botonAceptar() {
		JButton acp = new JButton("Aceptar");
		acp.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				try {
					String ti = "";
					ti = AnnadirObrasGUI.this.titulo.getText();
					String a =  AnnadirObrasGUI.this.anno.getText();
					int anno = 0;
					anno = Integer.parseInt(a);
					String pais = "";
					pais = AnnadirObrasGUI.this.pais.getText();
					String genero = "";
					genero = AnnadirObrasGUI.this.genero.getText();
					boolean des = false;
					des = AnnadirObrasGUI.this.destaca.isSelected();
					String sinop = "";
					sinop = AnnadirObrasGUI.this.sinopsis.getText();
					ObraGenero g = AnnadirObrasGUI.this.getGenero(genero);
					Obra o = new Obra(ti, pais, anno, g, sinop, des);
					AnnadirObrasGUI.this.obras.addObra(o);
					JOptionPane.showMessageDialog(AnnadirObrasGUI.this, "Obra a�adida a la base de datos correctamente", "Obra", 1);
					AnnadirObrasGUI.this.dispose();
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(AnnadirObrasGUI.this, "Revise los datos", "AVISO", 2);					
				}
				
			}			
		});
		
		return acp;
	}
	
	private ObraGenero getGenero(String g) {
		ObraGenero o;
		switch(g) {
		case "Comedia":
		case "comedia":	
		case "COMEDIA":
			o = ObraGenero.Comedia; break;
		case "Terror": 
		case "terror":
		case "TERROR":
			o = ObraGenero.Terror; break;
		case "Aventuras": 
		case "aventuras":
		case "AVENTURAS":
			o = ObraGenero.Aventuras; break;
		case "Policiaca":
		case "policiaca":
		case "POLICIACA":
			o = ObraGenero.Policiaca; break;
		case "SiFi":
		case "SIFI":
		case "sifi":
			o = ObraGenero.SiFi; break;
		case "Drama":
		case "drama":
		case "DRAMA":
			o = ObraGenero.Drama; break;
		default: o = ObraGenero.Drama;	break;
		}
		
		return o;
	}
	
	private JButton botonCancelar(){
		JButton can = new JButton("Cancelar");
		//boolean close = false;
		can.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {	
				int c = JOptionPane.showConfirmDialog(null, "�Cancelar a�adir la nueva obra?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (c == 0) {
					AnnadirObrasGUI.this.dispose();
				}				
			}			
		});
		
		return can;
	}
	

}