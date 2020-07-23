package br.ufrn.projetocrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.projetocrud.dominio.Pessoa;
import br.ufrn.projetocrud.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;	
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}

	public Pessoa findOne(Integer id) {
		return pessoaRepository.findOne(id);
	}				
	
	@Transactional(rollbackFor=Exception.class)	
	public void remover(Integer id) throws Exception {
		pessoaRepository.remover(id);						
	}

}
