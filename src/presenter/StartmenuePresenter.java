package presenter;

import model.*;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import gui.*;

public class StartmenuePresenter {
	
	private StartmenueGUI menueGUI;
	private StartmenueLogik menueDaten;


	public StartmenuePresenter() {
		this.menueGUI = new StartmenueGUI();
		this.menueDaten = new StartmenueLogik();
		starteStartmenue();
	}
	
	private void starteStartmenue() {
		menueGUI.getExit().addActionListener(klick -> {System.exit(0);});
		menueGUI.getEinstellungen().addActionListener(klick -> {}); //TODO muss noch implementiert werden
		menueGUI.getStarteSpiel().addActionListener(klick ->{
			zeigeSpielerAuswahl(); //hier wird SpielfeldPresenter() aufgerufen
		});
		
	}
	
	
	private void zeigeSpielerAuswahl() {
		//Hauptfenster Blockieren und eingabe User
		JDialog abfrage = new JDialog(menueGUI.getStartmenueGesamt(), "Anzahl Spieler",true);
		abfrage.setLayout(new GridLayout(2,2,15,15)); //Abstand zwischenButtons
		abfrage.getRootPane().setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		//Banstand von Button zu Fenster oben, links, unten, rechts
		//ja hier machen wir mal ein Layout 
		
		//Buttons anlegen zwar nur 1-4 dadurch aber keine Abfragefehler
		for(int i = 1; i<= 4; i++) {
			final int anzahl = i; 
			JButton druecker = new JButton(String.valueOf(i));
			//auswahl speichern und Schließen
			druecker.setPreferredSize(new Dimension(200,40));
			
			druecker.addActionListener(e ->{
				menueDaten.setSpieleranzahl(anzahl); //speichern 
				abfrage.dispose(); //schließen
				
				benenneSpieler(anzahl); //Namen hinzufügen
				
				SpielLogik spielLogik = new SpielLogik();
				spielLogik.setSpieler(menueDaten.getSpielerListe()); //Initialisierung Spiellogik mit Spielern
				
				new SpielfeldPresenter(menueGUI.getEimer(), menueDaten, spielLogik);
				
				menueGUI.getStartmenueGesamt().setVisible(true);
			});
			abfrage.add(druecker);
		}
		abfrage.pack(); // größe anpassen es könnte so einfach sein
		abfrage.setLocationRelativeTo(menueGUI.getStartmenueGesamt()); //schön zentrieren
		abfrage.setVisible(true); 
	}
	
	private void benenneSpieler(int spieleranzahl) {
		for(int i = 0; i < spieleranzahl; i++) {
			String name = JOptionPane.showInputDialog(
					menueGUI.getStartmenueGesamt(),
					"Name fuer Spieler " + (i+1) + ":",
					"Spieler benennen",
					JOptionPane.PLAIN_MESSAGE);
			if(name != null) {
				menueDaten.setSpielerName(i, name);
			}
		}
	}
	
	public void setMenueGUI(StartmenueGUI menueGUI) {
		this.menueGUI = menueGUI;
	}

	public StartmenueLogik getMenueDaten() {
		return menueDaten;
	}

	public void setMenueDaten(StartmenueLogik menueDaten) {
		this.menueDaten = menueDaten;
	}
	
	
}
