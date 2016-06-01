package modelo_z;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo_z.Signosvitales;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2016-06-01T00:15:16")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile ListAttribute<Persona, Signosvitales> signosvitalesList;
    public static volatile SingularAttribute<Persona, Integer> idpersona;
    public static volatile SingularAttribute<Persona, Date> fechanac;
    public static volatile SingularAttribute<Persona, Double> estatura;
    public static volatile SingularAttribute<Persona, Character> sexo;
    public static volatile SingularAttribute<Persona, String> nombre;

}