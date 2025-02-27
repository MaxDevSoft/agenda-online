package agendaclinica.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginAgendaClinica (@RequestParam("nameUser") String user,@RequestParam("passwordUser") String password, Model model){

        model.addAttribute("user", user);
        model.addAttribute("pass", password);

        return "LayoutPadrao";

  }
    
}
