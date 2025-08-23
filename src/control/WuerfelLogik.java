package control;

import java.util.Random;
import model.WuerfelDaten;

public class WuerfelLogik implements WuerfelDaten {
	
	private int  seiten;
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
	

	
	@Override
	public void wuerfeln() {
		if(!behalten) {
			augen = zufall.nextInt(seiten)+1; //keine 0 seiten
		}
		
	}

	
	@Override
	public int getAugen() {
		return augen;
	}


	@Override
	public int getSeiten() {
		return seiten;
	}



	@Override
	public void setBehalten(boolean behalten) {
		this.behalten = behalten;
		
	}
	
	//Getter
	public boolean istBhalten() {
		return behalten;
	}

	@Override
	public void nichtBehalten() {
		behalten = false;
	}


	public void anzahlZuege() {
		
	}
	
	public void positionWuerfel() {
		
	}

}
