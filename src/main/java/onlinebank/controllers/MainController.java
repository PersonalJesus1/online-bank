package onlinebank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/online-bank")
public class MainController {

    @GetMapping()
    public String openMainPage() {
        return "onlineBank";
    }
}
