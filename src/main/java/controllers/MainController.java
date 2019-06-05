package controllers;

import entitys.Check;
import entitys.Shop;
import jdbc.ConnectToDB;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        Shop shop = ConnectToDB.getShop(id);
        m.addAttribute("shop",shop);
        if (shop.getId() != "")
            return "shop";
        else
            return "error404";
    }



    @PostMapping("/{id}")
    public String showInfo(@ModelAttribute Check check,  @PathVariable String id) throws SQLException {
        //ConnectToDB.saveCheck(id,check.getSum());
        //System.out.println(check.getDate());
        return "redirect:/" + id + "/graphic/" + check.getDate();
    }


    @PostMapping("/{id}/createCheck")
    public String createCheck(@ModelAttribute Check check, @PathVariable String id) throws SQLException {
        ConnectToDB.saveCheck(id,check.getSum());
        return "redirect:/" + id;
    }

    @GetMapping("/{id}/createCheck")
    public String createCheck(@PathVariable String id, Model m) throws SQLException {
        Shop shop = ConnectToDB.getShop(id);
        m.addAttribute("shop",shop);
        if (shop.getId() != "")
            return "createCheck";
        else
            return "error404";
    }


    @GetMapping("/{id}/graphic/{date1}")
    public String showGraphic(@PathVariable String id, @PathVariable String date1, Model m) throws SQLException {

        //ConnectToDB.generateCheck(id);
        //System.out.println(date1);
        Shop shop = ConnectToDB.getShop(id);
        List<Double> doubleList;
        m.addAttribute("shop",shop);
        m.addAttribute("date1",date1);
        if (shop.getId() != "") {
            doubleList = ConnectToDB.getStatistic(id, date1);
            Long[] ar = new Long[5];
            if (doubleList.size() == 0) {
                doubleList.add(0.0);
                doubleList.add(0.0);
                doubleList.add(0.0);
                doubleList.add(0.0);
                doubleList.add(0.0);
            }
            for (int i = 0; i < 5; i++) {
                double d  = doubleList.get(i);
                //d = d * 100;
                long a = (long) Math.round(d);
                //d = (double)a / 100;
                ar[i] = a;
            }

            m.addAttribute("count",ar[2]);
            m.addAttribute("avg",ar[1]);
            m.addAttribute("sum",(ar[0]));
            m.addAttribute("max",ar[3]);
            m.addAttribute("min",ar[4]);
            return "graphic";
        }
        else
            return "error404";
    }
}
