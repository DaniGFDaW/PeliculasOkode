package com.app.peliculasOkode.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeliculaInfo {
    public int id;
    public String original_title;
    
    
}
