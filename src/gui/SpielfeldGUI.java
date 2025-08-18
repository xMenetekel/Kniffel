package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SpielfeldGUI {
	
	private JFrame spielflaecheGesamt;
	private WuerfelflacheGUI wuerfelflaeche;
	private WuerfelGUI wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private SpielbankGUI spielbank;
	private JButton Test;
	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI (Container Eimer) {
		spielflaecheGesamt = new JFrame("Spiel");
		spielflaecheGesamt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Test = new JButton("Test");
		Test.setBounds(20, 20, 200, 30);
		
		Eimer.setLayout(null);
		
		Eimer.add(Test);
		
	
		
		wuerfelflaeche = new WuerfelflacheGUI();
		
		wuerfel = new WuerfelGUI();
		
		punktetabelle = new PunkteTabelleGUI();
		
		spielbank = new SpielbankGUI();
			
		
		spielflaecheGesamt.setVisible(true);
	}
	
	public void aktualisieren() {
		
	}

}
