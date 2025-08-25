package control;

import model.*;
import gui.*;

public class StartmenueLogik {
	
	private StartmenueGUI menueGUI;
	private StartmenueDaten menueDaten;
	
	public StartmenueLogik() {
		this.menueGUI = new StartmenueGUI();
		this.menueDaten = new StartmenueDaten();
		starteStartmenue();
	}
	
	private void starteStartmenue() {
		menueGUI.getExit().addActionListener(klick -> {System.exit(0);});
		menueGUI.getStarteSpiel().addActionListener(klick -> {new SpielfeldGUI();}););
		
	}
	
	
}
