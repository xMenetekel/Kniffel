package gui;

import javax.swing.JPanel;

public class WuerfelflaecheGUI {
	private JPanel flaeche;

	public WuerfelflaecheGUI() {
		flaeche = new JPanel();
		flaeche.setBounds(0,0,300,100);
		flaeche.setVisible(true);
		flaeche.setLayout(null);
	}
	
	public void zeigeWuerfel() {
		
	}
	public JPanel getPanel() {
		return this.flaeche;
	}
}
