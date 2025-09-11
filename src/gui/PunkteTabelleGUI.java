package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import model.Kategorie;
import model.StartmenueLogik;
import model.SpielLogik;

public class PunkteTabelleGUI extends AbstractGUI {
	 
	private JTable table;
	private static final double X = 0.5;
	private static final double Y = 0.0;	
	private static final double B = 0.5;	
	private static final double H = 1.0;
	private ZugTableModel tableModel;
	private final int spielerAnzahl;
	private SpielLogik spielLogik;
	private KategorieSelectListener selectListener;
	private Point selectedCell = null;
	
	private Map<String, Integer> finalePunkte = new HashMap<>();
	
	public JTable getTable() {
		return this.table;
	}
	

	public PunkteTabelleGUI(Container Eimer, StartmenueLogik menueDaten,SpielLogik spielLogik) {
		super(X, Y, B, H, Eimer);
		
		
		

		this.spielerAnzahl = menueDaten.getSpieleranzahl();
		panel = new JPanel(new BorderLayout());
		tableModel = new ZugTableModel(menueDaten);
		table = new JTable(tableModel);
		
		macheTabelleNichtEditierbar();
		table.setRowHeight(40);

		Color mischmasch = new Color(150,190,140); // evtl muss raus
		table.setBackground(mischmasch);

		
		table.setDefaultRenderer(Object.class, new HighlightingCellRenderer());
		
		table.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F2||
					e.getKeyCode() == KeyEvent.VK_ENTER||
					e.getKeyCode() == KeyEvent.VK_TAB||
					e.getKeyCode() == KeyEvent.VK_SPACE) {
					e.consume();
				}
					
			}
		});
		
       /* table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                
                if(k)

                if (col >= 2) { 
                    Kategorie kategorie = Kategorie.fromTabellenIndex(row);
                    
                    if (kategorie != null && tableModel.hatMoeglichePunkteFuerKategorie(kategorie)) {
                    	HighlightingCellRenderer renderer = (HighlightingCellRenderer)table.getDefaultRenderer(Object.class);
                    	setSelectedCell(row,col);
         
                    	selectedCell = new Point (row, col);
	            		selectListener.onKategorieSelected(kategorie);
	                	System.out.println("Kategorie ausgewählt: "+ kategorie);
	                	
	                	table.repaint();
	                	
            		}
            	}
            }
            
        });*/
		
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
               

                if (col >= 2) { 
                    Kategorie kategorie = Kategorie.fromTabellenIndex(row);
                    
                    if(kategorie != null) {
                    	setSelectedCell(row,col);
                    	boolean hatMoeglichePunkte = tableModel.hatMoeglichePunkteFuerKategorie(kategorie);
                    	boolean feldIstLeer = (tableModel.getValueAt(row, col)==null);
                    	boolean dreiMalGewuerfelt = (spielLogik.getWuerfeAnzahl() >= 3);
                    	
                    	if (hatMoeglichePunkte || (feldIstLeer&&dreiMalGewuerfelt)) {
                    		selectListener.onKategorieSelected(kategorie);
                    		System.out.println("Kategorie ausgewaehlt: "+ kategorie);
                    	}else if (feldIstLeer&& !dreiMalGewuerfelt) {
                    		System.out.println("Null kann erst nach 3 Würfen eingetragen werden!");
                    	}
                    }
            	}
            }
            
        });
        
		table.getColumnModel().getColumn(0).setCellRenderer(new SymbolCellRenderer());	//Brauchen CellRenderer um in der ersten spalte sowohl Bilder als auch Text verwenden zu können
		
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll, BorderLayout.CENTER);
		
		this.Eimer.add(panel);
		
		
				
		}

	
	public void setSelectedCell(int row, int col) {
		this.selectedCell = new Point(row,col);
		HighlightingCellRenderer renderer = (HighlightingCellRenderer)table.getDefaultRenderer(Object.class);
		if(renderer != null) {
			renderer.setSelectedCell(row, col);
		}
		table.repaint();
	}

	
	public void macheTabelleNichtEditierbar() {	//UMSCHREIBEN ITS COPIED FROM CHATGPT	
	    if(table != null) {
	        // Tabelle komplett aktiv für Selektion
	        table.setEnabled(true);
	        table.setFocusable(true);
	        
	        // Nur Einzelzellen-Selektion erlauben
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setRowSelectionAllowed(false);
	        table.setColumnSelectionAllowed(false);
	        table.setCellSelectionEnabled(true);
	        
	        // Alle Editoren entfernen
	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellEditor(null);
	        }
	        
	        // Alle Editor-Klassen auf null setzen
	        table.setDefaultEditor(Object.class, null);
	        table.setDefaultEditor(String.class, null);
	        table.setDefaultEditor(Integer.class, null);
	        table.setDefaultEditor(Double.class, null);
	        table.setDefaultEditor(Boolean.class, null);
	        
	        // Tastatureingaben blockieren
	        table.getInputMap().clear();
	        table.getActionMap().clear();
	    }
	}
	

	
	public void zeigeMoeglichePunkte(Map<Kategorie, Integer> moeglichePunkte, int aktuellerSpielerIndex) {
	    if (tableModel != null) {
	        tableModel.setMoeglichePunkte(moeglichePunkte, aktuellerSpielerIndex); 
	        macheTabelleNichtEditierbar();
	    }
	}

    
    public void tragePunkteEin(Kategorie kategorie, int punkte, int spielerIndex) {
    	System.out.println("Trage "+ punkte + "Punkte für" + kategorie + "bei Spieler"+ spielerIndex + "Ein");
    	try {
    		int row= kategorie.getTabellenIndex();
    		int column = 2+spielerIndex;
    		tableModel.setValueAt(punkte, kategorie.getTabellenIndex(), 2 + spielerIndex);
    		macheTabelleNichtEditierbar();
            tableModel.entferneMoeglicheKategorie(kategorie);
            
            if(kategorie.ordinal() <= Kategorie.SECHSER.ordinal()) {
            	tableModel.berechneUndSetzeGesamtOben(spielerIndex);
            }
            
            if(kategorie.ordinal() >= Kategorie.DREIER_PASCH.ordinal() && kategorie.ordinal() <= Kategorie.CHANCE.ordinal()) {
            	tableModel.berechneUndSetzeGesamtUnten(spielerIndex);
            }
            
            table.repaint();
            System.out.println("Punkte erfolgreich eingetragen");
    	}catch(Exception e) {
    		System.out.println("Fehler in tragePunkteEin"+ e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public void clearMoeglichePunkte() {
    	if(tableModel != null) {
    		tableModel.resetMoeglichePunkte();
    		macheTabelleNichtEditierbar();
    		table.repaint();
    	}
    }
    
    public void highlightSpalte(int spielerIndex) {
    	if(tableModel != null) {
    		tableModel.setHighlightedSpalte(2+spielerIndex);
    	}
    	if (table != null) {
    		HighlightingCellRenderer renderer = (HighlightingCellRenderer)table.getDefaultRenderer(Object.class);
    		if (renderer != null) {
    			renderer.setHighlightedSpalte(2+spielerIndex);
    		}
    		macheTabelleNichtEditierbar();
    		table.repaint();
    	}
    	System.out.println("Tabelle für Spieler" + (spielerIndex +1)+"aktualisiert.");
    }
	
	static class HighlightingCellRenderer extends DefaultTableCellRenderer {
		private int highlightedSpalte = -1;
		private int selectedRow = -1;
		private int selectedCol = -1;
		private int wuerfeAnzahl = 0;
		private Color DUNKELGRÜN = new Color (100,180,100);
		private Color AUSWAHL = new Color (150,200,150);
		
		
		public void setHighlightedSpalte (int spalte) {
			this.highlightedSpalte = spalte;
		}
		
		public void setSelectedCell(int row, int col) {
			this.selectedRow = row;
			this.selectedCol = col;
			System.out.println("Renderer: selectedRow ="+ row + " , selectedCol =" + col);
		}
		
		public void setWuerfeAnzahl (int anzahl) {
			this.wuerfeAnzahl = anzahl;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			ZugTableModel model = (ZugTableModel) table.getModel();
			Kategorie kategorie = Kategorie.fromTabellenIndex(row);
			boolean hatMoeglichePunkte = false;
			boolean feldIstLeer = (value == null);
			boolean dreiMalGewuerfelt = (wuerfeAnzahl >= 3);
			Container container = SwingUtilities.getAncestorOfClass(PunkteTabelleGUI.class, table);
			
			if(kategorie != null) {
				hatMoeglichePunkte = model.hatMoeglichePunkteFuerKategorie(kategorie);
			}
			
			if(row == selectedRow &&  column == selectedCol) {
				c.setBackground(AUSWAHL);
				c.setForeground(Color.BLACK);
				setToolTipText("Ausgewählt - Punkte eintragen Bestätigen");
			}
			
			
			if (column >= 2 && value !=null) {
				c.setBackground(DUNKELGRÜN);
				c.setForeground(Color.BLACK);
				setToolTipText("Fester Wert - Kann nicht geändert werden");
			} else if(column == highlightedSpalte && value != null) {
				c.setBackground(new Color(220,240,255));
				setToolTipText("MöglichePunkte - doppelklick zum Auswählen");
			}else {
				c.setBackground(table.getBackground());
				c.setForeground(table.getForeground());
				setToolTipText(null);
			}
	
			return c;
		}
	}
	
	
	static class SymbolCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, 
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        Kategorie kategorie = Kategorie.fromTabellenIndex(row);
	        
	        if (kategorie != null) {
	        	if(kategorie.hatBild()) {
	        		setIcon(new ImageIcon(kategorie.getBildDatei()));
	        		setText("");
	 
	        	} else {
	        		setIcon(null);
	        		if(value != null) {
	        			setText(value.toString());
	        		}else {
	        			setText("");
	        		}
	        }
	        }else {
	        	setIcon(null);
	        	if(value != null) {
	        		setText(value.toString());
	        	}else {
	        		setText("");
	        	}
	        }
	        
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
	        
	        return c;
	    }
	}
	
	
	public void setMoeglichkeiten() {
		
	}
	
	public interface KategorieSelectListener{
		void onKategorieSelected(Kategorie kategorie);
	}
	
	public void setKategorieSelectListener(KategorieSelectListener listener) {
		this.selectListener = listener;
	}
	
	static class ZugTableModel extends AbstractTableModel{
		private String[] columnNames;
		private Object[][] data;
		private final int spielerAnzahl; //muss später übergeben werden!!!!Wichtig!!!ToDo
		private Map<Kategorie, Integer> moeglichePunkte = new HashMap<>();
		private int highlightedSpalte = -1;
		
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
		
        public void setMoeglichePunkte(Map<Kategorie, Integer> moeglichePunkte, int aktuellerSpielerIndex) {
        	Map<Kategorie,Integer>gefiltertePunkte = new HashMap<>();
        	for(Map.Entry<Kategorie, Integer> entry : moeglichePunkte.entrySet()) {
        		Kategorie kategorie = entry.getKey();
        		int row = kategorie.getTabellenIndex();
        		int spielerSpalte = 2 + aktuellerSpielerIndex;

        		
        		boolean aktuellerSpielerHatWert = (data [row][spielerSpalte] != null);
        		
        		if(!aktuellerSpielerHatWert) {
        			gefiltertePunkte.put(kategorie,entry.getValue());
        		}
        	}
        	
            this.moeglichePunkte = gefiltertePunkte;
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
        
        public boolean hatSpielerBereitsWert (int row, int spielerIndex) {
        	int spielerSpalte= 2+ spielerIndex;
        	return (data[row][spielerSpalte] != null);
        }
        

        public boolean istFeldLeer(int row, int column) {
        	if(column <2)return false;
        	return (data[row][column]==null);
        }
        
        public boolean kannKategorieEingetragenWerden(Kategorie kategorie, int spielerIndex) {
        	int row = kategorie.getTabellenIndex();
        	int column = 2 + spielerIndex;
        	return (data[row][column]== null);
        }
        
        public void berechneUndSetzeGesamtOben(int spielerIndex) {
        	int spielerSpalte = 2 + spielerIndex;
        	int gesamtOben = 0;
        	for(int row = 0; row <= 5; row ++) {
        		if(data[row][spielerSpalte] instanceof Integer) {
        			gesamtOben += (Integer)data[row][spielerSpalte];
        		}
        	}
        	data[6][spielerSpalte] = gesamtOben;
        	fireTableCellUpdated(6,spielerSpalte);
        	
        	if(istObereHaelfteVoll(spielerIndex)) {
        	int bonus = (gesamtOben >= 63) ? 35 : 0;
        	data[7][spielerSpalte] = bonus;
        	fireTableCellUpdated(6,spielerSpalte);
        	
        	data[8][spielerSpalte] = gesamtOben + bonus;
        	fireTableCellUpdated(8,spielerSpalte);
        	
        	data[17][spielerSpalte] = gesamtOben + bonus;
        	fireTableCellUpdated(17,spielerSpalte);
        	
        	System.out.println("Gesamt oben für Spieler " + bonus + ", Gesamt mit Bonus: "+ (gesamtOben + bonus));
        	}else {
        		
        		data[7][spielerSpalte] = null;
        		data[8][spielerSpalte] = null;
        		data[17][spielerSpalte] = null;
        		fireTableCellUpdated(7,spielerSpalte);
        		fireTableCellUpdated(8,spielerSpalte);

        	}
        }
        
        public void berechneUndSetzeGesamtUnten(int spielerIndex) {
        	int spielerSpalte = 2+spielerIndex;
        	int gesamtUnten = 0;
        	for (int row = 9;row<=15; row++) {
        		if(data[row][spielerSpalte] instanceof Integer) {
        			gesamtUnten += (Integer)data[row][spielerSpalte]; 
        		}
        	}
        	data[16][spielerSpalte] = gesamtUnten;
        	fireTableCellUpdated(16,spielerSpalte);
        	
        	if(istUntereHaelfteVoll(spielerIndex)) {
            	int gesamtObenMitBonus = (data[8][spielerSpalte] instanceof Integer)? (Integer)data[8][spielerSpalte]:0;
            	data[18][spielerSpalte] = gesamtObenMitBonus + gesamtUnten;
            	fireTableCellUpdated(18,spielerSpalte);        	
            	System.out.println("Endsumme berechnet: "+ (gesamtObenMitBonus + gesamtUnten));
   
        	}else {
        		data[18][spielerSpalte] = null;
        		fireTableCellUpdated(18,spielerSpalte);
        	}
        	System.out.println("Gesamt unten für Spieler "+spielerIndex + ": "+ gesamtUnten);
        	
        }
        
        public boolean istObereHaelfteVoll(int spielerIndex) {
        	int spielerSpalte = 2 + spielerIndex;
        	for (int row = 0; row <= 5; row++) {
        		if(data[row][spielerSpalte] == null) {
        			return false;
        		}
        	}
        	return true;
        }
        
        public boolean istUntereHaelfteVoll(int spielerIndex) {
        	int spielerSpalte = 2 + spielerIndex;
        	for(int row = 9; row <= 15; row ++) {
        		if(data[row][spielerSpalte]== null) {
        			return false;
        		}
        	}
        	return true;
        }
		
		
		@Override
		public int getRowCount() {
			return data.length;
		}
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		
		public void setHighlightedSpalte(int spalte) {
			this.highlightedSpalte = spalte	;
			fireTableDataChanged();
		}
		
		public void resetMoeglichePunkte() {
			moeglichePunkte.clear();
			fireTableDataChanged();
			System.out.println("MoeglichePunkte zurückgeetzt");
		}
		
        @Override
        public Object getValueAt(int row, int column) {
        	
        	Object result = null;
        	
        	if(column == 0||column ==1) {
        			result = data[row][column];
        			return result;
        		}
        	if(column >= 2) {
        		if(data[row][column] != null) {
        			result = data[row][column];
        		}else if (column == highlightedSpalte && !moeglichePunkte.isEmpty()) {

                Kategorie kategorie = Kategorie.fromTabellenIndex(row);
                if (kategorie != null && moeglichePunkte.containsKey(kategorie)) {
                    result = moeglichePunkte.get(kategorie);
                }
            }
        	}	
            return result;
        }
		
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		@Override
		public Class<?> getColumnClass(int column){
			if(column == 0) return Object.class; // Bilder( und Text
			if(column >= 2) return Integer.class; // Spieler
			return String.class; // Kombinationen spalte 1	
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
		@Override 
		public void setValueAt(Object value, int row, int column) {
			data[row][column] = value;
			fireTableCellUpdated(row, column);
			
			if(column >=2) {
				int spielerIndex = column -2;
				if (row <=5) {
					if(istObereHaelfteVoll(spielerIndex)) {
						berechneUndSetzeGesamtOben(spielerIndex);
					}
				}
			
			if (row >= 9 && row <= 15) {
				if(istUntereHaelfteVoll(spielerIndex)) {
				berechneUndSetzeGesamtUnten(spielerIndex);
				}
			}
		}
	}
		
		
		
		
		
		

	}
    public void aktualisiereFuerNeuenSpieler(int aktuellerSpielerIndex) {
    	zeigeMoeglichePunkte(new HashMap<>(), aktuellerSpielerIndex);
    	if (table != null) {
    		table.repaint();
    	}
    }
    public void berechneAlleGesamtPunkte() {
    	if(tableModel != null) {
    		for (int spielerIndex = 0; spielerIndex < spielerAnzahl; spielerIndex++) {
    			if(tableModel.istObereHaelfteVoll(spielerIndex)){
    				tableModel.berechneUndSetzeGesamtOben(spielerIndex);
    			}
    		}
    		table.repaint();
    	}
    }

}
