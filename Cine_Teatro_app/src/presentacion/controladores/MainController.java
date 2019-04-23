package presentacion.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import presentacion.vistas.FactoriaGUI;
import presentacion.vistas.GUI;

public class MainController {

    private static final int NUM_FILAS = 2;
    private static final int NUM_COL = 4;


    /**
     * En función del subsistema clickado lanzaremos una GUI
     * @param mouseEvent
     */
    @FXML
    public void onClickSubsistema(MouseEvent mouseEvent) {
        Platform.runLater(()->{
            Node source = (Node)mouseEvent.getSource();
            Integer col = GridPane.getColumnIndex(source);
            col = col == null ? 0: col;
            Integer fila = GridPane.getRowIndex(source);
            fila = fila == null ? 0: fila;
            FactoriaGUI fac = new FactoriaGUI();
            try {
                GUI gui = fac.creaGUI(fila*NUM_COL + col);
                gui.ejecutar();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
