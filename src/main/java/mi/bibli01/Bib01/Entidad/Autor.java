/* @author fabri  */
package mi.bibli01.Bib01.Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String ayn;
    private Boolean estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.toUpperCase();
    }

    public String getAyn() {
        return ayn;
    }

    public void setAyn(String ayn) {
        //this.ayn = apellido.trim().toUpperCase().concat(());
        //String a=apellido.trim().toUpperCase()+","+nombre.trim().toLowerCase();
        //a=a+",";
        //a=a+nombre.trim().toLowerCase();
        this.ayn = ayn;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
