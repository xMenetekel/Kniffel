package model;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

import gui.WuerfelGUI;


public class WuerfelLogik {

	private int seiten;
	private int augen;
	private boolean behalten;
	private Random zufall;
	
	
	//Konstruktor 
	public WuerfelLogik(int seiten) {
		this.seiten = seiten;
		this.behalten = false;
		this.zufall = new Random();
		wuerfeln();
	}
	

	
	
	public void wuerfeln() {
		if(!behalten) {
			augen = zufall.nextInt(seiten)+1; //keine 0 seiten
		}
		
	}

	
	
	public int getAugen() {
		return augen;
	}
	
	public void setAugen(int augen) {
		this.augen = augen;
	}


	
	public int getSeiten() {
		return seiten;
	}



	
	public void setBehalten(boolean behalten) {
		this.behalten = behalten;
		
	}
	
	//Getter
	public boolean istBehalten() {
		return behalten;
	}

	
	public void nichtBehalten() {
		behalten = false;
	}


	public void anzahlZuege() {
		
	}
	
	public void positionWuerfel() {
		
	}
	/**
	 * /**
	 * Rechnet mit der Gradengleichung die neuen Werte aus um dann Schrittweise in einer schöden Gerade den Würfel von A nach B zu transportieren
	 * 
	 * Bei flachem Anstieg muss man x Schrittweise berechnen und bei starken Anstieg y Schrittweise. Wenn m nicht mit 1.0 multipliziert wird dann
	 * wertet es nicht als Double aus und das ganze kriegt einen hässligen Knick, das plus Hilfe bei der Geschwindigkeit vom Timer (Also das eben erwähnte
	 * ob man x oder y Schritweise inkrementiert) wurde mit KI Hilfe verstanden. 
	 * 
	 * Für langsamer / Schneller das x bei new Timer{x, e -> ...} vergrößern / verkleinern
	 * 
	 * @param gui welche ich mitgebe um da die positionen zu ändern und da den würfelbutton rauszuziehen
	 * @param behalten die Flag ob der Würfel grade auf der Auswechselbank sitzt oder nicht
	 * @return NIX, ändert ja nur Bild
	 */
	public Object wuerfelAngeklickt(WuerfelGUI gui, boolean behalten) { //methode irgendwann nochmal ausseinanderziehen da zu uncleancodig TODO
		JButton wuerfel = gui.getButton();
		int zielX;
		int zielY;
		if(behalten) { //will die werte auf die positionsdinge im vektor draufpacken
			zielX = gui.getStartX(); //die werte später anpassen auf werte zum skalieren!!!!!!!!!! TODO TODO TODO 
			zielY = gui.getStartY(); //lol die werte schon jetzt anpassen, die werden nämlich immer unter dem würfelpanel sein weil das als 0,0 zählt. irgendwie müssen die aus dem panel rausgeholt werden. flag probably boolean für ob unten oder oben problem für später
		}
		else {
			zielX = (new Random().nextInt(300))+50;  //die werte später anpassen auf werte zum skalieren!!!!!!!!!! TODO TODO TODO 
			zielY = (new Random().nextInt(150))+200; //lol die werte schon jetzt anpassen, die werden nämlich immer unter dem würfelpanel sein weil das als 0,0 zählt. irgendwie müssen die aus dem panel rausgeholt werden. flag probably boolean für ob unten oder oben problem für später
		}
		double m;
		if(zielX - wuerfel.getX() == 0) { m = 0; }
		else { m =(zielY - wuerfel.getY()) * 1.0 / ( zielX - wuerfel.getX()); } //wenn man nicht mit 1.0 multipliziert dann wird es nicht als double gewertet und die ganze rechnung wird bescheuert lmao
		double n = zielY - (zielX * m);
		
		Timer timer = new Timer(4, e -> {
			int xJetzt = wuerfel.getX();
			int yJetzt = wuerfel.getY();
			
			if(Math.abs(m) <= 1) {
				if (xJetzt > zielX) xJetzt--;
				if (xJetzt < zielX) xJetzt++;	
				int yNeu = (int) Math.round(m * xJetzt + n);				
				wuerfel.setLocation(xJetzt, yNeu);	
				gui.setyPos(yNeu);
				gui.setxPos(xJetzt);
				gui.setProzentXAufFeld(gui.getxPos() * 1.0 / gui.getEimer().getWidth());
				gui.setProzentYAufFeld(gui.getyPos() * 1.0 / gui.getEimer().getHeight());
			
				if(xJetzt == zielX && yNeu == zielY) { ((Timer)e.getSource()).stop();	}
			} else {
				if (yJetzt > zielY) yJetzt--;	
				if (yJetzt < zielY) yJetzt++;				
				int xNeu = (int) Math.round((yJetzt - n) / m);				
				wuerfel.setLocation(xNeu, yJetzt);	
				gui.setyPos(yJetzt);
				gui.setxPos(xNeu);
				gui.setProzentXAufFeld(gui.getxPos() * 1.0 / gui.getEimer().getWidth());
				gui.setProzentYAufFeld(gui.getyPos() * 1.0 / gui.getEimer().getHeight());
				
				if(xNeu == zielX && yJetzt == zielY) { ((Timer)e.getSource()).stop();	}
			}
		});
		timer.start();		
		return null;
		
	}
}

