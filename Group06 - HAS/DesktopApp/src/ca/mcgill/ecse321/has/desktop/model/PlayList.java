/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.has.desktop.model;

// line 17 "../../../../../HomeAudioSystem.ump"
// line 49 "../../../../../HomeAudioSystem.ump"
public class PlayList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayList Attributes
  private String name;
  private int listSize;

  //PlayList Associations
  private Album album;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayList(String aName, int aListSize, Album aAlbum)
  {
    name = aName;
    listSize = aListSize;
    if (!setAlbum(aAlbum))
    {
      throw new RuntimeException("Unable to create PlayList due to aAlbum");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setListSize(int aListSize)
  {
    boolean wasSet = false;
    listSize = aListSize;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getListSize()
  {
    return listSize;
  }

  public Album getAlbum()
  {
    return album;
  }

  public boolean setAlbum(Album aNewAlbum)
  {
    boolean wasSet = false;
    if (aNewAlbum != null)
    {
      album = aNewAlbum;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    album = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "listSize" + ":" + getListSize()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "album = "+(getAlbum()!=null?Integer.toHexString(System.identityHashCode(getAlbum())):"null")
     + outputString;
  }
}