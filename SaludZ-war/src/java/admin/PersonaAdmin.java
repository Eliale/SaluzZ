/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import ln_z.PersonaLn;
import ln_z.SignosLn;
import modelo_z.Persona;
import modelo_z.Signosvitales;

/**
 *
 * @author eli
 */
@Named(value = "personaAdm")
@SessionScoped
public class PersonaAdmin implements Serializable {

    @EJB
    private SignosLn signosLn;
    private Signosvitales signo;
    private List<Signosvitales> signos;
    private static boolean sePuedeSignoVitales;

    @EJB
    private PersonaLn personaLn;
    private Persona persona;
    private static int nr;
    private List<Persona> personas;
    private int objeto;
    private int idsve;
    private ArrayList<Integer> listaidsv;
    private ArrayList<Integer> listaObjetos;
    private Date FechaE;

    public PersonaAdmin() {
    }

    public int getObjeto() {
        return objeto;
    }

    public void setObjeto(int objeto) {
        this.objeto = objeto;
    }

    public ArrayList<Integer> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(ArrayList<Integer> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void personas() {
        personas = personaLn.personas();
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void creaPersona(ActionEvent e) {
        persona = new Persona();
        persona.setIdpersona(ni());
    }

    public void nuevaPersona(ActionEvent e) {
        persona = new Persona();
        llenaLista();
    }

    public int ni() {
        if ((nr = personaLn.contar()) == 0) {
            nr = 1;
        } else {
            nr = (personaLn.personas().get(nr - 1)).getIdpersona() + 1;
        }
        return nr;
    }

    public static int getNr() {
        return nr;
    }

    public static void setNr(int nr) {
        PersonaAdmin.nr = nr;
    }

    public void agregar(ActionEvent e) {
        FacesContext contexto
                = FacesContext.getCurrentInstance();
        FacesMessage mensaje;
        personaLn.agregar(persona);
        mensaje = new FacesMessage("se agrego" + persona.getNombre());
        contexto.addMessage(null, mensaje);
        limpiar(e);

    }

    public void limpiar(ActionEvent e) {
        creaPersona(e);
    }

    public void listar(ActionEvent e) {
        personas();
    }

    public void buscaSigno(ActionEvent e) {
        // BUscar el signo apartir de su id
        signo = signosLn.buscar(getIdsve());
        FacesContext contexto
                = FacesContext.getCurrentInstance();
        FacesMessage mensaje;
        mensaje = new FacesMessage("Buscado " + getIdsve());
        contexto.addMessage(null, mensaje);

    }

    public void clean() {
        signo = null;
    }

    public void buscar(ActionEvent e) {
        persona = personaLn.buscar(objeto);
        sePuede();

    }

    public void sePuede() {
        signos = signosLn.listar();
        Date hoy = new Date();
        Date fecha;
        for (int i = 0; i < signos.size(); i++) {
            if (signos.get(i).getIdpersona().equals(persona)) {
                fecha = signos.get(i).getFecha();
                if (hoy.getDay() == fecha.getDay() && hoy.getMonth() == fecha.getMonth() && hoy.getYear() == fecha.getYear()) {
                    setSePuedeSignoVitales(true);
                } else {
                    setSePuedeSignoVitales(false);
                }
            }

        }
    }

    public void listaSignosDeUnEspecifico(ActionEvent e) {
        List<Signosvitales> todos = signosLn.listar();
        signos.clear();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getIdpersona().getIdpersona() == objeto) {
                signos.add(todos.get(i));
            }
        }
        llenaListaSV();
    }

    public void buscar_fechas(ActionEvent e) {
        busca_fecha_es();
    }

    public void busca_fecha_es() {
        List<Signosvitales> todos = signosLn.listar();
        signos.clear();
        FacesContext contexto
                = FacesContext.getCurrentInstance();
        FacesMessage mensaje;
        mensaje = new FacesMessage("Fecha evaluada" + getFechaE());
        contexto.addMessage(null, mensaje);

        for (int i = 0; i < todos.size(); i++) {
            int anio = todos.get(i).getFecha().getYear();
            int mes = todos.get(i).getFecha().getMonth();
            int dia = todos.get(i).getFecha().getDay();

            if (anio == getFechaE().getYear() && mes == getFechaE().getMonth() && dia == getFechaE().getDay()) {

                signos.add(todos.get(i));
            }
        }
    }

    public void editar(ActionEvent e) {
        personaLn.editar(persona);
        listar(e);
    }

    public void borrar(ActionEvent e) {
        personaLn.borrar(persona);
        listar(e);
    }

    public void llenaLista() {
        personas = personaLn.personas();
        listaObjetos = new ArrayList<>(personas.size());
        for (int i = 0; i < personas.size(); i++) {
            listaObjetos.add(personas.get(i).getIdpersona());
        }
    }

    public void llenaListaSV() {
        //Obtener signos
        // signos = signosLn.listar();
        //No se su tamaño
        listaidsv = new ArrayList<>();
        //Recorrer todos los signos
        for (int i = 0; i < signos.size(); i++) {
            // Solo nos interesa de un id de persona en especial
            if (signos.get(i).getIdpersona().getIdpersona() == getObjeto()) {
                // llenar el array con los idsv de la persona en concreto ¡Hala Madrid y nada más!
                listaidsv.add(signos.get(i).getIdsv());
            }
        }

    }

    public Signosvitales getSigno() {
        return signo;
    }

    public void setSigno(Signosvitales signo) {
        this.signo = signo;
    }

    public boolean isSePuedeSignoVitales() {
        return sePuedeSignoVitales;
    }

    public static void setSePuedeSignoVitales(boolean sePuedeSignoVitales) {
        PersonaAdmin.sePuedeSignoVitales = sePuedeSignoVitales;
    }

    public List<Signosvitales> getSignos() {
        return signos;
    }

    public void setSignos(List<Signosvitales> signos) {
        this.signos = signos;
    }

    public void creaSignosVitales(ActionEvent e) {
        signo = new Signosvitales();
    }

    public void agregarSigno(ActionEvent e) {
        //sePuedeSignoVitales = false;
        signo.setIdpersona(persona);
        signo.setFecha(new Date());
        signosLn.agregar(signo);

        listarSignos(e);
    }

    public void editarSignos(ActionEvent e) {
        FacesContext contexto
                = FacesContext.getCurrentInstance();
        FacesMessage mensaje ;
        mensaje = new FacesMessage("Registro editado"); 
        
        if (fecha_viable(signo.getFecha())) {
            signosLn.editar(signo);
        }
        else{
           mensaje = new FacesMessage("Fecha incorrecta " + signo.getFecha() + " no editado"); 
        }
        
        contexto.addMessage(null, mensaje);
       
    }

    public boolean fecha_viable(Date fecha) {
        Date hoy = new Date();
        return hoy.getYear() >= fecha.getYear() && hoy.getMonth() >= fecha.getMonth() && hoy.getDay() >= fecha.getDay();

    }

    public void borrarSignos(ActionEvent e) {
        signosLn.borrar(signo);
    }

    public void buscarSignos(ActionEvent e) {
        signosLn.buscar(signo.getIdsv());
    }

    public void listarSignos(ActionEvent e) {
        signos = signosLn.listar();
    }

    public Date getFechaE() {
        return FechaE;
    }

    public void setFechaE(Date FechaE) {
        this.FechaE = FechaE;
    }

    public int getIdsve() {
        return idsve;
    }

    public void setIdsve(int idsve) {
        this.idsve = idsve;

    }

    public ArrayList<Integer> getListaidsv() {
        return listaidsv;
    }

    public void setListaidsv(ArrayList<Integer> listaidsv) {
        this.listaidsv = listaidsv;
    }

}
