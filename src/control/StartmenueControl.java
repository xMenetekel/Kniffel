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
		menueGUI.getStarteSpiel().addActionListener(klick -> {new SpielfeldGUI(menueGUI.getEimer());});
		
	}
	public StartmenueGUI getMenueGUI() {
		return menueGUI;
	}

	public void setMenueGUI(StartmenueGUI menueGUI) {
		this.menueGUI = menueGUI;
	}

	public StartmenueLogik getMenueDaten() {
		return menueDaten;
	}

	public void setMenueDaten(StartmenueLogik menueDaten) {
		this.menueDaten = menueDaten;
	}
	
	
}
