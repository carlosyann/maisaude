package com.maissaude.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.maissaude.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findByLogin(String login);

}
