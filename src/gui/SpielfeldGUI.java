package gui;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;

import model.Kategorie;
import model.StartmenueLogik;
import presenter.SpielfeldPresenter;

public class SpielfeldGUI extends AbstractGUI{
	
	private SpielfeldPresenter presenter;
	private WuerfelnButtonGUI wuerfelnButton;
	private PunkteEintragenButtonGUI punkteEintragenButton;
	private PunkteTabelleGUI punkteTabelle;
	
	private StartmenueLogik menueDaten;
		
	private Kategorie ausgewaehlteKategorie = null; 
		
	/**
	 * Erstellt Spielfeld mit Hingerunrbild 
	 * regiestriert  Groeßenanpassungen
	 * 
	 * @param Eimer			uebergeordneter Container
	 * @param presenter		zugehoeriger SpielfeldPresenter
	 * @param menueDaten	Daten aus dem Startmenue
	 */
	public SpielfeldGUI(Container eimer, SpielfeldPresenter presenter, StartmenueLogik menueDaten) {
		super(0,0,1,1, eimer);
		this.presenter = presenter;
		this.menueDaten = menueDaten;
				
		eimer.removeAll();
		eimer.setLayout(null);
		bildIcon = new ImageIcon("src/Other/hintergrund.jpg");		
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);
		panel.add(bild);
		setzeNeueGroesse(eimer.getHeight(),eimer.getWidth());
		
		eimer.revalidate();
		eimer.repaint();
		
		eimer.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				presenter.rearrangiereAlles(eimer);				
			}
		});
	}
	
	public WuerfelnButtonGUI getWuerfelnButton() {
		return wuerfelnButton;
	}
	
	public PunkteEintragenButtonGUI getPunkteEintragenButton() {
		return punkteEintragenButton;
	}
	
	public PunkteTabelleGUI getPunkteTabelle() {
		return punkteTabelle;
	}
	
}
