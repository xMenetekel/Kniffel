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
import model.Kategorie;

public class SpielfeldGUI {
	
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelControl wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private WuerfelBankGUI wuerfelbankGUI;
	private SpielbankGUI spielbank;
	private JButton wuerfeln;
	private JLabel hintergrund;
	private JButton punkteEintragen;
	private Kategorie ausgewaehlteKategorie = null;

	
	public SpielfeldGUI (Container Eimer) {
		Eimer.removeAll();
		Eimer.setLayout(null);
		
		punktetabelle = new PunkteTabelleGUI();
		wuerfelflaeche = new WuerfelflaecheGUI();
		punktetabelle = new PunkteTabelleGUI();
		wuerfelbankGUI = new WuerfelBankGUI();
		//wuerfel = new WuerfelControl(Eimer, wuerfelflaeche, wuerfelbankGUI, punktetabelle);	
		spielbank = new SpielbankGUI();
		wuerfeln = new JButton ("Würfeln");
		punkteEintragen = new JButton("Punkte eintragen");
		hintergrund = new JLabel();
		
		wuerfel = new WuerfelControl(Eimer,wuerfelflaeche, wuerfelbankGUI, punktetabelle);
		
		wuerfelflaeche.getPanel().setBounds(50, 200, 350, 200);
		punktetabelle.getPanel().setBounds(440, 50, 300, 550);
		spielbank.getPanel().setBounds(50, 500, 350, 100);
		wuerfelbankGUI.getPanel().setBounds(75,415,280,60);
		wuerfeln.setBounds(160, 477, 120, 20);
		punkteEintragen.setBounds(290, 475, 150, 20);
		wuerfeln.addActionListener(klick -> {wuerfel.wuerfeln(); 
											if (wuerfel.getWurfAnzahl() == wuerfel.getMaxWuerfe()) {
									            aktualisierePunkteTabelle();
									        }
									    });	
		
									    
		punkteEintragen.addActionListener(klick -> {
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
		
	
			
		
		
		/*	
		table.addMouseListener(new java.awt.event.MouseEvent evt){
			int row = table.rowAtPoint(evt.getPoint());
			int col = table.columnAtPoint(evt.getPoint());
			
			if(col >=2) {
				Kategorie kategorie = Kategorie.fromTabellenIndex(row);
				if (kategorie != null && tableModel.hatMoeglichePunkteFuerKateforie(kategorie)) {
					ausgewaehlteKategorie = kategorie;
					System.out.println("Kategorie ausgewählt:" + kategorie);
					table.setRowSelectionInterval(row,row);
					table.setColumnSelectionInterval(col,col)
				}
			}
				
		});
		*/
		 
		
			


		for (WuerfelGUI wuerfelGUI : wuerfel.getAlleWuerfelGUI()) {
	        Eimer.add(wuerfelGUI.getButton());
	    }
		
		Eimer.add(wuerfelflaeche.getPanel());
		Eimer.add(punkteEintragen);
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
		Eimer.add(wuerfeln);
		Eimer.add(wuerfelbankGUI.getPanel());
		Eimer.add(hintergrund);
		
		Eimer.revalidate();
		Eimer.repaint();
		
		
	}
	
	
	private void aktualisierePunkteTabelle() {
	    if (wuerfel.getWurfAnzahl() == wuerfel.getMaxWuerfe()) {
	        System.out.println("Aktualisiere Tabelle mit möglichen Punkten");
	        
	    }
	}
    
	private void eintragePunkteFuerAktuellenSpieler() {
			if (ausgewaehlteKategorie != null) {
				int punkte = wuerfel.getMoeglicheZuege().berechnePunkte(ausgewaehlteKategorie);

	    punktetabelle.tragePunkteEin(ausgewaehlteKategorie, punkte, 0);

	    wuerfel.neueRunde();
	    ausgewaehlteKategorie = null;
			}else {
				System.out.println("Keine Kategorie ausgewählt!");
			}
	    
	    System.out.println("Punkte eingetragen: " + ausgewaehlteKategorie + " = ???" );
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
