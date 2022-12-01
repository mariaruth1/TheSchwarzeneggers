package Datalayer;

import entities.Song;

import java.io.*;
import java.util.ArrayList;
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
        {
            List<Song> songs = getAllSongs();
            int biggestId = 0;
            for(Song s : songs)
            {
                if (biggestId<s.getId())
                    biggestId = s.getId();
            }
            return biggestId+1;
        }
    }

    public Song getSong(int id)
    {
        throw new RuntimeException();
    }
}
