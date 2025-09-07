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
import presenter.SpielfeldPresenter;
import presenter.WuerfelPresenter;

import model.SpielLogik;


public class SpielfeldGUI extends AbstractGUI{
	
	/*	
	private SpielLogik spielLogik;
	
	
	private PunkteEintragenButtonGUI punkteEintragen;	
	private Kategorie ausgewaehlteKategorie = null; 
	*/
	
	public SpielfeldGUI(Container Eimer, SpielfeldPresenter presenter) {
		super(1,1,1,1);
		
		Eimer.removeAll();
		Eimer.setLayout(null);
		bildIcon = new ImageIcon("Other/hintergrund.jpg");		
		bild.setBounds(0,0, Eimer.getWidth(), Eimer.getHeight());
		bild.setIcon(bildIcon);
		panel.add(bild);
		setzeneueGroesse(Eimer.getHeight(),Eimer.getWidth());
		
	
		
		Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				presenter.rearrangiereAlles(Eimer);
				
			}
		});
		
		
		
	}
	
}
	
	
	
	
	/*
	

	public SpielfeldGUI (Container Eimer, StartmenueLogik menueDaten, SpielLogik spielLogik) {
		super(1, 1, 1, 1);
		this.spielLogik = spielLogik;
		Eimer.removeAll();
		Eimer.setLayout(null);
		
		
		

		wuerfel = new WuerfelPresenter(Eimer, wuerfelflaeche, wuerfelbankGUI, punktetabelle, spielLogik);	
		rearrangiereWuerfel(Eimer, wuerfel, wuerfelbankGUI);
		
		
				
	
		wuerfeln.getButton().addActionListener(klick -> {
        }});
		
			
								
		punkteEintragen.getButton().addActionListener(klick -> {eintragePunkteFuerAktuellenSpieler();});
		
		
		
		

		
		
		
		
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
		 
		
			
/*

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
    
	
	
	
	
	public void aktualisieren() {
		
	}
	
	private void updateStatusLabel() {
		String status = "Runde " + spielLogik.getRunde() + 
				"| Spieler: " + spielLogik.getAktiverSpieler().getName() + 
				"| Würfe: " + spielLogik.getWuerfeAnzahl() + "/3";
		statusLabel.setText(status);
	}
	
	
	
	

}

*/

