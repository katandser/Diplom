package controllers;

import entitys.Shop;
import jdbc.ConnectToDB;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) throws SQLException {
        List<Shop> listShops = ConnectToDB.getShops();
        model.addAttribute("shops",listShops);
        for (Shop s: listShops) {
            System.out.println(s);
        }
        return "index";
    }
}
