/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.PlayController;

// line 116 "../../../../../Tile-O.ump"
public class TurnTileToActionTileActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TurnTileToActionTileActionCard(String aInstructions, Deck aDeck)
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
    return Game.Mode.GAME_TURNTILETOACTIONTILEACTIONCARD;
  }

public void play(Tile tilePicked, int inactivePeriod) {
    PlayController.getCurrentGame().replaceTile(tilePicked,"ActionTile", inactivePeriod); //change tile at new position to new win tile	
}

}