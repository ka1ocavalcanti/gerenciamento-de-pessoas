package com.kaio.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.kaio.erp.model.Pessoa;

public class Pessoas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Pessoas() {
	}
	
	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	public List<Pessoa> pesquisar(String nome){
		String jpql = "from Pessoa where nome like :nome";
		
		TypedQuery<Pessoa> query = manager
			    .createQuery(jpql, Pessoa.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Pessoa> listarTodas() {
        return manager.createQuery("from Pessoa", Pessoa.class).getResultList();
   }
	
	public Pessoa guardar(Pessoa pessoa) {
		return manager.merge(pessoa);
	}
	
	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
	
}
