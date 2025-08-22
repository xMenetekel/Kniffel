package gui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SpielfeldGUI {
	
	private JFrame spielflaecheGesamt;
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelGUI wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private SpielbankGUI spielbank;

	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI (Container Eimer) {
		Eimer.removeAll();		
		
		Eimer.setLayout(null);
		
		
		wuerfelflaeche = new WuerfelflaecheGUI();
		wuerfel = new WuerfelGUI();
		punktetabelle = new PunkteTabelleGUI();
		spielbank = new SpielbankGUI();
		
		wuerfelflaeche.getPanel().setBounds(50, 200, 350, 200);
		wuerfel.getPanel().setBounds(100, 435, 270, 50);
		punktetabelle.getPanel().setBounds(440, 50, 300, 550);
		spielbank.getPanel().setBounds(50, 500, 350, 100);
		
		wuerfelflaeche.getPanel().setBackground(Color.RED);
		wuerfel.getPanel().setBackground(Color.BLACK);
		punktetabelle.getPanel().setBackground(Color.CYAN);
		spielbank.getPanel().setBackground(Color.GREEN);
		
		Eimer.add(wuerfelflaeche.getPanel());
		Eimer.add(wuerfel.getPanel());
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
				
		Eimer.revalidate();
		Eimer.repaint();
		
		
		
	}
	
	public void aktualisieren() {
		
	}

}
