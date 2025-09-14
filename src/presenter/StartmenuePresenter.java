package presenter;

import model.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.*;

public class StartmenuePresenter {
	
	private StartmenueGUI menueGUI;
	private StartmenueLogik menueDaten;
	private JFrame hauptfenster;

	/**
	 * Initalisiert GUI und Logik fuer Startmenues
	 * 
	 * @param hauptfenster		Hauptfenster der Anwendung
	 * @param eimer				Container in dem die GUI ist
	 */
	public StartmenuePresenter(JFrame hauptfenster, Container eimer) {
		this.hauptfenster = hauptfenster;
		this.menueDaten = new StartmenueLogik();
		this.menueGUI = new StartmenueGUI(eimer);		
		starteStartmenue();
		eimer.revalidate();
		eimer.repaint();		
	}
	
	private void starteStartmenue() {
		menueGUI.getExit().addActionListener(klick -> System.exit(0));
		menueGUI.getEinstellungen().addActionListener(klick -> oeffneEinstellungenDialog());
		menueGUI.getStarteSpiel().addActionListener(klick -> zeigeSpielerAuswahl());
	}
	
	/**
	 * setzt die spieleranzahl und startet das Spiel, wenn alle spieler ihre Daten eingegeben haben
	 * @param anzahl
	 * @param abrage
	 */
	private void spielerButtonGeklickt(int anzahl, JDialog abrage) {
		menueDaten.setSpieleranzahl(anzahl);
		abrage.dispose();
		
		SpielerDaten(anzahl);
		
		SpielLogik spielLogik = new SpielLogik();
		spielLogik.setAlleSpieler(menueDaten.getSpielerListe());
		
		new SpielfeldPresenter(menueGUI.getEimer(), spielLogik, menueDaten);
		menueGUI.getEimer().setVisible(true);
	}
	
	/**
	 * zeigt dialog für die einstellungen
	 */
	private void oeffneEinstellungenDialog() {
		JCheckBox debugBox = new JCheckBox("Debugging aktivieren");
		debugBox.setSelected(menueDaten.isDebugingModus());
		
		JCheckBox endloseWuerfeBox = new JCheckBox("Endlose Würfe aktivieren");
		endloseWuerfeBox.setSelected(menueDaten.isEndloseWuerfe());
		
		JPanel zusammen = new JPanel();
		zusammen.add(endloseWuerfeBox);
		zusammen.add(debugBox);			
		
		int auswahl = JOptionPane.showConfirmDialog(menueGUI.getEimer(),
			zusammen,
			"Einstellungen",
			JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE
		);
		if (auswahl == JOptionPane.OK_OPTION) {
			menueDaten.setDebugingModus(debugBox.isSelected());
			menueDaten.setEndloseWuerfe(endloseWuerfeBox.isSelected());
		}
	}

	/**
	 * zeigt vier buttons um auszuwählen wie viele spieler das spiel haben soll
	 */
	private void zeigeSpielerAuswahl() {
		JDialog abfrage = erstelleSpielerDialog();
		
		for(int i = 1; i<= 4; i++) {
			abfrage.add(erstelleSpielerButton(i, abfrage));
		}
		
		abfrage.pack();
		abfrage.setLocationRelativeTo(menueGUI.getEimer());
		abfrage.setVisible(true);
	}
	
	/**
	 * erstellt das JDialog fenster für die anzahl spieler abfrage
	 * @return
	 */
	private JDialog erstelleSpielerDialog() {
		JDialog abfrage = new JDialog(hauptfenster, "Anzahl Spieler", true);
		abfrage.setLayout(new GridLayout(2, 2, 15, 15));
		abfrage.getRootPane().setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		return abfrage;
	}
	
	/**
	 * Erstellt Buttons fuer die Auswahl der Spieleranzahl
	 * 
	 * @param anzahl	Anzahl der Spieler
	 * @param abfrage	Antwort auf Frage
	 * @return			der ausgewahlte Button
	 */
	private JButton erstelleSpielerButton(int anzahl, JDialog abfrage) {
		JButton button = new JButton(String.valueOf(anzahl));
		button.setPreferredSize(new Dimension(200, 40));
		button.addActionListener(e -> spielerButtonGeklickt(anzahl, abfrage));
		return button;
	}
	
	
	/**
	 * speichert spielerdaten
	 * @param spieleranzahl
	 */
	private void SpielerDaten(int spieleranzahl) {
		for(int i = 0; i < spieleranzahl; i++) {
			String name = frageSpielerNachName(i);
			int iconIndex = icons(i);
			speichereSpielerWennName(i, name, iconIndex);
		}
	}
	
	/**
	 * öffnet die JOptionPane für den Dialog mit dem Spieler um seinen Namen zu bekommen
	 * @param spielerNummer
	 * @return returned den namen des spielers
	 */
	private String frageSpielerNachName(int spielerNummer) {
		return JOptionPane.showInputDialog(
			menueGUI.getEimer(),
			"Name fuer Spieler " + (spielerNummer + 1) + ":",
			"Spieler benennen",
			JOptionPane.PLAIN_MESSAGE
		);
	}
			

	/**
	 * setzt den spielernamen in den daten
	 * @param index index des spielers dessen name gesetzt wird
	 * @param name
	 * @param iconIndex index welches icon der spieler gewählt hat
	 */
	private void speichereSpielerWennName(int index, String name, int iconIndex) {
		if(name != null) {		
			menueDaten.setSpielerName(index, name);
			menueDaten.setSpielerIcon(index, iconIndex);
		}
	}
	
	/**
	 * Dialog zuer Icon-Auswahl fuer jeweiligen Spieler
	 * 
	 * @param indexSpieler		Index des Spielers
	 * @return					angeklicktes Icon 
	 */
	private int icons(int indexSpieler) {		
		Object[] liste = menueGUI.getIcons().toArray();
														
		int auswahl = JOptionPane.showOptionDialog(
				menueGUI.getEimer(), 
				"Wähle ein Icon :)", 
				"Icon Auswahl",
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null, 
				liste, 
				liste[0]);
		
		return auswahl;
	}
	
	
	//Getter und Setter
	
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
