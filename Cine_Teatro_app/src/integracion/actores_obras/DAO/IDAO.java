package integracion.actores_obras.DAO;

import java.util.ArrayList;

public interface IDAO {
    Object buscaId(int id);
    Object buscaNombre(String nombre);
    boolean annade(Object obj);
    boolean borra(int id);
    boolean modifica(Object obj);
    ArrayList listar();
}
