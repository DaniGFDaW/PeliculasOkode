
package com.app.peliculasOkode.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Busqueda {

   public PeliculaInfo results[];
}
