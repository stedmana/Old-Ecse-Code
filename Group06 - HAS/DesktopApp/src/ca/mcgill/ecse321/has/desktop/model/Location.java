/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.has.desktop.model;

// line 11 "../../../../../HomeAudioSystem.ump"
// line 44 "../../../../../HomeAudioSystem.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private String name;
  private boolean muted;
  private int volume;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(String aName, boolean aMuted, int aVolume)
  {
    name = aName;
    muted = aMuted;
    volume = aVolume;
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

  public boolean setMuted(boolean aMuted)
  {
    boolean wasSet = false;
    muted = aMuted;
    wasSet = true;
    return wasSet;
  }

  public boolean setVolume(int aVolume)
  {
    boolean wasSet = false;
    volume = aVolume;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getMuted()
  {
    return muted;
  }

  public int getVolume()
  {
    return volume;
  }

  public boolean isMuted()
  {
    return muted;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "muted" + ":" + getMuted()+ "," +
            "volume" + ":" + getVolume()+ "]"
     + outputString;
  }
}