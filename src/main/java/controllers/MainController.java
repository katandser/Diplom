package controllers;

import entitys.Shop;
import jdbc.ConnectToDB;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) throws SQLException {
        List<Shop> listShops = ConnectToDB.getShops();
        model.addAttribute("shops",listShops);
        return "index";
    }


    @GetMapping("/createShop")
    public String sreateShop() {
        return "createShop";
    }

    @PostMapping("/createShop")
    public String createShop(@ModelAttribute Shop shop) throws SQLException {
        if (!shop.getName().equals("") && !shop.getAdress().equals("") ) {
            ConnectToDB.saveShop(shop.getAdress(), shop.getName());
            return "redirect:/";
        } else {
            return "error";
        }
    }
    @GetMapping("/{id}")
    public String showShop(@PathVariable String id, Model m) throws Exception {
        return "shop";
    }



}
