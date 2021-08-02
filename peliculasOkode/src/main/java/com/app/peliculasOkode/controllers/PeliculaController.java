package com.app.peliculasOkode.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin
@RequestMapping("/peliculas")
public class PeliculaController {
	
	final String urlApi = "https://api.themoviedb.org/3/search/movie?api_key=a163e83c34632b051d44c054f63c73e9&query=" ;
	

	@RequestMapping("/{titulo}")
	    public String getMovieInfo(@PathVariable("titulo") String titulo) throws IOException {
	
		//Inicio Conexion
		URL url = new URL(urlApi + titulo);
		URLConnection conexion = url.openConnection();
		
		//Lectura Respuesta 
		
		Reader respuesta = new InputStreamReader(conexion.getInputStream());
		BufferedReader br = new BufferedReader(respuesta);
		
		String datos;
		String infoPelicula = null;
		
		while((datos = br.readLine()) != null) {
			
		infoPelicula = datos;
		}
		
	return infoPelicula;

}
}
