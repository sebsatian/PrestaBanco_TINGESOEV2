package com.example.registerservice.services;

import com.example.registerservice.entities.ClientEntity;
import com.example.registerservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    public ClientEntity registerClient(ClientEntity client) {
        // Verify that the client does not already exist
        Optional<ClientEntity> existingClient = Optional.ofNullable(clientRepository.findClientByRut(client.getRut()));
        if (existingClient.isPresent()) {
            throw new RuntimeException("El RUT ya está registrado");
        }

        // Save the client
        return clientRepository.save(client);
    }

    public ClientEntity getClientByRut(String rut) {
        return clientRepository.findClientByRut(rut);
    }

    public Long getClientIdByRut(String rut) {
        return clientRepository.findClientByRut(rut).getId();
    }

    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public String getRutById(Long id) {
        return clientRepository.getClientRutById(id);
    }


}
