/* @author fabri  */

package mi.bibli01.Bib01.Repositorio;

import java.util.List;
import mi.bibli01.Bib01.Entidad.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepo extends JpaRepository<Editorial, String> {

    @Query("select e from Editorial e order by e.nombre")
    public List<Editorial> Lista();

    @Query("select e from Editorial e where nombre = :nombre")
    public Editorial HallaNombre(@Param("nombre") String nombre);
}
