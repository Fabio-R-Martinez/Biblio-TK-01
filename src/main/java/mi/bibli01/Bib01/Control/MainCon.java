/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.bibli01.Bib01.Control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fabri
 */
@Controller
@RequestMapping("/")
public class MainCon {

    @GetMapping("/")
    public String Index() {
        return "Index.html";
    }

}
