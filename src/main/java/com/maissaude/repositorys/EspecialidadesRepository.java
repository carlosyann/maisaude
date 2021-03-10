package com.maissaude.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.maissaude.models.Especialidades;
import com.maissaude.models.Ubs;


public interface EspecialidadesRepository extends CrudRepository<Especialidades, String>{
	Iterable<Especialidades> findByUbs(Ubs ubs);
	Especialidades findByCodigo (long codigo);
}
