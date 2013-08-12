package cluedo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**Class that represents the game board. 
 * Allows and controls player movement throughout the board.
 * Sets room locations, and player start points.
 *
 */
public class Board {
	private Cluedo game;
	private HashMap<Player, Location> playerLocations;
	private ArrayList<Point> startPos;

	//Board layout.
	int[][]layout=
		{
			{90,90,90,90,90,90,22,10,90,90,90,90,90,10,90,90,90,90,90,90,22,10,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,90,90,10,10,90,90,90,90,90,11,90,90,90,90,90,90,10,10,90,90,90,90,88,88,88,88},
			{90,90,90,90,90,90,10,10,90,90,90,90,90,10,90,90,90,90,90,90,10,10,90,90,90,90,88,88,88,88},
			{90,90,90,90,90,90,10,10,90,90,90,90,90,10,90,90,90,90,90,90,10,10,90,90,90,90,88,88,88,88},  
			{90,90,90,90,90,90,10,11,90,90,90,90,90,10,90,90,90,90,90,90,10,10,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,90, 2,10,10,90,90,90,90,90,10,90,90,90,90,90,90,10,10,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,90,10,10,10,90,90,90,90,90,10,90,90,90,90,90,90,10,11,90,90,90,90,88,88,88,88},  
			{90,90,90,90,90,10,10,10,90,90, 5,90,90,10,90,90,90,90,90,90,10,10,90,90,90,90,88,88,88,88},  
			{10,10,10,11,10,10,10,10,10,10,10,10,10,10,10,10,90, 6,90,10,10,10, 9,90,90,90,88,88,88,88},  
			{22,10,10,10,10,10,10,10,10,10,10,11,10,10,10,10,10,10,10,10,10,10,10,10,10,10,88,88,88,88},  
			{90,90,90,90,10,10,10,10,10,10,10,10,10,10,10,10,11,10,10,10,10,10,10,10,10,10,88,88,88,88},  
			{90,90,90,90,90, 1,90,90,10,10,90,90,90,90, 4,90,90,90,10,90,90,90, 8,90,90,90,88,88,88,88},  
			{90,90,90,90,90,90,90, 1,10,10,90,90,90,90,90,90,90,90,10,90,90,90,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10, 8,90,90,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,90,90,90,90,11,10,90,90,90,90,90,90,90,90,10, 8,90,90,90,90,90,90,88,88,88,88},  
			{90,90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10,90,90,90,90,90,90,90,88,88,88,88},  
			{90,90,90,90,90,90,90, 1,10,10, 4,90,90,90,90,90,90, 4,10,90,90,90,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,90, 1,90,90,10,10,10,10,10,10,11,10,10,10,10,90,90,90,90,90,90,90,88,88,88,88}, 
			{90,90,90,90,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,10,10,10,10,10,88,88,88,88}, 
			{22,10,10,10,10,10,10,10,10,10,90,90, 3,90,90,90,10,10,10,10,10,10,10,10,10,10,88,88,88,88},  
			{10,10,10,10,10,10,10,10,10,10,90,90,90,90,90,90,10,10,10,10,10, 7,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,10,10,10,10,90,90,90,90,90,90,10,10,10,10, 7,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90, 0,10,10,10,90,90,90,90,90,90,10,10,11,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,10,11,90,90,90,90,90,90,90,90,10,10,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10,10,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10,10,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10,10,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10,10,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,10,10,90,90,90,90,90,90,90,90,10,10,10,90,90,90,90,90,90,90,90,90,90},
			{90,90,90,90,90,90,90,22,10,90,90,90,90,90,90,90,90,10,22,10,90,90,90,90,90,90,90,90,90,90}
		};	

	//Actual grid of locations.
	Location grid[][] = new Location [layout.length][layout[0].length];

	/**Create a new board.
	 * 
	 * @param game - Game to represent. 
	 */
	public Board(Cluedo game) {	
		this.game = game;
		startPos = new ArrayList<Point>();
		for (int x = 0; x < layout.length; x++) {
			for (int y = 0; y < layout[x].length; y++) {
				int tile = layout[x][y];
				Location l;
				if(tile==11){l = new Location(x, y, true);}
				else if(tile==88||tile==90){l=null;}
				else{l = new Location(x, y);}
				//Create location.
				grid[x][y]=l;
			}
		}
		//Setup room standing spots.
		for (Room r: game.getRooms()) {
			if (r.getSpot()!=null) {
				Location temp = r.getSpot();
				grid[temp.getX()][temp.getY()] = temp;
			}
		}


		//Set up list of start points. x and y are actually inverted in reference to initial layout.
		startPos.add(new Point(29,18));
		startPos.add(new Point(29,7));
		startPos.add(new Point(19,0));
		startPos.add(new Point(9,0));
		startPos.add(new Point(0,6));
		startPos.add(new Point(0,20));
		playerLocations = new HashMap<Player, Location>();
		for (int i = 0; i < game.getpCount(); i ++){
			//Setup player locations.
			Point tmp = startPos.get(i);
			playerLocations.put(game.getPlayers().get(i), grid[tmp.x][tmp.y]);
			game.getPlayers().get(i).setAtLoc(grid[tmp.x][tmp.y]);
			grid[tmp.x][tmp.y].setP(game.getPlayers().get(i));
			grid[tmp.x][tmp.y].setPlayer(true);
		}

	}

	public boolean move(Player p, int x, int y) {
		int temp;
		//Get the layout value of the new Location.
		try {
			temp = layout[playerLocations.get(p).getX() +x][playerLocations.get(p).getY() + y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		//Allow movement to non-null locations only.
		if (!(temp ==22 || temp < 12)) {
			return false;
		}

		//Store old and new Locations.
		Location oldLoc = playerLocations.get(p);
		Location newLoc = grid[playerLocations.get(p).getX() +x][playerLocations.get(p).getY() + y];

		//Don't allow stacked players.
		if (newLoc.hasPlayer()) {
			return false;
		}

		//Moving to a door.
		if (temp < 10) {
			newLoc = game.getRooms().get(temp).getSpot();
			//Check room is empty.
			if (newLoc.hasPlayer()) {
				return false;
			}
			//Enter room.
			p.setRoomIn(game.getRooms().get(temp));
			p.setEntrance(oldLoc);
		}
		//Clear any old values.
		else {
			p.setEntrance((Location)null);
			p.setRoomIn((Room)null);
		}

		//Set new locations and clear old ones.
		oldLoc.setPlayer(false);
		newLoc.setPlayer(true);
		oldLoc.setP(null);
		newLoc.setP(p);
		p.setAtLoc(newLoc);
		playerLocations.put(p, newLoc);

		return true;
	}

	/**Method to exit a room, and place the player back at the entrance.
	 * 
	 * @param p - Player 
	 */
	public void exitRoom(Player p) {
		Location oldLoc = p.getAtLoc();
		Location newLoc = p.getEntrance();

		oldLoc.setPlayer(false);
		newLoc.setPlayer(true);
		oldLoc.setP((Player)null);
		newLoc.setP(p);
		p.setAtLoc(newLoc);
		playerLocations.put(p, newLoc);
		p.setRoomIn((Room)null);
	}

	/**Attempt to take the given player through the current rooms secret passage.
	 * 
	 * @param p - Player
	 * @return - Boolean, whether it succeeded.
	 */
	public boolean takePassage(Player p) {
		//Get current room.
		switch (p.getRoomIn().getName().toLowerCase()) {
		case "kitchen":
			moveTo(grid[8][21],"observatory", p);
			break;
		case "observatory":
			moveTo(grid[22][6], "kitchen", p);
			break;
		case "guest house":
			moveTo(grid[6][5], "spa", p);
			break;
		case "spa":
			moveTo(grid[20][20], "guest house", p);
			break;
		default:
			return false;
		}
		return true;
	}

	/**Method to move a player to a completely new room.
	 * 
	 * @param enter - Location, new Room entrance
	 * @param roomName - Name of new room
	 * @param p - Player
	 */
	private void moveTo(Location enter, String roomName, Player p) {
		Room oldRoom = p.getRoomIn();
		Room newRoom = null;
		//Find room
		loop:
			for (Room r: game.getRooms()) {
				if (r.getName().toLowerCase().equals(roomName)) {
					//Store new room.
					newRoom = r;
					break loop;
				}
			}

		p.getAtLoc().setPlayer(false);
		newRoom.getSpot().setPlayer(true);
		oldRoom.getSpot().setP((Player)null);
		newRoom.getSpot().setP(p);
		playerLocations.put(p, newRoom.getSpot());

		p.setRoomIn(newRoom);
		p.setAtLoc(p.getRoomIn().getSpot());

		p.setEntrance(enter);
	}

	/**Method to print the current state of the game board.
	 * 
	 */
	public void print() {
		String str = "";
		for (int x = 0; x < layout.length; x++) {
			StringBuilder line = new StringBuilder();
			for (int y = 0; y < layout[x].length; y++) {
				int tile = layout[x][y];
				switch (tile) {
				//Characters used to represent the varying game aspects.
				case 10:
					str = " - ";
					break;
				case 90:
					str = " + ";
					break;
				case 88:
					str = " # ";
					break;
				case 22:
					str = " % ";
					break;
				case 11:
					str = " ? ";
					break;
					//Doors
				default:
					str = "[+]";
					break;
				}

				//Display players.
				if (grid [x][y] != null && grid[x][y].hasPlayer && (!grid[x][y].getP().getHasLost())) {
					str = " " +grid[x][y].getP().getName().substring(0, 1) + " ";
				}
				line.append(str);
			}
			System.out.println(line.toString());
		}
	}
	
	public Location getPlayerLoc(Player p){
		return playerLocations.get(p);
	}
}
