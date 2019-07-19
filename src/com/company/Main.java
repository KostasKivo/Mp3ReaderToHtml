package com.company;
import com.mpatric.mp3agic.*;
import java.sql.*;
import java.io.IOException;
import java.nio.file.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Objects;


public class Main {

    private static ArrayList<Song> musicFiles = new ArrayList<>();

    public static void main(String[] args) {

        getMp3Files(args);

        databaseConnection();

        SongServlet.startServer();

    }

    private static void getMp3Files(String[] args) {

        try {

            Path directoryPath = Paths.get(args[0]);

            if (Files.exists(directoryPath)) {

                for(java.io.File file: Objects.requireNonNull(directoryPath.toFile().listFiles())){

                    if (file.isFile() && file.getName().endsWith(".mp3")) {

                        Mp3File mp3file = new Mp3File(file.toPath());

                        if( mp3file.hasId3v1Tag()) {

                            ID3v1 id3v1Tag = mp3file.getId3v1Tag();

                            Song song = new Song(id3v1Tag.getArtist(),id3v1Tag.getYear(),id3v1Tag.getAlbum(),id3v1Tag.getTitle(),id3v1Tag.getGenre(),mp3file.getLength(), file.getPath());

                            Main.musicFiles.add(song);

                        } else if( mp3file.hasId3v2Tag()){

                            ID3v2 id3v2Tag = mp3file.getId3v2Tag();

                            Song song = new Song(id3v2Tag.getArtist(),id3v2Tag.getYear(),id3v2Tag.getAlbum(),id3v2Tag.getTitle(),id3v2Tag.getGenre(),mp3file.getLength(), file.getPath());

                            Main.musicFiles.add(song);
                        }
                    }
                }
            } else if (!Files.exists(directoryPath)) {
                System.err.println("Path is invalid.Exiting!");
                System.exit(1);
            }


        } catch (IllegalArgumentException | IOException | UnsupportedTagException e) {
            System.err.println(e.getMessage());
        }   catch (InvalidDataException  e) {
            System.err.println(e.getDetailedMessage());
        }
    }

    private static void databaseConnection() {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:h2:~/project_db;AUTO_SERVER=TRUE;INIT=runscript from '~/create.sql'")){

            final String SQL_SONG_INSERT = "INSERT INTO SONGS(ARTIST,SONG_YEAR,ALBUM,TITLE) VALUES (?,?,?,?)";

            PreparedStatement dbInsert = conn.prepareStatement(SQL_SONG_INSERT);

            for(Song s:musicFiles) {

                dbInsert.setString(1,s.getArtist());
                dbInsert.setString(2,s.getYear());
                dbInsert.setString(3,s.getAlbum());
                dbInsert.setString(4,s.getTitle());
                dbInsert.addBatch();
            }

            int [] rows = dbInsert.executeBatch();

            System.out.println("Inserted [=" + rows.length + "] records into the database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


