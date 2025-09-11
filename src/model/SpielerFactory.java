package model;

public class SpielerFactory {
	
	public static SpielerLogik createSpieler(String typ) { 
		switch(typ) {
			case "ErsterSpieler":
				return new SpielerLogik();
			default:
				return null;
		}
	}

}
