package LogicLayer;

import Datalayer.PlaylistDAO;
import Datalayer.SongDAO;
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
import Datalayer.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LogicManager {
    public static void main(String[] args) {
        LogicManager DBC = new LogicManager();
        DBC.addSong("Sang navn",543,"John Doe","best Album","jazz", "path of no return" );
    }

    private SongDAO songDAO = new SongDAO();
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public List<Song> searchSongs(String query) {

        throw new RuntimeException();
    }

    public Song createSong(String title, String artist) {

      //  return songDAO.addSong(title, artist);
        return null;
    }

    public Playlist createPlaylist(String name) {

        return playlistDAO.addPlaylist(name);
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


    public void addSong(String title,int year,String artist,String album,String genre,String filePath) {
        int nextiD = songDAO.getNextSongID();

        songDAO.addSongToDataBase(title, year,artist, album,genre,filePath, nextiD);

        //moveFile(filePath);
    }





}




