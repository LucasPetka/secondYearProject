package group9rcraggs.application.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	
    @RequestMapping("/home")
    String index() {
        return "index";
    }


}
