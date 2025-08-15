package gui;

import javax.swing.JFrame;

public class SpielfeldGUI {
	
	private JFrame spielflaecheGesamt;
	private WuerfelflacheGUI wuerfelflaeche;
	private WuerfelGUI wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private SpielbankGUI spielbank;
	
	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI() {
		
		spielflaecheGesamt = new JFrame("Spielfeld");
		
		wuerfelflaeche = new WuerfelflacheGUI();
		
		wuerfel = new WuerfelGUI();
		
		punktetabelle = new PunkteTabelleGUI();
		
		spielbank = new SpielbankGUI();
			
	}
	
	public void aktualisieren() {
		
	}

}
