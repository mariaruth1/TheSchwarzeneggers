package entities;

public class Song {
    private int id;

    private String title;

    private int year;

    private String artist;

    private String album;

    private String genre;

    private String path;

    public Song(int id, String title, int year, String artist, String album, String genre, String path) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.path = path;
    }

    public Song(){
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public String getPath(){return path;}


    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void getPath(String path){this.path = path;}


    @Override
    public String toString() {
        return  title + " " + year + " " + artist + " " + album + " " + genre + " " + path;
    }
}
