package com.ocordero.spring.api.rest.models.services;

import com.ocordero.spring.api.rest.models.dao.IClientDao;
import com.ocordero.spring.api.rest.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClientServiceImplement implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>)clientDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public Client update(Client client, Long id) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientDao.deleteById(id);
    }
}
