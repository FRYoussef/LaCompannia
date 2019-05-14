package presentacion.vistas.transporte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import negocio.transfers.DatosBancarios;
import negocio.transfers.Persona;
import negocio.transfers.ReservaBillete;
import negocio.transfers.Viaje;

import presentacion.controladores.transporte.ControladorTransporte;

public class PagarReserva  extends JFrame implements ActionListener {
	Persona nuevaP;
	DatosBancarios datosbancarios;
	JFrame pagarReserva;
	JButton cancelar, aceptar, buscar;
	JTextField dnir, nombrer, apellidor, paisr, ciudadr, cpostalr, telefonor, emailr, caller;
	JTextField tarjetar, titularr, paispagor;
	JTextArea total;
	int pagoSeguro=0;
	
	Border b = BorderFactory.createLineBorder(Color.black, 1);
	
	public PagarReserva(Viaje viaje, ReservaBillete reserva)	{
		ejecutar(viaje, reserva);
	}

	private void ejecutar(Viaje viaje, final ReservaBillete reserva) {
		pagarReserva = new JFrame("Pago de Reserva");
		pagarReserva.setPreferredSize(new Dimension(600, 600));
		pagarReserva.setLocation(650, 100);
		
		//*********PANEL DE TITULO
		JPanel panel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("PAGAR RESERVA");
		title.setFont(new Font(getWarningString(), getDefaultCloseOperation(), 20));
		//title.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(title, BorderLayout.NORTH);
		
		//********PANEL DE DATOS PERSONALES/DATOS DE PAGO
		JPanel datos = new JPanel();
		datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
		
		//Resumen de compra
		JPanel resumen = new JPanel();
		resumen.setLayout(new FlowLayout());
		resumen.setBorder((BorderFactory.createTitledBorder(b, "Resumen de Reserva")));
		total = new JTextArea(12, 50);
		total.setEditable(false);
		total.setLineWrap(true);
		total.setWrapStyleWord(true);
		//total.setText("Resumen de Reserva: " + "\n     " + Controlador_GC.getInstancia().cantidadProductos() + separatorCont_Cod + sel);
		if(reserva.getSeguro()){
			pagoSeguro=30;
		}
		total.setText("Nº de personas: " + viaje.getNPersonas() + "\n Origen: " + viaje.getLugarOrigen() + "\n Destino: " + viaje.getLugarDestino() + "\n Fecha de ida: " + viaje.getFechaIda() + " Fecha de vuelta: " + viaje.getFechaVuelta() +  
		"\n Hora salida: "+ viaje.gethoraSalida() + " Hora llegada: "+ viaje.gethoraLlegada() + "\n Precio cada billete: " + viaje.getPrecio() + "€" + "\n Importe por seguro: " + pagoSeguro + "€" + "\n Importe cada pieza Equipaje: 50€" + "\n" + "\n Total: " + reserva.getTotal() + "€\n" + 
		 "Rellene los datos personales y de pago para proceder a confirmar la reserva. Gracias");
		
		resumen.add(total);
		datos.add(resumen);
		
		//Datos Personales
		JPanel datosPersonales = new JPanel();
		datosPersonales.setLayout(new BoxLayout(datosPersonales, BoxLayout.Y_AXIS));
		datosPersonales.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Datos Personales", TitledBorder.LEFT,TitledBorder.TOP));
		
		JPanel datosPersonales1 = new JPanel();
		datosPersonales1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel dni = new JLabel("DNI: ");
		datosPersonales1.add(dni);
		dnir = new JTextField(10);
		datosPersonales1.add(dnir);
		JLabel nombre = new JLabel("Nombre: ");
		datosPersonales1.add(nombre);
		nombrer = new JTextField(10);
		//nombrer.setEnabled(false);
		nombrer.addActionListener(this);
		datosPersonales1.add(nombrer);
		JLabel apellido = new JLabel("Apellidos: ");
		datosPersonales1.add(apellido);
		apellidor = new JTextField(12);
		//apellidor.setEnabled(false);
		apellidor.addActionListener(this);
		datosPersonales1.add(apellidor);
		
		JPanel datosPersonales2 = new JPanel();
		datosPersonales2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel pais = new JLabel("Pais: ");
		datosPersonales2.add(pais);
		paisr = new JTextField(10);
		//paisr.setEnabled(false);
		datosPersonales2.add(paisr);
		JLabel ciudad = new JLabel("Ciudad: ");
		datosPersonales2.add(ciudad);
		ciudadr = new JTextField(12);
		//ciudadr.setEnabled(false);
		datosPersonales2.add(ciudadr);
		JLabel cpostal = new JLabel("Código Postal: ");
		datosPersonales2.add(cpostal);
		cpostalr = new JTextField(8);
		//cpostalr.setEnabled(false);
		datosPersonales2.add(cpostalr);
		
		JPanel datosPersonales3 = new JPanel();
		datosPersonales3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel calle = new JLabel("Calle: ");
		datosPersonales3.add(calle);
		caller = new JTextField(10);
		//caller.setEnabled(false);
		datosPersonales3.add(caller);
		JLabel telefono = new JLabel("Teléfono: ");
		datosPersonales3.add(telefono);
		telefonor = new JTextField(10);
		//telefonor.setEnabled(false);
		datosPersonales3.add(telefonor);
		JLabel email = new JLabel("Email: ");
		datosPersonales3.add(email);
		emailr = new JTextField(13);
		//emailr.setEnabled(false);
		datosPersonales3.add(emailr);
		
		datosPersonales.add(datosPersonales1);
		datosPersonales.add(datosPersonales2);
		datosPersonales.add(datosPersonales3);
		datos.add(datosPersonales);
		
		//Datos de Pago
		JPanel datosPago = new JPanel();
		datosPago.setLayout(new BoxLayout(datosPago, BoxLayout.Y_AXIS));
		datosPago.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Datos de Pago", TitledBorder.LEFT,TitledBorder.TOP));
		
		JPanel datosPago1 = new JPanel();
		datosPago1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel tarjeta = new JLabel("Número de Tarjeta: ");
		datosPago1.add(tarjeta);
		tarjetar = new JTextField(15);
		datosPago1.add(tarjetar);
		JLabel titular = new JLabel("Nombre Titular: ");
		datosPago1.add(titular);
		titularr = new JTextField(15);
		datosPago1.add(titularr);
		
		JPanel datosPago2 = new JPanel();
		datosPago2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel paispago = new JLabel("Pais: ");
		datosPago2.add(paispago);
		paispagor = new JTextField(12);
		datosPago2.add(paispagor);
		
		datosPago.add(datosPago1);
		datosPago.add(datosPago2);
		datos.add(datosPago);
		
		panel.add(datos, BorderLayout.CENTER);
		
		//*******PANEL INFERIOR BOTONES
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buscar = new JButton("Buscar por DNI");
		buscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String dninuevo = dnir.getText();
				nuevaP = ControladorTransporte.getInstancia().nuevaPersona(dninuevo);
				datosbancarios = nuevaP.getDatosBancarios();
				//Se habilitan los JTextField para rellenar el resto de los datos
				String dni = nuevaP.getDni();
				if (dnir.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo DNI está vacío");
				}
				else if (dninuevo.equals(dni)){
					//Se autocompletan los valores
					nombrer.setText(nuevaP.getNombre());
					apellidor.setText(nuevaP.getApellidos());
					paisr.setText(nuevaP.getPais());
					ciudadr.setText(nuevaP.getCiudad());
					caller.setText(nuevaP.getCalle());
					cpostalr.setText(nuevaP.getCodPostal());
					String telf= String.valueOf(nuevaP.getTelefono());
					telefonor.setText(telf);
					emailr.setText(nuevaP.getEmail());
					
					titularr.setText(datosbancarios.getNombreCuenta());
					tarjetar.setText(datosbancarios.getNumCuenta());
					paispagor.setText(datosbancarios.getPaisCuenta());
					
					reserva.setidPersona(nuevaP.getId());
				}
				else {
					JOptionPane.showMessageDialog(null, "La persona no existe");
				}
			}
		});
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pagarReserva.setVisible(false);
			}
		});
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(nombrer.getText().equals("") || apellidor.getText().equals("") || paisr.getText().equals("") || ciudadr.getText().equals("") || caller.getText().equals("") || cpostalr.getText().equals("") || telefonor.getText().equals("") || emailr.getText().equals("") || titularr.getText().equals("") || tarjetar.getText().equals("") || paispagor.getText().equals("")){
					ControladorTransporte.getInstancia().datosVacios(true);
				}
				
				else {
					String dninuevo = dnir.getText();
					nuevaP = ControladorTransporte.getInstancia().nuevaPersona(dninuevo);
					datosbancarios = nuevaP.getDatosBancarios();
					String dni = nuevaP.getDni();
					
					if(!dninuevo.equals(dni)){
						nuevaP.setId(nuevaP.getId());
						//Rellenar campos de persona
						nuevaP.setDni(dninuevo);
						String campo0 = nombrer.getText();
						nuevaP.setNombre(campo0);
						String campo1 = apellidor.getText();
						nuevaP.setApellidos(campo1);
						String campo2 = paisr.getText();
						nuevaP.setPais(campo2);
						String campo3 = ciudadr.getText();
						nuevaP.setCiudad(campo3);
						String campo4 = caller.getText();
						nuevaP.setCalle(campo4);
						String campo5 = cpostalr.getText();
						nuevaP.setCodPostal(campo5);
						int campo6 = Integer.parseInt(telefonor.getText());
						nuevaP.setTelefono(campo6);
						String campo7 = emailr.getText();
						nuevaP.setEmail(campo7);
						
						//Rellenar campos de pago
						String campo8 = titularr.getText();
						datosbancarios.setNombreCuenta(campo8);
						String campo9 = tarjetar.getText();
						datosbancarios.setNumCuenta(campo9);
						String campo10 = paispagor.getText();
						datosbancarios.setPaisCuenta(campo10);
						nuevaP.setDatosBancarios(datosbancarios); //Cambian los datos bancarios de la persona
						reserva.setidPersona(nuevaP.getId()); //Guardar el id de la persona en la reserva.
						ControladorTransporte.getInstancia().guardarPersona(nuevaP);
					}
					else {
						reserva.setidPersona(nuevaP.getId());
					}
					pagarReserva.setVisible(false);
				}
			}
		});
		panelInferior.add(buscar);
		panelInferior.add(cancelar);
		panelInferior.add(aceptar);
		panel.add(panelInferior, BorderLayout.SOUTH);
		
		pagarReserva.add(panel);
		pagarReserva.setVisible(true);
		pagarReserva.pack();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void enabledText(){
		nombrer.setEnabled(true);
		apellidor.setEnabled(true);
		paisr.setEnabled(true);
		ciudadr.setEnabled(true);
		cpostalr.setEnabled(true);
		caller.setEnabled(true);
		telefonor.setEnabled(true);
		emailr.setEnabled(true);
	}

}
