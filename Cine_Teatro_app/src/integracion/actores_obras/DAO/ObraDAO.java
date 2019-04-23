package integracion.actores_obras.DAO;

import negocio.transfers.Obra;

import java.io.*;
import java.util.ArrayList;

public class ObraDAO implements IDAO {
    private String ruta = new File("src/integracion/actores_obras/archivos/obras.bin").getAbsolutePath();

    @Override
    public Obra buscaId(int id) {
        ArrayList<Obra> alObras = (ArrayList<Obra>) listar();
        for (Obra o : alObras) {
            if(o.getId() == id)
                return o;
        }
        return null;
    }

    @Override
    public Obra buscaNombre(String titulo) {
        ArrayList<Obra> alObras = (ArrayList<Obra>) listar();
        for (Obra o : alObras) {
            if(o.getTitulo().equals(titulo))
                return o;
        }
        return null;
    }

    @Override
    public boolean annade(Object obj) {
        Obra o = (Obra) obj;
        ArrayList<Obra> alObras = (ArrayList<Obra>) listar();
        alObras.add(o);
        return escribeArray(alObras);
    }

    @Override
    public boolean borra(int id) {
        ArrayList<Obra> alObras = (ArrayList<Obra>) listar();
        for (Obra o: alObras) {
            if(o.getId() == id){
                alObras.remove(o);
                return escribeArray(alObras);
            }
        }
        return false;
    }

    @Override
    public boolean modifica(Object obj) {
        Obra nuevo = (Obra) obj;
        if(borra(nuevo.getId()))
            return annade(nuevo);
        return false;
    }

    @Override
    public ArrayList listar() {
        ArrayList <Obra> alObras = new ArrayList();
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(new File(ruta));
            try{
                ois = new ObjectInputStream(fis);
                while(true)
                    alObras.add((Obra) ois.readObject());
            }catch (EOFException e){
                if(ois != null)
                    ois.close();
                return alObras;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean escribeArray(ArrayList<Obra> alObras){
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(new File(ruta));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Obra o: alObras)
                objectOut.writeObject(o);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
