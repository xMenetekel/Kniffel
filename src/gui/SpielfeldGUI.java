package gui;


import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

import model.Kategorie;
import model.StartmenueLogik;

import presenter.WuerfelPresenter;

import model.SpielLogik;


public class SpielfeldGUI extends AbstractGUI{
	
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelPresenter wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private WuerfelBankGUI wuerfelbankGUI;
	private SpielerbankGUI spielbank;
	private WuerfelnButtonGUI wuerfeln;
	private SpielLogik spielLogik;
	private JLabel statusLabel;
	
	private PunkteEintragenButtonGUI punkteEintragen;	
	private Kategorie ausgewaehlteKategorie = null;
	
	

	public SpielfeldGUI (Container Eimer, StartmenueLogik menueDaten, SpielLogik spielLogik) {
		super(1, 1, 1, 1);
		this.spielLogik = spielLogik;
		Eimer.removeAll();
		Eimer.setLayout(null);
		
		statusLabel = new JLabel();
		statusLabel.setBounds((int)(Eimer.getWidth() * 0.1), (int)(Eimer.getHeight() * 0.05), (int)(Eimer.getWidth() * 0.3),30);
		statusLabel.setFont(new Font("Arial",Font.BOLD,16));
		updateStatusLabel();
		
		if (spielLogik != null) {
			updateStatusLabel();
		}else {
			statusLabel.setText("Spiel wird initialisiert...");
		}
		
		Eimer.add(statusLabel);
		
		wuerfelflaeche = new WuerfelflaecheGUI();
		rearrangiere(Eimer, wuerfelflaeche);
		
		punktetabelle = new PunkteTabelleGUI(menueDaten,spielLogik);
		rearrangiere(Eimer, punktetabelle);
		
		spielbank = new SpielerbankGUI();
		rearrangiere(Eimer, spielbank);
		
		wuerfelbankGUI = new WuerfelBankGUI();

		wuerfel = new WuerfelPresenter(Eimer, wuerfelflaeche, wuerfelbankGUI, punktetabelle, spielLogik);	
		rearrangiereWuerfel(Eimer, wuerfel, wuerfelbankGUI);
		
		wuerfeln = new WuerfelnButtonGUI();
		rearrangiere(Eimer, wuerfeln);
		
		punkteEintragen = new PunkteEintragenButtonGUI();
		rearrangiere(Eimer, punkteEintragen);
		
				
	
		wuerfeln.getButton().addActionListener(klick -> {wuerfel.wuerfeln(); 
														if (wuerfel.getWurfAnzahl() == wuerfel.getMaxWuerfe()) {
												            aktualisierePunkteTabelle();
        }});
		
			
								
		punkteEintragen.getButton().addActionListener(klick -> {eintragePunkteFuerAktuellenSpieler();});
		
		bildIcon = new ImageIcon("Other/hintergrund.jpg" );
		bild.setIcon(bildIcon);
		setzeneueGroesse(Eimer.getHeight(),Eimer.getWidth());
		
		

		
		
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
		
		JTable table = punktetabelle.getTable();
			if(table != null) {
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
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
		Eimer.add(bild);
		
		Eimer.revalidate();
		Eimer.repaint();
		
		
	}

	
	private void aktualisierePunkteTabelle() {
		try {
			Map<Kategorie,Integer> moeglichePunkte = wuerfel.getMoeglicheZuege().berechneAlleMoeglichenPunkte();
			punktetabelle.zeigeMoeglichePunkte(moeglichePunkte);
			System.out.println("Wurf"+wuerfel.getWurfAnzahl()+"/"+wuerfel.getMaxWuerfe()+"Moegliche Punkte:"+moeglichePunkte);
		}catch(Exception e) {
			System.out.println("Fehler beim Aktualisieren der Punkte: " + e.getMessage());
		}
	}
    
	
	
	/**
	 * junge ich hab das selber ohne KI geschrieben ich werde nie wieder so einen höhenflug erleben wie grade
	 * jedenfalls resized das alle würfel auf ihre prozentualen werte auf dem bildschirm wenn man das spielfeld größer oder kleinerzieht
	 * @param e
	 * @param w
	 */
	protected void rearrangiereWuerfel(Component e, WuerfelPresenter w, WuerfelBankGUI bank) {
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
	
	public void rearrangiere(Component e, AbstractGUI gui) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		gui.getPanel().setBounds((int)Math.floor(breite*gui.getProzentFlaecheX()), 
								(int)Math.floor(hoehe*gui.getProzentFlaecheY()), 
								(int)Math.floor(breite*gui.getProzentFlaecheBreite()),
								(int)Math.floor(hoehe*gui.getProzentFlaecheHoehe()));
		
	}
	
	public void aktualisieren() {
		
	}
	
	private void updateStatusLabel() {
		String status = "Runde " + spielLogik.getRunde() + 
				"| Spieler: " + spielLogik.getAktiverSpieler().getName() + 
				"| Würfe: " + spielLogik.getWuerfeAnzahl() + "/3";
		statusLabel.setText(status);
	}
	
	private void eintragePunkteFuerAktuellenSpieler() {
		if(ausgewaehlteKategorie != null) {
			try {
				int punkte = wuerfel.getMoeglicheZuege().berechnePunkte(ausgewaehlteKategorie);
				int aktiverSpielerIndex = spielLogik.getAktiverSpielerIndex();
				punktetabelle.tragePunkteEin(ausgewaehlteKategorie, punkte, aktiverSpielerIndex);
				wuerfel.getMoeglicheZuege().entferneKategorie(ausgewaehlteKategorie);
				punktetabelle.zeigeMoeglichePunkte(new HashMap<>());
				ausgewaehlteKategorie = null;
				JTable table = punktetabelle.getTable();
				if(table != null) {
					table.clearSelection();
				}
				spielLogik.naechsterSpieler();
				punktetabelle.zeigeMoeglichePunkte(new HashMap<>());
				if(punktetabelle.getTable() != null) {
					punktetabelle.getTable().repaint();
				}
				wuerfel.neueRunde();
				updateStatusLabel();
				System.out.println("Spieler " + (spielLogik.getAktiverSpielerIndex() + 1)+ "ist jetzt an der Reihe. Runde: " + spielLogik.getRunde());
				
			}catch(Exception e) {
				System.out.println("Fehler beim Eintragen der Punkte" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Keine Kategorie ausgewählt!");
		}
	}
	
	

}
