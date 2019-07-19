package com.company;

class Song {

    private final String title;
    private final String artist;
    private final String album;
    private final String year;

    Song(String artist, String year, String album, String title, int genre, long length, String path) {
        this.artist = artist;
        this.year = year;
        this.album = album;
        this.title = title;
    }

    String getArtist() {
        return artist;
    }

    String getYear() {
        return year;
    }

    String getAlbum() {
        return album;
    }

    String getTitle() {
        return title;
    }

}