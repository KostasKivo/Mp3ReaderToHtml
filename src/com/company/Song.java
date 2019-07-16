package com.company;

public class Song {

    private final String title;
    private final String artist;
    private final String album;
    private final String year;
    private final String genre;

    public Song(String artist, String year, String album, String title,String genre) {
        this.artist = artist;
        this.year = year;
        this.album = album;
        this.title = title;
        this.genre=genre;
    }

    public String getArtist() {
        return artist;
    }

    public String getYear() {
        return year;
    }

    public String getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}