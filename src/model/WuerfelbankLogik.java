package model;

import java.util.ArrayList;
import java.util.List;

import gui.WuerfelGUI;

public class WuerfelbankLogik {
	
	private List<WuerfelGUI> Wuerfelbank = new ArrayList<>();
	private List<int[]> wuerfelxPos = new ArrayList<>();
	private List<int[]> wuerfelyPos = new ArrayList<>();
	private List<double[]> wuerfelxProz = new ArrayList<>();
	private List<double[]> wuerfelyProz = new ArrayList<>();
	
	public WuerfelbankLogik() {
	//	this.setWuerfelbank(Wuerfelbank);		
	}
	
	
	public List<int[]> getWuerfelxPos() {
		return wuerfelxPos;
	}

	public void setWuerfelxPos(List<int[]> wuerfelxPos) {
		this.wuerfelxPos = wuerfelxPos;
	}

	public List<int[]> getWuerfelyPos() {
		return wuerfelyPos;
	}

	public void setWuerfelyPos(List<int[]> wuerfelyPos) {
		this.wuerfelyPos = wuerfelyPos;
	}

	public List<double[]> getWuerfelxProz() {
		return wuerfelxProz;
	}

	public void setWuerfelxProz(List<double[]> wuerfelxProz) {
		this.wuerfelxProz = wuerfelxProz;
	}

	public List<double[]> getWuerfelyProz() {
		return wuerfelyProz;
	}

	public void setWuerfelyProz(List<double[]> wuerfelyProz) {
		this.wuerfelyProz = wuerfelyProz;
	}

	public List<WuerfelGUI> getWuerfelbank() {
		return Wuerfelbank;
	}

	public void setWuerfelbank(List<WuerfelGUI> wuerfelbank) {
		Wuerfelbank = wuerfelbank;
	}	
}
