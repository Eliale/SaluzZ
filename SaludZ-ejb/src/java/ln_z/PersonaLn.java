/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ln_z;

import dao_z.PersonaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo_z.Persona;

/**
 *
 * @author eli
 */
@Stateless
@LocalBean
public class PersonaLn {

    @EJB
    private PersonaFacade personaFacade;
    private List<Persona> personas;

    public List<Persona> personas() {
        personas = personaFacade.findAll();
        return personas;
    }

    public int contar() {
        return personaFacade.count();
    }

    public void agregar(Persona persona) {
        personaFacade.create(persona);
    }

    public Persona buscar(int id) {
        return personaFacade.find(id);
    }

    public void editar(Persona p) {
        personaFacade.edit(p);
    }

    public void borrar(Persona p) {
        personaFacade.remove(p);
    }
}
