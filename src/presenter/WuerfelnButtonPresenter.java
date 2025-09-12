package presenter;

import java.awt.Container;

import gui.WuerfelnButtonGUI;
import model.WuerfelnButtonLogik;

public class WuerfelnButtonPresenter {
	
	private WuerfelnButtonLogik logik;
	private WuerfelnButtonGUI gui;
	
	private WuerfelPresenter wuerfel;
	private PunktetabellePresenter tabelle;

	/**
	 * erstellt Logik und GUI des Buttons
	 * speichert Referenzen zu anderen Presentern
	 * 
	 * @param Eimer		Container in dem die GUI angezeigt wird
	 * @param wuerfel	Referenz auf WuerfelPresenter
	 * @param tabelle	Referenz auf PunktetabellenPresenter
	 */
	public WuerfelnButtonPresenter(Container Eimer, WuerfelPresenter wuerfel, PunktetabellePresenter tabelle) {
		this.logik = new WuerfelnButtonLogik();
		this.gui = new WuerfelnButtonGUI(Eimer);
				
		this.wuerfel = wuerfel;
		this.tabelle = tabelle;
	}
		
	
	public WuerfelnButtonLogik getLogik() {
		return logik;
	}

	public void setLogik(WuerfelnButtonLogik logik) {
		this.logik = logik;
	}

	public WuerfelnButtonGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelnButtonGUI gui) {
		this.gui = gui;
	}

	public WuerfelPresenter getWuerfel() {
		return wuerfel;
	}

	public void setWuerfel(WuerfelPresenter wuerfel) {
		this.wuerfel = wuerfel;
	}

	public PunktetabellePresenter getTabelle() {
		return tabelle;
	}

	public void setTabelle(PunktetabellePresenter tabelle) {
		this.tabelle = tabelle;
	}
}
