package presentacion.vistas.reserva;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.transfers.Lugar;

@SuppressWarnings("serial")
public class DialogInfoLugar extends JDialog {

	private JButton okButton;
	
	public DialogInfoLugar(Frame frame, Lugar lug) {
		super(frame, "Información de Lugar", true);
		initGUI(lug);
	}
	
	
	private void initGUI(Lugar lug) {
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel pTittle = new JPanel(new FlowLayout());
		JLabel tittle = new JLabel(lug.getNombre());
		tittle.setFont(new Font("", Font.BOLD, 16));
		pTittle.add(tittle);
		this.add(pTittle, BorderLayout.NORTH);
		
		JPanel pDesc = new JPanel(new FlowLayout());
		JTextArea txAreaDesc = new JTextArea(8, 43);
		txAreaDesc.setEditable(false);
		txAreaDesc.setLineWrap(true);
		txAreaDesc.setAlignmentX(JTextArea.RIGHT_ALIGNMENT);
		txAreaDesc.setText("---DESCRIPCION: \n" + lug.getDescripcion());
		pDesc.add(txAreaDesc);
		JScrollPane scroll = new JScrollPane(pDesc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel pInfo = new JPanel(new FlowLayout());
		pInfo.setPreferredSize(new Dimension(350, 50));
		JTextField labAforo = new JTextField(Integer.toString(lug.getAforo()));
		labAforo.setEditable(false);
		labAforo.setPreferredSize(new Dimension(50, 20));
		JTextField labPrecioMes = new JTextField(Float.toString(lug.getTarifa()) + "€/dia");
		labPrecioMes.setEditable(false);
		labPrecioMes.setPreferredSize(new Dimension(120, 20));
		JTextField labCalle = new JTextField(lug.getCalle());
		labCalle.setEditable(false);
		labCalle.setPreferredSize(new Dimension(140, 20));
		JTextField labCiudad = new JTextField(lug.getCiudad());
		labCiudad.setEditable(false);
		labCiudad.setPreferredSize(new Dimension(160, 20));
		JTextField labComAut = new JTextField(lug.getComAutonoma());
		labComAut.setEditable(false);
		labComAut.setPreferredSize(new Dimension(160, 20));
		pInfo.add(new JLabel("Aforo: "));
		pInfo.add(labAforo);
		pInfo.add(new JLabel("Precio/Mes: "));
		pInfo.add(labPrecioMes);
		pInfo.add(new JLabel("Calle: "));
		pInfo.add(labCalle);
		pInfo.add(new JLabel("Ciudad: "));
		pInfo.add(labCiudad);
		pInfo.add(new JLabel("Com. Autonoma: "));
		pInfo.add(labComAut);
		
		JPanel pFoto = new JPanel(new FlowLayout());
		pFoto.setPreferredSize(new Dimension(350, 200));
		try {
			pFoto.add(new JLabel(new ImageIcon(ImageIO.read(new File(lug.getPathFoto())))));
		} catch (IOException e) {
			pFoto.add(new JLabel("Fotos no disponibles"));
		}
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		pCenter.add(scroll);
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
	}
	
	
}
