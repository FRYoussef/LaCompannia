package presentacion.vistas.reserva;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.transfers.Cliente;
import negocio.transfers.DatosBancarios;

/***************************************************************************************************
 * Fichero		: PanelPago.java
 *
 * Descripcion	: Clase JPanel para visualizar y recojer la informacion de pago del cliente
 *
 * Autor		: Daniel Alfaro Miranda
 **************************************************************************************************/
public class PanelPago extends JPanel{
	private static final long serialVersionUID = 3884114118277576308L;
	private JTextField tfNombre, tfApellidos, tfPais, tfCiudad, tfCalle, tfTelef, tfEmail, tfCodPos;
	private JTextField tfNumTarjeta, tfNombreTarjeta, tfPaisCuenta;
	private JRadioButton rButtTarjeta, rButtPaypal;
	
	private Cliente actualCli; //Cliente visualizado 
	
	//Expresion regular para parsear el email
	private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public PanelPago() {
		initGUI();
		rButtPaypal.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				boolean visible = e.getStateChange() != ItemEvent.SELECTED;
				tfNumTarjeta.setEditable(visible);
				tfNombreTarjeta.setEditable(visible);	
				tfPaisCuenta.setVisible(visible);
			}
		});
	}
	
	public void setCliente(Cliente cli) {
		actualCli = cli;
		tfNombre.setText(cli.getNombre());
		tfNombre.setEditable(false);
		tfApellidos.setText(cli.getApellidos());
		tfApellidos.setEditable(false);
		tfPais.setText(cli.getPais());
		tfCiudad.setText(cli.getCiudad());
		tfCalle.setText(cli.getCalle());
		tfTelef.setText(Integer.toString(cli.getTelefono()));
		tfEmail.setText(cli.getEmail());
		tfCodPos.setText(cli.getCodPostal());
		if(cli.getDatosBancarios() != null) {
			tfNumTarjeta.setText(cli.getDatosBancarios().getNumCuenta());
			tfNombreTarjeta.setText(cli.getDatosBancarios().getNombreCuenta());
			tfPaisCuenta.setText(cli.getDatosBancarios().getPaisCuenta());
		}
	}
	
	
	public Cliente getDatos() {
		if(comprobarDatos()) {
			actualCli.setNombre(tfNombre.getText().trim());
			actualCli.setApellidos(tfApellidos.getText().trim());
			actualCli.setPais(tfPais.getText().trim());
			actualCli.setCiudad(tfCiudad.getText().trim());
			actualCli.setCalle(tfCalle.getText().trim());
			actualCli.setTelefono(Integer.parseInt(tfTelef.getText().trim()));
			actualCli.setEmail(tfEmail.getText().trim());
			actualCli.setCodPostal(tfCodPos.getText().trim());
			if(rButtTarjeta.isSelected()) {
				DatosBancarios dBank = (actualCli.getDatosBancarios() == null) ? new DatosBancarios() : actualCli.getDatosBancarios();
				dBank.setNombreCuenta(tfNombreTarjeta.getText().trim());
				dBank.setNumCuenta(tfNumTarjeta.getText().trim());
				dBank.setPaisCuenta(tfPaisCuenta.getText().trim());
				actualCli.setDatosBancarios(dBank);
			}
			return actualCli;
		}
		return null;
	}
	
	//Comprobacion de los datos del formulario mediante expresiones regulares
	private boolean comprobarDatos() {
		if(tfNombre.getText().matches(".*\\d.*")) return false;
		if(tfApellidos.getText().matches(".*\\d.*")) return false;
		if(tfPais.getText().matches(".*\\d.*")) return false;
		if(tfCiudad.getText().matches(".*\\d.*")) return false;
		if(tfTelef.getText().matches(".*\\D.*")) return false;
		if(!EMAIL_REGEX.matcher(tfEmail.getText().trim()).find()) return false;
		if(!tfCodPos.getText().matches("\\s*\\d{5}\\s*")) return false;
		if(rButtTarjeta.isSelected()) {
			if(!tfNumTarjeta.getText().matches("\\s*\\d{16}\\s*")) return false;
			if(tfNombreTarjeta.getText().matches(".*\\d.*")) return false;
			if(tfPaisCuenta.getText().matches(".*\\d.*")) return false;
		}
		return true;
	}
	
	public boolean isPaypalSelected() {
		return rButtPaypal.isSelected();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Constantes
		Font fTittle = new Font("", Font.BOLD,15);
		Font fSubTittle = new Font("", Font.BOLD, 13);
		Dimension dTittle = new Dimension(470, 20);
		Color tittleColor = new Color(124, 255, 203);
		
		//Panel de titulo cliente
		JPanel pTittleCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTittleCliente.setPreferredSize(dTittle);
		pTittleCliente.setBackground(tittleColor);
		
		JLabel labCliente = new JLabel("Datos de Cliente: ");
		labCliente.setFont(fTittle);
		
		pTittleCliente.add(labCliente);
		this.add(pTittleCliente);
		
		
		//Panel de formulario de datos de cliente
		JPanel pDatCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pDatCliente.setPreferredSize(new Dimension(470, 110));
		pDatCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pDatCliente.add(new JLabel("Nombre: "));
		tfNombre = new JTextField();
		tfNombre.setPreferredSize(new Dimension(130, 25));
		pDatCliente.add(tfNombre);
		
		pDatCliente.add(new JLabel("Apellidos: "));
		tfApellidos = new JTextField();
		tfApellidos.setPreferredSize(new Dimension(210, 25));
		pDatCliente.add(tfApellidos);
		
		pDatCliente.add(new JLabel("Pais:  "));
		tfPais = new JTextField();
		tfPais.setPreferredSize(new Dimension(176, 25));
		pDatCliente.add(tfPais);
		
		pDatCliente.add(new JLabel("Ciudad: "));
		tfCiudad = new JTextField();
		tfCiudad.setPreferredSize(new Dimension(196, 25));
		pDatCliente.add(tfCiudad);
		
		pDatCliente.add(new JLabel("Calle: "));
		tfCalle = new JTextField();
		tfCalle.setPreferredSize(new Dimension(136, 25));
		pDatCliente.add(tfCalle);
		
		pDatCliente.add(new JLabel("Cod. Postal: "));
		tfCodPos = new JTextField();
		tfCodPos.setPreferredSize(new Dimension(60, 25));
		pDatCliente.add(tfCodPos);
		
		pDatCliente.add(new JLabel("Telefono: "));
		tfTelef = new JTextField();
		tfTelef.setPreferredSize(new Dimension(81, 25));
		pDatCliente.add(tfTelef);
		
		pDatCliente.add(new JLabel("Email: "));
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(290, 25));
		pDatCliente.add(tfEmail);

		this.add(pDatCliente);
		
		//Panel de titulo de datos de pago
		JPanel pTittlePago = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTittlePago.setPreferredSize(dTittle);
		pTittlePago.setBackground(tittleColor);
		
		JLabel labPago = new JLabel("Datos de Pago: ");
		labPago.setFont(fTittle);
		
		pTittlePago.add(labPago);	
		this.add(pTittlePago);
		
		//Panel de formulario de datos de pago
		JPanel pDatPago = new JPanel();
		pDatPago.setLayout(new BoxLayout(pDatPago, BoxLayout.Y_AXIS));
		
		//Panel para el radio button de pago con tarjeta
		JPanel pRButtTarjeta = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pRButtTarjeta.setPreferredSize(dTittle);
		pRButtTarjeta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		rButtTarjeta = new JRadioButton("Pago con tarjeta");
		rButtTarjeta.setFont(fSubTittle);
		pRButtTarjeta.add(rButtTarjeta);
		
		//Panel para el formulario del numero de tarjeta
		JPanel pNumTarjeta = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pNumTarjeta.setPreferredSize(new Dimension(470, 20));
		
		tfNumTarjeta = new JTextField();
		tfNumTarjeta.setPreferredSize(new Dimension(355, 25));
		pNumTarjeta.add(new JLabel("Num. Tarjeta: "));
		pNumTarjeta.add(tfNumTarjeta);
		
		//Panel para el formulario del nombre de tarjeta
		JPanel pNombreTarjeta = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pNombreTarjeta.setPreferredSize(new Dimension(470, 20));
		
		tfNombreTarjeta = new JTextField();
		tfNombreTarjeta.setPreferredSize(new Dimension(320, 25));
		pNombreTarjeta.add(new JLabel("Nombre : "));
		pNombreTarjeta.add(tfNombreTarjeta);
		
		JPanel pPaisCuenta = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pPaisCuenta.setPreferredSize(new Dimension(470, 20));
		
		tfPaisCuenta = new JTextField();
		tfPaisCuenta.setPreferredSize(new Dimension(170, 25));
		pPaisCuenta.add(new JLabel("Pais de cuenta: "));
		pPaisCuenta.add(tfPaisCuenta);
		
		
		//Panel para el radio button de pago por paypal
		JPanel pRButtPaypal = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pRButtPaypal.setPreferredSize(dTittle);
		pRButtPaypal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		rButtPaypal = new JRadioButton("Paypal");
		rButtPaypal.setFont(fSubTittle);
		pRButtPaypal.add(rButtPaypal);
		
		//Panel para dejar espacio con el final
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(470, 65));

		//Grupo de botones de los radio button
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(rButtTarjeta);
		bGroup.add(rButtPaypal);
		rButtTarjeta.setSelected(true);
		
		pDatPago.add(pRButtTarjeta);
		pDatPago.add(pNumTarjeta);
		pDatPago.add(pNombreTarjeta);
		pDatPago.add(pRButtPaypal);
		
		this.add(pDatPago);
		this.add(space);
	}
}
