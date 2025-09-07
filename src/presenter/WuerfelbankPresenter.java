package presenter;

import gui.WuerfelBankGUI;
import model.WuerfelbankLogik;

public class WuerfelbankPresenter {
	private WuerfelBankGUI gui;
	private WuerfelbankLogik logik;
	
	public WuerfelbankPresenter() {
		
		gui = new WuerfelBankGUI();
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
