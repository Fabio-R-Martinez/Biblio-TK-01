/* @author fabri  */

package mi.bibli01.Bib01.Repositorio;

import mi.bibli01.Bib01.Entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepo extends JpaRepository<Libro, String> {
    
/* Buscar 
    libros por autor, 
    libros por editorial,  
    libros por titulo,
    import java.util.List;
    */    
    
    //@Query("select l from libro l where id = :id")
    //public Libro HallaId(@Param("id") String id);
    
    //@Query("select l from libro l where titulo = :titulo")
    //public Libro HallaTitulo(@Param("titulo") String titulo);

}
