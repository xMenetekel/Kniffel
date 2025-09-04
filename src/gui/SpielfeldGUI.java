package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import control.WuerfelControl;

public class SpielfeldGUI {
	
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelControl wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private WuerfelBankGUI wuerfelbankGUI;
	private SpielbankGUI spielbank;
	private WuerfelnButtonGUI wuerfeln;
	private JLabel hintergrund;
	private PunkteEintragenButtonGUI punkteEintragen;
	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI (Container Eimer) {
		Eimer.removeAll();		
		
		Eimer.setLayout(null);
		
		
		
		wuerfelflaeche = new WuerfelflaecheGUI();
		rearrangiere(Eimer, wuerfelflaeche);
		
		punktetabelle = new PunkteTabelleGUI();
		rearrangiere(Eimer, punktetabelle);
		
		spielbank = new SpielbankGUI();
		rearrangiere(Eimer, spielbank);
		
		wuerfelbankGUI = new WuerfelBankGUI();
		wuerfel = new WuerfelControl(Eimer, wuerfelflaeche, wuerfelbankGUI, punktetabelle);	
		rearrangiereWuerfel(Eimer, wuerfel, wuerfelbankGUI);
		
		wuerfeln = new WuerfelnButtonGUI();
		rearrangiere(Eimer, wuerfeln);
		
		punkteEintragen = new PunkteEintragenButtonGUI();
		rearrangiere(Eimer, punkteEintragen);
		
		hintergrund = new JLabel();
		
		
		
	
		wuerfeln.getButton().addActionListener(klick -> {wuerfel.wuerfeln(); 
											if (wuerfel.getWurfAnzahl() == wuerfel.getMaxWuerfe()) {
									            aktualisierePunkteTabelle();
									        }
									    });		
									    
		punkteEintragen.getButton().addActionListener(klick -> {
		        eintragePunkteFuerAktuellenSpieler();
		});
		ImageIcon bildi = new ImageIcon("Other/hintergrund.jpg" );
		hintergrund.setIcon(bildi);
		hintergrund.setBounds(0,0,800,700);
		hintergrund.setVisible(true);
		

		
		
		Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiere(Eimer, wuerfelflaeche);
				rearrangiereWuerfel(Eimer, wuerfel, wuerfelbankGUI);
				rearrangiere(Eimer, punktetabelle);
				rearrangiere(Eimer, spielbank);
				
			}

			
		});
		
			


		for (WuerfelGUI wuerfelGUI : wuerfel.getAlleWuerfelGUI()) {
	        Eimer.add(wuerfelGUI.getButton());
	    }
		
		Eimer.add(wuerfelflaeche.getPanel());
		Eimer.add(punkteEintragen.getPanel());
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
		Eimer.add(wuerfeln.getPanel());
		Eimer.add(wuerfelbankGUI.getPanel());
		Eimer.add(hintergrund);
		
		Eimer.revalidate();
		Eimer.repaint();
		
		
	}
	
	
    private void aktualisierePunkteTabelle() {
        System.out.println("Tabelle sollte aktualisiert werden");	// Damit die Tabelle aktualisiert werden kann
    }
    
    private void eintragePunkteFuerAktuellenSpieler() {	//damit Punkte eingetragen werden köennen
        System.out.println("Punkte eintragen für aktuellen Spieler");
    }
    
	protected void rearrangiereSpielbank(Component e, SpielbankGUI sb) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		sb.getPanel().setBounds((int)Math.floor(breite*0.0625), (int)Math.floor(hoehe*0.71428), (int)Math.floor(breite*0.4375),(int)Math.floor(hoehe*0.142857));
	}
	
	/**
	 * junge ich hab das selber ohne KI geschrieben ich werde nie wieder so einen höhenflug erleben wie grade
	 * jedenfalls resized das alle würfel auf ihre prozentualen werte auf dem bildschirm wenn man das spielfeld größer oder kleinerzieht
	 * @param e
	 * @param w
	 */
	protected void rearrangiereWuerfel(Component e, WuerfelControl w, WuerfelBankGUI bank) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		w.getAlleWuerfelGUI().forEach(t -> { t.setxPos( (int) Math.round(t.prozentXAufFeld * breite)  );
											 t.setyPos( (int) Math.round(t.prozentYAufFeld * hoehe));
											 t.setProzentXAufFeld(t.getxPos() *1.0 / breite);
											 t.setProzentYAufFeld(t.getyPos() *1.0 / hoehe);
											 t.getButton().setBounds(t.getxPos(), t.getyPos(), 50, 50);
											 });
		bank.getPanel().setBounds((int)Math.floor(breite*bank.getProzentFlaecheX()), 
								  (int)Math.floor(hoehe*bank.getProzentFlaecheY()), 280, 60);
			
	}
	
	public void rearrangiere(Component e, GUIProzente gui) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		gui.getPanel().setBounds((int)Math.floor(breite*gui.getProzentFlaecheX()), 
								(int)Math.floor(hoehe*gui.getProzentFlaecheY()), 
								(int)Math.floor(breite*gui.getProzentFlaecheBreite()),
								(int)Math.floor(hoehe*gui.getProzentFlaecheHoehe()));
		
	}
	
	public void aktualisieren() {
		
	}

}
