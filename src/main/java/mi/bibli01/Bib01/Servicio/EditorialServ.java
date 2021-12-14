/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.bibli01.Bib01.Servicio;

import java.util.List;
import java.util.Optional;
import mi.bibli01.Bib01.Entidad.Editorial;
import mi.bibli01.Bib01.Error.ErrorMio;
import mi.bibli01.Bib01.Repositorio.EditorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fabri
 */
@Service
public class EditorialServ {

    @Autowired
    EditorialRepo EdRep;

    /* METODOS PUBLICOS CRUD 
    Agregar: crea una editorial a partir de un nombre. También le da alta.
    */
    // Crear
    @Transactional
    public void Agregar(String nombre) throws ErrorMio {
        validarNombre(nombre);
        validarDuplicado(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setEstado(Boolean.TRUE);
        EdRep.save(editorial);
    }

    
    // Modificar
    @Transactional
    public void Alternar(String id) throws ErrorMio {
        Editorial e = new Editorial();
        Optional<Editorial> ed = EdRep.findById(id);
        if (ed.isPresent()) {
            e = ed.get();
            if (e.getEstado() == null) {
                throw new ErrorMio("El estado de la editorial no existe");
            }
            if (e.getEstado() == true) {
                e.setEstado(false);
            } else {
                e.setEstado(true);
            }
        } else {
            throw new ErrorMio("No tengo editorial con esa id.");
        }
        EdRep.save(e);
    }

    @Transactional
    public void Renombrar(String id, String nombre) throws ErrorMio {
        validarNombre(nombre);
        validarDuplicado(nombre);
        Editorial ed = new Editorial();
        Optional<Editorial> edi = EdRep.findById(id);
        if (edi.isPresent()) {
            ed = edi.get();
            ed.setNombre(nombre);
        } else {
            throw new ErrorMio("No existe el id buscado");
        }
        EdRep.save(ed);
    }

    // Falta Eliminar: Por ser libros no deberían eliminarse
    
    /* OTROS METODOS PUBLICOS  */
    // ListarTodos
    @Transactional(readOnly = true)
    public List<Editorial> ListarTodas() {
        return EdRep.Lista();
    }

    // Listar Algunos
    
    // Listar Uno
    @Transactional(readOnly = true)
    public Editorial HallarId(String id) throws ErrorMio {
        Optional<Editorial> ed=EdRep.findById(id);
        if (ed.isPresent()) {
            return ed.get();
        } else {
            throw new ErrorMio ("No hay editorial con tal id.");
        }
    }    
    
    /* METODOS PRIVADOS */
    // Validar Nombre
    private void validarNombre(String nombre) throws ErrorMio {
        if (nombre == null || nombre.trim().isEmpty()) { //el trim elimina espacios
            throw new ErrorMio("La editorial no tiene nombre válido");
        }
    }

    // Validar Duplicado
    @Transactional(readOnly = true)
    private void validarDuplicado(String nombre) throws ErrorMio {
        //nombre=nombre.trim().toUpperCase();
        if (EdRep.HallaNombre(nombre) != null) {
            throw new ErrorMio("nombre duplicado");
        }
    }
// nombre.trim().toUpperCase
    
}
