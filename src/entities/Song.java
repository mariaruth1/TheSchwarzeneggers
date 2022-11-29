package entities;

public class Song {
    private final int id;

    private String title;

    private int year;

    private String artist;

    private String album;

    private int songLength;

    private String genre;

    public Song(int id, String title, int year, String artist, String album, int songLength, String genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.album = album;
        this.songLength = songLength;
        this.genre = genre;
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

    public int getSongLength() {
        return songLength;
    }

    public String getGenre() {
        return genre;
    }

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

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return  title + " " + year + " " + artist + " " + album + " " + songLength + " " + genre;
    }
}
