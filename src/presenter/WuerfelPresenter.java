package presenter;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.WuerfelGUI;
import gui.WuerfelflaecheGUI;
import gui.WuerfelBankGUI;
import gui.PunkteTabelleGUI;
import model.Kategorie;
import model.WuerfelLogik;
import model.SpielLogik;
import model.StartmenueLogik;

public class WuerfelPresenter  {

	private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
	private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
	
	private Container container;
	private MoeglicheZuege moeglicheZuege = new MoeglicheZuege();
	
	
	private SpielLogik spielLogik;	
	
	
	private WuerfelflaecheGUI flaeche;
	private WuerfelBankGUI bank; //ich hasse das so sehr 
	private DummeChatGPTLoesungIchHasseAlles keinBockMehr; //TODO umbennenenen
	private StartmenueLogik menueDaten;
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3; //Standardwert
	
	final int WUERFEL_GROESSE = 50;	
	
	private boolean init;

	
	
	
	
	public WuerfelPresenter(Container Eimer, SpielLogik spielLogik, 
							WuerfelflaecheGUI flaeche, WuerfelBankGUI bank, 
							DummeChatGPTLoesungIchHasseAlles keinBockMehr,
							StartmenueLogik menueDaten){
		this.container = Eimer;
		this.flaeche = flaeche;
		this.bank = bank;
		this.keinBockMehr = keinBockMehr;
		this.spielLogik = spielLogik;
		this.menueDaten = menueDaten;
		this.init = true;
		
		for(int i = 0; i < 5; i++) {		//das erstellt 5 würfel und fügt in jeder iteration den wuerfel den entsprechenden arrays hinzu
			WuerfelLogik model = new WuerfelLogik(6);
			WuerfelGUI view = new WuerfelGUI(this, model, 
											(i*55+((int)(bank.getProzentFlaecheX() * Eimer.getWidth())))+5, 
												   (int)((bank.getProzentFlaecheY() * Eimer.getHeight())+6), Eimer); //+6 weil aus irgendeinem grund will es das nicht ordentlich mit 5 machen was aber sinn machen würde
			
			model.setGui(view);
			bank.getReihenfolge().add(view);
			alleWuerfelGUI.add(view);
			alleWuerfelLogik.add(model);		
			container.add(view.getButton());
		}
		
		container.revalidate();
		container.repaint();	
	}
	
	
	public void wuerfelAngeklickt(WuerfelGUI gui) {
		if(this.isInit()) { return; }
		
		if(menueDaten.isDebugingModus()) {
			String[] liste = {"1", "2", "3", "4", "5", "6"};
			
			int auswahl = JOptionPane.showOptionDialog(
					container, 
					"Zahl wählen", 
					"Cheatermodus",
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.PLAIN_MESSAGE,
					null, 
					liste, 
					liste[0]);
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
	
	
	public int getValidesX(WuerfelflaecheGUI flaeche) {
		int validesX;
		int xVon = (int) (flaeche.getProzentFlaecheX() * this.container.getWidth());
		int xBis = (int) ((flaeche.getProzentFlaecheBreite() * this.container.getWidth()) + xVon);
		validesX = new Random().nextInt(xBis-xVon-WUERFEL_GROESSE)+xVon;	
		
		
		
		return validesX;
	}
	public int getValidesY(WuerfelflaecheGUI flaeche) {
		int validesY;
		int YVon = (int) (flaeche.getProzentFlaecheY() * this.container.getHeight());
		int YBis = (int) ((flaeche.getProzentFlaecheHoehe() * this.container.getHeight()) + YVon);
		validesY = new Random().nextInt(YBis-YVon-WUERFEL_GROESSE)+YVon;		
		
		
		return validesY;
	}

	/**
	 * Der ganze Spaß korrigiert Kollisionen. Es muss die WuerfelGUI übergeben werden, da dort die fertigen x/y pos gespeichert werden. der JButton an sich hat durch
	 * den Timer sehr unterschiedliche x/y Werte und dann wird die Kollisionsprüfung absoluter Mist. 
	 * KI viel befragt aber letztenendes hat die KI auch nicht die Lösung des Problems erkannt 
	 * 
	 * BLOSS NIEMALS ÄNDERN
	 * es funktioniert !!!!!!!!!
	 * 
	 * @param aktuellerWuerfel 
	 * @param zielX
	 * @param zielY
	 * @return
	 */
		private int[] korrigierteKollision(WuerfelGUI aktuellerWuerfel, int zielX, int zielY) {
				
			boolean kollisionGefunden;
			do {
				kollisionGefunden = false;
				for(WuerfelGUI andererWuerfel : alleWuerfelGUI) {
					if(andererWuerfel == aktuellerWuerfel || andererWuerfel.getLogik().istBehalten()) {	continue; }				
					int andererX = andererWuerfel.getxPos(); //Es muss das von der gui sein, weil das die zielpositionen sind die auf kollisionen gecheckt werden omg
					int andererY = andererWuerfel.getyPos(); // this mf cost me 3 hours of my life						
					boolean kollisionX = (zielX < andererX + WUERFEL_GROESSE)   &&   (zielX + WUERFEL_GROESSE > andererX);
					boolean kollisionY = (zielY < andererY + WUERFEL_GROESSE)   &&   (zielY + WUERFEL_GROESSE > andererY);										
					if(kollisionX && kollisionY) {
						kollisionGefunden = true;
						zielX = getValidesX(flaeche);
						zielY = getValidesY(flaeche);
						break;
					}
				}					
		} while (kollisionGefunden || zielX == aktuellerWuerfel.getStartX() || zielY == aktuellerWuerfel.getStartY());
			return new int[] {zielX, zielY};
		}
		


	private void hochRunterhochRunter(WuerfelLogik wuerfel) { 
		
		if(wuerfel.istBehalten()) {
				
			this.runter(wuerfel.getGui());
			bank.getReihenfolge().set(bank.getReihenfolge().indexOf(null), wuerfel.getGui());	
			
		
		} else {
			bank.getReihenfolge().set(bank.getReihenfolge().indexOf(wuerfel.getGui()), null);
			this.hoch(wuerfel.getGui());			
			
		}
		
	}
	
	private void runter(WuerfelGUI gui) {
		gui.setIstinBank(true);
		int x = (int)(container.getWidth() * bank.getProzentFlaecheX());
		int y = (int)(container.getHeight() * bank.getProzentFlaecheY());
		
		x = ((gui.getButton().getWidth() + 5) * bank.getReihenfolge().indexOf(null) ) + x+4; //die +4 sind pixel ausgleiche weil meine völlig legitime rechnung nicht funktioniert
		y = y + 6; //ebenso hier, eigentlich sollte es +5 sein aber das ist einen pixel zuviel verschoben weil natürlich ist es das
	
		gui.zeichneLinie(gui.getButton(), x, y, false);
		
		
		
	}

	private void hoch(WuerfelGUI gui) {
		int x = getValidesX(flaeche);
		int y = getValidesY(flaeche);
		int xy[] = korrigierteKollision(gui, x, y);
		gui.zeichneLinie(gui.getButton(), xy[0], xy[1], false);
		gui.setIstinBank(false);
		
	}
	
	
	

	public void wuerfeln() {
		maxWuerfe = menueDaten.isEndloseWuerfe() ? 999 : 3;		
		if(wurfAnzahl >= maxWuerfe) { return; }
		
		for(WuerfelLogik wuerfel : alleWuerfelLogik) {
			if(!wuerfel.istBehalten()) {
					wuerfel.wuerfeln();
				
			}				
		
		}
		getAlleWuerfelGUI().forEach(wuerfelGUI -> {
			
			if (!wuerfelGUI.getLogik().istBehalten()) {
				
				if(! (bank.getReihenfolge().indexOf(wuerfelGUI) == -1)) { bank.getReihenfolge().set(bank.getReihenfolge().indexOf(wuerfelGUI), null); } //bitte töten sie mich nicht dafür herr höppner
				
				
				wuerfelGUI.setIstinBank(false);
				int x, y;
				x = getValidesX(flaeche);
				y = getValidesY(flaeche);
				int[] xy = korrigierteKollision(wuerfelGUI, x, y);
			  wuerfelGUI.zeichneLinie(wuerfelGUI.getButton(), xy[0], xy[1], true);	
		
			}	
		});				
		
		wurfAnzahl++;
		
		tabellenkommunikation();
	}
	
	private void tabellenkommunikation() {
		List<Integer> wuerfelWerte = new ArrayList<>();
		for(WuerfelLogik wuerfel : alleWuerfelLogik) {
			wuerfelWerte.add(wuerfel.getAugen());
		}
		if(keinBockMehr != null) {
			keinBockMehr.wasPassiertWennWirEinenWuerfelWerfen(wuerfelWerte);
		}
		System.out.println("Wurf "+ wurfAnzahl + "/" + maxWuerfe + "-Würfel: "+ wuerfelWerte);
		
	}


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
			// TODO Auto-generated method stub
			return 0;
		}
}
    
  

                        
     
   
  