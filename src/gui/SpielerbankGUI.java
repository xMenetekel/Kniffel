package gui;

import java.awt.Container;

import javax.swing.ImageIcon;

import presenter.SpielerbankPresenter;

public class SpielerbankGUI extends AbstractGUI{	
	
	private static final double X = 0.0625;
	private static final double Y = 0.75;
	private static final double B = 0.2;
	private static final double H = 0.2;
	
	private final SpielerbankPresenter presenter;

	/**
	 * Erzeugen der Spielerbank GUI
	 * 
	 * @param Eimer		container uebergeordneter Container
	 * @param presenter	zuegehoriger Presenter liefert Bildname 
	 */
	public SpielerbankGUI(Container eimer, SpielerbankPresenter presenter) {
		super(X, Y, B, H, eimer);
		bildIcon = new ImageIcon("src/Other/" + presenter.getBildname(presenter.getAktiverSpieler()) + ".jpg" );		
		this.presenter = presenter;
		setzeNeueGroesse((int)(B * eimer.getWidth()), (int)(H * eimer.getHeight()));
		panel.add(bild);						
	}
	
	/**
	 * Aktualisiert das angezeigte Spieler Icon
	 * 
	 * @param index Index des Spielers dessen Bild angezeigt werden soll
	 */
	public void aktualisiereIcon(int index){
		setBildIcon(new ImageIcon("src/Other/" + presenter.getBildname(index) + ".jpg"));
		setzeNeueGroesse((int)(B * eimer.getWidth()), (int)(H * eimer.getHeight()));
		getPanel().revalidate();
		getPanel().repaint();
	}	
}
