package com.maissaude.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.maissaude.models.Especialidades;
import com.maissaude.models.Solicitacao;
import com.maissaude.models.Usuario;


public interface SolicitacaoRepository extends CrudRepository<Solicitacao, String>{

	Iterable<Solicitacao> findByUsuario(Usuario usuario);
	Iterable<Solicitacao> findByEsp(Especialidades espe);
	Solicitacao findByIdR(long idR);
}
