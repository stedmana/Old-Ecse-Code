/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.util.List;

import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 128 "../../../../../Tile-O.ump"
public class NextPlayerRollsOneActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NextPlayerRollsOneActionCard(String aInstructions, Deck aDeck)
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

  @Override
  public Game.Mode getActionCardGameMode() {
    return Mode.GAME_NEXTPLAYERROLLSONEACTIONCARD;
  }

public List<Tile> play() {
    Game currentGame = PlayController.getCurrentGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    Player nextPlayer = currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1);
    nextPlayer.loseTurns(1);
    currentGame.setCurrentPlayer(nextPlayer); //Todo: Use determineNextPlayer() instead

    List<Tile> tiles = nextPlayer.getPossibleMoves(1);
    currentGame.setMode(Game.Mode.GAME);
    return tiles;
	
}

}