/* @author fabri  */
package mi.bibli01.Bib01.Servicio;

import mi.bibli01.Bib01.Entidad.Autor;
import mi.bibli01.Bib01.Entidad.Editorial;
import mi.bibli01.Bib01.Entidad.Libro;
import mi.bibli01.Bib01.Error.ErrorMio;
import mi.bibli01.Bib01.Repositorio.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServ {

    @Autowired
    LibroRepo LiRep;

    @Transactional
    public void Sumar(String titulo, Integer anho, String isbn) throws ErrorMio {
        Libro book = new Libro();
        validarTit(titulo);
        book.setTitulo(titulo);
        book.setIsbn(isbn);
        book.setAnho(anho);
        //book.setEstado(Boolean.TRUE);
        //book.setAutor(autor);
        //book.setEditorial(editorial);
        //book.setEjemplares(7);
        LiRep.save(book);
    }

    @Transactional
    public void Agregar(String titulo, String isbn, int anho, Autor autor, Editorial editorial, int ejemp) {
        Libro book = new Libro();
        book.setTitulo(titulo);
        book.setIsbn(isbn);
        book.setEstado(Boolean.TRUE);
        //book.setAutor(autor);
        //book.setEditorial(editorial);
        book.setEjemplares(ejemp);
        LiRep.save(book);
    }

    private void validarTit(String t) throws ErrorMio {
        if (t == null || t.trim().isEmpty()) { //el trim elimina espacios
            throw new ErrorMio("¿Sabía usted que... <br>los libros tienen título.");
        }
    }
}
