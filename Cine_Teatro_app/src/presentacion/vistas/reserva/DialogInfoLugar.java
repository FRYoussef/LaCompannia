package presentacion.vistas.reserva;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.transfers.Lugar;

/***************************************************************************************************
 * Fichero		: DialogInfoLugar.java
 *
 * Descripcion	: Clase JDialog que muestra la informacion en detalle de un Lugar
 *
 * Autor		: Daniel Alfaro Miranda
 **************************************************************************************************/
public class DialogInfoLugar extends JDialog {
	private static final long serialVersionUID = 6064718639251535240L;
	private JButton okButton;
	
	public DialogInfoLugar(Frame frame, Lugar lug) {
		super(frame, "Información de Lugar", true);
		initGUI(lug);
	}
	
	private void initGUI(Lugar lug) {
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		//Panel con el titulo
		JPanel pTittle = new JPanel(new FlowLayout());
		JLabel tittle = new JLabel(lug.getNombre());
		tittle.setFont(new Font("", Font.BOLD, 16));
		pTittle.add(tittle);
		this.add(pTittle, BorderLayout.NORTH);
		
		//Panel con la descripcion
		JPanel pDesc = new JPanel(new FlowLayout());
		pDesc.setPreferredSize(new Dimension(500, 150));
		JTextArea txAreaDesc = new JTextArea(10, 43);
		txAreaDesc.setEditable(false);
		txAreaDesc.setLineWrap(true);
		txAreaDesc.setWrapStyleWord(true);
		txAreaDesc.setAlignmentX(JTextArea.RIGHT_ALIGNMENT);
		txAreaDesc.setText("---DESCRIPCION: \n" + lug.getDescripcion());
		JScrollPane scroll = new JScrollPane(txAreaDesc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pDesc.add(scroll);
		
		//Panel con la informacion del lugar
		JPanel pInfo = new JPanel(new BorderLayout());
		pInfo.setPreferredSize(new Dimension(350, 90));
		JTextField labAforo = new JTextField(Integer.toString(lug.getAforo()));
		labAforo.setEditable(false);
		labAforo.setPreferredSize(new Dimension(60, 20));
		JTextField labPrecioMes = new JTextField(new DecimalFormat("#,##0.00").format(lug.getTarifa()) + " €/dia");
		labPrecioMes.setEditable(false);
		labPrecioMes.setPreferredSize(new Dimension(110, 20));
		JTextField labCalle = new JTextField(lug.getCalle());
		labCalle.setEditable(false);
		labCalle.setPreferredSize(new Dimension(320, 20));
		JTextField labCiudad = new JTextField(lug.getCiudad());
		labCiudad.setEditable(false);
		labCiudad.setPreferredSize(new Dimension(160, 20));
		JTextField labComAut = new JTextField(lug.getComAutonoma());
		labComAut.setEditable(false);
		labComAut.setPreferredSize(new Dimension(160, 20));
		
		JPanel pInfoSup = new JPanel(new FlowLayout());
		pInfoSup.add(new JLabel("Aforo: "));
		pInfoSup.add(labAforo);
		pInfoSup.add(new JLabel("Precio/Mes: "));
		pInfoSup.add(labPrecioMes);
		pInfo.add(pInfoSup, BorderLayout.NORTH);
		
		JPanel pInfoMid = new JPanel(new FlowLayout());
		pInfoMid.add(new JLabel("Calle: "));
		pInfoMid.add(labCalle);
		pInfo.add(pInfoMid, BorderLayout.CENTER);
		
		JPanel pInfoLow = new JPanel(new FlowLayout());
		pInfoLow.add(new JLabel("Com. Autonoma: "));
		pInfoLow.add(labComAut);
		pInfoLow.add(new JLabel("Ciudad: "));
		pInfoLow.add(labCiudad);
		pInfo.add(pInfoLow, BorderLayout.SOUTH);
		
		//Panel con la foto (Ampliable a mas de una en un futuro)
		JPanel pFoto = new JPanel(new FlowLayout());
		pFoto.setPreferredSize(new Dimension(350, 200));
		try {
			pFoto.add(new JLabel(new ImageIcon(resize(ImageIO.read(new File(lug.getPathFoto())), 460, 250))));
		} catch (IOException e) {
			pFoto.add(new JLabel("Fotos no disponibles"));
		}
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		pCenter.add(pDesc);
		pCenter.add(pInfo);
		pCenter.add(pFoto);
		this.add(pCenter, BorderLayout.CENTER);
		
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogInfoLugar.this.setVisible(false);
			}
		});
		
		JPanel pButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pButton.add(okButton);
		
		this.add(pButton, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	//Metodo para cambiarle el tamaño a una imagen a los parametros dados
	private static BufferedImage resize(BufferedImage image, int width, int height) { 
	    BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
	    g2d.dispose();
	    return dimg;
	}
	
	
}
