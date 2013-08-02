package cluedo;

public class Weapon extends Card{
	private String name;
	
	public Weapon(Integer nameKey){
		switch(nameKey){
		case 0:
			name = "Rope"; break;
		case 1:
			name = "Candlestick"; break;
		case 2:
			name = "Knife"; break;
		case 3:
			name = "Pistol"; break;
		case 4:
			name = "Baseball bat"; break;
		case 5:
			name = "Dumbbell"; break;
		case 6:
			name = "Trophy"; break;
		case 7:
			name = "Poison"; break;
		case 8:
			name = "Axe"; break;
		}
	}

	public String getName() {
		return name;
	}
}
