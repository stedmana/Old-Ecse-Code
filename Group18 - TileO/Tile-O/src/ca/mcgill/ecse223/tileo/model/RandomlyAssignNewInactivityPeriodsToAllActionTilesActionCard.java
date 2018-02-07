package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-09e69a5 modeling language!*/


import ca.mcgill.ecse223.tileo.application.TileOApplication;

// line 545 "../../../../../../../../ump/tmp870205/model.ump"
// line 758 "../../../../../../../../ump/tmp870205/model.ump"
public class RandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard extends ActionCard implements Serializable
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public RandomlyAssignNewInactivityPeriodsToAllActionTilesActionCard(String aInstructions, Deck aDeck)
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

    // line 548 "../../../../../../../../ump/tmp870205/model.ump"
    public Game.Mode getActionCardGameMode(){
        return Game.Mode.GAME_RANDOMLYASSIGNNEWINACTIVITYPERIODSTOALLACTIONTILESACTIONCARD;
    }

    // line 552 "../../../../../../../../ump/tmp870205/model.ump"
    public void play(int inactivePeriod){
        Game currentGame = TileOApplication.getTileO().getCurrentGame();
        for(Tile tile : currentGame.getTiles()) {

            if(tile instanceof ActionTile) {
                currentGame.replaceTile(tile, "ActionTile", inactivePeriod);
            }
        }
    }
    private static final long serialVersionUID = 930609121655989380L;

}
