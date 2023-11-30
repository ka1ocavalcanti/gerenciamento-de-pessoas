package com.kaio.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.kaio.erp.model.Pessoa;
import com.kaio.erp.model.Registro;
import com.kaio.erp.repository.Pessoas;
import com.kaio.erp.service.CadastroPessoasService;
import com.kaio.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	private Pessoa pessoa;
	
	@Inject
	private Registro registro;
	
	@Inject
	private CadastroPessoasService cadastroPessoasService;
	
	@Inject
	private FacesMessages messages;

	private List<Pessoa> listaPessoas;
	
	private String termoPesquisa;
	
	private Converter registroConverter;
	
	public void prepararNovaPessoa(){
		pessoa = new Pessoa();
	}
	
	public void prepararEdicao(){
	     pessoa = cadastroPessoasService.buscarPorId(pessoa.getId());
	}
	
	public void salvar(){
		cadastroPessoasService.salvar(pessoa);
		
		atualizarRegistros();
		
		messages.info("Pessoa salva com sucesso!");
		
		RequestContext.getCurrentInstance().update(Arrays.asList(
				"frm:pessoasDataTable", "frm:messages"));
	}
	
	public void excluir(){
		cadastroPessoasService.excluir(pessoa);
		
		pessoa = null;
		
		atualizarRegistros();
		
		messages.info("Pessoa excluida com sucesso!");
	}
	
	
	public void pesquisar(){
		listaPessoas = pessoas.pesquisar(termoPesquisa);
		
		if(listaPessoas.isEmpty()){
			messages.info("Sua consulta não retornou registros.");
		}
	}
	
	public void todasPessoas() {
	    listaPessoas = pessoas.listarTodas();
	    System.out.println("Tamanho da lista de pessoas: " + (listaPessoas != null ? listaPessoas.size() : 0));
	}
	
	private void atualizarRegistros(){
		if(jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasPessoas();
		}
	}
	
	private boolean jaHouvePesquisa(){
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	
	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public Converter getRegistroConverter() {
        return registroConverter;
    }
	
	public Registro getRegistro() {
		return registro;
	}
	
	public boolean isPessoaSeleciona(){
		return pessoa != null && pessoa.getId() != null;
	}
	
}



