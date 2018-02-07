/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.PlayController;

// line 108 "../../../../../Tile-O.ump"
public class MoveWinTileActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MoveWinTileActionCard(String aInstructions, Deck aDeck)
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
  
  public void play(Tile tilePicked){
	  Game currentGame = PlayController.getCurrentGame();
	  Tile oldWinTile = currentGame.getWinTile(); //get previous winTile
      currentGame.replaceTile(oldWinTile, "NormalTile",0); //change old win Tile to normal tile
      currentGame.replaceTile(tilePicked,"WinTile", 0); //change tile at new position to new win tile
  }

  @Override
  public Game.Mode getActionCardGameMode() {
    return Game.Mode.GAME_MOVEWINTILEACTIONCARD;
  }

}