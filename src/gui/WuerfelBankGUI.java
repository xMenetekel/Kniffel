package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WuerfelBankGUI implements GUIProzente {
	private JPanel WuerfelBankGUI;
	private JLabel bild;
	private final double prozentFlaecheX = 0.09375;
	private final double prozentFlaecheHoehe =0.085714; //nicht nutzen zum resizen
	private final double prozentFlaecheY = 0.592857;
	private final double prozentFlaecheBreite = 0.35;	 //auch nicht nutzen zum resizen
	
	public WuerfelBankGUI() {
		ImageIcon bildi = new ImageIcon("Other/wuerfelbank.jpg" );
		bild = new JLabel();
		bild.setIcon(bildi);
		bild.setVisible(true);
		bild.setBounds(0, 0, 280,60);		
		WuerfelBankGUI = new JPanel();
		WuerfelBankGUI.add(bild);
		WuerfelBankGUI.setVisible(true);
		WuerfelBankGUI.setLayout(null);
	}

	
	public double getProzentFlaecheX() {
		return this.prozentFlaecheX;
	}

	
	public double getProzentFlaecheHoehe() {
		return this.prozentFlaecheHoehe;
	}

	public double getProzentFlaecheY() {
		return this.prozentFlaecheY;
	}

	
	public double getProzentFlaecheBreite() {		
		return this.prozentFlaecheBreite;
	}

	
	public JPanel getPanel() {
		return this.WuerfelBankGUI;
	}
	
	
	

}
