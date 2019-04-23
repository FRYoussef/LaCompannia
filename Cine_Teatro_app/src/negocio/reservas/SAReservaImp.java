package negocio.reservas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import integracion.reservas.DAOLugar;
import integracion.reservas.FactoriaDAO;
import negocio.transfers.Cliente;
import negocio.transfers.DatosBancarios;
import negocio.transfers.Lugar;
import negocio.transfers.Pago;
import negocio.transfers.ReservaLugar;
import negocio.transfers.TipoCompra;
import negocio.transfers.TipoPago;

public class SAReservaImp implements SAReserva {
	@Override
	public ArrayList<Lugar> listarLugaresDisponibles(FiltroBusquedaLugar filter) {
		return FactoriaDAO.getInstancia().nuevoDAOLugar().consultaLugar(filter);
	}

	@Override
	public String getFactura(Lugar lug, IntervaloTiempo fechas, TipoPago tPago, Date fechaIniPago) {
		StringBuilder factura = new StringBuilder();
		String endline = System.lineSeparator();
		factura.append("--ID de Lugar: " + lug.getIdLugar() + endline);
		factura.append("--Nombre de Lugar: " + lug.getNombre() + endline);
		factura.append("--Periodo de reserva: " + fechas + endline);
		factura.append("--Tipo de pago: " + tPago.name() + endline);
		float total = lug.getTarifa() * fechas.getDaysInBetween();
		ArrayList<Float> pagos = calcularPlanPago(total, tPago.getMesesDuracion());
		Date fechaPago = fechaIniPago;
		DecimalFormat fmter = new DecimalFormat("#,##0.00");
		factura.append("--Recibos: " + endline);
		for(Float pago : pagos) {
			factura.append("---" + IntervaloTiempo.formatDate(fechaPago) 
			+ "........." + fmter.format(pago) + "€" + endline);
			fechaPago = IntervaloTiempo.addMonthToDate(fechaPago);
		}
		factura.append("---------------------" + endline + "--TOTAL: " + fmter.format(total) + "€");
		return factura.toString();
	}
	
	private ArrayList<Float> calcularPlanPago(float total, int meses){
		ArrayList<Float> pagos = new ArrayList<Float>();
		if(meses <= 1) pagos.add(total);
		else {
			float primerPago = (float) (total * 0.50); //Primer pago 50% del total
			float subtotal = (total - primerPago) / (meses - 1);
			pagos.add(primerPago);
			for(int i = 0; i < meses - 1; i++) 
				pagos.add(subtotal);
		}
		return pagos;
	}

	@Override
	public Cliente obtenerDatosCliente(String dniCliente) {
		return FactoriaDAO.getInstancia().nuevoDAOCliente().consultaCliente(dniCliente);
	}
	
	@Override
	public boolean actualizarDatosCliente(Cliente tCli) {
		return FactoriaDAO.getInstancia().nuevoDAOCliente().modCliente(tCli);
	}
	

	@Override
	public void reservarLugar(String idLugar, Cliente tCli, IntervaloTiempo fechasReserva, 
			TipoPago tipoPago, boolean paypal) throws IllegalArgumentException {
		
		if(!paypal && !comprobarDatosBancarios(tCli.getDatosBancarios())) 
			throw new IllegalArgumentException("Datos bancarios incorrectos");
		if(!actualizarDatosCliente(tCli)) 
			throw new IllegalArgumentException("Error al actualizar los datos del cliente");
		
		DAOLugar daoLugar = FactoriaDAO.getInstancia().nuevoDAOLugar();
		Lugar lugarSel = daoLugar.consultaLugar(idLugar);
		
		if(lugarSel == null) 
			throw new IllegalArgumentException("Error al cargar el lugar seleccionado"); 
		if(lugarOcupado(lugarSel, fechasReserva)) 
			throw new IllegalArgumentException("El lugar seleccionado ya no esta disponible");
		
		ArrayList<IntervaloTiempo> ocupacion = lugarSel.getOcupacion();
		ocupacion.add(fechasReserva);
		Collections.sort(ocupacion);
		lugarSel.setOcupacion(ocupacion);
		
		if(!daoLugar.modLugar(lugarSel)) 
			throw new IllegalArgumentException("Error al actualizar los datos del lugar");
		
		Pago tPago = crearPagoReserva(tCli.getDni(), lugarSel.getTarifa() * fechasReserva.getDaysInBetween(), tipoPago,
				"Reserva del lugar " + lugarSel.getNombre() + " en fechas " + fechasReserva, new Date()); //fecha de inicio del pago actual
		ReservaLugar tReserva = crearReservaLugar(tPago.getIdCompra(), lugarSel.getIdLugar(), fechasReserva);
		
		if(!FactoriaDAO.getInstancia().nuevoDAOPago().altaPago(tPago)) 
			throw new IllegalArgumentException("Error al añadir el nuevo pago");
		if(!FactoriaDAO.getInstancia().nuevoDAOReservaLugar().altaReserva(tReserva))
			throw new IllegalArgumentException("Error al añadir la nueva reserva"); 
		
		
		realizarCobro(tCli.getDatosBancarios(), tPago.getDineroCobrado(), paypal);
		enviarEmail(FactoriaDAO.getInstancia().nuevoDAOCliente().consultaCliente(lugarSel.getDniPropietario()).getEmail(), 
				"Su lugar " + lugarSel.getNombre() + " ha sido reservado, para mas informacion consulte su cuenta.");
		enviarEmail(tCli.getEmail(), "Su compra se realizo con exito: \n" + getFactura(lugarSel, fechasReserva, tipoPago, new Date()));
		
	}
	
	private boolean comprobarDatosBancarios(DatosBancarios dBank) {
		//Conexion con entidad bancaria para la comprobacion de los datos
		return true;
	}
	
	private void realizarCobro(DatosBancarios dBank, float deuda, boolean paypal) {
		//Conexion con entidad bancaria o paypal para el cobro de la deuda
	}
	
	private void enviarEmail(String email, String info) {
		//Conexion con el servicio de email para el envio
	}
	
	private boolean lugarOcupado(Lugar tLug, IntervaloTiempo fechas) {
		ArrayList<IntervaloTiempo> ocupacion = tLug.getOcupacion();
		for(IntervaloTiempo fechasReservadas : ocupacion) {
			if(fechasReservadas.compareTo(fechas) == 0) return true;
		}
		return false;
	}
	
	private ReservaLugar crearReservaLugar(String idCompra, String idLugar, IntervaloTiempo duracion) {
		ReservaLugar tRes = new ReservaLugar();
		tRes.setIdCompra(idCompra);
		tRes.setIdLugar(idLugar);
		tRes.setFechaIni(duracion.getIni());
		tRes.setFechaFin(duracion.getFin());
		tRes.setTipoCompra(TipoCompra.RESERVA);
		return tRes;
	}
	
	private Pago crearPagoReserva(String dniCliente, float deuda, TipoPago tipoPago, String descripcion, Date fechaInicio) {
		Pago transfPago = new Pago();
		transfPago.setDniCliente(dniCliente);
		transfPago.setDescripcion(descripcion);
		transfPago.setFechaIni(fechaInicio);
		transfPago.setTipoPago(tipoPago);
		transfPago.setIdCompra(getIdCompra(fechaInicio));
		ArrayList<Float> pagos = calcularPlanPago(deuda, tipoPago.getMesesDuracion());
		ArrayList<Date> fechasCobro = new ArrayList<Date>();
		transfPago.setDineroCobrado(pagos.get(0));
		float dineroAdeudado = 0;
		Date fechaCobro = IntervaloTiempo.addMonthToDate(fechaInicio);
		for(int i = 1; i < pagos.size(); ++i) {
			dineroAdeudado += pagos.get(i);
			fechasCobro.add(fechaCobro);
			fechaCobro = IntervaloTiempo.addMonthToDate(fechaCobro);
		}
		transfPago.setDineroAdeudado(dineroAdeudado);
		transfPago.setFechasCobro(fechasCobro);
		return transfPago;
	}
	
	private String getIdCompra(Date fechaInicio) {
		return IntervaloTiempo.formatDate(fechaInicio) + System.currentTimeMillis();
	}

	

}
