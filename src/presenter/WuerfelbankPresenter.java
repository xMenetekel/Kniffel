package presenter;

import java.awt.Container;

import gui.WuerfelBankGUI;

public class WuerfelbankPresenter {
	
	private WuerfelBankGUI gui;
	
	/**
	 * Erzeugt GUI
	 * 
	 * @param eimer		Container in dem die GUI platziert wird
	 */
	public WuerfelbankPresenter(Container eimer) {		
		this.gui = new WuerfelBankGUI(eimer);	
	}

	//Getter und Setter
	
	public WuerfelBankGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelBankGUI gui) {
		this.gui = gui;
	}

}

