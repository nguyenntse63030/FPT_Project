/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Vector;

/**
 *
 * @author So Kai Con
 */
public class MovieDTO {
    private int id, movieLength, genreID;

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }
    private String name, genre;

    public MovieDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MovieDTO(int id, int movieLength, String name, String genre) {
        this.id = id;
        this.movieLength = movieLength;
        this.name = name;
        this.genre = genre;
    }

    public MovieDTO(int movieLength, String name, int genreID) {
        this.movieLength = movieLength;
        this.name = name;
        this.genreID = genreID;
    }
    
    public Vector toVector()
    {
        Vector v = new Vector();
        v.add(id);
        v.add(name);
        v.add(movieLength);
        return v;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
