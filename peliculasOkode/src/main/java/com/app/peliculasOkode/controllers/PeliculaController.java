package com.app.peliculasOkode.controllers;

import com.app.peliculasOkode.models.Pelicula;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/pelicula")
public class PeliculaController {

    @Autowired
    private Environment env;

    @RequestMapping("/{id}")
    @Cacheable("peliculas")
    public Pelicula getMovieInfo(@PathVariable("id") int id) throws IOException {
    	
    	//Prueba Cache
        System.out.println(String.format("Haciendo llamada para pelicula %s", id));
        
        WebClient client = WebClient.builder()
                .baseUrl(env.getProperty("api_url"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Pelicula response = client.get()
                .uri(uriBuilder -> uriBuilder
                .path("/movie/" + id)
                .queryParam("api_key", env.getProperty("key"))
                .queryParam("query", id)
                .build())
                    .exchangeToMono(r -> r.bodyToMono(Pelicula.class))
                    .block();        

        return response;

    }
}
