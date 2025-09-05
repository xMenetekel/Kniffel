package gui;


import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import control.WuerfelControl;
import model.Kategorie;
import model.StartmenueLogik;

public class SpielfeldGUI extends AbstractGUI{
	
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelControl wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private WuerfelBankGUI wuerfelbankGUI;
	private SpielbankGUI spielbank;
	private WuerfelnButtonGUI wuerfeln;
	private JLabel hintergrund;
	private PunkteEintragenButtonGUI punkteEintragen;	
	private Kategorie ausgewaehlteKategorie = null;
	private void aktualisierePunkteTabelle() {
		try {
			Map<Kategorie,Integer> moeglichePunkte = wuerfel.getMoeglicheZuege().berechneAlleMoeglichenPunkte();
			punktetabelle.zeigeMoeglichePunkte(moeglichePunkte);
			System.out.println("Wurf"+wuerfel.getWurfAnzahl()+"/"+wuerfel.getMaxWuerfe()+"Moegliche Punkte:"+moeglichePunkte);
		}catch(Exception e) {
			System.out.println("Fehler beim Aktualisieren der Punkte: " + e.getMessage());
		}
	}


	

	public SpielfeldGUI (Container Eimer, StartmenueLogik menueDaten) {
		super(1, 1, 1, 1);
		Eimer.removeAll();
		Eimer.setLayout(null);
		
		
		wuerfelflaeche = new WuerfelflaecheGUI();
		rearrangiere(Eimer, wuerfelflaeche);
		
		punktetabelle = new PunkteTabelleGUI(menueDaten);
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
        }});
		
			
								
		punkteEintragen.getButton().addActionListener(klick -> {eintragePunkteFuerAktuellenSpieler();});
		
		ImageIcon bildi = new ImageIcon("Other/hintergrund.jpg" );
		hintergrund.setIcon(bildi);
		hintergrund.setBounds(0,0,800,700);
		hintergrund.setVisible(true);
		

		
		
		Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				//rearrangiere(Eimer, wuerfelflaeche);
				rearrangiereWuerfel(Eimer, wuerfel, wuerfelbankGUI);
				rearrangiere(Eimer, punktetabelle);
				rearrangiere(Eimer, spielbank);
				wuerfelflaeche.setzeneueGroesse((int)(Eimer.getWidth() * wuerfelflaeche.getProzentFlaecheBreite()),(int)(Eimer.getHeight()*wuerfelflaeche.getProzentFlaecheHoehe()));
				
			}
		});
		
		javax.swing.JTable table = punktetabelle.getTable();
			if(table != null) {
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						int row = table.rowAtPoint(evt.getPoint());
						int col = table.columnAtPoint(evt.getPoint());
						
						if(col >= 2) {
							Kategorie kategorie = Kategorie.fromTabellenIndex(row);
							if(kategorie != null) {
								ausgewaehlteKategorie = kategorie;
								System.out.println("Kategorie ausgewählt: " + kategorie);
								table.setRowSelectionInterval(row,row);
								table.setColumnSelectionInterval(col,col);
							}
						}
					}
				});
			}
		
			
		
		
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
		Eimer.add(punkteEintragen.getPanel());
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
		Eimer.add(wuerfeln.getPanel());
		Eimer.add(wuerfelbankGUI.getPanel());
		Eimer.add(hintergrund);
		
		Eimer.revalidate();
		Eimer.repaint();
		
		
	}
	private void eintragePunkteFuerAktuellenSpieler() {
		if(ausgewaehlteKategorie != null) {
			try {
				int punkte = wuerfel.getMoeglicheZuege().berechnePunkte(ausgewaehlteKategorie);
				punktetabelle.tragePunkteEin(ausgewaehlteKategorie, punkte, 0);
				System.out.println("Punkte eingetragen: " + ausgewaehlteKategorie);
				wuerfel.getMoeglicheZuege().entferneKategorie(ausgewaehlteKategorie);
				ausgewaehlteKategorie = null;
			} catch (Exception e) {
				System.out.println ("Fehler beim Eintragen der Punkte" + e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.out.println("Keine Kategorie ausgewählt! Bitte zuerst eine Kategorie in der Tabelle anclicken.");
		}
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
