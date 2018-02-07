/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 39 "../../../../../Tile-O.ump"
//line 64 "../../../../../../../../ump/tmp601798/model.ump"
//line 105 "../../../../../../../../ump/tmp601798/model.ump"

public class ActionTile extends Tile implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int inactivityPeriod;
  private int turnsUntilActive;
  //ActionTile State Machines
  public enum ActionTileStatus { Active, Inactive }
  private ActionTileStatus actionTileStatus;
  
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod)
  {
    super(aX, aY, aGame);
    inactivityPeriod = aInactivityPeriod;
    turnsUntilActive = 0;
    setActionTileStatus(ActionTileStatus.Active);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getInactivityPeriod()
  {
    return inactivityPeriod;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "inactivityPeriod" + ":" + getInactivityPeriod()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]"
            + outputString;
  }




  /**
   *
   * Reactive method to landing on action tile. Sets player's current tile  Checks if inactivity period is over.
   * If it is then the deck is retrieved, a card is drawn, the deck is updated and the action tile is set to inactive
   * Otherwise, the turnUntilActive is decremented.
   * and
   *
   * @param none
   * @return void
   * @apiNote Author : Yaniv Bronshtein and Ananya Chandra
   */

  public void land() {
	int nextPlayerIndex;
    Game currentGame = this.getGame();
    Player currentPlayer = currentGame.getCurrentPlayer();
    currentPlayer.setCurrentTile(this);
    this.setHasBeenVisited(true);
    if(turnsUntilActive == 0) {
      Deck deck = currentGame.getDeck();
      ActionCard currentCard = deck.getCurrentCard();
      Game.Mode actionCardMode = currentCard.getActionCardGameMode();
      int nextCardIndex = deck.indexOfCard(currentCard) + 1;
      deck.setCurrentCard(deck.getCard(nextCardIndex));
      turnsUntilActive = inactivityPeriod+1; //reset inactivity period
      currentGame.setMode(actionCardMode);
    }
    else {
    	//this only lowers turnUntilActive if the tile is landed on again, we want it to decrement every turn
//      if (turnsUntilActive > 0){
//        turnsUntilActive--;
      //}
    }
    if(currentGame.indexOfPlayer(currentPlayer)==currentGame.getPlayers().size()-1)
    	nextPlayerIndex = 0;
    else
    	nextPlayerIndex = currentGame.indexOfPlayer(currentPlayer) +1;
    currentGame.setCurrentPlayer(currentGame.getPlayer(nextPlayerIndex));
  }

  /**
   *
   * Method that either turns a tile into actionTile, or creates an actionTile.
   *
   *
   *
   *
   * @param Tile tile
   * @return ActionTile
   * @apiNote Author : Kartik Misra 260663577
   */


  public ActionTile identifyActionTile (Tile tile){
    Game currentGame = this.getGame();
    if(identifyActionTile(tile).getX() == tile.getX() && identifyActionTile(tile).getY() == tile.getY()){
      ActionTile actionTile = new ActionTile(tile.getX(), tile.getY(), currentGame, 0);
      tile = null;
      return actionTile;
    }
    else{
      ActionTile actionTile = new ActionTile(tile.getX(), tile.getY(), currentGame, 0);
      return actionTile;
    }
  }
  
  public String getActionTileStatusFullName()
  {
    String answer = actionTileStatus.toString();
    return answer;
  }

  public ActionTileStatus getActionTileStatus()
  {
    return actionTileStatus;
  }

  public boolean deactivate()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Active:
        if (getInactivityPeriod()>0)
        {
        // line 67 "../../../../../../../../ump/tmp601798/model.ump"
          setTurnsUntilActive(getInactivityPeriod() + 1);
          setActionTileStatus(ActionTileStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeTurn()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Inactive:
        if (getTurnsUntilActive()>1)
        {
        // line 72 "../../../../../../../../ump/tmp601798/model.ump"
          setTurnsUntilActive(getTurnsUntilActive() - 1);
          setActionTileStatus(ActionTileStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        if (getTurnsUntilActive()<=1)
        {
        // line 75 "../../../../../../../../ump/tmp601798/model.ump"
          setTurnsUntilActive(0);
          setActionTileStatus(ActionTileStatus.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setActionTileStatus(ActionTileStatus aActionTileStatus)
  {
    actionTileStatus = aActionTileStatus;
  }


  private static final long serialVersionUID = 2225072607928790501L;

}