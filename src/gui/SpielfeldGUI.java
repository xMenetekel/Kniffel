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
	public SpielfeldGUI () {
		
		spielflaecheGesamt = new JFrame("Spielfeld");
		spielflaecheGesamt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spielflaecheGesamt.setSize(800,700);
		
		wuerfelflaeche = new WuerfelflacheGUI();
		
		wuerfel = new WuerfelGUI();
		
		punktetabelle = new PunkteTabelleGUI();
		
		spielbank = new SpielbankGUI();
			
		spielflaecheGesamt.setVisible(true);
	}
	
	public void aktualisieren() {
		
	}

}
