package control;

import model.*;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

import gui.*;

public class StartmenueControl {
	
	private StartmenueGUI menueGUI;
	private StartmenueLogik menueDaten;


	public StartmenueControl() {
		this.menueGUI = new StartmenueGUI();
		this.menueDaten = new StartmenueLogik();
		starteStartmenue();
	}
	
	private void starteStartmenue() {
		menueGUI.getExit().addActionListener(klick -> {System.exit(0);});
		menueGUI.getStarteSpiel().addActionListener(klick ->{
			zeigeSpielerAuswahl();
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
				
				new SpielfeldGUI(menueGUI.getEimer(),menueDaten);
			});
			abfrage.add(druecker);
		}
		abfrage.pack(); // größe anpassen es könnte so einfach sein
		abfrage.setLocationRelativeTo(menueGUI.getStartmenueGesamt()); //schön zentrieren
		abfrage.setVisible(true); 
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
