package model;

public class SpielerFactory {
	
	public static SpielerInfo createSpieler(String typ) {
		switch(typ) {
			case "ErsterSpieler":
				return new Spieler();
			default:
				return null;
		}
	}

}
