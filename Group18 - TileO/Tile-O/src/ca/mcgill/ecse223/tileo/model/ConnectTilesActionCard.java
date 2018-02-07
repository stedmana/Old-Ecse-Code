/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.io.Serializable;

// line 72 "../../../../../Tile-O.ump"
public class ConnectTilesActionCard extends ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ConnectTilesActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean play(Tile tile1, Tile tile2) throws InvalidInputException{

    boolean success = false;

    Game currentGame = TileOApplication.getTileO().getCurrentGame();
    Connection newConnection = new Connection(currentGame);
    newConnection.addTile(tile1);
    newConnection.addTile(tile2);
    tile1.addConnection(newConnection);
    tile2.addConnection(newConnection);
    success = currentGame.addConnection(newConnection);
    currentGame.setCurrentConnectionPieces(currentGame.getCurrentConnectionPieces()-1);
    return success;
  }



  public void delete()
  {
    super.delete();
  }

  private static final long serialVersionUID = -4440912597282882073L;

  @Override
  public Mode getActionCardGameMode() {


    return Game.Mode.GAME_CONNECTTILESACTIONCARD;
  }


}