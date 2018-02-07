/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 46 "../../../../../Tile-O.ump"
public class NormalTile extends Tile implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NormalTile(int aX, int aY, Game aGame)
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
   * Reactive method to landing on normal tile. Sets player's current tile and updates current player. Sets the mode of the game.
   *
   * @param 
   * @return void
   * @apiNote Author : Yaniv Bronshtein and Ananya Chandra
   */
  public void land() {
    Game currentGame = this.getGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(this);
    TileOApplication.getTileO().getCurrentGame().determineNextPlayer();
    this.setHasBeenVisited(true);
    currentGame.setMode(Game.Mode.GAME);

  }

  private static final long serialVersionUID = -990912597297182073L;

}