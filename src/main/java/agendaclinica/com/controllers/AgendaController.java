package agendaclinica.com.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaclinica.com.models.Consulta;
import agendaclinica.com.models.Evento;
import agendaclinica.com.models.Paciente;
import agendaclinica.com.models.Procedimento;
import agendaclinica.com.models.Prontuario;
import agendaclinica.com.repositories.ConsultaRepository;
import agendaclinica.com.repositories.EventoRepository;
import agendaclinica.com.repositories.PacienteRepository;
import agendaclinica.com.repositories.ProcedimentoRepository;
import agendaclinica.com.repositories.ProntuariosRepository;

@Controller 
public class AgendaController {
	
	@Autowired
	private ProcedimentoRepository pr;
	
	@Autowired
	private ConsultaRepository cr;
	
	@Autowired
	private PacienteRepository par;
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ProntuariosRepository prr;
	
	@GetMapping("/agenda")
	public ModelAndView MontaAgenda(Model model) {
		 
		ModelAndView mv = new ModelAndView("agenda/agenda"); 

		Iterable<Procedimento> listaProcedimentos = pr.findAll();
		model.addAttribute("procedimentos", listaProcedimentos);
		Iterable<Paciente> listaPacientes = par.findAll();
		model.addAttribute("pacientes", listaPacientes);
		
		return mv;
	}
	
	@PostMapping("/agenda")
	public String MontaAgenda(Consulta consulta){
		consulta.setStatus(true);
		cr.save(consulta);
		Evento evento = new Evento(consulta);
		er.save(evento);
		return "redirect:/agenda";
	}
	
	@GetMapping("/getEventos.json")
	public @ResponseBody Iterable<Evento> agenda(){
		
		Iterable<Evento> listaEventos = er.findAll();
		
		return listaEventos;
	}
	
	@GetMapping("/consulta/{codigo}")
	public ModelAndView detalhesConsulta(@PathVariable long codigo){
		ModelAndView mv = new ModelAndView("agenda/consultaDetalhes");
		Consulta consulta = cr.findByCodigo(codigo);
		mv.addObject("consulta", consulta);
		return mv;
	}
	
//	@RequestMapping(value="/prontuario/{codigo}", method = RequestMethod.GET)
//	public ModelAndView formProntuario(@PathVariable("codigo") long codigo){
//		ModelAndView mv = new ModelAndView("prontuario/form");
//		Consulta consulta = cr.findByCodigo(codigo);
//		mv.addObject("consulta", consulta);
//		return mv;
//	}
	
	@PostMapping("/consulta/{codigo}")
	public String formProntuarioPost(@PathVariable long codigo,  Prontuario prontuario, BindingResult result, RedirectAttributes attributes){
	
		Consulta consulta = cr.findByCodigo(codigo);
		
		
		Paciente paciente = consulta.getPaciente();
		prontuario.setPaciente(paciente);
		
		Procedimento procedimento = consulta.getProcedimento();
		prontuario.setProcedimento(procedimento);
		
		LocalDateTime now = LocalDateTime.now();
		String data = now.toString();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String newData = formatter.format(now);
		
		prontuario.setData(newData);
		
		prr.save(prontuario);
		
		return "redirect:/consulta/{codigo}";
	}
	
}
