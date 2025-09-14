package presenter;

import java.awt.Container;

import gui.WuerfelnButtonGUI;

public class WuerfelnButtonPresenter {
	
	private WuerfelnButtonGUI gui;	
	private WuerfelPresenter wuerfel;
	private PunktetabellePresenter tabelle;

	/**
	 * erstellt Logik und GUI des Buttons
	 * speichert Referenzen zu anderen Presentern
	 * 
	 * @param eimer		Container in dem die GUI angezeigt wird
	 * @param wuerfel	Referenz auf WuerfelPresenter
	 * @param tabelle	Referenz auf PunktetabellenPresenter
	 */
	public WuerfelnButtonPresenter(Container eimer, WuerfelPresenter wuerfel, PunktetabellePresenter tabelle) {
		
		this.gui = new WuerfelnButtonGUI(eimer);
				
		this.wuerfel = wuerfel;
		this.tabelle = tabelle;
	}
		
	

	//Getter und Setter

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
