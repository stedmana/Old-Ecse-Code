/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.io.Serializable;
import java.util.List;

// line 68 "../../../../../Tile-O.ump"
public class RollDieActionCard extends ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RollDieActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public List<Tile> play(){

    Game currentGame = TileOApplication.getTileO().getCurrentGame();

    Die die = currentGame.getDie();
    int number = die.roll();
    Player currentPlayer = currentGame.getCurrentPlayer();
    
    List<Tile> tiles = currentPlayer.getPossibleMoves(number);

    return tiles;
  }

  public void delete()
  {
    super.delete();
  }

  private static final long serialVersionUID = 4435072651528790501L;

  @Override
  public Mode getActionCardGameMode() {
    return Game.Mode.GAME_ROLLDIEACTIONCARD;
  }

}