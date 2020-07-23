package br.ufrn.projetocrud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.ufrn.projetocrud.dominio.Pessoa;
import br.ufrn.projetocrud.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	boolean editar = false;
	
	List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	@GetMapping("/index")
	public  ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView("/index");
		List<Pessoa> pessoas = pessoaService.findAll();
		model.addAttribute("pessoas", pessoas);
		editar = false;	
		return mv;
	}
	
	@GetMapping("/form")
	public ModelAndView form(Model model) { 

		ModelAndView mv = new ModelAndView("/pessoa/form");		
		
		if(editar == false) {									
			mv.addObject("pessoa", new Pessoa());			
		}		
				
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
		model.addAttribute("pessoa", pessoa);
		return form(model);
	}
	
	//Metodos para integrar operações de CRUD com mobile
	@GetMapping(value = "/listarApi")
	public ResponseEntity<List<Pessoa>> listar (){
		return ResponseEntity.ok().body(pessoaService.findAll());
	}

	@PostMapping(value = "/salvarApi")
	public ResponseEntity<Pessoa> salvar (@Valid @RequestBody Pessoa pessoa){
		return ResponseEntity.ok().body(pessoaService.salvar(pessoa));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> atualizar (@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa){
		pessoa.setId(id);
		return ResponseEntity.ok().body(pessoaService.salvar(pessoa));
	}
	
	@DeleteMapping(value = "/{id}")
	public void remover (@PathVariable Integer id){
		try {
			pessoaService.remover(id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}