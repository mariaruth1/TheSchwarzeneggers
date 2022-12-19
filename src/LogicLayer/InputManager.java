package LogicLayer;

import GUI.controller.AddSongMenuController;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;

public class InputManager {
    AddSongMenuController aSMC = new AddSongMenuController();

    /**
     * Takes the year input from edit and add song and checks if it's a number else 0 is returned
     * @param yearInput
     * @return
     */
   public boolean checkYear(String yearInput){
        if(yearInput != null){
            try{
                Integer.parseInt(yearInput);
                return true;
            } catch (Exception e) {
                aSMC.yearError();
            }
        }
        return false;
    }

    public int checkInput(String year){
        if(checkYear(year)==true){
            return Integer.parseInt(year);
        }
        return 0;
    }

    /**
     * Looks for a title on the file, that can be added to text field.
     * @param filePath
     * @return
     */
    public String getSongTitle(String filePath){
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

    /**
     * Looks for the artist on the file, that can be added to text field.
     * @param filePath
     * @return
     */
    public String getSongArtist(String filePath){
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

    /**
     * Looks for the year on the file, that can be added to text field.
     * @param filePath
     * @return
     */
    public String getSongReleaseYear(String filePath){
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
                    return year;
                }
            } else return "";


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

    /**
     * Looks for the genre on the file, that can be added to text field.
     * @param filePath
     * @return
     */
    public String getSongGenre(String filePath) {
        try {
            InputStream input = new FileInputStream(new File(filePath));
            ContentHandler handler = (ContentHandler) new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, (org.xml.sax.ContentHandler) handler, metadata, parseCtx);
            input.close();

            String genre = metadata.get("xmpDM:genre");
            if (genre == null) {
                return "";
            } else return genre;

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
}
