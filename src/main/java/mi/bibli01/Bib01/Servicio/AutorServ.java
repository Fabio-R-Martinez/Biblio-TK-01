/* @author fabri  */
package mi.bibli01.Bib01.Servicio;

import java.util.List;
import java.util.Optional;
import mi.bibli01.Bib01.Entidad.Autor;
import mi.bibli01.Bib01.Error.ErrorMio;
import mi.bibli01.Bib01.Repositorio.AutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServ {

    @Autowired
    AutorRepo AuRep;

    /* METODOS PUBLICOS CRUD 
    Agregar: crea autor desde nombre, apellido. Le da alta.
    ¿Qué pasa con ayn?
     */
    // Crear
    @Transactional
    public void Agregar(String nombre, String apellido) throws ErrorMio {
        validarApellido(apellido);
        Autor autor = Formar(nombre, apellido);
        validarDuplicado(autor);
        autor.setEstado(Boolean.TRUE);
        AuRep.save(autor);
    }

    // Modificar
    @Transactional
    public void Alternar(String id) throws ErrorMio {
        Autor a = new Autor();
        Optional<Autor> au = AuRep.findById(id);
        if (au.isPresent()) {
            a = au.get();
            if (a.getEstado() == null) {
                throw new ErrorMio("El estado de la editorial no existe");
            }
            if (a.getEstado() == true) {
                a.setEstado(false);
            } else {
                a.setEstado(true);
            }
        } else {
            throw new ErrorMio("No tengo editorial con esa id.");
        }
        AuRep.save(a);
    }

    
    @Transactional
    public void Renombrar(String id, String nombre, String apellido) throws ErrorMio { //
        Optional<Autor> au = AuRep.findById(id);
        if (au.isPresent()) {
            Autor a = au.get();
            if (nombre!=null) { a.setNombre(nombre);}
            if (apellido!=null) {a.setApellido(apellido);}
            a.setAyn(a.getApellido().trim().toUpperCase()+","+a.getNombre().trim().toLowerCase());
            // validarDuplicado(a);
            AuRep.save(a);
        } else {
            throw new ErrorMio("No existe el id buscado");
        }
    }
    
    /*
    @Transactional
    public void Renombrar(String id, String nombre, String apellido) {
        // Autor a=new Autor();
        Autor a= AuRep.HallaId(id);
        if (a!=null){
        a.setNombre(nombre);
        //if (nombre!=null) { a.setNombre(nombre);}
        // if (apellido!=null) {a.setApellido(apellido);}
        //a.setAyn(a.getApellido().trim().toUpperCase()+","+a.getNombre().trim().toLowerCase());
        AuRep.save(a); }
    }
    */
    
    

    // Falta Eliminar: Por ser libros no deberían eliminarse
    /* OTROS METODOS PUBLICOS  */
    // ListarTodos
    @Transactional(readOnly = true)
    public List<Autor> ListarTodos() {
        return AuRep.Lista();
    }

    // Listar Algunos
    // Listar Uno
    @Transactional(readOnly = true)
    public Autor HallarId(String id) throws ErrorMio {
        Autor a = new Autor();
        Optional<Autor> au = AuRep.findById(id);
        if (au.isPresent()) {
            a = au.get();
        } else {
            throw new ErrorMio("No hay editorial con tal id.");
        }
        return a;
    }
    
    /*      METODO PARA ENVIAR UN AUTOR CUALQUIERA A LIBRO, PRUEBA DE LIBRO
    
    @Transactional(readOnly = true)
public Autor HallarId(String id) throws ErrorMio {
        Autor a = new Autor();
        
}
    */
    /* METODOS PRIVADOS */
    
    private Autor Formar(String nombre, String apellido) {
        Autor autor = new Autor();
        autor.setApellido(apellido);
        autor.setNombre(nombre);
        autor.setAyn(apellido.trim().toUpperCase()+","+nombre.trim().toLowerCase());
        return autor;
    }
    
    // Validar Nombre
    private void validarApellido(String apellido) throws ErrorMio {
        if (apellido == null || apellido.trim().isEmpty()) { //el trim elimina espacios
            throw new ErrorMio("El autor requiere un apellido válido");
        }        
    }

    // Validar Duplicado
    @Transactional(readOnly = true)
    private void validarDuplicado(Autor a) throws ErrorMio {
        //nombre=nombre.trim().toUpperCase();
        if (AuRep.HallaAyn(a.getAyn()) != null) {
            throw new ErrorMio("El autor parece estar duplicado");
        }
    }
}
