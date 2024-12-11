package com.example.requestservice.services;
import com.example.requestservice.models.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;


@Service
public class RequestService {
    @Autowired
    private RestTemplate restTemplate;

    public ClientEntity getClientByRut(String rut) {
        try {
            ClientEntity client = restTemplate.getForObject("http://gateway-service:8080/client/getByRut/" + rut, ClientEntity.class);
            return client;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Manejar errores HTTP específicos
            System.err.println("Error al obtener el cliente por RUT: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw new RuntimeException("Error al obtener el cliente por RUT: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores
            System.err.println("Error inesperado al obtener el cliente por RUT: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el cliente por RUT: " + e.getMessage());
        }
    }

    public String getClientRutById(Long id) {
        try {
            String rut = restTemplate.getForObject("http://gateway-service:8080/client/getRutById/" + id, String.class);
            return rut;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Manejar errores HTTP específicos
            System.err.println("Error al obtener el RUT del cliente por ID: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw new RuntimeException("Error al obtener el RUT del cliente por ID: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores
            System.err.println("Error inesperado al obtener el RUT del cliente por ID: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el RUT del cliente por ID: " + e.getMessage());
        }
    }

}