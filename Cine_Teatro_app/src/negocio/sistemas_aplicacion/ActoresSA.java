package negocio.sistemas_aplicacion;

import integracion.DAO.PersonaEspectaculoDAO;
import negocio.transfers.PersonaEspectaculo;
import negocio.transfers.PersonaEspectaculoRol;
import presentacion.controladores.actores.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActoresSA {

    private static ActoresSA sa = null;

    private ActoresSA(){ }

    static public ActoresSA getInstancia() {
        if (sa == null){
            sa = new ActoresSA();
            return sa;
        }
        return sa;
    }

    public Object getObjetoSeleccionado(String nom, int contexto){
        if(contexto != Bundle.OBRA){
            PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
            PersonaEspectaculo p = dao.buscaNombre(nom);
            return p;
        }
        else{
            return null;//TODO
        }
    }

    public boolean borrarObjetoSeleccionado(String nom, int contexto){
        if(contexto != Bundle.OBRA){
            PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
            PersonaEspectaculo p = dao.buscaNombre(nom);
            return dao.borra(p.getId());
        }
        else{
            return false;//TODO
        }
    }

    public ArrayList<String> getData(int contexto){
        if(contexto != Bundle.OBRA){
            PersonaEspectaculoRol rol;
            PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
            if(contexto == Bundle.ACTOR)
                rol = PersonaEspectaculoRol.ACTOR;
            else if(contexto == Bundle.DIRECTOR)
                rol = PersonaEspectaculoRol.DIRECTOR;
            else
                rol = PersonaEspectaculoRol.PRODUCTOR;

            ArrayList <PersonaEspectaculo> alEsp = (ArrayList <PersonaEspectaculo>)dao.listar();
            List<PersonaEspectaculo> alFilt = alEsp.stream().filter(p -> p.getRol() == rol).collect(Collectors.toList());
            ArrayList<String> alNombres = new ArrayList(alFilt.stream().map(p -> p.getNombre()).collect(Collectors.toList()));
            return alNombres;
        }
        else
            return null; //TODO
    }

    public void modificaTransfer(int contexto, Object obj) {
        if(contexto != Bundle.OBRA){
            PersonaEspectaculo p = (PersonaEspectaculo) obj;
            PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
            if(dao.buscaId(p.getId()) == null) {
                dao.annade(p);
            }
            else
                dao.modifica(p);
        }
        else{

        }
    }
}
