package control;

import model.*;
import gui.*;

public class StartmenueControl {
	
	private StartmenueGUI menueGUI;
	private StartmenueLogik menueDaten;
	
	public StartmenueControl() {
		this.menueGUI = new StartmenueGUI();
		this.menueDaten = new StartmenueLogik();
		starteStartmenue();
	}
	
	private void starteStartmenue() {
		menueGUI.getExit().addActionListener(klick -> {System.exit(0);});
	//	menueGUI.getStarteSpiel().addActionListener(klick -> {new SpielfeldGUI();}););
		
	}
	
	
}
