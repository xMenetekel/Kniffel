package model;

import control.WuerfelPresenter;

public class WuerfelFactory {

	public static WuerfelLogik createWuerfel(String typ) {
		switch(typ) {
			case "W6":
				return new WuerfelLogik(6);
			default:
				return null;
		}
	}
}
