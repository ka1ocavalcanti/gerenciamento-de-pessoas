package com.kaio.erp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.kaio.erp.model.Pessoa;
import com.kaio.erp.repository.Pessoas;
import com.kaio.erp.util.Transacional;

public class CadastroPessoasService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pessoas pessoas;

    @Transacional
    public void salvar(Pessoa pessoa) {
        pessoas.guardar(pessoa);
    }

    @Transacional
    public void excluir(Pessoa pessoa) {
        pessoas.remover(pessoa);
    }
    
    public List<Pessoa> listarTodas() {
        return pessoas.listarTodas();
	}
    
    public Pessoa buscarPorId(Long id) {
        return pessoas.porId(id);
    }
    
}
