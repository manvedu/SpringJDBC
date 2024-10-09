package com.example.moviesapp.model;

public class Movie {
    private Long id;
    private String title;
    private String genre;
    private int releaseYear;
    private Long directorId;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Long getDirectorId() {
        return directorId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
}