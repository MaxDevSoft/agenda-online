package agendaclinica.com.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaclinica.com.models.Convenio;
import agendaclinica.com.models.Paciente;
import agendaclinica.com.models.Prontuario;
import agendaclinica.com.repositories.ConvenioRepository;
import agendaclinica.com.repositories.PacienteRepository;
import agendaclinica.com.repositories.ProntuariosRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PacientesController {//terminar, colocar remove e edite

	@Autowired
	private PacienteRepository pr;
	
	@Autowired
	private ConvenioRepository cr;
	
	@Autowired
	private ProntuariosRepository prr;
	
	@RequestMapping(value="/pacientes", method=RequestMethod.GET)//url
	public ModelAndView listaPacientes(Model model){
		ModelAndView mv = new ModelAndView("pacientes/listaPacientes");
		Iterable<Paciente> listaPacientes = pr.findAll();
		mv.addObject("pacientes", listaPacientes);
		
		Iterable<Convenio> listaConvenio = cr.findAll();
		model.addAttribute("convenios", listaConvenio);
		
		return mv;
	}
	
	@RequestMapping(value="/pacientes", method=RequestMethod.POST)
	public String listaPacientes(Paciente paciente, BindingResult result, RedirectAttributes attributes){
//		if(result.hasErrors()){
//			attributes.addFlashAttribute("mensagem", "Verifique os campos digitados!");
//			return cadastrarPaciente();
//		}
		pr.save(paciente);
		System.out.println(paciente.getConvenio());
		return "redirect:/pacientes";
	}
	
	@RequestMapping(value="/paciente/{nome}", method = RequestMethod.GET) 
	public ModelAndView detalhes(@PathVariable("nome") String nome){

		ModelAndView mv = new ModelAndView("pacientes/pacienteDetalhes");
		Paciente paciente = pr.findByNome(nome);
		mv.addObject("paciente", paciente);
		
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		mv.addObject("prontuarios", prontuarios);
		
		return mv;
	}

	
	@RequestMapping(value="/editar/{nome}", method = RequestMethod.GET) 
	public ModelAndView listar(@PathVariable("nome") String nome){

		ModelAndView mv = new ModelAndView("pacientes/editarPaciente");
		Paciente paciente = pr.findByNome(nome);
		mv.addObject("pacientes", paciente);
		
		Iterable<Prontuario> prontuarios = prr.findByPaciente(paciente);
		mv.addObject("prontuarios", prontuarios);
		
		return mv;
	}

	//------------------------------------------------------------------------------//

	@Primary
	@PutMapping(value="/editar/{nome}") 
	public ResponseEntity<Object> updPaciente (@PathVariable("nome") String nome){

		Optional<Paciente> pOptional = pr.findById(nome);

        if(pOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field Empty");
        }

        var peopleModel = pOptional.get();
        // BeanUtils.copyProperties(aDto, peopleModel);

        return ResponseEntity.status(HttpStatus.OK).body(pr.save(peopleModel));
	}
	//------------------------------------------------------------------------------//

	// @Primary
	// @RequestMapping(value = "/editar/{nome}", method = RequestMethod.PUT)
	// public String updatePaciente (@PathVariable("nome") String nome, @ModelAttribute("paciente") Paciente paciente, Model model){

	// 	Paciente modelPaciente = pr.findByNome(paciente.getNome()); //pegar o nome

	// 	modelPaciente.setNome(paciente.getNome());

	// 	pr.save(modelPaciente);

	// 	model.addAttribute("message", "modelPaciente");

	// 	return "redirect:/";

	// }
	
}
