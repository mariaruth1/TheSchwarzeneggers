package entities;

public class Playlist {
    private final int id;

    private String title;

    private int songs;

    private int playlistLength;

    public Playlist(int id, String title) {
        this.id = id;
        this.title = title;
        this.songs = songs;
        this.playlistLength = playlistLength;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPlaylistLength() {
        return playlistLength;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSongLength(int playlistLength) {
        this.playlistLength = playlistLength;
    }


    @Override
    public String toString() {
        return  "id" +id+ "name" +title;
    }
}

