package controllers;

import jdbc.ConnectToDB;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = ConnectToDB.getSt();
        model.addAttribute("object",list);
        for (String s: list) {
            System.out.println(s);
        }
        return "index";
    }
}
