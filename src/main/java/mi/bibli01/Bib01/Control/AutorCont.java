/* @author fabri  */
package mi.bibli01.Bib01.Control;

import java.util.List;
import mi.bibli01.Bib01.Entidad.Autor;
import mi.bibli01.Bib01.Error.ErrorMio;
import mi.bibli01.Bib01.Servicio.AutorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autor")
public class AutorCont {

    @Autowired
    AutorServ AuSer;

    @GetMapping("/nuevo")
    public String Formulario() {
        return "au-form";
    }

    @PostMapping("/nuevo")
    public String Guardar(ModelMap mod, String nombre, String apellido) {
        try {
            AuSer.Agregar(nombre, apellido);
            //mod.put("Ok", "Se añadió un autor");
            //return "au-form";
            mod.put("titulo", "AUTOR");
            mod.put("descripcion", "Se agregó un autor <br>");
            return "fin.html";
        } catch (ErrorMio e) {
            //mod.put("Error", "No se pudo anañdir la información <br>" + e.getMessage());
            //return "au-form";
            mod.put("titulo", "AUTOR");
            mod.put("descripcion", "No se pudo anañdir el autor <br>"+e.getMessage());
            return "fin.html";
        }
    }

    @GetMapping("/lista")
    public String Lista(ModelMap mod) {
        List<Autor> autores = AuSer.ListarTodos();
        mod.addAttribute("LAutores", autores);
        return "au-list";
    }

    @PostMapping("/lista")
    public String Listado(ModelMap mod, String id) throws ErrorMio {
        // agregar try catch
        List<Autor> LAus = AuSer.ListarTodos();
        mod.addAttribute("LAutores", LAus);
        return "au-list";
    }

    @GetMapping("/estado/{id}")
    public String Alterna(ModelMap mod, @PathVariable String id) throws ErrorMio {
        try {
            AuSer.Alternar(id);
            //mod.put("Ok", "Se cambió el estado");
            //return "redirect:/autor/lista";
            mod.put("titulo", "AUTOR");
            mod.put("descripcion", "Se modificó el estado <br>");
            return "fin.html";
        } catch (ErrorMio e) {
            //mod.put("Error", "No se pudo cambiar el estado <br>" + e.getMessage());
            //return "redirect:/autor/lista";
            mod.put("titulo", "AUTOR");
            mod.put("descripcion", "No se pudo cambiar el estado <br>"+e.getMessage());
            return "fin.html";
        }
    }

    @GetMapping("/renombra/{id}")
    public String Renombra(@PathVariable String id, ModelMap mod) throws ErrorMio {
        try {
            Autor au = AuSer.HallarId(id);
            mod.addAttribute("Autor", au);
            return "au-reno";
            //return "redirect:/autor/lista";
        } catch (ErrorMio e) {
            mod.put("Error", "problem 1");
            return "au-reno";
            //return "redirect:/autor/lista";
        }
    }

    @PostMapping("/renombra/{id}")
    //Quité el @PathVariable y funcionó
    public String Renombre(String id, ModelMap mod, String nombr1, String apellid1) throws ErrorMio { 
        try {
            AuSer.Renombrar(id, nombr1, apellid1); //
            mod.put("titulo", "AUTOR");
            mod.put("descripcion", "Modificado de Forma Correcta!");
            return "fin.html";
            //mod.put("Ok", "El proceso de renombrado fue adecuado.");
            //return "redirect:/autor/lista";
            //return "/autor/renombra/{id}";
        } catch (ErrorMio e) {
            //mod.put("Error", "Todo sigue igual (mal o peor)" + e.getMessage());
            // return "redirect:/autor/lista";
            //return "/autor/renombra/{id}";
            mod.put("titulo", "AUTOR");
            mod.put("descripcion", "No se pudo renombrar el autor <br>"+e.getMessage());
            return "fin.html";
        }
    }

}
