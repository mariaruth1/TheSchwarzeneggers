package dal;

import entities.Song;

import java.util.List;

public class SongDAO {

    public List<Song> getAllSongs()
    {
        return null;
    }

    public Song addSong(String title, String artist)
    {
        return null;
    }

    public void deleteSong(Song song)
    {
        throw new RuntimeException();
    }

    public void updateSong()
    {
        throw new RuntimeException();
    }

    private int getNextId()
    {
        throw new RuntimeException();
    }

    public Song getSong(int id)
    {
        throw new RuntimeException();
    }
}
