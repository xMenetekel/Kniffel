package model;


public class WuerfelFactory {
	
	/**
	 * Erzeugt Wuerfel z.B. hier W6
	 * @param typ	Art des Wuerfels
	 * @return		ein neues WuerfelLogik Objekt
	 */
	public static WuerfelLogik createWuerfel(String typ) {
		switch(typ) {
			case "W6":
				return new WuerfelLogik(6);
			default:
				return null;
		}
	}
}
