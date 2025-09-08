package presenter;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
	private DummeChatGPTLoesungIchHasseAlles keinBockMehr;
	private StartmenueLogik menueDaten;
	
	int wurfAnzahl = 0;
	int maxWuerfe = 3;
	
	final int WUERFEL_GROESSE = 50;	
	
	//, PunkteTabelleGUI punkteTabelle,SpielLogik spielLogik
	
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
		
		for(int i = 0; i < 5; i++) {		//das erstellt 5 würfel und fügt in jeder iteration den wuerfel den entsprechenden arrays hinzu
			WuerfelLogik model = new WuerfelLogik(6);
			WuerfelGUI view = new WuerfelGUI(this, model, i*55+((int)(0.1 * Eimer.getWidth())), (int)(0.592857 * Eimer.getHeight()+5), Eimer);
			
			model.setGui(view);
			
			alleWuerfelGUI.add(view);
			alleWuerfelLogik.add(model);		
			container.add(view.getButton());
		}
		
		container.revalidate();
		container.repaint();	
	}
	
	
	public void wuerfelAngeklickt(WuerfelGUI gui) {
		WuerfelLogik zustandDerLogik = gui.getLogik();
		boolean neuerZustand = !zustandDerLogik.istBehalten();										
		zustandDerLogik.setBehalten(neuerZustand);
		hochRunterhochRunter(zustandDerLogik);
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
		} while (kollisionGefunden);
			return new int[] {zielX, zielY};
		}
		


	private void hochRunterhochRunter(WuerfelLogik wuerfel) { 
		
		if(wuerfel.istBehalten()) {
			this.runter(wuerfel.getGui());
		
		} else {
			this.hoch(wuerfel.getGui());
		}
		
	}
	
	private void runter(WuerfelGUI gui) {
		int x = (int)(container.getWidth() * bank.getProzentFlaecheX());
		int y = (int)(container.getHeight() * bank.getProzentFlaecheY());
		
		x = ((gui.getButton().getWidth() + 5) * zaehleBankFuelle()-1) + x;
		y = y + 5;
	
		gui.zeichneLinie(gui.getButton(), x, y, false);
		
		
		
	}

	private void hoch(WuerfelGUI gui) {
		int x = getValidesX(flaeche);
		int y = getValidesY(flaeche);
		int xy[] = korrigierteKollision(gui, x, y);
		gui.zeichneLinie(gui.getButton(), xy[0], xy[1], false);
		
		
	}
	
	private int zaehleBankFuelle() {
		int bank = 0;
		for (WuerfelLogik wuerfel : alleWuerfelLogik) {
			if (wuerfel.istBehalten()) { bank++; }			
		}		
		return bank;
	}
	
	

	public void wuerfeln() {
		int aktuellesMaxWuerfeln;
		if(menueDaten.isDebugingModus()) {
			aktuellesMaxWuerfeln = 999;
		} else {
			aktuellesMaxWuerfeln = 3;
		}
		
		if(wurfAnzahl >= aktuellesMaxWuerfeln) { return; }
		
		if(menueDaten.isDebugingModus()) {
			int debugWert = (wurfAnzahl % 6) +1;
			
			for(WuerfelLogik wuerfel : alleWuerfelLogik) {
				if(!wuerfel.istBehalten()) {
					wuerfel.setAugen(debugWert);
			
				}
			} 
		}else {
			for(WuerfelLogik wuerfel : alleWuerfelLogik) {
				if(!wuerfel.istBehalten()) {
					wuerfel.wuerfeln();
				}		
			}
		}
		
		//getAlleWuerfelLogik().forEach(w -> { if(menueDaten.isDebugingModus()) {
			//int counter = 1;
			//for(WuerfelLogik wuerfel : alleWuerfelLogik) {
			//	if (!wuerfel.istBehalten()) {
			//		wuerfel.setAugen(counter); 
			//		counter++;
			//		if(counter > 6) counter = 1;
			//	}
			//}
		//}else {
			//getAlleWuerfelLogik().forEach(wuerfel ->{
			//	if(!w.istBehalten()) {
				//	w.wuerfeln();
				//}
			//});	
	//	}
	//});
		
		getAlleWuerfelGUI().forEach(wuerfelGUI -> {
			if (!wuerfelGUI.getLogik().istBehalten()) {
				int x, y;
				x = getValidesX(flaeche);
				y = getValidesY(flaeche);
				int[] xy = korrigierteKollision(wuerfelGUI, x, y);
			  wuerfelGUI.zeichneLinie(wuerfelGUI.getButton(), xy[0], xy[1], true);											  										  
			}	
		});	
			
		
		wurfAnzahl++;
		
		List<Integer> wuerfelWerte = new ArrayList<>();
		for(WuerfelLogik wuerfel : alleWuerfelLogik) {
			wuerfelWerte.add(wuerfel.getAugen());
		}
		if(keinBockMehr != null) {
			keinBockMehr.wasPassiertWennWirEinenWuerfelWerfen(wuerfelWerte);

		}
		System.out.println("Wurf"+ wurfAnzahl + "/" + maxWuerfe + "-Würfel: "+ wuerfelWerte);
	}
	
	public void neueRunde() {
		wurfAnzahl =0;
	  	spielLogik.setWuerfeAnzahl(0); 
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
}
    
  

                        
     
   
  