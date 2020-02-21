package br.ufrn.projetocrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.ufrn.projetocrud.dominio.Pessoa;
import br.ufrn.projetocrud.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	boolean editar = false;
	
	@GetMapping("/index")
	public  ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView("/index");
		List<Pessoa> pessoas = pessoaService.findAll();
		model.addAttribute("pessoas", pessoas);
		return mv;
	}
	
	@GetMapping("/form")
	public ModelAndView form(Model model) { 

		ModelAndView mv = new ModelAndView("/pessoa/form");		
		
		if(editar == false) {									
			//List<Pessoa> pessoas = pessoaService.findAll();
			//model.addAttribute("pessoas", pessoas);
			mv.addObject("pessoa", new Pessoa());			
		}		
				
		//List<Pessoa> pessoas = pessoaService.findAll();		
		//mv.addObject("pessoas", pessoas);

		return mv; 
	}
	
	@PostMapping("/salvar")	
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult br, RedirectAttributes ra) { 

		pessoaService.salvar(pessoa);
		editar = false;
		return new ModelAndView(new RedirectView("/pessoa/index", true));
	}
	

	@GetMapping("/{id}/remover")
	public ModelAndView remover(@PathVariable Integer id, RedirectAttributes ra) {
		
		try {
			pessoaService.remover(id);			
		}
		catch (Exception e) {
			
		}
		return new ModelAndView(new RedirectView("/pessoa/index", true));
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Integer id, Model model) {
				
		editar = true;		
		Pessoa pessoa = pessoaService.findOne(id);
		//List<Pessoa> pessoas = pessoaService.findAll();
		//model.addAttribute("pessoas", pessoas);
		model.addAttribute("pessoa", pessoa);

		return form(model);
	}


}
