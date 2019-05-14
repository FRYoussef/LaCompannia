/**
 * 
 */
package presentacion.controladores.vestimenta;

import negocio.vestimenta.SAReservaVestimentaImp;
/**
 * @author usuario_local
 *
 */
public class Controlador_Reserva {
	SAReservaVestimentaImp SAR;
	public Controlador_Reserva(){}
	public void BuscarProducto(String ID) {
		SAR.BuscarProducto(ID);
	}
	
}
