package agendaclinica.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	@RequestMapping("/")
	public String index(){
		return "LayoutPadrao";
	}

}
