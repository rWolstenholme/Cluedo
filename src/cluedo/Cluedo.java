package cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Represents a game of cluedo.
 * Holds the list of all players, rooms, and weapons.
 * Also keeps track of the murder cards.
 * 
 * Sets up the game, and controls turn taking.
 *
 */
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

	/**Asks how many players, then starts a new game of 
	 * cluedo with the given number of players.
	 * 
	 */
	public static void main(String[] args){
		int pCount = 0;
		input = new Scanner(System.in);
		//Get number of players.
		while(pCount<3||pCount>6){
			System.out.println("How many players? 3-6");
			if(input.hasNextInt()){pCount = input.nextInt();}
			else{input.next();}
		}
		//Create new game.
		new Cluedo(pCount);
	}

	/**Creates a new game a cluedo with the given number of players.
	 * 
	 * @param pCount - Number of players.
	 */
	public Cluedo(int pCount){
		this.setpCount(pCount);
		//Create players
		for(Integer i = 0;i<6;i++){
			Player p = new Player(i);
			deck.add(p);
			players.add(p);
		}
		//Display players of the game.
		for (int i = 0; i < pCount; i++){
			System.out.println("Player "+players.get(i).getName()+" has joined the game");
		}
		System.out.println("Starting game with "+pCount+" players\n");

		mPlayer = players.get((int)(Math.random()*6));
		deck.remove(mPlayer);
		//Create weapons
		for(Integer i = 0;i<8;i++){
			Weapon w = new Weapon(i);
			deck.add(w);
			weapons.add(w);
		}
		mWeapon = weapons.get((int)(Math.random()*9));
		deck.remove(mWeapon);
		//Create Rooms
		for(Integer i = 0;i<8;i++){
			Room w = new Room(i);
			deck.add(w);
			rooms.add(w);
		}
		mRoom = rooms.get((int)(Math.random()*9));
		deck.remove(mRoom);
		//Setup player hands.
		int  i = 0;
		for (Card cr: deck) {
			Player p = players.get(i%pCount);
			p.addToHand(cr);
			i++;
		}

		//Create the board for this game.
		board = new Board(this);

		//Take each players turn, unless they have lost.
		while(GAME_OVER == false) {
			Player cP = players.get(currentTurn % pCount);	//Alternates turns between players.
			if(cP.getHasLost()) {
				currentTurn++;
				continue;
			}
			System.out.println("It is now " + cP.getName() +"'s turn.");
			takeTurn(cP);
			currentTurn++;
		}

		System.out.println("GAME OVER");
	}

	/**Returns a list of all the players in the game.
	 * 
	 * @return - List of all players.
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**Method to allow a player to take their turn 
	 * with various choice upon how to act.
	 * 
	 * @param p - Player of which to take the turn.
	 */
	public void takeTurn(Player p) {
		String answer = ""; 
		input = new Scanner(System.in);
		System.out.println("Type 'roll' to throw your dice.");
		//Wait for user input
		while (!answer.equals("roll")) {
			if (input.hasNext()) {
				answer = input.nextLine();
			}
			else {
				input.next();
			}
		}
		//Print current board state.
		board.print();
		//Print player's hand of cards.
		System.out.println(p.handToString());
		//Call method allowing the player to move
		rollMove(p);

		//Check if player in a room.
		if(p.getRoomIn() != null && (!p.getRoomIn().getName().equalsIgnoreCase("Pool"))){
			//Ask for user input
			System.out.println("Would you like to start a rumor?");
			if(!askBool()){System.out.println(p.getName()+"'s turn is now over");}
			else{
				//Get the rumored cards. 
				Room r = p.getRoomIn();
				Weapon w = askForWeapon();
				Player accused = askForChar();
				//Works out the players index to go clockwise from.
				int pIndex = 0;
				while(players.get(pIndex)!=p){pIndex++;}
				pIndex++;
				//Disapprove if one of the players holds one of the rumored cards.
				for(int i = 0;i!=pIndex;i++){
					if(i>pCount){i=0;}
					Player skeptic = players.get(i);
					if(skeptic.getHand().contains(r)){System.out.println("Dissproved by "+skeptic.getName()+" who showed "+r.getName());break;}
					if(skeptic.getHand().contains(w)){System.out.println("Dissproved by "+skeptic.getName()+" who showed "+w.getName());break;}
					if(skeptic.getHand().contains(accused)){System.out.println("Dissproved by "+skeptic.getName()+" who showed "+accused.getName());break;}
				}
			}
		}
		//Check if player is in the pool.
		else if(p.getRoomIn() != null && p.getRoomIn().getName().equalsIgnoreCase("Pool")){
			System.out.println("Would you like to make an accusation?");
			//Ask for user input.
			if(!askBool()){System.out.println(p.getName()+"'s turn is now over");}
			else{
				//Get accused cards.
				Room r = askForRoom();
				Weapon w = askForWeapon();
				Player accused = askForChar();
				//Check if game has been won.
				if(r==mRoom&&w==mWeapon&&accused==mPlayer){
					System.out.println(p.getName()+" deduced correctly, the game has ended!");
					System.out.println("The murder cards were: "+ mPlayer.getName()+", " + 	mRoom.getName() +", " + mWeapon.getName()+".");
					System.exit(0);
				}
				//Player has lost.
				else{
					System.out.println(p.getName()+" deduced incorrectly, and is no longer playing.");
					p.hasLost();
					int lost = 0;
					//Check if all players have lost.
					for (int i = 0; i < pCount; i++) {
						if (players.get(i).getHasLost())
							lost++;
					}
					if (lost == pCount)
						GAME_OVER = true;
				}
			}
		}
	}

	/**Method to ask whether for a 'Yes' or 'No' input.
	 * Yes is true, No is false.
	 * 
	 * @return - Returns user answer. 
	 */
	public boolean askBool(){
		System.out.print("Enter 'y'/'n'\n");
		while (true) {
			if (input.hasNext()) {
				String response = input.nextLine();
				if(response.equals("n")){return false;}
				else if (response.equals("y")){return true;}
				//If unknown response
				else{System.out.print("Invalid response, 'y' or 'n' only\n");}
			}
			else {
				input.next();
			}
		}
	}

	/**Method to ask a user for a character name, which 
	 * returns the player referred to by that name.
	 * 
	 * @return - Player with the given name.
	 */
	public Player askForChar(){
		System.out.println("Choose a character name, possible options are ");
		//Display list of characters.
		for(Player c:players){System.out.print(c.getName()+", ");}
		System.out.print("\n");
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

	/**Method to ask a user for a weapon name, which
	 * returns the player referred to by that name.
	 * 
	 * @return - Weapon with the given name.
	 */
	public Weapon askForWeapon(){
		System.out.println("Choose a weapon, possible options are ");
		//Display list of weapons.
		for(Weapon w:weapons){System.out.print(w.getName()+", ");}
		System.out.print("\n");
		while(true){
			if (input.hasNext()) {
				String name = input.nextLine();
				for(Weapon w:weapons){
					//Compare input to names.
					if(w.getName().equalsIgnoreCase(name)){
						return w;
					}
				}
				System.out.println("No such weapon, retry");
			}
			else {input.next();}
		}
	}

	/**Method to ask a user for a room name, which
	 * returns the player referred to by that name.
	 * 
	 * @return - Room with the given name.
	 */
	public Room askForRoom(){
		System.out.println("Choose a room name, possible options are ");
		//Display a list of room names.
		for(Room c:rooms){System.out.print(c.getName()+", ");}
		System.out.print("\n");
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

	/**Method to roll a dice and allow player movement.
	 * 
	 * @param p - Player of which to move.
	 */
	private void rollMove(Player p) {
		Dice diceRoll = new Dice();
		//Roll a dice and display the roll.
		System.out.println("You rolled a " + diceRoll.getCurrent());
		//Break point.
		mainloop:
			for(int moves = diceRoll.getCurrent(); moves>0; moves--){
				//If player is currently in a room, call exitRoom method.
				//To display context sensitive options.
				if (p.getRoomIn() != null) {
					//True only if player tries to take non-existent secret passage.
					if (!exitRoom(p)) {
						moves++;
						System.out.println("Sorry, this room does not contain a secret passage");
						//Offer options again.
						continue mainloop;
					}
					moves--;
					//If used a secret passage.
					if (p.getRoomIn() != null) {
						break mainloop;
					}
				}
				//Give options of how to move.
				System.out.println(moves + " Moves left, type 'up','left,'right, or 'down' to move");
				String dir=null;
				while(dir==null){
					if (input.hasNext()) {dir = input.nextLine();}
					else {input.next();}
					try{direction.valueOf(dir.toUpperCase());}
					catch(IllegalArgumentException e){dir = null;}
				}
				//Calculate direction.
				direction d = direction.valueOf(dir.toUpperCase());
				switch(d.ordinal()){
				//Call move method depending on how user wishes to move.
				case 0:
					if (board.move(p, -1, 0))
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
					//Display current player's hand.
					System.out.println(p.handToString());
					moves = 0;
				}

				if (p.getAtLoc().hasCard())
					//Pickup intrigue card.
					System.out.println("Pick up an intrigue card.");
				//Display current position.
				System.out.println(p.getName() + " now at: " + p.getAtLoc().getY() + ","+ p.getAtLoc().getX());
			}
	}

	/**Method to move a player out of a room, via a door or a secret passage.
	 * 
	 * @param p - Player trying to move.
	 * @return - Boolean whether they succeeded.
	 */
	private boolean exitRoom(Player p) {
		System.out.println("Type 'exit' to leave the room, or 'sneak' to use the secret passage(May or may not exist)");			
		String option = null;
		//Ask for user input.
		while(option == null){
			if (input.hasNext()) {
				option = input.nextLine();
				if (!(option.equalsIgnoreCase("sneak") || option.equalsIgnoreCase("exit")))
					option = null;
			}
			else {input.next();}
		}

		if (option.equalsIgnoreCase("exit")) {
			//Leave room
			board.exitRoom(p);
		}
		else if (option.equalsIgnoreCase("sneak")) {
			//Check if there is a passage.
			if (!board.takePassage(p))
				return false;
		}
		//Display new board state
		board.print();
		return true;
	}

	/**Set the number of players.
	 * 
	 * @param pCount - Number of players
	 */
	private void setpCount(int pCount) {
		this.pCount = pCount;
	}

	/**Get number of players in the game.
	 * 
	 * @return - int, number of players.
	 */
	public int getpCount() {
		return pCount;
	}

	/**Get a list of players in the game.
	 * 
	 * @return - List of players.
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
