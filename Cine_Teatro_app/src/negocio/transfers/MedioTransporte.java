package negocio.transfers;

public enum MedioTransporte {
	AVION, AUTOBUS, TREN;
	
	//Para buscar un valor de enumeracion por nuestro campo de etiqueta
		public static MedioTransporte valueOfLabel(String label){
			for(MedioTransporte m: values()){
				if(m.name().equals(label)){
					return m;
				}
			}
			return null;
		}
		
		public static String[] getString() {
			String[] val=new String[MedioTransporte.values().length];
			int i = 0;
			for(MedioTransporte m: values())
				val[i++]=m.name();
			return val;
		}
}
