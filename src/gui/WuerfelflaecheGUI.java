package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WuerfelflaecheGUI extends AbstractGUI {
	
	private JLabel bild;
	private static final double X = 0.0625;	
	private static final double Y = 0.285;	
	private static final double B = 0.4375;
	private static final double H = 0.301659125;
	ImageIcon bildi;

	public WuerfelflaecheGUI() {
		super(X, Y, B, H);
		bildi = new ImageIcon("Other/wuerfelflaeche.jpg");		
		panel = new JPanel();
		bild = new JLabel();				
		panel.setLayout(null);
		setzeneueGroesse(350,200);
		bild.setVisible(true);					
		panel.add(bild);	
		panel.setOpaque(false); //das jpanel ist zu groß. ich will einfach nicht rausfinden warum. set opaque regelt.
		panel.setVisible(true);				
		
		
	}
	private void skaliereBild(int breite, int hoehe) {
		Image bildii = bildi.getImage();
		Image skaliertesBild = bildii.getScaledInstance(breite, hoehe, Image.SCALE_SMOOTH);
		bild.setIcon(new ImageIcon(skaliertesBild));
	}
	public void setzeneueGroesse(int breite, int hoehe) {
		skaliereBild(breite, hoehe);
		panel.setSize(breite, hoehe);
		bild.setBounds(0,0,breite, hoehe);
	}
	
	public void zeigeWuerfel() {
		
	}
	

}
