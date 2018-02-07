# Tile-O Controller Interface

## Game Designer

### createGame(size, players)
Create a new game with size and number of players. Will return a new instance of Game.

 - Author
 	- Connor Fowlie 260687955
 - Parameters
 	- size : int 
 	- players : int
 - Returns
 	- Game

### addTile(x, y, type)
Add a tile to the game board at position x,y and with specific type (Regular, Action or Hidden)

 - Author
 	- Connor Fowlie 260687955
- Parameters
 	- x : int 
 	- y : int
 	- type : Enum (REGULAR, ACTION, HIDDEN)
 - Returns
 	- Success/Fail

### addTile(x, y)
Remove a tile to the game board at position x, y

 - Author
 	- Connor Fowlie 260687955
- Parameters
 	- x : int 
 	- y : int
 - Returns
 	- Success/Fail

### saveGameDesign()
In design mode, saves game design to Persistence store

 - Author
 	- Yaniv Bronshtein 260618099

 - Returns
 	- success : bool

### getGameDesign()
In design mode, retrieves game design from Persistence store

 - Author
 	- Yaniv Bronshtein 260618099

 - Returns
 	- game : Game

### displayGameDesign()
In design mode, loads retrieved game design onto UI

 - Author
 	- Yaniv Bronshtein 260618099

 - Returns
 	- void

### shuffleDeck()
In play mode, shuffles Deck.

 - Author
 	- Yaniv Bronshtein 260618099

 - Parameters
 	- aDeck: Deck

 - Returns
 	- shuffledDeck: Deck

### endGame()
In play mode, ends game if winner tile is activated

 - Author
 	- Yaniv Bronshtein 260618099

 - Parameters
 	- none

 - Returns
 	- void

### returnInstructions()
In play mode, returns instructions contained in Action Card

 - Author
 	- Yaniv Bronshtein 260618099

 - Parameters
 	- called by specific action card

 - Returns
 	- instructions: String

### saveGame()
In play mode, saves a game so it can be accessed and continued at a later time

 - Author
	- Ananya Chandra 260682532

 - Parameters
	- currentConnectionPieces: int
	- aDeck: Deck
	- aDie: Die

 -Returns
	- success/fail

### loadGame()
In play mode, displays saved game so it can be continued

 - Author:
	- Ananya Chandra 260682532

 - Parameters
	- user selection from list

 - Returns
	- Game instance

### specifyInactivityPeriod()
sets inactivity period of action tiles

- Author
	- Ananya Chandra 260682532
- Parameters
	- int: numTurns
- Returns
	- void

## Game

### takeCard()
Returns the next Action Card. Once all cards have been drawn, shuffles the deck.

 - Author
	- Jackson Li 260681801

 - Parameters
	-
 - Returns
	- ActionCard

### applyAddConnectionActionCard(Tile tile1, Tile tile2)
Adds a connector piece between the two selected tiles. throws InvalidExceptions

 - Author
	- Jackson Li 260681801
 - Parameters
	- tile1 : Tile
	- tile2 : Tile
 - Returns
	- void

### extraTurnActionCard()
Rolls a die and returns a list of possible tiles the current player can move to. 

 - Author
	- Jackson Li 260681801
 - Parameters
	-
 - Returns
	- List\<Tile\>

### moveCurrentPlayer(Tile tile1)
Moves the current player to designated tile.

 - Author
	- Jackson Li 260681801
 - Parameters
	- tile1 : Tile
 - Returns
	- void

### addConnection(Tile tile1,Tile tile2)
Creates a connection between tile1 and tile2.

 - Author
	- Alex Stedman 260627145
 - Parameters
	- tile1 : Tile
	- tile2 : Tile
 - Returns
	- void

### addConnection(Tile tile1,Tile tile2)
Removes the connection between tile1 and tile2.

 - Author
	- Alex Stedman 260627145
 - Parameters
	- tile1 : Tile
	- tile2 : Tile
 - Returns
	- void

### getWinTile()
Returns the winning tile.

 - Author
	- Alex Stedman 260627145
 - Parameters
   - 
 - Returns
	- WinTile
	
### getStartTile()
returns starting tile of player

- Author
	- Kartik Misra 260663577
- Parameters
	- currentPlayer
- Return
	- startTile

### getActionTile()
returns wheather or not the tile is an action tile

- Author
	- Kartik Misra 260663577
	
- Parameters
	- Tile
- Return
	- ActionTile
	
### select32Cards()

- Author
	- Kartik Misra 260663577
- Parameters
	- RollDieActionCard
	- ConnectTilesActionCard
	- RemoveConnectionActionCard
	- TeleportActionCard
	- LoseTurnActionCard
- Return
	- Deck.addCard(ActionCard)

###playLoseTurnActionCard()
skips the current players turn

- Author
	- Ananya Chandra 260682532
-Parameters
	- Player: currentPlayer
-Returns
	- void