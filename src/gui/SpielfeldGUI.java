package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import control.WuerfelControl;

public class SpielfeldGUI {
	
	private JFrame spielflaecheGesamt;
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelGUI wuerfel;
	private WuerfelControl test;
	private PunkteTabelleGUI punktetabelle;
	private SpielbankGUI spielbank;

	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI (Container Eimer) {
		Eimer.removeAll();		
		
		Eimer.setLayout(null);
		
		test = new WuerfelControl();
		wuerfelflaeche = new WuerfelflaecheGUI();
	//	wuerfel = new WuerfelGUI();
		punktetabelle = new PunkteTabelleGUI();
		spielbank = new SpielbankGUI();
		
		wuerfelflaeche.getPanel().setBounds(50, 200, 350, 200);
		//wuerfel.getPanel().setBounds(100, 435, 270, 50);
		punktetabelle.getPanel().setBounds(440, 50, 300, 550);
		spielbank.getPanel().setBounds(50, 500, 350, 100);
		
		Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiereWuerfelflaeche(Eimer, wuerfelflaeche);
			//	rearrangiereWuerfel(Eimer, wuerfel);
				rearrangierePunktetabelle(Eimer, punktetabelle);
				rearrangiereSpielbank(Eimer, spielbank);
			}

			
		});
		
		wuerfelflaeche.getPanel().setBackground(Color.RED);
	//	wuerfel.getPanel().setBackground(Color.BLACK);
		
		punktetabelle.getPanel().setBackground(Color.CYAN);
		spielbank.getPanel().setBackground(Color.GREEN);
		

	/*	wuerfel.wuerfel1.setBounds(100, 435, 50, 50);
		wuerfel.wuerfel2.setBounds(155, 435, 50, 50);
		wuerfel.wuerfel3.setBounds(210, 435, 50, 50);
		wuerfel.wuerfel4.setBounds(265, 435, 50, 50);
		wuerfel.wuerfel5.setBounds(320, 435, 50, 50);
		
		Eimer.add(wuerfel.wuerfel1);
		Eimer.add(wuerfel.wuerfel2);
		Eimer.add(wuerfel.wuerfel3);
		Eimer.add(wuerfel.wuerfel4);
		Eimer.add(wuerfel.wuerfel5);
		
		
		*/
		for (WuerfelGUI wuerfelGUI : test.getAlleWuerfelGUI()) {
	        Eimer.add(wuerfelGUI.getButton());
	    }
		
		Eimer.add(wuerfelflaeche.getPanel());
	//	Eimer.add(wuerfel.getPanel());
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
				
		Eimer.revalidate();
		Eimer.repaint();
		
		
		
	}
	protected void rearrangiereSpielbank(Component e, SpielbankGUI sb) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		sb.getPanel().setBounds((int)Math.floor(breite*0.0625), (int)Math.floor(hoehe*0.71428), (int)Math.floor(breite*0.4375),(int)Math.floor(hoehe*0.142857));
	
	}
/*	protected void rearrangiereWuerfel(Component e, WuerfelGUI w) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		w.getPanel().setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.6214), (int)Math.floor(breite*0.3375),(int)Math.floor(hoehe*0.0714));
		
	}*/
	protected void rearrangierePunktetabelle(Component e, PunkteTabelleGUI p) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		p.getPanel().setBounds((int)Math.floor(breite*0.55), (int)Math.floor(hoehe*0.0714), (int)Math.floor(breite*0.375),(int)Math.floor(hoehe*0.7857));
		
	}
	public void rearrangiereWuerfelflaeche(Component e, WuerfelflaecheGUI wf) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		wf.getPanel().setBounds((int)Math.floor(breite*0.0625), (int)Math.floor(hoehe*0.285), (int)Math.floor(breite*0.4375),(int)Math.floor(hoehe*0.25));
		
		
	}
	
	public void aktualisieren() {
		
	}

}
