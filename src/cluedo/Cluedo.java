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
		while(pCount<1||pCount>6){
			System.out.println("How many players? 3-6");
			if(input.hasNextInt()){pCount = input.nextInt();}
			else{input.next();}
		}
		//input.close(); Don't close the scanner here, 
		//as it will also close 'System.in' apparently.
		//Cluedo c = new Cluedo(pCount); Do we need a var?
		new Cluedo(pCount);
	}

	public Cluedo(int pCount){
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
			if(cP.getLost()) {
				currentTurn++;
				continue;
			}
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

		//Check if player in room. use board class
		if(p.getRoomIn() != null && (!p.getRoomIn().getName().equalsIgnoreCase("Pool"))){
			System.out.println("Would you like to start a rumor?");
			if(!askBool()){System.out.println(p.getName()+"'s turn is now over");}
			else{
				Room r = p.getRoomIn();
				Weapon w = askForWeapon();
				Player accused = askForChar();
				//Works out the players index to go clockwise from, hacky sorta
				int pIndex = 0;
				while(players.get(pIndex)!=p){pIndex++;}
				pIndex++;
				for(int i = 0;i!=pIndex;i++){
					if(i>pCount){i=0;}
					Player skeptic = players.get(i);
					if(skeptic.getHand().contains(r)){System.out.println("Dissproved by "+skeptic.getName()+" who showed "+r.getName());break;}
					if(skeptic.getHand().contains(w)){System.out.println("Dissproved by "+skeptic.getName()+" who showed "+w.getName());break;}
					if(skeptic.getHand().contains(accused)){System.out.println("Dissproved by "+skeptic.getName()+" who showed "+accused.getName());break;}
				}
			}
		}
		else if(p.getRoomIn() != null && p.getRoomIn().getName().equalsIgnoreCase("Pool")){
			System.out.println("Would you like to make an accusation?");
			if(!askBool()){System.out.println(p.getName()+"'s turn is now over");}
			else{
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
			}
		}
	}

	public boolean askBool(){
		System.out.print("Enter 'y'/'n'");
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
		System.out.println("Choose a character name, possible options are ");
		for(Player c:players){System.out.print(c.getName()+",");}
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Player c:players){
					if(c.getName().equalsIgnoreCase(name)){
						return c;
					}
				}
				System.out.println("No such player, retry");
			}
			else {input.next();}
		}
		
	}

	public Weapon askForWeapon(){
		System.out.println("Choose a weapon, possible options are ");
		for(Weapon w:weapons){System.out.print(w.getName()+",");}
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Weapon w:weapons){
					if(w.getName().equalsIgnoreCase(name)){
						return w;
					}
				}
				System.out.println("No such weapon, retry");
			}
			else {input.next();}
		}
	}

	public Room askForRoom(){
		System.out.println("Choose a room name, possible options are ");
		for(Room c:rooms){System.out.print(c.getName()+",");}
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Room r:rooms){
					if(r.getName().equalsIgnoreCase(name)){
						return r;
					}
				}
				System.out.println("No such room, retry");
			}
			else {input.next();}
		}
	}

	private void rollMove(Player p) {
		Dice diceRoll = new Dice();
		System.out.println("You rolled a " + diceRoll.getCurrent());
		mainloop:
		for(int moves = diceRoll.getCurrent(); moves>0; moves--){
			if (p.getRoomIn() != null) {
				if (!exitRoom(p)) {
					moves++;
					System.out.println("Sorry, this room does not contain a secret passage");
					continue mainloop;
				}
				moves--;
				if (p.getRoomIn() != null) {
					break mainloop;
				}
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
				if (board.move(p, -1, 0))
					//if now in a room/door -> moves = 0;
					break;
				else 
					moves++; System.out.println("Invalid move!");continue;
			case 1:
				if (board.move(p, 1, 0))
					break;
				else 
					moves++; System.out.println("Invalid move!");continue;
			case 2:
				if (board.move(p, 0, -1))
					break;
				else 
					moves++; System.out.println("Invalid move!");continue;
			case 3:
				if (board.move(p, 0, 1))
					break;
				else 
					moves++; System.out.println("Invalid move!");continue;
			default:
				throw new RuntimeException("Direction failure encountered.");
			}
			board.print();
			if (p.getRoomIn() !=null) {
				System.out.println("Now inside of the " + p.getRoomIn().getName() + ".");
				moves = 0;
				//TODO if in a room move 'char' to center of a room or something, rather than door.
			}

			if (p.getAtLoc().hasCard())
				System.out.println("Pick up intrigue card.");
			//X and Y are set wrong way around.
			System.out.println(p.getName() + " now at: " + p.getAtLoc().getY() + ","+ p.getAtLoc().getX());
			System.out.println(p.handToString());
		}
	}

	private boolean exitRoom(Player p) {
		System.out.println("Type 'exit' to leave the room, or 'sneak' to use the secret passage(May or may not exist)");			
		String option = null;
		while(option == null){
			if (input.hasNext()) {
				option = input.nextLine();
				if (!(option.equalsIgnoreCase("sneak") || option.equalsIgnoreCase("exit")))
					option = null;
			}
			else {input.next();}
		}

		if (option.equalsIgnoreCase("exit")) {
			board.exitRoom(p);
		}
		else if (option.equalsIgnoreCase("sneak")) {
			if (!board.takePassage(p))
				return false;
		}
		//TODO 'exit' -> move to last position
		//Passage exists && 'sneak' -> move to the opposite room.
		board.print();
		return true;
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
