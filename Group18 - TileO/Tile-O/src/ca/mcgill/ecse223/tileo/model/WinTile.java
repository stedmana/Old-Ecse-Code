/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 50 "../../../../../Tile-O.ump"
public class WinTile extends Tile implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }


  /**
   *
   * Reactive method to landing on hidden tile. Marks tile as visited and changes game mode to "Game Won"
   *
   * @param none
   * @return void
   * @apiNote Author : Yaniv Bronshtein and Ananya Chandra
   */
  public void land() {
    Game currentGame = this.getGame();
    this.setHasBeenVisited(true);
    currentGame.setMode( Game.Mode.GAME_WON);

  }


  private static final long serialVersionUID = -8871002774454467836L;

}