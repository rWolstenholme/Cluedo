package cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cluedo {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Card> deck = new ArrayList<Card>();
	private Weapon mWeapon;
	private Player mPlayer;
	private Room mRoom;
	private int pCount;
	private int currentTurn = 0;
	private Board board;
	private boolean GAME_OVER = false;
	private static Scanner input;

	private enum direction{UP,DOWN,LEFT,RIGHT}

	public static void main(String[] args){
		int pCount = 0;
		input = new Scanner(System.in);
		while(pCount<3||pCount>6){
			System.out.println("How many players? 3-6");
			if(input.hasNextInt()){pCount = input.nextInt();}
			else{input.next();}
		}
		//input.close(); Don't close the scanner here, 
		//as it will also close 'System.in' apparently.
		Cluedo c = new Cluedo(pCount);
	}

	public Cluedo(int pCount){
		Dice die = new Dice();
		this.setpCount(pCount);
		//Create players
		for(Integer i = 0;i<6;i++){
			Player p = new Player(i);
			deck.add(p);
			players.add(p);
		}
		for (int i = 0; i < pCount; i++){
			System.out.println("Player "+players.get(i).getName()+" has joined the game");
		}
		System.out.println("Starting game with "+pCount+" players\n");
		
		mPlayer = players.get((int)Math.random()*6);
		deck.remove(mPlayer);
		//Create weapons
		for(Integer i = 0;i<8;i++){
			Weapon w = new Weapon(i);
			deck.add(w);
			weapons.add(w);
		}
		mWeapon = weapons.get((int)Math.random()*9);
		//Create Rooms
		for(Integer i = 0;i<8;i++){
			Room w = new Room(i);
			deck.add(w);
			rooms.add(w);
		}
		mRoom = rooms.get((int)Math.random()*9);

		//Setup player hands.
		int  i = 0;
		for (Card cr: deck) {
			Player p = players.get(i%pCount);
			p.addToHand(cr);
			i++;
		}

		board = new Board(this);

		while(GAME_OVER == false) {
			Player cP = players.get(currentTurn % pCount);	//Alternates turns between players.
			System.out.println("It is now " + cP.getName() +"'s turn.");
			takeTurn(cP);
			currentTurn++;
		}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void takeTurn(Player p) {
		String answer = ""; 
		input = new Scanner(System.in);
		System.out.println("Type 'roll' to throw your dice.");
		while (!answer.equals("roll")) {
			if (input.hasNext()) {
				answer = input.nextLine();
			}
			else {
				input.next();
			}
		}
		board.print();
		rollMove(p);

		//TODO Ask for announcement/accusations etc.. what ever else a player can do.
		//Check if player in room. use board class
		/*if(player is in room){
			System.out.println("Would you like to start a rumor?");
			if(!askBool()){System.out.println(p.getName()+"'s turn is now over");}
			Room r = askForRoom();
			Weapon w = askForWeapon();
			Player accused = askForChar();
			//TODO loop round players checking for incosistancy
		}
		else if(player is in pool){
			System.out.println("Would you like to make an accusation?");
			if(!askBool()){System.out.println(p.getName()+"'s turn is now over");}
			Room r = askForRoom();
			Weapon w = askForWeapon();
			Player accused = askForChar();
			if(r==mRoom&&w==mWeapon&&accused==mPlayer){
				System.out.println(p.getName()+" deduced correctly, the game has ended");
				System.exit(0);
			}
			else{
				System.out.println(p.getName()+" deduced incorrectly, and is no longer playing");
				p.hasLost();
			}
		}*/
	}
	
	public boolean askBool(){
		System.out.print(" ,enter 'y'/'n'");
		while (true) {
			if (input.hasNext()) {
				String response = input.nextLine();
				if(response.equals("n")){return false;}
				else if (response.equals("y")){return true;}
				else{System.out.print("Invalid response, 'y' or 'n' only");}
			}
			else {
				input.next();
			}
		}
	}
	
	public Player askForChar(){
		System.out.println("Choose a character name");
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Player c:players){
					if(c.getName().toLowerCase().equals(name.toLowerCase())){
						return c;
					}
					else{
						System.out.println("No such character, retry");
					}
				}
			}
			else {input.next();}
		}
	}
	
	public Weapon askForWeapon(){
		System.out.println("Choose a weapon name");
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Weapon w:weapons){
					if(w.getName().toLowerCase().equals(name.toLowerCase())){
						return w;
					}
					else{
						System.out.println("No such weapon, retry");
					}
				}
			}
			else {input.next();}
		}
	}
	
	public Room askForRoom(){
		System.out.println("Choose a room name");
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Room r:rooms){
					if(r.getName().toLowerCase().equals(name.toLowerCase())){
						return r;
					}
					else{
						System.out.println("No such room, retry");
					}
				}
			}
			else {input.next();}
		}
	}

	private void rollMove(Player p) {
		Dice diceRoll = new Dice();
		System.out.println("You rolled a " + diceRoll.getCurrent());
		for(int moves = diceRoll.getCurrent(); moves>0; moves--){
			//TODO Check if next to room Door. 
			//If so, then give option to enter.
			if (p.getRoomIn() != null) {
				//Check rules for moving out of a room, this may need to be inside of 'takeTurn' rather.
			}
			//TODO Also give different options if inside of room currently.
			System.out.println(moves + " Moves left, type 'up','left,'right, or 'down' to move");
			String dir=null;
			while(dir==null){
				if (input.hasNext()) {dir = input.nextLine();}
				else {input.next();}
				try{direction.valueOf(dir.toUpperCase());}
				catch(IllegalArgumentException e){dir = null;}
			}
			direction d = direction.valueOf(dir.toUpperCase());
			switch(d.ordinal()){
			case 0:
				if (board.move(p, 0, -1))
					//if now in a room/door -> moves = 0;
					break;
				else 
					moves++; System.out.println("invalid move");continue;
			case 1:
				if (board.move(p, 0, 1))
					break;
				else 
					moves++; System.out.println("invalid move");continue;
			case 2:
				if (board.move(p, -1, 0))
					break;
				else 
					moves++; System.out.println("invalid move");continue;
			case 3:
				if (board.move(p, 1, 0))
					break;
				else 
					moves++; System.out.println("invalid move");continue;
			default:
				throw new RuntimeException("Direction failure");
			}
			board.print();
			if (board.inRoom(p)) {
				System.out.println("Now inside of the " + p.getRoomIn().getName() + ".");
				moves = 0;
				//TODO if in a room move 'char' to center of a room or something, rather than door.
			}
			
			if (p.getAtLoc().hasCard())
				System.out.println("I should pick up an intrigue card right about now...");
			//x and y are wrong.
			System.out.println(p.getName() + " now at: " + p.getAtLoc().getX() + ","+ p.getAtLoc().getY() +". (These are currently reversed)");
			System.out.println(p.handToString());
		}
	}

	private void setpCount(int pCount) {
		this.pCount = pCount;
	}

	public int getpCount() {
		return pCount;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
