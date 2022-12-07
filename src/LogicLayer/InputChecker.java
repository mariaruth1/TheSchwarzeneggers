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

public class InputChecker {
    AddSongMenuController aSMC = new AddSongMenuController();

    public boolean checkTitle(String titleInput){
        if (titleInput!=null&& titleInput!=""){
            return true;
        }
        aSMC.titleError();
        return false;
    }
    public boolean checkYear(String yearInput){
        if(yearInput!=null){
            try{
            Integer.parseInt(yearInput);
            } catch (Exception e) {
                aSMC.yearError();
            }
        }
        return false;
    }
    public boolean checkArtist(String artistInput){
        if(artistInput!=null){
            return true;
        }

        aSMC.artistError();
        return false;
    }

    public String getSongTitle(String filePath){
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
    public String getSongArtist(String filePath){
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

    public String getSongReleaseYear(String filePath){
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
    public String getSongGenre(String filePath) {
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
            if (genre == null) {
                return "";
            } else if(genre == "Pop" || genre =="Rock" || genre =="Disco" || genre =="Metal"  || genre =="Classical"
                    || genre =="Country" || genre =="Jazz" || genre =="Blues" || genre =="Hip hop" || genre =="Techno" || genre =="Folk"){
                return genre;
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

}
