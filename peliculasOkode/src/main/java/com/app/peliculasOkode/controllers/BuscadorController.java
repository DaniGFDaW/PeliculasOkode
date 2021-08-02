package com.app.peliculasOkode.controllers;

import com.app.peliculasOkode.models.Busqueda;
import com.app.peliculasOkode.models.PeliculaInfo;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/buscador")
public class BuscadorController {

    @Autowired
    private Environment env;

    @Cacheable("busquedas")
    @RequestMapping("")
    public PeliculaInfo[] getMovieInfo(@RequestParam("q") String q) throws IOException {

        System.out.println(String.format("Haciendo busqueda %s", q));
        
        WebClient client = WebClient.builder()
                .baseUrl(env.getProperty("api_url"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Busqueda response = client.get()
                .uri(uriBuilder -> uriBuilder
                .path("/search/movie")
                .queryParam("api_key", env.getProperty("key"))
                .queryParam("query", q)
                .build())
                    .exchangeToMono(r -> r.bodyToMono(Busqueda.class))
                    .block();        
        
        
        return response.results;  
        
        
    }
}
