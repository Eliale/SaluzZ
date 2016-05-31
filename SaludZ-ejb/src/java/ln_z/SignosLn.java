/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ln_z;

import dao_z.SignosvitalesFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo_z.Signosvitales;

/**
 *
 * @author eli
 */
@Stateless
@LocalBean
public class SignosLn {

    @EJB
    private SignosvitalesFacade signosvitalesFacade;

    private List<Signosvitales> signosvital;

    public List<Signosvitales> listar() {
        signosvital = signosvitalesFacade.findAll();
        return signosvital;
    }

    public int contar() {
        return signosvitalesFacade.count();
    }

    public void agregar(Signosvitales signo) {
        signosvitalesFacade.create(signo);
    }

    public Signosvitales buscar(int id) {
        return signosvitalesFacade.find(id);
    }

    public void editar(Signosvitales sv) {
        signosvitalesFacade.edit(sv);
    }

    public void borrar(Signosvitales sv) {
        signosvitalesFacade.remove(sv);
    }

}
