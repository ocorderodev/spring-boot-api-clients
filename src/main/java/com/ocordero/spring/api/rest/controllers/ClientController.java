package com.ocordero.spring.api.rest.controllers;

import com.ocordero.spring.api.rest.models.entity.Client;
import com.ocordero.spring.api.rest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4000"})
@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> index() {
        List<Client> clients = null;
        Map<String, Object> response = new HashMap<>();
        try {
            clients = clientService.findAll();
        } catch (DataAccessException e) {
            response.put("status", false);
            response.put("message", e.getMostSpecificCause());
            response.put("error", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("status", true);
        response.put("message", "Clients query successfully");
        response.put("result", clients);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable Long id) {
        Client client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("status", false);
            response.put("message", e.getMostSpecificCause());
            response.put("error", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (client == null) {
            response.put("status", false);
            response.put("message", "Client not found");
            response.put("result", null);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Client query successfully");
        response.put("result", client);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Client client) {
        Client _client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            _client = clientService.save(client);
        } catch (DataAccessException e) {
            response.put("status", false);
            response.put("message", e.getMostSpecificCause().getMessage());
            response.put("error", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("status", true);
        response.put("message", "Client add successfully");
        response.put("result", _client);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id) {
        Client _client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            _client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("status", false);
            response.put("message", e.getMostSpecificCause().getMessage());
            response.put("error", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (_client == null) {
            response.put("status", false);
            response.put("message", "Client not found");
            response.put("result", null);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        _client.setName(client.getName());
        _client.setLastname(client.getLastname());
        _client.setEmail(client.getEmail());

        Client _clientUpd = clientService.save(_client);

        response.put("status", true);
        response.put("message", "Client updated successfully");
        response.put("result", _clientUpd);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Client _client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            Client client = clientService.findById(id);
            _client = client;
        } catch (DataAccessException e) {
            response.put("status", false);
            response.put("message", e.getMostSpecificCause().getMessage());
            response.put("error", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (_client == null) {
            response.put("status", false);
            response.put("message", "Client not found");
            response.put("result", null);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        clientService.delete(id);

        response.put("status", true);
        response.put("message", "Client deleted successfully");
        response.put("result", _client);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
