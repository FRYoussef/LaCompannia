package negocio.transfers;


public class Cliente extends Persona{
	private static final long serialVersionUID = -1522502191106147303L;
	private boolean comensal;

	public boolean isComensal() {
		return comensal;
	}

	public void setComensal(boolean comensal) {
		this.comensal = comensal;
	}
	
}
