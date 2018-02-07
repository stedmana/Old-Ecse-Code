/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

// line 124 "../../../../../Tile-O.ump"
// line 132 "../../../../../Tile-O.ump"
public class WinTileHintActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTileHintActionCard(String aInstructions, Deck aDeck)
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
    return Game.Mode.GAME_WINTILEHINTACTIONCARD;
  }

public boolean play(Tile selectedTile, int x, int y) throws InvalidInputException {
	Game currentGame = PlayController.getCurrentGame();
    boolean returnValue = false;
    WinTile currentWin = currentGame.getWinTile();
    if (selectedTile == null) throw new InvalidInputException("Selected tile is not currently a valid tile.");
    //These variables are used to check if the selected tile is the win tile, or one of its neighbours
    int overX = currentWin.getX() + 1;
    int underX = currentWin.getX() - 1;
    int overY = currentWin.getY() + 1;
    int underY = currentWin.getY() - 1;
    if (y == currentWin.getY() && (x <= overX && x >= underX)) {
        returnValue = true;
    }
    else if (x == currentWin.getX() && ( y <= overY && y >= underY)) {
        returnValue = true;
    }	
    
    return returnValue;
    
}

}