package presenter;

import java.awt.Container;

import gui.WuerfelBankGUI;
import model.WuerfelbankLogik;

public class WuerfelbankPresenter {
	private WuerfelBankGUI gui;
	private WuerfelbankLogik logik;
	
	public WuerfelbankPresenter(Container Eimer) {
		
		gui = new WuerfelBankGUI(Eimer);
		logik = new WuerfelbankLogik();
		
	}

	public WuerfelBankGUI getGui() {
		return gui;
	}

	public void setGui(WuerfelBankGUI gui) {
		this.gui = gui;
	}

	public WuerfelbankLogik getLogik() {
		return logik;
	}

	public void setLogik(WuerfelbankLogik logik) {
		this.logik = logik;
	}

}
