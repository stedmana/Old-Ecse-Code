/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;


import java.io.Serializable;

// line 84 "../../../../../Tile-O.ump"
public class LoseTurnActionCard extends ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoseTurnActionCard(String aInstructions, Deck aDeck)
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
   * Skips the turn of the currentPlayer, the next player plays and increments inactivity period
   *
   *
   * @param currentPlayer
   * @return void
   * @apiNote Author : Yaniv Bronshtein 260618099 and Kartik Misra 260663577
   */
  public void play(Player currentPlayer) throws InvalidInputException {
    currentPlayer.loseTurns(1); //make player lose this turn
  }


  private static final long serialVersionUID = 8886099581657149380L;
  @Override
  public Game.Mode getActionCardGameMode() {
    return Game.Mode.GAME_LOSETURNACTIONCARD;
  }
}
