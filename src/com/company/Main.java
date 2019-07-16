package com.company;
import java.io.File;
import java.util.ArrayList;


public class Main {

    static File directoryPath;
    static ArrayList<File> musicFiles = new ArrayList<>();
    static ArrayList<Song> musicFilesMetadata = new ArrayList<>();

    public static void main(String[] args)  {

        getMp3Files(args);

    }

    private static void getMp3Files(String[] args) {

            try {
                directoryPath = new File(args[3]);

                if (directoryPath.exists()) {

                    File [] musicFiles = directoryPath.listFiles();

                    for(File file: musicFiles) {

                        if(file.isFile() && file.getName().endsWith(".mp3")){

                            Main.musicFiles.add(file);
                            System.out.println("Found file: " + file.getName());




                        }
                    }

                } else if(!directoryPath.exists()) {
                    System.err.println("Path is invalid.Exiting!");
                    System.exit(1);
                }



            } catch (IllegalArgumentException e) {
                System.err.println("Error while reading directory path.Exiting!");
                System.exit(1);
            }
    }
}
