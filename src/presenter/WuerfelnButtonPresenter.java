package presenter;

import gui.WuerfelnButtonGUI;
import model.WuerfelnButtonLogik;

public class WuerfelnButtonPresenter {
	private WuerfelnButtonLogik logik;
	private WuerfelnButtonGUI gui;
	
	private WuerfelPresenter wuerfel;
	private PunktetabellePresenter tabelle;

	public WuerfelnButtonPresenter(WuerfelPresenter wuerfel, PunktetabellePresenter tabelle) {
		logik = new WuerfelnButtonLogik();
		gui = new WuerfelnButtonGUI();
		gui.getButton().addActionListener(klick -> { WuerfelnButtonGeklickt(); }); 
		this.wuerfel = wuerfel;
		this.tabelle = tabelle;
	}
	
	public void WuerfelnButtonGeklickt() {
	
		wuerfel.wuerfeln(); 
		if (wuerfel.getWurfAnzahl() == wuerfel.getMaxWuerfe()) {
            tabelle.aktualisierePunkteTabelle();
		}
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
}
