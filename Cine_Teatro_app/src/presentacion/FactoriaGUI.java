package presentacion;


import presentacion.recursosAudiovisuales.RecursosAudiovisuales;
import presentacion.vistas.GUI;
import presentacion.vistas.actores_obras.MainActoresObrasImpGUI;
import presentacion.vistas.audiovisuales.AudiovisualesImpGUI;
import presentacion.vistas.catering.CateringImpGUI;
import presentacion.vistas.obras.ObrasImpGUI;
import presentacion.vistas.publicidad.PublicidadGUI;
import presentacion.vistas.reserva.GUIReserva;
import presentacion.vistas.transporte.TransporteImpGUI;
import presentacion.vistas.vestimenta.VestimentaGUIImp;
import presentacion.vistas.vestimenta.VestimentaImpGUI;

public class FactoriaGUI {


    public GUI creaGUI(int codigo) throws Exception{
        GUI gui = null;
        switch (codigo){
            case GUIReserva.CODE:
                gui = GUIReserva.getInstancia();
                break;
            case PublicidadGUI.CODE:
                gui = PublicidadGUI.getInstance();
                break;
            case TransporteImpGUI.CODE:
                gui = new TransporteImpGUI();
                break;
            case CateringImpGUI.CODE:
                gui = CateringImpGUI.getInstancia();
                break;
            case VestimentaGUIImp.CODE:
                gui = VestimentaGUIImp.getInstance();
                break;
            case ObrasImpGUI.CODE:
                gui = new ObrasImpGUI();
                break;
            case MainActoresObrasImpGUI.CODE:
                gui = new MainActoresObrasImpGUI();
                break;
            case AudiovisualesImpGUI.CODE:
                gui = RecursosAudiovisuales.getInstance();
                break;
            default:
                throw new Exception("Codigo de GUI no valido: " + codigo);
        }
        return gui;
    }
}
