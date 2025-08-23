package model;

public class SpielerFactory {
	
	public static SpielerDaten createSpieler(String typ) { 
		switch(typ) {
			case "ErsterSpieler":
				return new SpielerDaten();
			default:
				return null;
		}
	}

}
