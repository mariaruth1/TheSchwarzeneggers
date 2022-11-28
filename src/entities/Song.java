package entities;

public class Song {
    private final int id;

    private String title;

    private int year;

    private String artist;

    private String album;

    private int songLenth;

    private String genre;

    public Song(int id, String title, int year, String artist, String album, int songLenth, String genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.album = album;
        this.songLenth = songLenth;
        this.genre = genre;
    }
}
