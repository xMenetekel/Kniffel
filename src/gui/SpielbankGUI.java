package gui;

import javax.swing.JPanel;

public class SpielbankGUI extends AbstractGUI{
	JPanel bank;
	private final double prozentFlaecheX = 0.0625;
	private final double prozentFlaecheHoehe =0.142857;
	private final double prozentFlaecheY = 0.71428;
	private final double prozentFlaecheBreite = 0.4375;	
	

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
	public double getProzentFlaecheX() {
		return prozentFlaecheX;
	}

	public double getProzentFlaecheHoehe() {
		return prozentFlaecheHoehe;
	}

	public double getProzentFlaecheY() {
		return prozentFlaecheY;
	}

	public double getProzentFlaecheBreite() {
		return prozentFlaecheBreite;
	}
}
