package negocio.transfers;

import javax.swing.ComboBoxModel;

public enum Clase {
	ECONOMICA, BUSINESS;
	
	//Para buscar un valor de enumeracion por nuestro campo de etiqueta
	public static Clase valueOfLabel(String label){
		for(Clase c: values()){
			if(c.name().equals(label)){
				return c;
			}
		}
		return null;
	}
	
	public static String[] getString() {
		String[] val=new String[Clase.values().length];
		int i = 0;
		for(Clase c: values())
			val[i++]=c.name();
		return val;
	}
	
	/*public String clase;

    private Clase(String s) {
        clase = s;
    }
    public String getValue(){
        return clase;
    }*/
}
