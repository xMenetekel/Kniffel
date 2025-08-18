package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Startmenue {
		private JFrame startmenueGesamt;
		private JButton starteSpiel;
		private JButton einstellungen;
		private JButton exit;
		private Container Eimer;
			
	
	
	public Startmenue() {
		startmenueGesamt = new JFrame("Startmenue");
		startmenueGesamt.setSize(800, 700);
		
		Eimer = startmenueGesamt.getContentPane();		
		Eimer.setLayout(null); //Hab erstmal den Layout Manager ausgemacht um es manuell anzuordnen, können wir später aber noch ändern
		
		starteSpiel = new JButton("Starte Spiel");
		einstellungen = new JButton("Einstellungen");
		exit = new JButton("Schließen");
		
		
		starteSpiel.setBounds  (100, 100, 600, 50);
		einstellungen.setBounds(100, 200, 600, 50);
		exit.setBounds         (100, 300, 600, 50);
		
		exit.addActionListener(klick -> {System.exit(0);});
		starteSpiel.addActionListener(klick -> {Eimer.removeAll();
												new SpielfeldGUI(Eimer);
												Eimer.repaint();});
		startmenueGesamt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		Eimer.add(starteSpiel);
		Eimer.add(exit);
		Eimer.add(einstellungen); //junge ich hab keine ahnung warum man alles dem content pane zufügen muss und dann aber das gesamte menü visible sein muss, make it make sense
		
		startmenueGesamt.setVisible(true);
		
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
	
}
