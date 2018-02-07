/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.io.Serializable;

//import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 80 "../../../../../Tile-O.ump"
public class TeleportActionCard extends ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TeleportActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  /**
   * Implements play() for TeleportActionCard and throws InvalidInputException
   *  Gets currentGame, currentPlayer, and sets the current tile to the specified tile
   *
   * @param Tile tile
   * @return void
   * @apiNote Author : Ananya Chandra
   */
  public void play(Tile tile) throws InvalidInputException {
    Game currentGame = tile.getGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(tile);

  }
  private static final long serialVersionUID = 5546099581655989380L;
  @Override
  public Mode getActionCardGameMode() {
    return Game.Mode.GAME_TELEPORTACTIONCARD ;
  }

}