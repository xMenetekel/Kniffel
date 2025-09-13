package gui;

import java.awt.Container;

import javax.swing.ImageIcon;

import presenter.SpielerbankPresenter;

public class SpielerbankGUI extends AbstractGUI{	
	
	private static final double X = 0.0625; // links
	private static final double Y = 0.75;   // oben 
	private static final double B = 0.2;	// Breite
	private static final double H = 0.2;	// Hoehe
	
	private SpielerbankPresenter presenter;

	public SpielerbankGUI(Container eimer, SpielerbankPresenter presenter) {
		super(X, Y, B, H, eimer);
		bildIcon = new ImageIcon("src/Other/" + presenter.getBildname(presenter.getAktiverSpieler()) + ".jpg" );		
		this.presenter = presenter;
		setzeNeueGroesse((int)(B * eimer.getWidth()), (int)(H * eimer.getHeight()));
		panel.add(bild);						
	}
	
	// aktualisiert Spieler Icon wenn Spieler wechselt
	public void aktualisiereIcon(int index){
		setBildIcon(new ImageIcon("src/Other/" + presenter.getBildname(index) + ".jpg"));
		setzeNeueGroesse((int)(B * eimer.getWidth()), (int)(H * eimer.getHeight()));
		getPanel().revalidate();
		getPanel().repaint();
	}	
}
