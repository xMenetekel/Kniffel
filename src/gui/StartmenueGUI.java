package gui;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartmenueGUI extends AbstractGUI{
		
		private JButton starteSpiel;
		private JButton einstellungen;
		private JButton exit;
		
			
	
	public StartmenueGUI(Container Eimer) {
		super(1,1,1,1, Eimer);
		this.Eimer.setLayout(null); //Hab erstmal den Layout Manager ausgemacht um es manuell anzuordnen, können wir später aber noch ändern : famous last words, i am so sorry
		
		this.starteSpiel = new JButton("Starte Spiel");
		this.einstellungen = new JButton("Einstellungen");
		this.exit = new JButton("Schließen");
		
			
		
		this.Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiereFelder(Eimer, starteSpiel, einstellungen, exit);
			}			
		});
		
		this.Eimer.add(starteSpiel);
		this.Eimer.add(exit);
		this.Eimer.add(einstellungen); //junge ich hab keine ahnung warum man alles dem content pane zufügen muss und dann aber das gesamte menü visible sein muss, make it make sense
		
		
		
	}
	private void rearrangiereFelder(Container eimer, JButton starteSpiel, JButton einstellungen, JButton exit) {
		int breite = eimer.getWidth();
		int hoehe = eimer.getHeight();
		
		starteSpiel.setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.14285), (int)Math.floor(breite*0.75),(int)Math.floor(hoehe*0.071));
		einstellungen.setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.28571), (int)Math.floor(breite*0.75),(int)Math.floor(hoehe*0.071));
		exit.setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.42857), (int)Math.floor(breite*0.75),(int)Math.floor(hoehe*0.071));
		
		
		
	}

	
	public JButton getStarteSpiel() {
		return starteSpiel;
	}


	public void setStarteSpiel(JButton starteSpiel) {
		this.starteSpiel = starteSpiel;
	}


	public JButton getEinstellungen() {
		return einstellungen;
	}


	public void setEinstellungen(JButton einstellungen) {
		this.einstellungen = einstellungen;
	}


	public JButton getExit() {
		return exit;
	}


	public void setExit(JButton exit) {
		this.exit = exit;
	}


	public void moeglicheSpielModi() {
		
	}
	
	public void hauptmenue() {
		
	}
	
	public void einstellungen() {
		
	}
	
	public void auswahlSpiel() {
	
		
	}
	
	public void debugging() {
		
	}
	
	public void exit() {
		
	}
	

	public Container getEimer() {
		return Eimer;
	}


	
	
}
