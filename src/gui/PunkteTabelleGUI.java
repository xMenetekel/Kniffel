package gui;

import javax.swing.JPanel;

public class PunkteTabelleGUI {
	JPanel tabelle;
	
	public PunkteTabelleGUI() {
		tabelle = new JPanel();
		tabelle.setBounds(0, 0, 100, 600);
		tabelle.setVisible(true);
		tabelle.setLayout(null);
		}
	
	public void setMoeglichkeiten() {
		
	}
	public JPanel getPanel() {
		return this.tabelle;
	}

}
