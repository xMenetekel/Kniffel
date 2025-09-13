package presenter;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.WuerfelGUI;
import gui.WuerfelflaecheGUI;
import gui.WuerfelBankGUI;
import model.WuerfelLogik;
import model.SpielLogik;
import model.StartmenueLogik;

public class WuerfelPresenter  {

	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	
	private Container eimer;
	private MoeglicheZuege moeglicheZuege = new MoeglicheZuege();
		
	private SpielLogik spielLogik;	
		
	private WuerfelflaecheGUI flaeche;
	private WuerfelBankGUI bank;
	private SpielEreignisListener spielListener; 
	private StartmenueLogik menueDaten;
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3; //Standardwert
	
	final int WUERFEL_GROESSE = 50;	
	
	private boolean init;

	/**
	 * Erzeugt WuerfelPresenter, der die WuerfelLogik mit der Darstellung
	 * und den Spielereignissen verbindet (schnittstelle)
	 * @param eimer 			uebergeordneter Container
	 * @param spielLogik		zentrale Logik Regeln und Zustaende 
	 * @param flaeche			GUI Element Wuerfelflache
	 * @param bank				GUI Element Wuerfelbank
	 * @param spielErListener	Listener der auf Spielereignisse reagiert
	 * @param menueDaten		Logik fuer Startmenue, Debug
	 */
	public WuerfelPresenter(Container eimer, SpielLogik spielLogik, 
							WuerfelflaecheGUI flaeche, WuerfelBankGUI bank, 
							SpielEreignisListener spielErListener,
							StartmenueLogik menueDaten){
		this.eimer = eimer;
		this.flaeche = flaeche;
		this.bank = bank;
		this.spielListener = spielErListener;
		this.spielLogik = spielLogik;
		this.menueDaten = menueDaten;
		this.init = true;
		
		for(int i = 0; i < 5; i++) {
			WuerfelLogik model = new WuerfelLogik(6);
			WuerfelGUI view = new WuerfelGUI(this, model, 
											(i*55+((int)(bank.getProzentFlaecheX() * eimer.getWidth())))+5, 
											(int)((bank.getProzentFlaecheY() * eimer.getHeight())+6), eimer);
			
			model.setGui(view);
			bank.getReihenfolge().add(view);
			alleWuerfelGUI.add(view);
			alleWuerfelLogik.add(model);		
			eimer.add(view.getButton());
		}	
		eimer.revalidate();
		eimer.repaint();	
	}
	
	/**
	 * Reagiert auf Klicken auf Wuerfel
	 * prueft ob Debugging Modus aktiv
	 * aktualisiert Position und Tabgelle
	 * 
	 * @param gui WuerfelGUI
	 */
	public void wuerfelAngeklickt(WuerfelGUI gui) {
		if(this.isInit()) { return; }
		
		if(menueDaten.isDebugingModus()) {
			String[] liste = {"1", "2", "3", "4", "5", "6"};
			
			int auswahl = JOptionPane.showOptionDialog(
					eimer, 
					"Zahl wählen", 
					"Cheatermodus",
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.PLAIN_MESSAGE,
					null, 
					liste, 
					liste[0]
			);
			gui.getLogik().setAugen(auswahl+1); 
			gui.fresherButton();
			tabellenkommunikation();
		} else {			
			WuerfelLogik zustandDerLogik = gui.getLogik();
			boolean neuerZustand = !zustandDerLogik.istBehalten();										
			zustandDerLogik.setBehalten(neuerZustand);
			
			gui.setIstinBank(!gui.isIstinBank());
			
			hochRunterhochRunter(zustandDerLogik);
		}		
	}
	
	/**
	 * Berechnet zufaellige, gueltige X-Position auf der Wuerfelflache
	 * 
	 * @param flaeche 	Wuerfelflache
	 * @return 			gibt eine gueltige X-Koordinate zurueck
	 */
	public int zufallsKoordinateX(WuerfelflaecheGUI flaeche) {
		int validesX;
		int xVon = (int) (flaeche.getProzentFlaecheX() * this.eimer.getWidth());
		int xBis = (int) ((flaeche.getProzentFlaecheBreite() * this.eimer.getWidth()) + xVon);
		validesX = new Random().nextInt(xBis-xVon-WUERFEL_GROESSE)+xVon;	
				
		return validesX;
	}
	
	/**
	 * Berechnet zufaellige, gueltige Y-Position auf der Wuerfelflache
	 * 
	 * @param flaeche 	Wuerfelflaeche
	 * @return			gibt eine gueltige Y-Koordinate zurueck
	 */
	public int zufallsKoordinateY(WuerfelflaecheGUI flaeche) {
		int validesY;
		int YVon = (int) (flaeche.getProzentFlaecheY() * this.eimer.getHeight());
		int YBis = (int) ((flaeche.getProzentFlaecheHoehe() * this.eimer.getHeight()) + YVon);
		validesY = new Random().nextInt(YBis-YVon-WUERFEL_GROESSE)+YVon;			
		
		return validesY;
	}

	/**
	 * Das ganze korrigiert Kollisionen. Es muss die WuerfelGUI uebergeben werden, 
	 * da dort die fertigen x/y pos gespeichert werden. 
	 * der JButton an sich hat durch den Timer sehr unterschiedliche x/y Werte 
	 * und dann wird die Kollisionspruefung absoluter Bescheiden. 
	 * 
	 * BLOSS NIEMALS ÄNDERN
	 * es funktioniert !
	 * 
	 * @param aktuellerWuerfel 
	 * @param zielX
	 * @param zielY
	 * @return
	 */
	private int[] korrigierteKollision(WuerfelGUI aktuellerWuerfel, int zielX, int zielY) {
		while(true) {
			WuerfelGUI kollidierenderWuerfel = findeKollidierendeWuerfel(aktuellerWuerfel, zielX, zielY);
			
			if(kollidierenderWuerfel == null && !istStartPositionGleich(aktuellerWuerfel, zielX, zielY)) {
				break;
			}
			zielX = zufallsKoordinateX(flaeche);
			zielY = zufallsKoordinateY(flaeche);
		}
		return new int[] {
			zielX, zielY
		};
	}
	
	/**
	 * Hilfsmethode fuer korrigierteKoordinaten
	 * 
	 * @param aktuellerWuerfel	Pos des aktuellenn Wuerfels
	 * @param x					x-Koordinate des Wuerfels
	 * @param y					y-Koordinate des Wuerfels
	 * @return					wenn kollision dann wuerfel sonst null
	 */
	private WuerfelGUI findeKollidierendeWuerfel(WuerfelGUI aktuellerWuerfel, int x, int y) {
		for (WuerfelGUI andererWuerfel : alleWuerfelGUI) {
			if(istIgnorierterWuerfel(aktuellerWuerfel, andererWuerfel)) continue;
			if(pruefeKollision(x ,y , andererWuerfel)) return andererWuerfel;
		}
		return null;
	}
	
	private boolean istIgnorierterWuerfel(WuerfelGUI aktueller, WuerfelGUI anderer) {
		return anderer == aktueller || anderer.getLogik().istBehalten();
	}
	
	/**
	 * Hilfsmethode fuer korrigierteKoordinaten 
	 * @param x			x-Koordinaten des Wuerfels
	 * @param y			y-Koordinaten des Wuerfels
	 * @param anderer	der andere Wuerfel
	 * @return 			true, wenn Kollision
	 */
	private boolean pruefeKollision(int x, int y, WuerfelGUI anderer) {
		int andererX = anderer.getxPos();
		int andererY = anderer.getyPos();
		boolean kollisionX = (x < andererX + WUERFEL_GROESSE) && (x + WUERFEL_GROESSE > andererX);
		boolean kollisionY = (y < andererY + WUERFEL_GROESSE) && (y + WUERFEL_GROESSE > andererY);
		return kollisionX && kollisionY;
	}
	
	private boolean istStartPositionGleich(WuerfelGUI wuerfel, int x, int y) {
		return x == wuerfel.getStartX() || y == wuerfel.getStartY();
 	}

	/**
	 * Verschiebt je klick Wuerfel in die WuerfelBank oder zurueck auf Wuerfelflache
	 * Wenn behalten dann kommt er in die WuerfelBank 
	 * wenn nicht behalten also erneut Wuerfeln dann Wuerfelflache
	 * 
	 * @param wuerfel	Der Wuerfel der verschoben wird
	 */
	private void hochRunterhochRunter(WuerfelLogik wuerfel) { 
		if(wuerfel.istBehalten()) {				
			this.runter(wuerfel.getGui());
			bank.getReihenfolge().set(bank.getReihenfolge().indexOf(null), wuerfel.getGui());		
		} else {
			bank.getReihenfolge().set(bank.getReihenfolge().indexOf(wuerfel.getGui()), null);
			this.hoch(wuerfel.getGui());						
		}	
	}
	
	/**
	 * Verschiebt den Wuerfel in die Wuerfelbank
	 * 
	 * @param gui	WuerfelGUI
	 */
	private void runter(WuerfelGUI gui) {
		gui.setIstinBank(true);
		int x = (int)(eimer.getWidth() * bank.getProzentFlaecheX());
		int y = (int)(eimer.getHeight() * bank.getProzentFlaecheY());
		
		x = ((gui.getButton().getWidth() + 5) * bank.getReihenfolge().indexOf(null) ) + x+14;
		y = y + 10;
	
		gui.zeichneLinie(gui.getButton(), x, y, false);	
	}

	/**
	 * Verschiebt den Wuerfel in die Wuerfelflache
	 * Berechnet zufaellige Pos und Korrigiert sie gegebenenfalls
	 * siehe Methoden oben
	 * 
	 * @param gui 	WuerfelGUI
	 */
	private void hoch(WuerfelGUI gui) {
		int x = zufallsKoordinateX(flaeche);
		int y = zufallsKoordinateY(flaeche);
		int xy[] = korrigierteKollision(gui, x, y);
		gui.zeichneLinie(gui.getButton(), xy[0], xy[1], false);
		gui.setIstinBank(false);		
	}
	
	/**
	 * Wuerfelt die Wuerfel
	 * max Anzahl an Wuerfe je nach Debug oder normal 
	 * Wuerfelt nur was nicht Bahlten wird
	 */
	public void wuerfeln() {
		maxWuerfe = menueDaten.isEndloseWuerfe() ? 999 : 3;
		if(wurfAnzahl >= maxWuerfe) return;

		for(WuerfelLogik wuerfel : alleWuerfelLogik) {
			wuerfelWuerfelWennNichtBehalten(wuerfel);
		}
		for (WuerfelGUI gui : getAlleWuerfelGUI()) {
			zeigeWuerfelAufFlache(gui);
		}
		wurfAnzahl++;
		tabellenkommunikation();
	}
	
	/**
	 * Hilfsmethode zu wuerfeln
	 * 
	 * @param wuerfel	WuerfelLogik
	 */
	private void wuerfelWuerfelWennNichtBehalten(WuerfelLogik wuerfel) {
		if(!wuerfel.istBehalten()) wuerfel.wuerfeln();
	}
	
	/**
	 * Berechnet neue Position des Wuerfel auf WuerfelBank
	 * @param gui 		WuerfelGUI
	 */
	private void zeigeWuerfelAufFlache(WuerfelGUI gui) {
		if (gui.getLogik().istBehalten()) return;
		
		int index = bank.getReihenfolge().indexOf(gui);
		if(index != -1) bank.getReihenfolge().set(index, null);
		
		gui.setIstinBank(false);
		int x = zufallsKoordinateX(flaeche);
		int y = zufallsKoordinateY(flaeche);
		int[] xy = korrigierteKollision(gui, x, y);
		gui.zeichneLinie(gui.getButton(), xy[0], xy[1], true);
	}

	/**
	 * kommuniziert die aktuellen Wuerfelwerte
	 * erstellt eine Liste aller Augen der Wuerfel
	 */
	private void tabellenkommunikation() {
		List<Integer> wuerfelWerte = new ArrayList<>();
		for(WuerfelLogik wuerfel : alleWuerfelLogik) {
			wuerfelWerte.add(wuerfel.getAugen());
		}
		if(spielListener != null) {
			spielListener.wennWuerfelGeworfenWerden(wuerfelWerte);
		}
		System.out.println("Wurf "+ wurfAnzahl + "/" + maxWuerfe + "-Würfel: "+ wuerfelWerte);		
	}

	/**
	 * Startet eine neue Runde
	 * setzt WurfAnzahl zurück und 
	 * alle Wuerfel wieder nicht behalten
	 */
	public void neueRunde() {
		wurfAnzahl = 0;
	  	spielLogik.setWuerfeAnzahl(0); 
	  	
	  	this.init = true;
        for (WuerfelLogik wuerfel : alleWuerfelLogik) {
            wuerfel.setBehalten(false);           
        }        
        System.out.println("Neue Runde Gestartet");
    }
	
	
	  public List<WuerfelGUI> getAlleWuerfelGUI() {
			return alleWuerfelGUI;
		}

		public void setAlleWuerfelGUI(List<WuerfelGUI> alleWuerfelGUI) {
			this.alleWuerfelGUI = alleWuerfelGUI;
		}

		public List<WuerfelLogik> getAlleWuerfelLogik() {
			return alleWuerfelLogik;
		}

		public void setAlleWuerfelLogik(List<WuerfelLogik> alleWuerfelLogik) {
			this.alleWuerfelLogik = alleWuerfelLogik;
		}
		 
	    public int getWurfAnzahl() {
	        return wurfAnzahl;
	    }
	    
	    public int getMaxWuerfe() {
	        return maxWuerfe;
	    }
	    
	    public MoeglicheZuege getMoeglicheZuege() {
	        return moeglicheZuege;
	    }
	    
	    public boolean isInit() {
			return init;
		}

		public void setInit(boolean init) {
			this.init = init;
		}

		public int debug() {
			return 0;
		}
}

  