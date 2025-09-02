package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpielbankGUI implements GUIProzente{
	JPanel bank;
	JLabel bild;
	private final double prozentFlaecheX = 0.0625;
	private final double prozentFlaecheHoehe =0.142857;
	private final double prozentFlaecheY = 0.71428;
	private final double prozentFlaecheBreite = 0.4375;	
	

	public SpielbankGUI() {
		ImageIcon bildi = new ImageIcon("Other/spielerbank.jpg" );
		bank = new JPanel();
		bild = new JLabel();
		bild.setIcon(bildi);
		bild.setVisible(true);
		bild.setBounds(0, 0, 350,100);
		bank.add(bild);		
		bank.setVisible(true);
		bank.setBounds(0, 0, 350, 100);
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
