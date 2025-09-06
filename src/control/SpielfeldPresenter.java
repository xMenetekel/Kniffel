package control;

import java.awt.Container;

import gui.SpielfeldGUI;
import model.SpielfeldLogik;
import model.StartmenueLogik;

public class SpielfeldPresenter {
	 private SpielfeldGUI gui;
	 private SpielfeldLogik logik;
	 
	 private WuerfelPresenter wuerfel;
	 private PunktetabellePresenter tabelle;
	 private WuerfelbankPresenter bank;
	 private SpielerPresenter spieler;
	 
	 public SpielfeldPresenter(Container Eimer, StartmenueLogik ichGlaubDasMussSpaeterStartMenueDatenWerden) {
		 gui = new SpielfeldGUI(Eimer, ichGlaubDasMussSpaeterStartMenueDatenWerden);
		 logik = new SpielfeldLogik();
		 
	 }

}
