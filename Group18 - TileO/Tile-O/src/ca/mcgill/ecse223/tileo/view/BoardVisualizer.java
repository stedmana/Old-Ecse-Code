package ca.mcgill.ecse223.tileo.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.view.Coordinates2D;
import org.w3c.dom.css.Rect;

public class BoardVisualizer extends JPanel {
	private static final long serialVersionUID = 5765666411683246454L;


	// UI elements
	private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	private List<Line2D> connectLines = new ArrayList<Line2D>();
	private static final int LINEX = 14;
	private static final int LINETOPY = 14;
	int lineHeight;
	private static final int RECTWIDTH = 14;
	private static final int RECTHEIGHT = 14;
	private static final int SPACING = 4;
	private static final int DIMENSION = 16;

	// Player Colors
	private static Color playerOneColor = Color.RED;
	private static Color playerTwoColor = Color.GREEN;
	private static Color playerThreeColor = Color.MAGENTA;
	private static Color playerFourColor = Color.ORANGE;

	// data elements
	private HashMap<Rectangle2D, Tile> rectangle2DTileHashMap;
	private HashMap<Tile, Rectangle2D> tileRectangle2DHashMap;
	//new Data elements experimental
	private HashMap<Rectangle2D, Coordinates2D> rectangle2DCoordinates2DHashMap;
	private HashMap<Coordinates2D, Tile> coordinates2DTileMap;

	private Game game;
	private Tile selectedTile;
	private Coordinates2D selectedCoords;
	private Tile[][] tileArray; // in form tileArray[x][y]

	/**
	 * @apiNote Author : Alex Stedman
	 */
	public BoardVisualizer(Game inputGame) {

		super();

		init(inputGame);

	}

	private void init(Game inputGame) {
		int gameSize = DIMENSION;
		this.game = inputGame;
		selectedCoords = null;
		rectangle2DTileHashMap = new HashMap<Rectangle2D, Tile>();
		tileRectangle2DHashMap = new HashMap<Tile, Rectangle2D>();

		/**
		 * Experimmental Code
		 */
		rectangle2DCoordinates2DHashMap = new HashMap<Rectangle2D, Coordinates2D>();
		coordinates2DTileMap = new HashMap<Coordinates2D, Tile>();

		//tileDetails = null;
		selectedTile = null;
		tileArray = new Tile[gameSize][gameSize];
		connectLines = new ArrayList<Line2D>();

		for (int i = 0; i > tileArray.length; i++) {
			for (int j = 0; j > tileArray[i].length; j++) {
				tileArray[i][j] = findTileByPos(i, j);

			}
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				for (Rectangle2D rectangle : rectangles) {
					if (rectangle.contains(x, y)) {
						selectedTile = rectangle2DTileHashMap.get(rectangle);
						selectedCoords = rectangle2DCoordinates2DHashMap.get(rectangle);
						System.out.println("the coords are x: " + selectedCoords.getX() + " y: " + selectedCoords.getY());
						//System.out.println("the selected tile coords are :" + selectedTile.getX() + " <- x, y -> " + selectedTile.getY());
						break;
					}
				}
				repaint();

			}
		});
	}


	private void doDrawing(Graphics g) {
		if (game != null) {
			int number = game.getTiles().size();
			List<Tile> tileList = new ArrayList<Tile>();
			if (game.getTiles() != null) {
				tileList = game.getTiles();
			}

			//Draw blank board
			if (tileList.isEmpty()) {
				Graphics2D g2d = (Graphics2D) g.create();
				int visibleIndexX = 0;
				int visibleIndexY = 0;

				for (int i = 0; i < 16; i++) {
					visibleIndexY = 0;
					for (int j = 0; j < 16; j++) {
						Coordinates2D currentCoords = new Coordinates2D(i, j);
						Rectangle2D rectangle = new Rectangle2D.Float(LINEX - RECTWIDTH / 2 + visibleIndexX * (RECTWIDTH + SPACING)+4, LINETOPY - RECTHEIGHT / 2 + visibleIndexY * (RECTHEIGHT + SPACING), RECTWIDTH, RECTHEIGHT);
						rectangles.add(rectangle);
						rectangle2DTileHashMap.put(rectangle, tileArray[i][j]);
						tileRectangle2DHashMap.put(tileArray[i][j], rectangle);

						/**
						 * Experimental Code
						 */
						rectangle2DCoordinates2DHashMap.put(rectangle, currentCoords);
						coordinates2DTileMap.put(currentCoords, tileArray[i][j]);

						g2d.setColor(Color.PINK);

						g2d.fill(rectangle);

						if (tileArray[i][j] == null) {
							g2d.setColor(Color.WHITE);
							g2d.draw(rectangle);
						} else if (tileArray.equals(selectedTile)) {
							g2d.setColor(new Color(200, 0, 178));
							g2d.draw(rectangle);
						} else {
							g2d.setColor(Color.BLACK);
							g2d.draw(rectangle);
						}

						visibleIndexY++;
					}
					visibleIndexX++;
				}
			}

			for (Tile currentUsedTile : tileList) {
				int xPos = currentUsedTile.getX();
				int yPos = currentUsedTile.getY();
				tileArray[xPos][yPos] = currentUsedTile;

				Graphics2D g2d = (Graphics2D) g.create();
				BasicStroke thickStroke = new BasicStroke(4);
				g2d.setStroke(thickStroke);
				lineHeight = (number - 1) * (RECTHEIGHT + SPACING);

				BasicStroke thinStroke = new BasicStroke(2);
				g2d.setStroke(thinStroke);
				rectangles.clear();
				//rectangle2DTileHashMap.clear();
				connectLines.clear();
				//int index = 0;
				int visibleIndexX = 0;
				int visibleIndexY = 0;
				List<Player> playerListCurrent = game.getPlayers();

				//this loop draws the tiles in the game
				for (int i = 0; i < tileArray.length; i++) {
					visibleIndexY = 0;
					for (int j = 0; j < tileArray[i].length; j++) {
						Coordinates2D currentCoords = new Coordinates2D(i, j);
						Rectangle2D rectangle = new Rectangle2D.Float(LINEX - RECTWIDTH / 2 + visibleIndexX * (RECTWIDTH + SPACING), LINETOPY - RECTHEIGHT / 2 + visibleIndexY * (RECTHEIGHT + SPACING), RECTWIDTH, RECTHEIGHT);
						rectangles.add(rectangle);
						rectangle2DTileHashMap.put(rectangle, tileArray[i][j]);
						tileRectangle2DHashMap.put(tileArray[i][j], rectangle);

						/**
						 * This is experimental code
						 */
						rectangle2DCoordinates2DHashMap.put(rectangle, currentCoords);
						coordinates2DTileMap.put(currentCoords, tileArray[i][j]);


						if ((tileArray[i][j] instanceof WinTile) && game.getMode() == Game.Mode.DESIGN) {
							g2d.setColor(Color.GRAY);
							//System.out.println("There is a win tile at " + i +" " + j);
						}
						else if ((tileArray[i][j] instanceof WinTile) && game.getMode() != Game.Mode.DESIGN) {
							g2d.setColor(Color.WHITE);
						}
						else if (tileArray[i][j] instanceof ActionTile && game.getMode() == Game.Mode.DESIGN) {



							//ActionTile currentAct = (ActionTile) currentUsedTile;
							//currentAct = currentAct.identifyActionTile(currentUsedTile);
							Color dankColor = new Color(48, 211, 255);
							g2d.setColor(Color.BLUE);

						} else if (tileArray[i][j] instanceof ActionTile && game.getMode() != Game.Mode.DESIGN){
							g2d.setColor(Color.WHITE);
						}else if (tileArray[i][j] instanceof NormalTile) {
							g2d.setColor(Color.WHITE);
						}else if (tileArray[i][j] != null && tileArray[i][j].getHasBeenVisited()) {
							g2d.setColor(Color.BLACK);
						} else {
							g2d.setColor(Color.PINK);
						}

						//This is the for loop that sets the color of the tile to the pre-defined player color, only if the player tile is the same as the current tile.
						int count = 0;
						for (int k = 0; k < playerListCurrent.size(); k++) {
							Player currentP = playerListCurrent.get(k);
							Tile currentPlayerTile = currentP.getCurrentTile();
							if (currentPlayerTile != null) {
								int playerTileX = currentPlayerTile.getX();
								int playerTileY = currentPlayerTile.getY();
								boolean areXEqual = (playerTileX == i);
								boolean areYEqual = (playerTileY == j);
								int currentNumber = currentP.getNumber();
								if (areXEqual && areYEqual) {
									if (currentNumber == 1) {
										g2d.setColor(playerOneColor);
									} else if (currentNumber == 2) {
										g2d.setColor(playerTwoColor);
									} else if (currentNumber == 3) {
										g2d.setColor(playerThreeColor);
									} else if (currentNumber == 4) {
										g2d.setColor(playerFourColor);
									}

								}
							}
						}
						g2d.fill(rectangle);
						//these if statements make the rectangle's outline black if it is a tile
						if (tileArray[i][j] == null) {
							g2d.setColor(Color.WHITE);
							g2d.draw(rectangle);
						} else {
							g2d.setColor(Color.BLACK);
							g2d.draw(rectangle);
						}
						visibleIndexY++;
					}
					visibleIndexX++;
				}
				g2d.setColor(Color.BLACK);

				//This loop draws the connections between tiles
				for (int i = 0; i < game.getConnections().size(); i++) {

					Connection workingConnection = game.getConnections().get(i);
					Tile tile1 = workingConnection.getTile(0);
					Tile tile2 = workingConnection.getTile(1);
					Rectangle2D rect1 = tileRectangle2DHashMap.get(tile1);
					Rectangle2D rect2 = tileRectangle2DHashMap.get(tile2);
					int tile1x = tile1.getX();
					int tile1y = tile1.getY();
					int tile2x = tile2.getX();
					int tile2y = tile2.getY();
					Line2D line = new Line2D.Double(LINEX + tile1x * (RECTWIDTH + SPACING), LINETOPY + tile1y * (RECTWIDTH + SPACING), LINEX + tile2x * (RECTWIDTH + SPACING), LINETOPY + tile2y * (RECTWIDTH + SPACING));
					g2d.draw(line);


				}

			}
			if (selectedCoords != null) {
				String selectedInfo = "The coordinates are x: " + selectedCoords.getX() + " y: " + selectedCoords.getY();
				g.setColor(Color.BLACK);
				g.drawString(selectedInfo, LINEX, (LINETOPY + DIMENSION*(RECTHEIGHT + SPACING)));
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}


	public void drawConnection() {

	}

	public void drawtile() {

	}

	private static Tile findTileByPos(int x, int y) {
		Game game = TileOApplication.getTileO().getCurrentGame();

		//Find tile by position
		List<Tile> tiles = game.getTiles();

		for (Tile tile : tiles) {
			if (tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}

		return null;
	}

	public Tile getSelected() {
		return selectedTile;
	}

	public void setGame(Game g) {
		init(g);
	}

	public Coordinates2D getSelectedCoords() {
		return selectedCoords;
	}
}