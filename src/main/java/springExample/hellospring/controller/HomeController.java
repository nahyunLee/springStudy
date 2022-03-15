package springExample.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //여길 먼저 찾아봄 --> index.html무시됨
    @GetMapping("/")
    public String hoem(){
        return "home";
    }
}
