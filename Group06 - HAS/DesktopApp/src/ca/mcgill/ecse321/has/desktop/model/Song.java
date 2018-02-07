/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.has.desktop.model;
import java.util.*;

// line 22 "../../../../../HomeAudioSystem.ump"
// line 54 "../../../../../HomeAudioSystem.ump"
public class Song
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Song Attributes
  private String title;
  private int listPosition;
  private int duration;
  private int albumPosition;

  //Song Associations
  private List<Album> albums;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Song(String aTitle, int aListPosition, int aDuration, int aAlbumPosition)
  {
    title = aTitle;
    listPosition = aListPosition;
    duration = aDuration;
    albumPosition = aAlbumPosition;
    albums = new ArrayList<Album>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setListPosition(int aListPosition)
  {
    boolean wasSet = false;
    listPosition = aListPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setAlbumPosition(int aAlbumPosition)
  {
    boolean wasSet = false;
    albumPosition = aAlbumPosition;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public int getListPosition()
  {
    return listPosition;
  }

  public int getDuration()
  {
    return duration;
  }

  public int getAlbumPosition()
  {
    return albumPosition;
  }

  public Album getAlbum(int index)
  {
    Album aAlbum = albums.get(index);
    return aAlbum;
  }

  public List<Album> getAlbums()
  {
    List<Album> newAlbums = Collections.unmodifiableList(albums);
    return newAlbums;
  }

  public int numberOfAlbums()
  {
    int number = albums.size();
    return number;
  }

  public boolean hasAlbums()
  {
    boolean has = albums.size() > 0;
    return has;
  }

  public int indexOfAlbum(Album aAlbum)
  {
    int index = albums.indexOf(aAlbum);
    return index;
  }

  public static int minimumNumberOfAlbums()
  {
    return 0;
  }

  public boolean addAlbum(Album aAlbum)
  {
    boolean wasAdded = false;
    if (albums.contains(aAlbum)) { return false; }
    albums.add(aAlbum);
    if (aAlbum.indexOfSong(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAlbum.addSong(this);
      if (!wasAdded)
      {
        albums.remove(aAlbum);
      }
    }
    return wasAdded;
  }

  public boolean removeAlbum(Album aAlbum)
  {
    boolean wasRemoved = false;
    if (!albums.contains(aAlbum))
    {
      return wasRemoved;
    }

    int oldIndex = albums.indexOf(aAlbum);
    albums.remove(oldIndex);
    if (aAlbum.indexOfSong(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAlbum.removeSong(this);
      if (!wasRemoved)
      {
        albums.add(oldIndex,aAlbum);
      }
    }
    return wasRemoved;
  }

  public boolean addAlbumAt(Album aAlbum, int index)
  {  
    boolean wasAdded = false;
    if(addAlbum(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAlbumAt(Album aAlbum, int index)
  {
    boolean wasAdded = false;
    if(albums.contains(aAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAlbums()) { index = numberOfAlbums() - 1; }
      albums.remove(aAlbum);
      albums.add(index, aAlbum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAlbumAt(aAlbum, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Album> copyOfAlbums = new ArrayList<Album>(albums);
    albums.clear();
    for(Album aAlbum : copyOfAlbums)
    {
      aAlbum.removeSong(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "listPosition" + ":" + getListPosition()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "albumPosition" + ":" + getAlbumPosition()+ "]"
     + outputString;
  }
}