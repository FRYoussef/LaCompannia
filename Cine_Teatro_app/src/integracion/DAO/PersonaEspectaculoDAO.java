package integracion.DAO;

import negocio.transfers.PersonaEspectaculo;

import java.io.*;
import java.util.ArrayList;

public class PersonaEspectaculoDAO implements IDAO {
    private String ruta = new File("src/integracion/archivos/persona_espectaculo.bin").getAbsolutePath();

    @Override
    public PersonaEspectaculo buscaId(int id) {
        ArrayList<PersonaEspectaculo> alEsp = (ArrayList<PersonaEspectaculo>) listar();
        for (PersonaEspectaculo p : alEsp) {
            if(p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public PersonaEspectaculo buscaNombre(String nombre) {
        ArrayList<PersonaEspectaculo> alEsp = (ArrayList<PersonaEspectaculo>) listar();
        for (PersonaEspectaculo p : alEsp) {
            if(p.getNombre().equals(nombre))
                return p;
        }
        return null;
    }

    @Override
    public boolean annade(Object obj) {
        PersonaEspectaculo p = (PersonaEspectaculo) obj;
        ArrayList<PersonaEspectaculo> alEsp = (ArrayList<PersonaEspectaculo>) listar();
        alEsp.add(p);
        return escribeArray(alEsp);
    }

    @Override
    public boolean borra(int id) {
        ArrayList<PersonaEspectaculo> alEsp = (ArrayList<PersonaEspectaculo>) listar();
        for (PersonaEspectaculo p: alEsp) {
            if(p.getId() == id){
                alEsp.remove(p);
                return escribeArray(alEsp);
            }
        }
        return false;
    }

    @Override
    public boolean modifica(Object obj) {
        PersonaEspectaculo nuevo = (PersonaEspectaculo) obj;
        if(borra(nuevo.getId()))
            return annade(nuevo);
        return false;
    }

    @Override
    public ArrayList listar() {
        ArrayList <PersonaEspectaculo> alEsp = new ArrayList();
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(new File(ruta));
            try{
                ois = new ObjectInputStream(fis);
                while(true)
                    alEsp.add((PersonaEspectaculo)ois.readObject());
            }catch (EOFException e){
                if(ois != null)
                    ois.close();
                return alEsp;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean escribeArray(ArrayList<PersonaEspectaculo> alEsp){
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(new File(ruta));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (PersonaEspectaculo p: alEsp)
                objectOut.writeObject(p);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
