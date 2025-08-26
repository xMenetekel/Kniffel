package model;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;


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
	public Object wuerfelAngeklickt(JButton wuerfel, boolean behalten) {
		int zielX;
		int zielY;
		if(behalten) { //will die werte auf die positionsdinge im vektor draufpacken
			zielX = (new Random().nextInt(300))+50;  //die werte später anpassen auf werte zum skalieren!!!!!!!!!! TODO TODO TODO 
			zielY = (new Random().nextInt(150))+200; //lol die werte schon jetzt anpassen, die werden nämlich immer unter dem würfelpanel sein weil das als 0,0 zählt. irgendwie müssen die aus dem panel rausgeholt werden. flag probably boolean für ob unten oder oben problem für später
		}
		else {
			zielX = (new Random().nextInt(300))+50;  //die werte später anpassen auf werte zum skalieren!!!!!!!!!! TODO TODO TODO 
			zielY = (new Random().nextInt(150))+200; //lol die werte schon jetzt anpassen, die werden nämlich immer unter dem würfelpanel sein weil das als 0,0 zählt. irgendwie müssen die aus dem panel rausgeholt werden. flag probably boolean für ob unten oder oben problem für später
		}
		
		
		Timer timer = new Timer(6, e -> {
			int xJetzt = wuerfel.getX();
			int yJetzt = wuerfel.getY();
			
			if (xJetzt > zielX) xJetzt--;
			if (xJetzt < zielX) xJetzt++;
			if (yJetzt > zielY) yJetzt--;		//lol
			if (yJetzt < zielY) yJetzt++;
			
			wuerfel.setLocation(xJetzt, yJetzt);
			
			if(xJetzt == zielX && yJetzt == zielY) {
				((Timer)e.getSource()).stop();
			}
			
		});
		timer.start();
		
		return null;
		
	}
}

