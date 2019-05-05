package presentacion.vistas.catering;


import javax.swing.*;

import integracion.catering.DAO_GCImp;
import negocio.catering.TransferProducto_GC;
import negocio.transfers.EstadoPedido;
import presentacion.controladores.catering.Controlador_GC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.List;

public class GUIPedido_GCImp extends CateringImpGUI {
	public static final String codigoProd = "Codigo: ";
	public static final String nombreProd = "  |  Producto: ";
	public static final String separatorCont_Cod = " -  ";
	
	private JFrame marco= null; 
	private JLabel _sel= null;
	private JLabel _confirma= null; 
	private JButton eliminar=null;
	private JTextArea textArea = null;
	private JButton finalizar = null;
	private JButton botonok = null;
	private JButton elegir = null;
	
	public GUIPedido_GCImp()	{
		initGUI();
	}
	
	public void initGUI(){
		marco= new JFrame(" - GESTION DE CATERING - ");
		marco.setMaximumSize(new Dimension(600, 500));
		marco.setMinimumSize(new Dimension(600, 500));
		marco.setPreferredSize(new Dimension(600, 500));
		marco.setAutoRequestFocus(true);
		marco.setLocationRelativeTo(null);
		JPanel panel= new JPanel(new BorderLayout());
		
		JPanel superior= new JPanel();
		superior.setLayout(new BoxLayout(superior, BoxLayout.Y_AXIS));
		_sel = new JLabel("Selecciona los productos que desea agregar pulsandolo en la consulta.");
		_sel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		elegir = new JButton("CONSULTA PRODUCTOS");
		elegir.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		superior.add(new JLabel(" "));
		superior.add(_sel);
		superior.add(new JLabel(" "));
		superior.add(elegir);
		superior.add(new JLabel(" "));
		
		JPanel inf= new JPanel();
		inf.setLayout(new BoxLayout(inf, BoxLayout.Y_AXIS));
		_confirma = new JLabel("Pulsa para confirmar: ");
		_confirma.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		botonok= new JButton("CONFIRMAR");
		botonok.setAlignmentX(JButton.CENTER_ALIGNMENT);
		finalizar= new JButton("Finalizar");
		finalizar.setAlignmentX(JButton.CENTER_ALIGNMENT);
		finalizar.setVisible(false);
		JButton ponerQueja = new JButton("PON QUEJA");
		ponerQueja.setAlignmentX(JButton.CENTER_ALIGNMENT);
		ponerQueja.setVisible(true);
		JButton consultaComensales = new JButton("CONSULTA COMENSALES");
		consultaComensales.setAlignmentX(JButton.CENTER_ALIGNMENT);
		consultaComensales.setVisible(true);
		
		
		inf.add(new JLabel(" "));
		inf.add(_confirma);		
		inf.add(botonok);
		inf.add(new JLabel(" "));
		inf.add(finalizar);
		inf.add(new JLabel(" "));
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(ponerQueja);
		p.add(consultaComensales);
		inf.add(p);
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBorder(BorderFactory.createBevelBorder(0));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(" Aqui estan los productos de tu pedido:");
		JScrollPane area = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		eliminar = new JButton("ELIMINAR PRODUCTO");
		eliminar.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		
		center.add(area);
		center.add(eliminar);
		
		panel.add(superior, BorderLayout.NORTH);
		panel.add(center, BorderLayout.CENTER);
		panel.add(inf, BorderLayout.SOUTH);
		panel.add(new JLabel("  "), BorderLayout.EAST);
		panel.add(new JLabel("  "), BorderLayout.WEST);
		
		marco.getContentPane().add(panel);
		marco.pack();
		marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		marco.setVisible(true);
		
		ponerQueja.addActionListener(new ActionListener()	{ 
			
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(marco, "Caso de Uso no implementado", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		consultaComensales.addActionListener(new ActionListener()	{ 
			
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(marco, "Caso de Uso no implementado", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		elegir.addActionListener(new ActionListener()	{ 
			
			public void actionPerformed(ActionEvent e){
				elegirProductos();
			}
		});
		
		eliminar.addActionListener(new ActionListener()	{ 
			
			public void actionPerformed(ActionEvent e){
				eliminarProducto();
			}
		});
		
		botonok.addActionListener(new ActionListener()	{ 
			
			public void actionPerformed(ActionEvent e){
				eliminar.setVisible(false);
				elegir.setVisible(false);
				confirmarPedido();
			}
		});
		
		finalizar.addActionListener(new ActionListener()	{ 
			
			public void actionPerformed(ActionEvent e){
				finalizarPedido();
				finalizar.setVisible(false);
				_confirma.setVisible(false);
				botonok.setVisible(false);
				elegir.setVisible(false);
				_sel.setText("NO OLVIDE RECOGER SU CODIGO DEL PEDIDO");
			}
		});
		
		marco.addWindowListener(new WindowAdapter() { //Para cerrar el Frame con el aspa.
			public void windowClosing(WindowEvent e) {
				
				Controlador_GC.getInstancia().eliminarGUIPedido_GC();
				marco.dispose();
			}
		});
	}
	
	public void elegirProductos() {
		String prodOption[] = ListToArray(Controlador_GC.getInstancia().productosDisponibles());
		String sel = (String) JOptionPane.showInputDialog(marco, "Selecciona los productos que desea.", "Selector de Productos",
				JOptionPane.INFORMATION_MESSAGE, null, prodOption, prodOption[0]);
		
		if(sel != null) {
			try {
				Controlador_GC.getInstancia().addProducto(Integer.parseInt(sel.substring(codigoProd.length(), codigoProd.length() + DAO_GCImp.tam_Codigo_Producto)));
				textArea.setText(textArea.getText() + "\n     "+ Controlador_GC.getInstancia().cantidadProductos() + separatorCont_Cod + sel);
			}
			catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(marco, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	
	public String[] ListToArray(List<String> list) {
		String[] str = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			str[i] = list.get(i);
		}
		return str;
	}
	
	public String[] ListToCodigoProductoDelPedido(List<TransferProducto_GC> list) {
		String[] str = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			str[i] = String.valueOf(list.get(i).get_codProd());
		}
		return str;
	}
	
	public void eliminarProducto() {
		List<TransferProducto_GC> prod = Controlador_GC.getInstancia().listProductos();
			if(prod.size() > 0) {
				String prodOption[] = ListToCodigoProductoDelPedido(Controlador_GC.getInstancia().listProductos());
				String sel = (String) JOptionPane.showInputDialog(marco, "Seleccione el codigo del producto que desea eliminar", "Eliminar Producto",
						JOptionPane.INFORMATION_MESSAGE, null, prodOption, prodOption[0]);
		
				if(sel != null) {
					TransferProducto_GC p = Controlador_GC.getInstancia().buscarProductoEnPedido(Integer.parseInt(sel));
					if(p != null) {
						Controlador_GC.getInstancia().borrarProducto(p);
						prod = Controlador_GC.getInstancia().listProductos(); //ACTUALIZO LA LISTA
						textArea.setText(" Aqui estan los productos de tu pedido:");
						for(int i = 0; i < prod.size(); i++) {
							int cont = i+1;
							textArea.setText(textArea.getText() + "\n     "+ cont + separatorCont_Cod + codigoProd + prod.get(i).get_codProd() + 
									nombreProd + prod.get(i).get_nombre());
						}
					}
				}	
			}
			else {
				JOptionPane.showMessageDialog(marco, "El pedido no contiene productos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
	}


	@Override
	public void confirmarPedido() {
		if(Controlador_GC.getInstancia().listProductos().size() == 0) {
			String[] opt = {"SI", "NO"};
			int n = JOptionPane.showOptionDialog(marco,"El pedido no contiene productos, ¿Desea cancelarlo?",  "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null,opt, opt[0]);
			if (n==JOptionPane.YES_OPTION) {
				Controlador_GC.getInstancia().cambiarEstado(EstadoPedido.CANCELADO);
				Controlador_GC.getInstancia().eliminarGUIPedido_GC();
				marco.dispose();
			}
			else {
				eliminar.setVisible(true);
				elegir.setVisible(true);
			}
		}
		else {
			Controlador_GC.getInstancia().actualizarCantidadProd();
			_sel.setText("PULSA FINALIZAR PARA PROCEDER AL PAGO.");			
			textArea.setText(textArea.getText() + "\n\n---------------------------------------------------------------------------------");
			textArea.setText(textArea.getText() + "\n" + "RESUMEN DEL PEDIDO:" +"\n");
			textArea.setText(textArea.getText() + "\n   - CANTIDAD DE PRODUCTOS: " + Controlador_GC.getInstancia().cantidadProductos());
			textArea.setText(textArea.getText() + "\n   - PRECIO TOTAL: " + Controlador_GC.getInstancia().calcularPrecioTotal());
			textArea.setText(textArea.getText() + "\n   - PRECIO TOTAL APLICANDO OFERTA: " + Controlador_GC.getInstancia().aplicarOferta());
			finalizar.setVisible(true);
			_confirma.setVisible(false);
			botonok.setVisible(false);
			elegir.setVisible(false);
		}
	}
	
	public void finalizarPedido() { // SUPONGO SIEMPRE QUE LA CUENTA ES CORRECTA
		String data = (String) JOptionPane.showInputDialog(marco,  "Agrega numero de cuenta y clave separados por un espacio.",  "PAGAR PEDIDO",
				JOptionPane.INFORMATION_MESSAGE, null, null, "");
		
		if(data != null && !data.equals("")) {
			if(Controlador_GC.getInstancia().pagar(data)) {
				JOptionPane.showMessageDialog(marco, "         El pedido ha sido pagado correctamente, \nNo olvide el CODIGO que aparece en el RESUMEN del pedido.",
						"PAGADO", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/resources/correcto.jpg"));
				textArea.setText(textArea.getText() + "\n\n---------------------------------------------------------------------------------");
				textArea.setText(textArea.getText() + "\n EL CODIGO DE TU PEDIDO ES EL SIGUIENTE:   " + Controlador_GC.getInstancia().CodidoProducto());	
				textArea.setText(textArea.getText() + "\n---------------------------------------------------------------------------------");
				Controlador_GC.getInstancia().cambiarEstado(EstadoPedido.PAGADO);
			}
			else {
				JOptionPane.showMessageDialog(marco, "Error en la transaccion bancaria", "ERROR", JOptionPane.ERROR_MESSAGE);
				Controlador_GC.getInstancia().cambiarEstado(EstadoPedido.CANCELADO);
				Controlador_GC.getInstancia().eliminarGUIPedido_GC();
				marco.dispose();
			}
		}
		else {
			JOptionPane.showMessageDialog(marco, "Error al introducir la cuenta. \nPEDIDO CANCELADO", "ERROR", JOptionPane.ERROR_MESSAGE);
			Controlador_GC.getInstancia().cambiarEstado(EstadoPedido.CANCELADO);
			Controlador_GC.getInstancia().eliminarGUIPedido_GC();
			marco.dispose();
		}
	}
}