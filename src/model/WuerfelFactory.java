package model;

public class WuerfelFactory {

	public static WuerfelInfo createWuerfel(String typ) {
		switch(typ) {
			case "W6":
				return new W6();
			default:
				return null;
		}
	}
}
