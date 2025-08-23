package model;

import java.util.Random;

public class W6 implements WuerfelDaten {
	private int augen;
	private int seiten = 6;
	private boolean behalten = false;
	private Random zufall = new Random();
	
	@Override
	public void wuerfeln() {
		if(!behalten) {
			augen = zufall.nextInt(seiten)+1;
		}
	}
	
	@Override
	public void nichtBehalten() {
		behalten = false;
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
}
