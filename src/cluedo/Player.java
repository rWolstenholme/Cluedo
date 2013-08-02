package cluedo;

public class Player extends Card{
	private String name;
	
	public Player(Integer nameKey){
		switch(nameKey){
		case 0:
			name = "Kasandra Scarlet"; break;
		case 1:
			name = "Jack Mustard"; break;
		case 2:
			name = "Diane White"; break;
		case 3:
			name = "Jacob Green"; break;
		case 4:
			name = "Eleanor Peacock"; break;
		case 5:
			name = "Victor Plum"; break;
		}
		System.out.println(name+" has joined the game");
	}

}
