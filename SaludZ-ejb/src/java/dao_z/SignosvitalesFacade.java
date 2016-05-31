/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao_z;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo_z.Signosvitales;

/**
 *
 * @author eli
 */
@Stateless
public class SignosvitalesFacade extends AbstractFacade<Signosvitales> {
    @PersistenceContext(unitName = "SaludZ-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SignosvitalesFacade() {
        super(Signosvitales.class);
    }
    
}
