package LogicLayer;


import dal.PlaylistDAO;
import dal.SongDAO;
import entities.Playlist;
import entities.Song;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LogicManager {
    public static void main(String[] args) {
        LogicManager DBC = new LogicManager();
        DBC.getSongMetadata(("songs/SUPERARE - Bikinis & Swimshorts.mp3"));
    }

    private SongDAO songDAO = new SongDAO();
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public List<Song> searchSongs(String query) {

        throw new RuntimeException();
    }

    public Song createSong(String title, String artist) {

        return songDAO.addSong(title, artist);
    }

    public Playlist createPlaylist(String name) {

        return playlistDAO.addPlaylist(name);
    }

    //String inputPath = "C:\\Users\\madsp\\Documents/Atch - Time Out.mp3";

    private String moveFile(String inputPath) {
        File f = new File(inputPath);
        String songName = f.getName();
        String outputPath = ("songs/" + songName);
        try {
            Files.move(Path.of(inputPath), Path.of(outputPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputPath;
    }

    private void getSongMetadata(String filePath){
        //looks for relevant metadata on the file, that can be added
        try {

            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();

            String[] metadataNames = metadata.names();
            for(String name : metadataNames){
                System.out.println(name + ": " + metadata.get(name));
            }
            String title = metadata.get("title");
            String artist = metadata.get("xmpDM:artist");
            String date = metadata.get("xmpDM:releaseDate");
            String genre = metadata.get("xmpDM:genre");
            String album = metadata.get("xmpDM:album");
            String duration = metadata.get("xmpDM:duration");

            String year = "";
            if(date != null) {
                if (date.length() > 0) {
                    year = date.substring(0, 4);
                    Integer.parseInt(year);
                }
            }



            System.out.println(title + " " + artist + " " + genre + " " + album + " " + year  + " " + duration);
           // return (title, artist, genre, album, year);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
    }
    public void addSong(String filePath) {
        //moveFile(filePath);
        //DatabaseConnection dc = new DatabaseConnection();
        //dc.addSongToDataBase(songName, 2000,"artist", "album","genre",1.3 ,"c/path");
    }





}




