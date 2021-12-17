/* @author fabri  */
package mi.bibli01.Bib01.Entidad;

/*
*/
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    
    /* @ManyToOne
    private Autor autor;
    */
    /* @ManyToOne
    private Editorial editorial;
    */
    
    //@NotNull     @NotEmpty
    private String isbn; //  10 digitos hasta 2006(12345-12345), 13 despues (123-12345-12345) 
    //@Min(18)     @Max(100)
    private Integer anho; // 
    private int ejemplares;
    private Boolean estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
/*
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
*/
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(Integer a) {
//                if (a >= 1860 && a <= 2021) {
//            this.anho = a;
//        } else {
//            this.anho = 987;
//        }
            this.anho = a;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



}
