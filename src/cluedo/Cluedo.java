package cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cluedo {
	private List<Player> players = new ArrayList<Player>();
	public List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Card> deck = new ArrayList<Card>();
	private Weapon mWeapon;
	private Player mPlayer;
	private Room mRoom;
	private int pCount;
	private int currentTurn = 0;
	private Board board;
	private UI console;
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
		this.pCount = pCount;
		System.out.println("Starting game with "+pCount+" players");
		//Create players
		for(Integer i = 0;i<6;i++){
			Player p = new Player(i);
			deck.add(p);
			players.add(p);
			//System.out.println("Player "+p.getName()+" has joined the game");
		}
		mPlayer = players.get((int)Math.random()*6);
		deck.remove(mPlayer);
		//Create weapons
		for(Integer i = 0;i<8;i++){
			Weapon w = new Weapon(i);
			deck.add(w);
			weapons.add(w);
		}
		mWeapon = weapons.get((int)Math.random()*8);
		//Create Rooms
		for(Integer i = 0;i<8;i++){
			Weapon w = new Weapon(i);
			deck.add(w);
			weapons.add(w);
		}
		mWeapon = weapons.get((int)Math.random()*8);

		board = new Board(this);
		console = new UI(board);

		while(GAME_OVER == false) {
			Player cP = players.get(currentTurn % pCount);	//Alternates turns between players.
			System.out.println("It is now " + cP.getName() +"'s turn.");
			takeTurn(cP);
			currentTurn++;
		}
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
		Dice diceRoll = new Dice();
		System.out.println("You rolled a " + diceRoll.getCurrent());
		for(int moves = diceRoll.getCurrent(); moves>0; moves--){
			System.out.println(moves + "Moves left, type 'up','left,'right, or 'down' to move");
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
				p.move(0, -1);break;
			case 1:
				p.move(0, 1);break;
			case 2:
				p.move(-1, 0);break;
			case 3:
				p.move(1, 0);break;
			default:
				throw new RuntimeException("Direction failure");
			}
		}
		//TODO Turn taking.
		//Options for what to do in turn.
		//Call move method.
		//Update Board.
		//Make murder suggestions... etc.
		//Call UI.print() when turn over.
	}
}
