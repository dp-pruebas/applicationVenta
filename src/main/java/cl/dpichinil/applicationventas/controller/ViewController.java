package cl.dpichinil.applicationventas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("entry")
    public String saleEntry(){
        return "saleEntry";
    }

    @GetMapping("report")
    public String saleReport(){
        return "saleReport";
    }
}
