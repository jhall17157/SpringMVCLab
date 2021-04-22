package edu.wctc.streetfood;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class MenuController {
    private MenuItem[] menuItemArray;

    @PostConstruct
    private void initMenuData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            menuItemArray = mapper.readValue(Paths.get("menuItems.json").toFile(), MenuItem[].class);
        } catch (IOException e) {
            e.printStackTrace();
            menuItemArray = new MenuItem[0];
        }
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("stuff", menuItemArray);
        return "index";
    }

}
