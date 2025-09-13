package model;

import java.util.Random;

public class W6 extends WuerfelLogik {
	public W6(int seiten) {
		super(seiten);		
	}

	private int augen;
	
	private int seiten = 6;
	private boolean behalten = false;
	private Random zufall = new Random();
	
	@Override
	public void wuerfeln() {
		if(!behalten) {
			setAugen(zufall.nextInt(seiten)+1);
		}
	}
	
	@Override
	public void nichtBehalten() {
		behalten = false;
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
}
