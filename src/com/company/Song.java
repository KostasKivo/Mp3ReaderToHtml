package com.company;

public class Song {

    private final String title;
    private final String artist;
    private final String album;
    private final String year;
    private final int genre;
    private final long length;
    private final String path;

    public Song(String artist, String year, String album, String title,int genre,long length,String path) {
        this.artist = artist;
        this.year = year;
        this.album = album;
        this.title = title;
        this.genre=genre;
        this.length=length;
        this.path=path;
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

    public int getGenre() {
        return genre;
    }

    public long getLength() {
        return length;
    }

    public String getPath() {
        return  path;
    }

    public void print() {
        System.out.println("Artist: " + this.getArtist() + "\n Year :" + this.getYear() + "\n Album :" + this.getAlbum()
                + "\n Title :" + this.getTitle() + "\n Genre :" + this.getGenre() + "\n Length :" + this.getLength());
    }
}