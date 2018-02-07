/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.io.Serializable;

// line 76 "../../../../../Tile-O.ump"
public class RemoveConnectionActionCard extends ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RemoveConnectionActionCard(String aInstructions, Deck aDeck)
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


  public void play(Connection connection) throws InvalidInputException {
    //int numberOfConnections = connection.getTiles().size();
    for(Tile tile : connection.getTiles()) {
      tile.removeConnection(connection);
      connection.removeTile(tile);
    }

  }
  private static final long serialVersionUID = -223380271454467836L;
  @Override
  public Mode getActionCardGameMode() {
    return Game.Mode.GAME_REMOVECONNECTIONACTIONCARD;
  }


}