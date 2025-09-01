package gui;

import model.*;

import java.awt.Container;

import javax.swing.*;

public class WuerfelGUI {
	JButton wuerfel;
	WuerfelLogik model;
	int xPos;
	int yPos;
	int startX;
	int startY;
	double prozentXAufFeld;
	double prozentYAufFeld;
	Container Eimer;
	

	public WuerfelGUI(WuerfelLogik model, int startX, int startY, Container Eimer){
		this.model = model;
		this.wuerfel = new JButton();
		this.startX = startX;
		this.startY = startY;
		wuerfel.addActionListener(klick -> { boolean neuerZustand = !model.istBehalten();										
											model.setBehalten(neuerZustand);
											this.hochRunterhochRunter();
											} );
		this.Eimer = Eimer;
		this.xPos = startX;
		this.yPos = startY;
		fresherButton();
		this.prozentXAufFeld = startX * 1.0 / Eimer.getWidth();
		this.prozentYAufFeld = startY * 1.0 / Eimer.getHeight();
		
		
	}
	
	public void fresherButton() {
		ImageIcon bildi = new ImageIcon("Other/" + model.getAugen() + ":50.png" );
		wuerfel.setIcon(bildi);
		wuerfel.setVisible(true);
		wuerfel.setBounds(this.xPos, this.yPos, 50, 50);
	
	}
	
	private void hochRunterhochRunter() { //das muss irgednwo anders hin ich diskutier da grade mit chatgpt drüber ich hasse mvc
		if(this.model.istBehalten()) {
			this.runter();
		
		} else {
			this.hoch();
		}
		
	}
	
	private void runter() {
		// TODO Auto-generated method stub
		
	}

	private void hoch() {
		// TODO Auto-generated method stub
		
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
	 * @param wuerfel welche ich mitgebe um da die positionen zu ändern und da den würfelbutton rauszuziehen
	 * @param zielX der x wert wo es hingezeichnet werden soll
	 * @param zielY der y wert wo es hingezeichnet werden soll
	 */
	
	public void zeichneLinie(JButton wuerfel, int zielX, int zielY) { //methode irgendwann nochmal ausseinanderziehen da zu uncleancodig TODO
		
		double m;
		if(zielX - wuerfel.getX() == 0) { m = 0; }
		else { m =(zielY - wuerfel.getY()) * 1.0 / ( zielX - wuerfel.getX()); } 
		double n = zielY - (zielX * m);
		
		Timer timer = new Timer(4, e -> {
			int xJetzt = wuerfel.getX();
			int yJetzt = wuerfel.getY();
			
			if(Math.abs(m) <= 1) {
				if (xJetzt > zielX) xJetzt--;
				if (xJetzt < zielX) xJetzt++;	
				int yNeu = (int) Math.round(m * xJetzt + n);				
				wuerfel.setLocation(xJetzt, yNeu);	
				this.setyPos(yNeu);
				this.setxPos(xJetzt);
				this.setProzentXAufFeld(this.getxPos() * 1.0 / this.getEimer().getWidth());
				this.setProzentYAufFeld(this.getyPos() * 1.0 / this.getEimer().getHeight());
			
				if(xJetzt == zielX && yNeu == zielY) { ((Timer)e.getSource()).stop();	}
			} else {
				if (yJetzt > zielY) yJetzt--;	
				if (yJetzt < zielY) yJetzt++;				
				int xNeu = (int) Math.round((yJetzt - n) / m);				
				wuerfel.setLocation(xNeu, yJetzt);	
				this.setyPos(yJetzt);
				this.setxPos(xNeu);
				this.setProzentXAufFeld(this.getxPos() * 1.0 / this.getEimer().getWidth());
				this.setProzentYAufFeld(this.getyPos() * 1.0 / this.getEimer().getHeight());
				
				if(xNeu == zielX && yJetzt == zielY) { ((Timer)e.getSource()).stop();	}
			}
		});
		timer.start();		
		
		
	}

	public JButton getButton() {
		return this.wuerfel;
	}
	
	public WuerfelLogik getLogik() {
		return this.model;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public double getProzentXAufFeld() {
		return prozentXAufFeld;
	}
	
	public void setProzentXAufFeld(double prozentXAufFeld) {
		this.prozentXAufFeld = prozentXAufFeld;
	}
	
	public double getProzentYAufFeld() {
		return prozentYAufFeld;
	}
	
	public void setProzentYAufFeld(double prozentYAufFeld) {
		this.prozentYAufFeld = prozentYAufFeld;
	}
	
	public Container getEimer() {
		return Eimer;
	}
	
	public void setEimer(Container eimer) {
		Eimer = eimer;
	}
	
}

