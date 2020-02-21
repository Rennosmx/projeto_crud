package br.ufrn.projetocrud.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.projetocrud.dominio.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	//void salvar(Pessoa pessoa);
				
	@Query(value = "select * from pessoa", nativeQuery=true)
	public List<Pessoa> findAll();
	
	@Query(value = "select * from pessoa where id_pessoa = ?1", nativeQuery=true)
	public Pessoa findOne(Integer id);
		
	@Transactional
	@Modifying
	@Query(value = "delete from pessoa where id_pessoa = ?1", nativeQuery=true)
	void remover(Integer id);
	
}