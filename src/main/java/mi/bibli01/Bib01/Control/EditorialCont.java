/* @author fabri  */
package mi.bibli01.Bib01.Control;

import java.util.List;
import mi.bibli01.Bib01.Entidad.Editorial;
import mi.bibli01.Bib01.Error.ErrorMio;
import mi.bibli01.Bib01.Servicio.EditorialServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialCont {

    @Autowired
    EditorialServ EdSer;

    @GetMapping("/nueva")
    public String Formular() {
        return "ed-form";//nombre del HTML
    }

    @PostMapping("/nueva")
    public String Guardar(ModelMap mod, String nombre) throws ErrorMio {
        try {
            EdSer.Agregar(nombre);
            //mod.put("Ok", "La editorial fue agregada");
            //return "ed-form";//nombre del HTML
            mod.put("titulo", "EDITORIAL");
            mod.put("descripcion", "Se agregó una editorial <br>");
            return "index.html";
        } catch (ErrorMio e) {
            //mod.put("Error", "No se pudo anadir la información <br>" + e.getMessage());
            //return "ed-form";//nombre del HTML
            mod.put("titulo", "EDITORIAL");
            mod.put("descripcion", "No se pudo anadir la editorial <br>" + e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/lista")
    public String ListaEd(ModelMap mod) {
        List<Editorial> LEdis = EdSer.ListarTodas();
        mod.addAttribute("Edis", LEdis);
        return "ed-list";
    }

    @PostMapping("/lista")
    public String Alternar(ModelMap mod, String id) throws ErrorMio {
        List<Editorial> Ledis = EdSer.ListarTodas();
        mod.addAttribute("Edis", Ledis);
        return "ed-list";
    }

    // Modifica el estado 
    @GetMapping("/estado/{id}")
    public String CambiarEstado(@PathVariable String id, ModelMap mod) {
        try {
            EdSer.Alternar(id);
            //mod.put("Ok", "Se modificó el estado.");
            //return "redirect:/editorial/lista";
            mod.put("titulo", "EDITORIAL");
            mod.put("descripcion", "Se cambió el estado de la editorial <br>");
            return "index.html";
        } catch (ErrorMio e) {
            //mod.put("Problema!", "No se pudo modificar el estado <br>" + e.getMessage());
            //return "redirect:/editorial/lista";
            mod.put("titulo", "EDITORIAL");
            mod.put("descripcion", "No se pudo modificar el estado de la editorial <br>" + e.getMessage());
            return "index.html";
        }
    }

    // renombrado de editoriales    /editorial/nombre/{id}
    @GetMapping("/nombre/{id}")
    public String Renombrar(ModelMap mod, @PathVariable String id) {
        try {
            Editorial ed = EdSer.HallarId(id);
            mod.addAttribute("Edit", ed);
            return "ed-reno";
        } catch (ErrorMio e) {
            return "redirect:/editorial/lista";
        }
    }

    @PostMapping("/nombre/{id}")
    public String Renombre(ModelMap mod, @PathVariable String id, @RequestParam String nombre) {
        try {
            EdSer.Renombrar(id, nombre);
            //mod.put("Ok", "El proceso es adecuado.");
            //return "redirect:/editorial/lista";
            mod.put("titulo", "EDITORIAL");
            mod.put("descripcion", "Se modificó el nombre de la editorial");
            return "index.html";
        } catch (ErrorMio e) {
            //mod.put("Error", "Todo sigue igual (mal o peor)");
            //return "redirect:/editorial/lista";
            mod.put("titulo", "EDITORIAL");
            mod.put("descripcion", "No se pudo modificar el nombre de la editorial <br>" + e.getMessage());
            return "index.html";
        }
    }

}
