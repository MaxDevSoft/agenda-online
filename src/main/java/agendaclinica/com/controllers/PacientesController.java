package agendaclinica.com.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaclinica.com.models.Convenio;
import agendaclinica.com.models.Paciente;
import agendaclinica.com.models.Prontuario;
import agendaclinica.com.repositories.ConvenioRepository;
import agendaclinica.com.repositories.PacienteRepository;
import agendaclinica.com.repositories.ProntuariosRepository;


@Controller
public class PacientesController {//terminar, colocar remove e edite

	@Autowired
	private PacienteRepository pr;
	
	@Autowired
	private ConvenioRepository cr;
	
	@Autowired
	private ProntuariosRepository prr;

	@PostMapping("/pacientes")
	public String savePacientes(Paciente paciente, BindingResult result, RedirectAttributes attributes){
//		if(result.hasErrors()){
//			attributes.addFlashAttribute("mensagem", "Verifique os campos digitados!");
//			return cadastrarPaciente();
//		}
		pr.save(paciente);
		System.out.println(paciente.getConvenio());
		return "redirect:/pacientes";
	}
	
	
	@GetMapping("/pacientes")//url
	public ModelAndView listaPacientes(Model model){
		
		ModelAndView mv = new ModelAndView("pacientes/listaPacientes"); // retorna o view que será exibida 
		Iterable<Paciente> listaPacientes = pr.findAll();
		mv.addObject("pacientes", listaPacientes);
		
		Iterable<Convenio> listaConvenio = cr.findAll();
		model.addAttribute("convenios", listaConvenio);
		
		return mv;
	}
	
	
	
	@GetMapping("/paciente/{nome}") 
	public ModelAndView detalhes(@PathVariable String nome){

		ModelAndView mv = new ModelAndView("pacientes/pacienteDetalhes"); 
		Paciente paciente = pr.findByNome(nome);
		mv.addObject("paciente", paciente);
		
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		mv.addObject("prontuarios", prontuarios);
		
		return mv;
	}

	
	@GetMapping("/editarpaciente/{nome}") 
	public ModelAndView listar(@PathVariable String nome){

		ModelAndView mv = new ModelAndView("pacientes/editarPaciente");
		Paciente paciente = pr.findByNome(nome);
		mv.addObject("pacientes", paciente);
		
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		mv.addObject("prontuarios", prontuarios);
		
		return mv;
	}

	//------------------------------------------------------------------------------//

	
	@PostMapping(value="/update/{nome}") 
	public ResponseEntity<Object> updPaciente (@PathVariable("nome") String nome, @ModelAttribute("paciente") Paciente paciente){

		Optional<Paciente> pOptional = pr.findById(nome); 

        if(pOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field Empty");
        }else{

			 var peopleModel = pOptional.get();
        // BeanUtils.copyProperties(aDto, peopleModel);

        return ResponseEntity.status(HttpStatus.OK).body(pr.save(peopleModel));
		}

       
	}
	//------------------------------------------------------------------------------//

	
	// @PostMapping("/atualizar-paciente")
	// public String updatePaciente (Paciente paciente, Model model){

	// 	Paciente modelPaciente = ps.savePaciente(paciente);


	// 	model.addAttribute("paciente", modelPaciente);

	// 	return "redirect:/";

	// }
	
}