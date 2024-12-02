package com.example.requestservice.services;
import com.example.requestservice.models.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;


@Service
public class RequestService {
    @Autowired
    private RestTemplate restTemplate;

    public ClientEntity getClientByRut(String rut) {
        ClientEntity client = restTemplate.getForObject("http://localhost:8080/client/" + rut, ClientEntity.class);

        return client;
    }

    public String getClientRutById(Long id) {
        String rut = restTemplate.getForObject("http://localhost:8080/client/rut/" + id, String.class);

        return rut;
    }
}