package gui;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;

import presenter.SpielfeldPresenter;
import presenter.WuerfelPresenter;

public class SpielfeldGUI extends AbstractGUI{
	
	private SpielfeldPresenter presenter;
	private WuerfelnButtonGUI wuerfelnButton;
	private PunkteEintragenButtonGUI punkteEintragenButton;
	private PunkteTabelleGUI punkteTabelle;
	
	/**
	 * Erstellt Spielfeld mit Hintergrundbild 
	 * registriert  Groeßenanpassungen
	 * 
	 * @param Eimer			uebergeordneter Container
	 * @param presenter		zugehoeriger SpielfeldPresenter
	 * @param menueDaten	Daten aus dem Startmenue
	 */
	public SpielfeldGUI(Container eimer, SpielfeldPresenter presenter) {
		super(0,0,1,1, eimer);
		this.setPresenter(presenter);
				
		eimer.removeAll();
		eimer.setLayout(null);
		bildIcon = new ImageIcon("src/other/hintergrund.jpg");		
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
	
	
	/**
	 * rearrangiert alle Würfel wenn das Fenster größer oder kleiner gezogen wird. die hilfsfunktion positioniere würfel trifft hierbei die fallentscheidung was bei Würfeln in 
	 * und ausserhalb der Bank passiert	 
	 * @param feld Component an welcher die relative positionierung festgemacht wird
	 * @param wPres presenter um alle würfel zu kriegen
	 * @param bank bank für die bankpositionierung
	 */
	public void rearrangiereWuerfel(Component feld, WuerfelPresenter wPres, WuerfelBankGUI bank) {
		int breite = feld.getWidth();
		int hoehe = feld.getHeight();
						
		bank.getPanel().setBounds(
			(int)Math.floor(breite*bank.getProzentFlaecheX()), 
			(int)Math.floor(hoehe*bank.getProzentFlaecheY()), 300, 80
		);
			
		wPres.getAlleWuerfelGUI().forEach(t -> { 
			positioniereWuerfel(t,bank, breite,hoehe);
			t.setProzentXAufFeld(t.getxPos() * 1.0 / breite);
			t.setProzentYAufFeld(t.getyPos() * 1.0 / hoehe);
			t.getButton().setBounds(t.getxPos(), t.getyPos(), 50, 50);	
		});				
	}
	/**
	 * setzt die positionen zur berechnen entsprechend der position der würfelbank
	 * @param wGUI wuerfelgui wo die positionen gesetzt werden
	 * @param bank bank um position in der bank festzustellen
	 * @param breite
	 * @param hoehe
	 */
	private void positioniereWuerfel(WuerfelGUI wGUI, WuerfelBankGUI bank, int breite, int hoehe) {
		if(wGUI.isIstinBank()) {						
			wGUI.setxPos((int) ((bank.getProzentFlaecheX() * breite ) + 15 ) + 55 * (bank.getReihenfolge().indexOf(wGUI)));
			wGUI.setyPos((int) ((bank.getProzentFlaecheY() * hoehe ) + 7 ) );
		} else {
			wGUI.setxPos( (int) Math.round(wGUI.prozentXAufFeld * breite));
			wGUI.setyPos( (int) Math.round(wGUI.prozentYAufFeld * hoehe));												
			}
	}
	
	/**
	 * arrangiert die meisten abstractGUI komponenten entsprechend des übergeordneten eimers. die punktetabelle wird immer auf die vollste rechte seite gesetzt und erhält
	 * deshalb special treatment
	 * @param e e wie eimer
	 * @param gui
	 */
	public void rearrangiere(Component e, AbstractGUI gui) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
			
		gui.getPanel().setBounds(
				(int)Math.floor(breite*gui.getProzentFlaecheX()), 
				(int)Math.floor(hoehe*gui.getProzentFlaecheY()), 
				(int)Math.floor(breite*gui.getProzentFlaecheBreite()),
				(int)Math.floor(hoehe*gui.getProzentFlaecheHoehe())
		);
			
		if(!(gui instanceof PunkteTabelleGUI)){
		gui.setzeNeueGroesse((int)(breite * gui.getProzentFlaecheBreite()), (int)(hoehe * gui.getProzentFlaecheHoehe()));
		}
	}
	
	
	//Getter und Setter
	
	public WuerfelnButtonGUI getWuerfelnButton() {
		return wuerfelnButton;
	}
	
	public PunkteEintragenButtonGUI getPunkteEintragenButton() {
		return punkteEintragenButton;
	}
	
	public PunkteTabelleGUI getPunkteTabelle() {
		return punkteTabelle;
	}

	public SpielfeldPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(SpielfeldPresenter presenter) {
		this.presenter = presenter;
	}
	
}
