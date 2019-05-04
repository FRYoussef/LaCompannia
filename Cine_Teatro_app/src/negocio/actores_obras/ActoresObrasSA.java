package negocio.actores_obras;

import negocio.transfers.Obra;
import negocio.transfers.PersonaEspectaculo;
import negocio.transfers.PersonaEspectaculoRol;
import presentacion.controladores.actores_obras.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import integracion.actores_obras.DAO.ObraDAO;
import integracion.actores_obras.DAO.PersonaEspectaculoDAO;

public class ActoresObrasSA {

    private static ActoresObrasSA sa = null;

    private ActoresObrasSA(){ }

    static public ActoresObrasSA getInstancia() {
        if (sa == null){
            sa = new ActoresObrasSA();
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
            ObraDAO dao = new ObraDAO();
            Obra o = dao.buscaNombre(nom);
            return o;
        }
    }

    public boolean borrarObjetoSeleccionado(String nom, int contexto){
        if(contexto != Bundle.OBRA){
            PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
            PersonaEspectaculo p = dao.buscaNombre(nom);
            return dao.borra(p.getId());
        }
        else{
            ObraDAO dao = new ObraDAO();
            Obra o = dao.buscaNombre(nom);
            return dao.borra(o.getId());
        }
    }

    public ArrayList<String> getData(int contexto){
        if(contexto != Bundle.OBRA){
            PersonaEspectaculoRol rol;
            if(contexto == Bundle.ACTOR)
                rol = PersonaEspectaculoRol.ACTOR;
            else if(contexto == Bundle.DIRECTOR)
                rol = PersonaEspectaculoRol.DIRECTOR;
            else
                rol = PersonaEspectaculoRol.PRODUCTOR;

            return getListaPersonas(rol);
        }
        else
            return getListaObras();
    }

    public void modificaTransfer(int contexto, Object obj) {
        if(contexto != Bundle.OBRA){
            PersonaEspectaculo p = (PersonaEspectaculo) obj;
            PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
            if(dao.buscaId(p.getId()) == null)
                dao.annade(p);
            else
                dao.modifica(p);
        }
        else{
            Obra obra = (Obra) obj;
            ObraDAO dao = new ObraDAO();
            if (dao.buscaId(obra.getId()) == null)
                dao.annade(obra);
            else
                dao.modifica(obra);
        }
    }

    public ArrayList<String> getListaPersonas(PersonaEspectaculoRol rol){
        PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
        ArrayList<PersonaEspectaculo> al = (ArrayList<PersonaEspectaculo>)dao.listar();
        List<PersonaEspectaculo> alFilt = al.stream().filter(p -> p.getRol() == rol).collect(Collectors.toList());
        ArrayList<String> alNombres = new ArrayList(alFilt.stream().map(p -> p.getNombre()).collect(Collectors.toList()));
        return alNombres;
    }

    public ArrayList<String> getListaObras(){
        ObraDAO dao = new ObraDAO();
        ArrayList<Obra> al = (ArrayList<Obra>)dao.listar();
        ArrayList<String> alNombres = new ArrayList(al.stream().map(o -> o.getTitulo()).collect(Collectors.toList()));
        return alNombres;
    }

    public ArrayList<PersonaEspectaculo> parseaObjetos(String str){
        PersonaEspectaculoDAO dao = new PersonaEspectaculoDAO();
        ArrayList<PersonaEspectaculo> alEsp = new ArrayList();
        String [] nombres = str.split(", ");
        for (String n: nombres) {
            PersonaEspectaculo p = dao.buscaNombre(n);
            if(p != null)
                alEsp.add(p);
        }
        return alEsp;
    }
}
