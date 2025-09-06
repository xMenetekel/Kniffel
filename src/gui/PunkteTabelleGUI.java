package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import model.Kategorie;
import model.StartmenueLogik;

public class PunkteTabelleGUI extends AbstractGUI {
	 
	private JTable table;
	private static final double X = 0.5;
	private static final double Y = 0.0;	
	private static final double B = 0.5;	
	private static final double H = 1.0;
	private ZugTableModel tableModel;
	private final int spielerAnzahl;
	

	public JTable getTable() {
		return this.table;
	}
	
	public PunkteTabelleGUI(StartmenueLogik menueDaten) {
		super(X, Y, B, H);
		this.spielerAnzahl = menueDaten.getSpieleranzahl();
		panel = new JPanel(new BorderLayout());
		
		tableModel = new ZugTableModel(menueDaten);
		table = new JTable(tableModel);
		table.setRowHeight(40); // habs von 35 auf 40 angepasst idk if this is too high tho?
		Color mischmasch = new Color(200,224,170);
		table.setBackground(mischmasch);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                
                if (col >= 2) { // Nur Punkte-Spalten
                    Kategorie kategorie = Kategorie.fromTabellenIndex(row);
                    if (kategorie != null && tableModel.hatMoeglichePunkteFuerKategorie(kategorie)) {
                        System.out.println("Spieler hat Kategorie gewählt: " + kategorie);
                        int punkte = tableModel.getMoeglichePunkteFuerKategorie(kategorie);
                        System.out.println("Punkte: " + punkte);
                        
                        // Hier kannst du die Logik für das Behalten der Punkte implementieren
                        // z.B.: tragePunkteEin(kategorie, punkte, 0); // Für Spieler 1
                    }
                }
            }
        });
		
		table.getColumnModel().getColumn(0).setCellRenderer(new SymbolCellRenderer());	//Brauchen CellRenderer um in der ersten spalte sowohl Bilder als auch Text verwenden zu können
		
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll, BorderLayout.CENTER);
				
		}
	
	public void zeigeMoeglichePunkte(Map<Kategorie, Integer> moeglichePunkte) {
	    if (tableModel != null) {
	        tableModel.setMoeglichePunkte(moeglichePunkte); 
	    }
	}

    
    public void tragePunkteEin(Kategorie kategorie, int punkte, int spielerIndex) {
        tableModel.setValueAt(punkte, kategorie.getTabellenIndex(), 2 + spielerIndex);
        tableModel.entferneMoeglicheKategorie(kategorie);
    }
	
	
	
	static class SymbolCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, 
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        Kategorie kategorie = Kategorie.fromTabellenIndex(row);
	        
	        if (kategorie != null) {
	            if (kategorie.hatBild()) {
	                setIcon(new ImageIcon(kategorie.getBildDatei()));
	                setText(null);
	            } else {
	                setIcon(null);
	                setText(kategorie.getAnzeigeName());
	            }
	        } else {
	            setIcon(null);
	            setText("");
	        }
	        
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
	        
	        return this;
	    }
	}
	
	
	public void setMoeglichkeiten() {
		
	}
	
	static class ZugTableModel extends AbstractTableModel{
		private String[] columnNames;
		private Object[][] data;
		private final int spielerAnzahl; //muss später übergeben werden!!!!Wichtig!!!ToDo
		private Map<Kategorie, Integer> moeglichePunkte = new HashMap<>();
		
		public ZugTableModel(StartmenueLogik menueDaten) {
			this.spielerAnzahl = menueDaten.getSpieleranzahl();
			columnNames = new String[2 + spielerAnzahl];
			columnNames[0] = "Kategorie";
			columnNames[1] = "Kombinationen";
			
			for(int i = 0; i < spielerAnzahl; i++) {
				String spielerName = menueDaten.getSpieler(i).getName();
				columnNames[2 + i] = spielerName;
			}	
        
		
			data = new Object[19][columnNames.length];
			data[0][0] = Kategorie.EINSER;
			data[0][1] = "nur Einser zaehlen";
			data[1][0] = Kategorie.ZWEIER;
			data[1][1] = "nur Zweier zaehlen";
			data[2][0] = Kategorie.DREIER;
			data[2][1] = "nur Dreier zeahlen";
			data[3][0] = Kategorie.VIERER;
			data[3][1] = "nur Vierer zeahlen";
			data[4][0] = Kategorie.FUENFER;
			data[4][1] = "nur Fuenfer zeahlen";
			data[5][0] = Kategorie.SECHSER;
			data[5][1] = "nur Sechser zeahlen";
			data[6][0] = Kategorie.GESAMT_OBEN;
			data[6][1] = "--->";
			data[7][0] = Kategorie.BONUS;
			data[7][1] = "plus 35";
			data[8][0] = Kategorie.GESAMT_OBEN_BONUS;
			data[8][1] = "--->";
			data[9][0] = Kategorie.DREIER_PASCH;
			data[9][1] = "alle Augen zaehlen";
			data[10][0] = Kategorie.VIERER_PASCH;
			data[10][1] = "alle Augen zaehlen";
			data[11][0] = Kategorie.FULL_HOUSE;
			data[11][1] = "25 Punkte";
			data[12][0] = Kategorie.KLEINE_STRASSE;
			data[12][1] = "30 Punkte";
			data[13][0] = Kategorie.GROSSE_STRASSE;
			data[13][1] = "40 Punkte";
			data[14][0] = Kategorie.KNIFFEL;
			data[14][1] = "50 Punkte";
			data[15][0] = Kategorie.CHANCE;
			data[15][1] = "alle Augen zaehlen";
			data[16][0] = Kategorie.GESAMT_UNTEN;
			data[16][1] = "--->";
			data[17][0] = Kategorie.GESAMT_OBEN_ZWEI;
			data[17][1] = "--->";
			data[18][0] = Kategorie.ENDSUMME;
			data[18][1] = "--->";
			
		}
		
        public void setMoeglichePunkte(Map<Kategorie, Integer> moeglichePunkte) {
            this.moeglichePunkte = moeglichePunkte;
            fireTableDataChanged(); 
        }

        public boolean hatMoeglichePunkteFuerKategorie(Kategorie kategorie) {
            return moeglichePunkte.containsKey(kategorie);
        }
        
        public int getMoeglichePunkteFuerKategorie(Kategorie kategorie) {
            return moeglichePunkte.getOrDefault(kategorie, 0);
        }
        
        public void entferneMoeglicheKategorie(Kategorie kategorie) {
        	moeglichePunkte.remove(kategorie);
        	fireTableDataChanged();
        }
        
        public void resetMoeglichePunkte() {
        	moeglichePunkte.clear();
        	fireTableDataChanged();
        }
		
		
		@Override
		public int getRowCount() {
			return data.length;
		}
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		
		
		
        @Override
        public Object getValueAt(int row, int column) {
            // Zeige mögliche Punkte in grau oder speziell formatieren
            if (column >= 2 && !moeglichePunkte.isEmpty()) {
                Kategorie kategorie = Kategorie.fromTabellenIndex(row);
                if (kategorie != null && moeglichePunkte.containsKey(kategorie)) {
                    return moeglichePunkte.get(kategorie);
                }
            }
            return data[row][column];
        }
		
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		@Override
		// Datentype der Spalten 
		public Class<?> getColumnClass(int column){
			if(column == 0) return Object.class; // Bilder( und Text
			if(column >= 2) return Integer.class; // Spieler
			return String.class; // Kombinationen spalte 1	
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return column >=2; //nur Punkte editierbar
		}
		
		@Override 
		public void setValueAt(Object value, int row, int column) {
			data[row][column] = value;
			fireTableCellUpdated(row, column);
		}
		
		
		
		
		
		

	}
	

}
