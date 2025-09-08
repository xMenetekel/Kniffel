package gui;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import presenter.SpielerbankPresenter;

public class SpielerbankGUI extends AbstractGUI{	
	
	private static final double X = 0.0625;
	private static final double Y = 0.77;
	private static final double B = 0.37;	
	private static final double H = 0.142857;	
	

	public SpielerbankGUI(Container Eimer, SpielerbankPresenter presenter) {
		super(X, Y, B, H, Eimer);
		bildIcon = new ImageIcon("Other/" + presenter.getBildname(presenter.getAktiverSpieler()) + ".jpg" );		
			
		setzeneueGroesse((int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight()));
		panel.add(bild);				
		
		
	}
	
	public void rotieren()
	{
		
	}
	
}
