package agendaclinica.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
// @RestController
// @RequestMapping("/login")
@Controller
public class LoginController {

  @PostMapping("/login")
  public String loginUser (){

    return "A";

  }


  //   @GetMapping("/login")
  //   public String loginAgendaClinica (@RequestParam("nameUser") String user,@RequestParam("passwordUser") String password, Model model){

  //       model.addAttribute("user", user);
  //       model.addAttribute("pass", password);

  //       return "LayoutPadrao";

  // }

  // @Autowired
  // UsuarioRepository usuarioRepository;

  // @PostMapping("/save")
  // public void pageLogin (@RequestBody Usuario usuario){

  //   usuarioRepository.save(usuario);

  //   System.out.println("Salvo");

  // }

    
}
