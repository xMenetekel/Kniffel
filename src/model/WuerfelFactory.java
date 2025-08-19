package model;

import control.WuerfelLogik;

public class WuerfelFactory {

	public static WuerfelInfo createWuerfel(String typ) {
		switch(typ) {
			case "W6":
				return new WuerfelLogik(6);
			default:
				return null;
		}
	}
}
