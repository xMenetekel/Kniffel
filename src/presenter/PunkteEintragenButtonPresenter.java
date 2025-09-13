package presenter;

import java.awt.Container;

import gui.PunkteEintragenButtonGUI;
import model.PunkteEintragenButtonLogik;

public class PunkteEintragenButtonPresenter {
	private PunkteEintragenButtonGUI gui;
	private PunkteEintragenButtonLogik logik;
	private SpielfeldPresenter ichBraucheNurDieseEineMethodeEsIstHalbDreiNachtsIchWillInsBett;
	
	public PunkteEintragenButtonPresenter(Container Eimer, SpielfeldPresenter arsch) {
		gui = new PunkteEintragenButtonGUI(Eimer);
		logik = new PunkteEintragenButtonLogik();
		
		gui.getKnopf().addActionListener(klick -> {punkteEintragenButtonGeklickt();});
		
		this.ichBraucheNurDieseEineMethodeEsIstHalbDreiNachtsIchWillInsBett = arsch;
	}
		
	private void punkteEintragenButtonGeklickt() {
		ichBraucheNurDieseEineMethodeEsIstHalbDreiNachtsIchWillInsBett.eintragePunkteFuerAktuellenSpieler();
		
	}

	//Gettler und Settler
	public PunkteEintragenButtonGUI getGui() {
		return gui;
	}

	public void setGui(PunkteEintragenButtonGUI gui) {
		this.gui = gui;
	}

	public PunkteEintragenButtonLogik getLogik() {
		return logik;
	}

	public void setLogik(PunkteEintragenButtonLogik logik) {
		this.logik = logik;
	}

}
