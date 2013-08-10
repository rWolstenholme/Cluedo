package cluedo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private Cluedo game;
	private HashMap<Player, Location> playerLocations;
	private ArrayList<Point> startPos;

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

	Location grid[][] = new Location [layout.length][layout[0].length];

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
				//TODO Holds intrigue card?
				//Maybe something about being a door?
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


		//Set up list of start points. x and y are actually inverted.
		startPos.add(new Point(29,18));
		startPos.add(new Point(29,7));
		startPos.add(new Point(19,0));
		startPos.add(new Point(9,0));
		startPos.add(new Point(0,6));
		startPos.add(new Point(0,20));
		playerLocations = new HashMap<Player, Location>();
		for (int i = 0; i < game.getpCount(); i ++){
			Point tmp = startPos.get(i);
			playerLocations.put(game.getPlayers().get(i), grid[tmp.x][tmp.y]);
			game.getPlayers().get(i).setAtLoc(grid[tmp.x][tmp.y]);
			grid[tmp.x][tmp.y].setP(game.getPlayers().get(i));
			grid[tmp.x][tmp.y].setPlayer(true);
		}

	}

	public boolean move(Player p, int x, int y) {
		//TODO Either include each room in this, or have a separate method for entering rooms.
		int temp;
		try {
			temp = layout[playerLocations.get(p).getX() +x][playerLocations.get(p).getY() + y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		if (!(temp ==22 || temp < 12)) {
			return false;
		}

		//Get old location, set hasPlayer to false;
		//Get new location, set has player to true;
		//Replace old location in map with new location;
		Location oldLoc = playerLocations.get(p);
		Location newLoc = grid[playerLocations.get(p).getX() +x][playerLocations.get(p).getY() + y];

		if (newLoc.hasPlayer()) {
			return false;
		}

		if (temp < 10) {
			newLoc = game.getRooms().get(temp).getSpot();
			if (newLoc.hasPlayer()) {
				return false;
			}
			p.setRoomIn(game.getRooms().get(temp));
			p.setEntrance(oldLoc);
			//Secret passage will need it's own version entrance.
		}
		else {
			p.setEntrance((Location)null);
			p.setRoomIn((Room)null);
		}

		oldLoc.setPlayer(false);
		newLoc.setPlayer(true);
		oldLoc.setP(null);
		newLoc.setP(p);
		p.setAtLoc(newLoc);
		playerLocations.put(p, newLoc);

		return true;

	}

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

	public boolean takePassage(Player p) {
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

	private void moveTo(Location enter, String roomName, Player p) {
		Room oldRoom = p.getRoomIn();
		Room newRoom = null;
		loop:
			for (Room r: game.getRooms()) {
				if (r.getName().toLowerCase().equals(roomName)) {
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

	public void print() {
		String str = "";
		for (int x = 0; x < layout.length; x++) {
			StringBuilder line = new StringBuilder();
			for (int y = 0; y < layout[x].length; y++) {
				int tile = layout[x][y];
				switch (tile) {
				//Placeholder characters.
				//TODO choose decent looking chars.
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

				if (grid [x][y] != null && grid[x][y].hasPlayer && (!grid[x][y].getP().getHasLost())) {
					str = " " +grid[x][y].getP().getName().substring(0, 1) + " ";
				}
				line.append(str);
			}
			System.out.println(line.toString());
		}
	}
}
