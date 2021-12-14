/* @author fabri */

package mi.bibli01.Bib01.Repositorio;

import java.util.List;
import mi.bibli01.Bib01.Entidad.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepo extends JpaRepository<Autor, String> {
    
    @Query("Select a From Autor a Order By apellido, nombre")
    public List<Autor> Lista();
    
    @Query("Select a From Autor a Where apellido = :apellido")
    public List<Autor> HallaApellido(@Param("apellido") String apellido);
    
    @Query("Select a From Autor a Where ayn = :ayn")
    public Autor HallaAyn(@Param("ayn") String ayn);
    
    @Query("Select a From Autor a Where id = :id")
    public Autor HallaId(@Param("id") String id);
    
}
