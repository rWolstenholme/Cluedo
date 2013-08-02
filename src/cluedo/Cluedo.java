package cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cluedo {
	private List<Player> players = new ArrayList<Player>();
	private List<Room> rooms = new ArrayList<Room>();
	private List<Weapon> weapons = new ArrayList<Weapon>();
	
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
		System.out.println("Starting game with "+pCount+" players");
		for(Integer i = 0;i<pCount;i++){
			Player p = new Player(i);
			players.add(p);
		}
	}
}
