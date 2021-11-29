package com.ocordero.spring.api.rest.models.services;

import com.ocordero.spring.api.rest.models.entity.Client;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();

    public Client findById(Long id);

    public Client save(Client client);

    public Client update(Client client, Long id);

    public void delete(Long id);

}
