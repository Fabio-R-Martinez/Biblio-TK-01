/* @author fabri  */

package mi.bibli01.Bib01.Control;

import mi.bibli01.Bib01.Error.ErrorMio;
import mi.bibli01.Bib01.Servicio.LibroServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroCont {
    @Autowired
    LibroServ LiSer;
    
    @GetMapping("/nuevo")
    public String Agregar() {
        
        return "li-form";
    }
    
    @PostMapping("/nuevo")
    public String Agregar(ModelMap mod, String titulo, int Year, String isbn, String ean, int ejes) {
        //String AutorId, String Autor2, String EditId, String Edit2,
        try {
            //Integer anho.valueOf(a);
            LiSer.Sumar(titulo, Year, isbn);            
            mod.put("descripcion", "¿Se habrá creado un libro? <br> Perhaps..."+ Year);
            return "index";
        } catch (ErrorMio e) {
            mod.put("descripcion", "¿Se habrá creado un libro? <br> Seguro que no <br> " +e.getMessage());    
            return "index";
        } catch (NullPointerException e) {
            mod.put("descripcion", "¿Ma que puntero? <br> Ta chapita! <br>" + Year);
            return "index";
        }
    }
    
}
