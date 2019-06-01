package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
