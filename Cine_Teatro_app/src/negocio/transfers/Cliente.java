package negocio.transfers;


public class Cliente extends Persona{
	private boolean comensal;

	public boolean isComensal() {
		return comensal;
	}

	public void setComensal(boolean comensal) {
		this.comensal = comensal;
	}
	
}
