package cluedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Cluedo {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Card> deck = new ArrayList<Card>();
	private Weapon mWeapon;
	private Player mPlayer;
	private Room mRoom;
	private int pCount;
	private int currentTurn = 0;
	private boolean GAME_OVER = false;
	
	public static void main(String[] args){
		int pCount = 0;
		Scanner sc = new Scanner(System.in);
		while(pCount<3||pCount>6){
			System.out.println("How many players? 3-6");
			if(sc.hasNextInt()){pCount = sc.nextInt();}
			else{sc.next();}
		}
		sc.close();
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
		
		while(GAME_OVER == false) {
			Player cP = players.get(currentTurn % pCount);	//Alternates turns between players.
			System.out.println("It is now " + cP.getName() +"'s turn.");
			takeTurn(cP);
			currentTurn++;
		}
	}
	
	public void takeTurn(Player p) {
		//TODO Turn taking.
		//Options for what to do in turn.
		//Call move method.
		//Make murder suggestions... etc.
	}
}
