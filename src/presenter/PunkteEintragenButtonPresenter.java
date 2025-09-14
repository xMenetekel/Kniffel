package presenter;

import java.awt.Container;

import gui.PunkteEintragenButtonGUI;


public class PunkteEintragenButtonPresenter {
	private PunkteEintragenButtonGUI gui;
	private SpielfeldPresenter presenter;
	
	public PunkteEintragenButtonPresenter(Container eimer, SpielfeldPresenter presenter) {
		gui = new PunkteEintragenButtonGUI(eimer);		
		gui.getKnopf().addActionListener(klick -> {punkteEintragenButtonGeklickt();});		
		this.presenter = presenter;
	}
	
	/**
	 * sagt dem presenter das der button gedrückt wurde
	 */
	private void punkteEintragenButtonGeklickt() {
		presenter.eintragePunkteFuerAktuellenSpieler();
		
	}

	//Gettler und Settler
	
	public PunkteEintragenButtonGUI getGui() {
		return gui;
	}

	public void setGui(PunkteEintragenButtonGUI gui) {
		this.gui = gui;
	}

}
