package LogicLayer;


import Datalayer.SongDAO;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class SongManager {
    private SongDAO songDAO = new SongDAO();

    private final ObservableList<Song> mistressSongList;

    public SongManager() {
        mistressSongList = FXCollections.observableArrayList();
    }

    public void getAllSongs(){
        mistressSongList.clear();
        mistressSongList.addAll(songDAO.getAllSongs());
    }

    public ObservableList<Song> getMistressSongList(){
        getAllSongs();
        return mistressSongList;
    }


    public List<Song> searchSongs(String query) {

        throw new RuntimeException();
    }


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

    private String getSongTitle(String filePath){
        //looks for a title on the file, that can be added
        try {

            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();

            String title = metadata.get("title");

            if(title == null){
                File f = new File(filePath);
                String fileTitle = f.getName();
                String fileTileWithOutMP3 = fileTitle.substring(0, fileTitle.length() - 4);
                return fileTileWithOutMP3;
            }else return title;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        return null;
    }
    private String getSongArtist(String filePath){
        //looks for an artist on the file, that can be added
        try {

            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();

            String artist = metadata.get("xmpDM:artist");
            if(artist == null) {
                return "";
            }else return artist;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        return "";
    }

    private int getSongReleaseYear(String filePath){
        //looks for a year on the file, that can be added
        try {

            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();


            String date = metadata.get("xmpDM:releaseDate");


            String year = "";
            if(date != null) {
                if (date.length() > 0) {
                    year = date.substring(0, 4);
                    return Integer.parseInt(year);
                }
            } else return 0;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        return 0;
    }
    private String getSongGenre(String filePath){
        //looks for a genre on the file, that can be added
        try {

            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();

            String genre = metadata.get("xmpDM:genre");
            if(genre == null) {
                return "";
            }else return genre;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        return "";
    }
    private String getSongAlbum(String filePath){
        //looks for an album on the file, that can be added
        try {

            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();

            String album = metadata.get("xmpDM:album");
            if(album == null) {
                return "";
            }else return album;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        return "";
    }


    public void addSong(String title,int year,String artist,String genre,String filePath) {
        int nextiD = songDAO.getNextSongID();

        songDAO.addSongToDataBase(title, year,artist,genre,filePath, nextiD);

        moveFile(filePath);
    }

}




