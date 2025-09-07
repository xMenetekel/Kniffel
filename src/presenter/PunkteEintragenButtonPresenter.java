package presenter;

import gui.PunkteEintragenButtonGUI;
import model.PunkteEintragenButtonLogik;

public class PunkteEintragenButtonPresenter {
	private PunkteEintragenButtonGUI gui;
	private PunkteEintragenButtonLogik logik;
	private SpielfeldPresenter ichBraucheNurDieseEineMethodeEsIstHalbDreiNachtsIchWillInsBett;
	
	public PunkteEintragenButtonPresenter(SpielfeldPresenter arsch) {
		gui = new PunkteEintragenButtonGUI();
		logik = new PunkteEintragenButtonLogik();
		gui.getButton().addActionListener(klick -> {punkteEintragenButtonGeklickt();});
		this.ichBraucheNurDieseEineMethodeEsIstHalbDreiNachtsIchWillInsBett = arsch;
	}
	
	
	
	
	
	private void punkteEintragenButtonGeklickt() {
		ichBraucheNurDieseEineMethodeEsIstHalbDreiNachtsIchWillInsBett.eintragePunkteFuerAktuellenSpieler();
		
	}
















	//Die Gettler und Settler von Catan

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
