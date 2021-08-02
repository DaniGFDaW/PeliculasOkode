
package com.app.peliculasOkode.models;

import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Pelicula {
    public String original_title;
    public String original_language;
    public String overview;
    public int popularity;
    public Date release_date;

}
