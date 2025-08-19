package gui;

import javax.swing.JPanel;

public class SpielbankGUI {
	JPanel bank;
	
	public SpielbankGUI() {
		bank = new JPanel();
		bank.setVisible(true);
		bank.setBounds(0, 0, 300, 100);
		bank.setLayout(null);
	}
	
	public void rotieren()
	{
		
	}
	public JPanel getPanel() {
		return this.bank;
	}
}
