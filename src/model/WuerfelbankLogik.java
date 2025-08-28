package model;

import java.util.ArrayList;
import java.util.List;

import gui.WuerfelGUI;

public class WuerfelbankLogik {
	private List<WuerfelGUI> Wuerfelbank = new ArrayList<>();
	
	public WuerfelbankLogik(List<WuerfelGUI> Wuerfelbank) {
		this.setWuerfelbank(Wuerfelbank);
	}

	public List<WuerfelGUI> getWuerfelbank() {
		return Wuerfelbank;
	}

	public void setWuerfelbank(List<WuerfelGUI> wuerfelbank) {
		Wuerfelbank = wuerfelbank;
	}
	
}
