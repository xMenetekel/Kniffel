package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WuerfelflaecheGUI implements GUIProzente {
	private JPanel flaeche;
	private JLabel bild;
	private final double prozentFlaecheX = 0.0625;
	private final double prozentFlaecheHoehe =0.25;
	private final double prozentFlaecheY = 0.285;
	private final double prozentFlaecheBreite = 0.4375;

	public WuerfelflaecheGUI() {
		ImageIcon bildi = new ImageIcon("Other/wuerfelflaeche.jpg" );
		bild = new JLabel();
		bild.setIcon(bildi);
		bild.setVisible(true);
		bild.setBounds(0, 0, 350,200);		
		flaeche = new JPanel();
		flaeche.add(bild);	
		flaeche.setBounds(0,0,300,100);
		//flaeche.setVisible(true);
		flaeche.setLayout(null);
	}
	
	public void zeigeWuerfel() {
		
	}
	public JPanel getPanel() {
		return this.flaeche;
	}

	public double getProzentFlaecheY() {
		return prozentFlaecheY;
	}

	public double getProzentFlaecheX() {
		return prozentFlaecheX;
	}

	public double getProzentFlaecheBreite() {
		return prozentFlaecheBreite;
	}

	public double getProzentFlaecheHoehe() {
		return prozentFlaecheHoehe;
	}

}
