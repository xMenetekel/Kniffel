package presenter;

import java.awt.Container;

import gui.WuerfelnButtonGUI;
import model.WuerfelnButtonLogik;

public class WuerfelnButtonPresenter {
	private WuerfelnButtonLogik logik;
	private WuerfelnButtonGUI gui;
	
	private WuerfelPresenter wuerfel;
	private PunktetabellePresenter tabelle;

	public WuerfelnButtonPresenter(Container Eimer, WuerfelPresenter wuerfel, PunktetabellePresenter tabelle) {
		logik = new WuerfelnButtonLogik();
		gui = new WuerfelnButtonGUI(Eimer);
				
		this.setWuerfel(wuerfel);
		this.setTabelle(tabelle);
	}
		
	//GetterSetterGetterSetterGetterSetterGetterSetterGetterSetterGetterSetter	
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
