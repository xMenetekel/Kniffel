package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartmenueGUI {
		private JFrame startmenueGesamt;
		private JButton starteSpiel;
		private JButton einstellungen;
		private JButton exit;
		private Container Eimer;
			
	
	
	


	public StartmenueGUI() {
		this.startmenueGesamt = new JFrame("Startmenue");
		this.startmenueGesamt.setSize(800, 700);
		
		this.Eimer = startmenueGesamt.getContentPane();		
		this.Eimer.setLayout(null); //Hab erstmal den Layout Manager ausgemacht um es manuell anzuordnen, können wir später aber noch ändern
		
		this.starteSpiel = new JButton("Starte Spiel");
		this.einstellungen = new JButton("Einstellungen");
		this.exit = new JButton("Schließen");
		
		
		this.starteSpiel.setBounds  (100, 100, 600, 50);
		this.einstellungen.setBounds(100, 200, 600, 50);
		this.exit.setBounds         (100, 300, 600, 50);
		
		this.exit.addActionListener(klick -> {System.exit(0);});
		this.starteSpiel.addActionListener(klick -> {new SpielfeldGUI(Eimer);});
		this.startmenueGesamt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		this.Eimer.add(starteSpiel);
		this.Eimer.add(exit);
		this.Eimer.add(einstellungen); //junge ich hab keine ahnung warum man alles dem content pane zufügen muss und dann aber das gesamte menü visible sein muss, make it make sense
		
		this.startmenueGesamt.setVisible(true);
		
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
	public JFrame getStartmenueGesamt() {
		return startmenueGesamt;
	}


	public void setStartmenueGesamt(JFrame startmenueGesamt) {
		this.startmenueGesamt = startmenueGesamt;
	}


	public Container getEimer() {
		return Eimer;
	}


	public void setEimer(Container eimer) {
		Eimer = eimer;
	}
	
}
