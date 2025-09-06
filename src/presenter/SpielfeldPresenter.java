package presenter;

import java.awt.Container;

import gui.SpielfeldGUI;
import model.SpielfeldLogik;
import model.StartmenueLogik;
import model.SpielLogik;

public class SpielfeldPresenter {
	 private SpielfeldGUI gui;
	 private SpielfeldLogik logik;
	 
	 private WuerfelPresenter wuerfel;
	 private PunktetabellePresenter tabelle;
	 private WuerfelbankPresenter bank;
	 private SpielerPresenter spieler;
	 private SpielLogik spielLogik;
	 
	 public SpielfeldPresenter(Container Eimer, StartmenueLogik menueDaten, SpielLogik spielLogik) {
		 this.spielLogik = spielLogik;
		 gui = new SpielfeldGUI(Eimer, menueDaten, spielLogik );
		 logik = new SpielfeldLogik();
		
		 
	 }
	 
	 public SpielLogik getSpielLogik() {
		 return spielLogik;
	 }

}
