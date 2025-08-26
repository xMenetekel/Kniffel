package model;

import control.WuerfelControl;

public class WuerfelFactory {

	public static WuerfelLogik createWuerfel(String typ) {
		switch(typ) {
			case "W6":
				return new WuerfelControl(6);
			default:
				return null;
		}
	}
}
