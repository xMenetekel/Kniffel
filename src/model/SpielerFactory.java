package model;

public class SpielerFactory {
	
	public static Spieler createSpieler(String typ) { 
		switch(typ) {
			case "ErsterSpieler":
				return new Spieler();
			default:
				return null;
		}
	}

}
