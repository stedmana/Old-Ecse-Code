/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.PlayController;

// line 112 "../../../../../Tile-O.ump"
public class RevealTileActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RevealTileActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String play(Tile selectedTile){
      String finalMessage;
      Game currentGame = PlayController.getCurrentGame();

      if(currentGame.getTiles().contains(selectedTile)) {

          if (selectedTile instanceof WinTile) {
              finalMessage = "The selected tile is the Win Tile !";
          } else if (selectedTile instanceof ActionTile) {
              finalMessage = "The selected tile is and Action Tile !";
          } else {
              finalMessage = "The selected tile is a Regular Tile !";
          }
      }
      else {
          finalMessage = "Please selected an active tile";
      }

      return finalMessage;
  }
  
  public void delete()
  {
    super.delete();
  }

  @Override
  public Game.Mode getActionCardGameMode() {
    return Game.Mode.GAME_REVEALTILEACTIONCARD;
  }

}