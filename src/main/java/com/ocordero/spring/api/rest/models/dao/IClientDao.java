package com.ocordero.spring.api.rest.models.dao;

import com.ocordero.spring.api.rest.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {
}
