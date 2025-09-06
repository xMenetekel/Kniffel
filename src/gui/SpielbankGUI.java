package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpielbankGUI extends AbstractGUI{	
	
	private static final double X = 0.0625;
	private static final double H =0.142857;
	private static final double Y = 0.71428;
	private static final double B = 0.4375;	
	

	public SpielbankGUI() {
		super(X, Y, B, H);
		ImageIcon bildi = new ImageIcon("Other/spielerbank.jpg" );		
		bild.setIcon(bildi);		
		bild.setBounds(0, 0, 350,100);
		panel.add(bild);				
		panel.setBounds(0, 0, 350, 100);
		
	}
	
	public void rotieren()
	{
		
	}
	
}
