/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo_z;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eli
 */
@Entity
@Table(name = "SIGNOSVITALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signosvitales.findAll", query = "SELECT s FROM Signosvitales s"),
    @NamedQuery(name = "Signosvitales.findByIdsv", query = "SELECT s FROM Signosvitales s WHERE s.idsv = :idsv"),
    @NamedQuery(name = "Signosvitales.findByFecha", query = "SELECT s FROM Signosvitales s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Signosvitales.findByPresiond", query = "SELECT s FROM Signosvitales s WHERE s.presiond = :presiond"),
    @NamedQuery(name = "Signosvitales.findByPresions", query = "SELECT s FROM Signosvitales s WHERE s.presions = :presions"),
    @NamedQuery(name = "Signosvitales.findByPeso", query = "SELECT s FROM Signosvitales s WHERE s.peso = :peso")})
public class Signosvitales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSV")
    private Integer idsv;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "PRESIOND")
    private Integer presiond;
    @Column(name = "PRESIONS")
    private Integer presions;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private Double peso;
    @JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA")
    @ManyToOne
    private Persona idpersona;

    public Signosvitales() {
    }

    public Signosvitales(Integer idsv) {
        this.idsv = idsv;
    }

    public Integer getIdsv() {
        return idsv;
    }

    public void setIdsv(Integer idsv) {
        this.idsv = idsv;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getPresiond() {
        return presiond;
    }

    public void setPresiond(Integer presiond) {
        this.presiond = presiond;
    }

    public Integer getPresions() {
        return presions;
    }

    public void setPresions(Integer presions) {
        this.presions = presions;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsv != null ? idsv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Signosvitales)) {
            return false;
        }
        Signosvitales other = (Signosvitales) object;
        if ((this.idsv == null && other.idsv != null) || (this.idsv != null && !this.idsv.equals(other.idsv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Signosvitales[ idsv=" + idsv + " ]";
    }
    
}
