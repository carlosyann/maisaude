package com.maissaude.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.maissaude.models.Ubs;

public interface UbsRepository extends CrudRepository<Ubs, String>{
	Ubs findById(long id);
}
