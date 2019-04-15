package presentacion.vistas;

import presentacion.vistas.subsistemasGUI.*;

public class FactoriaGUI {

    public GUI creaGUI(int codigo) throws Exception{
        GUI gui = null;
        switch (codigo){
            case ReservaImpGUI.CODE:
                gui = new ReservaImpGUI();
                break;
            case PublicidadImpGUI.CODE:
                gui = new PublicidadImpGUI();
                break;
            case TransporteImpGUI.CODE:
                gui = new TransporteImpGUI();
                break;
            case CateringImpGUI.CODE:
                gui = new CateringImpGUI();
                break;
            case VestimentaImpGUI.CODE:
                gui = new VestimentaImpGUI();
                break;
            case ObrasImpGUI.CODE:
                gui = new ObrasImpGUI();
                break;
            case ActoresImpGUI.CODE:
                gui = new ActoresImpGUI();
                break;
            case AudiovisualesImpGUI.CODE:
                gui = new AudiovisualesImpGUI();
                break;
            default:
                throw new Exception("Codigo de GUI no valido: " + codigo);
        }
        return gui;
    }
}
