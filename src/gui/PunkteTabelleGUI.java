package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class PunkteTabelleGUI {
	private JPanel tabelle;
	private JTable table;
	
	public PunkteTabelleGUI() {
		tabelle = new JPanel(new BorderLayout());
		
		table = new JTable(new ZugTableModel());
		//Zeilenhoehe Größer noch anpassen!!!!!!!!!!Wichtig!!! ToDo!!!!
		table.setRowHeight(35);
		
		JScrollPane scroll = new JScrollPane(table);
		tabelle.add(scroll, BorderLayout.CENTER);
				
		//tabelle.setBounds(0, 0, 100, 600);
		//tabelle.setVisible(true);
		//tabelle.setLayout(null);
		}
	
	public JPanel getPanel() {
		return this.tabelle;
	}
	
	
	public void setMoeglichkeiten() {
		
	}
	
	//Test TableModel 
	static class ZugTableModel extends AbstractTableModel{
		//Spaltenüberschriften
		private String[] columnNames;
		private Object[][] data;
		private final int spielerAnzahl = 2; //muss später übergeben werden!!!!Wichtig!!!ToDo
		
		public ZugTableModel() {
			//Spaltennamen dynamisch
			columnNames = new String[2 + spielerAnzahl];
			columnNames[0] = "Symbol";
			columnNames[1] = "Kombinationen";
			for(int i = 0; i < spielerAnzahl; i++) {
				columnNames[2 + i] = "Spieler " + (i + 1);
			}
		
			//19 Zeilen
			data = new Object[19][columnNames.length];
			data[0][0] = new ImageIcon("Other/eins.jpg");
			data[0][1] = "nur Einsen zaehlen";
			data[1][0] = new ImageIcon("Other/zwei.jpg");
			data[1][1] = "nur Zweier zaehlen";
			data[2][0] = new ImageIcon("Other/drei.jpg");
			data[2][1] = "nur Dreier zeahlen";
			data[3][0] = new ImageIcon("Other/vier.jpg");
			data[3][1] = "nur Vierer zeahlen";
			data[4][0] = new ImageIcon("Other/fuenf.jpg");
			data[4][1] = "nur Fuenfen zeahlen";
			data[5][0] = new ImageIcon("Other/sechs.jpg");
			data[5][1] = "nur Sechsen zeahlen";
			data[6][0] = "gesamt";
			data[6][1] = "--->";
			data[7][0] = "Bonus bei 63 oder mehr";
			data[7][1] = "plus 35";
			data[8][0] = "gesamt oberer Teil";
			data[8][1] = "--->";
			data[9][0] = "Dreierpasch";
			data[9][1] = "alle Augen zaehlen";
			data[10][0] = "Viererpasch";
			data[10][1] = "alle Augen zaehlen";
			data[11][0] = "Full House";
			data[11][1] = "25 Punkte";
			data[12][0] = "Kleine Straße";
			data[12][1] = "30 Punkte";
			data[13][0] = "Große Straße";
			data[13][1] = "40 Punkte";
			data[14][0] = "Kniffel";
			data[14][1] = "50 Punkte";
			data[15][0] = "Chance";
			data[15][1] = "alle Augen zaehlen";
			data[16][0] = "gesamt unterer Teil";
			data[16][1] = "--->";
			data[17][0] = "gesamt oberer Teil";
			data[17][1] = "--->";
			data[18][0] = "Endsumme";
			data[18][1] = "--->";
			// 0+18 = 19 ...
			// keine bilder mehr ich mag nicht mehr...
			 
		}
		
		
		
		@Override
		public int getRowCount() {return data.length;}
		
		@Override
		public int getColumnCount() {return columnNames.length;}
		
		@Override
		public Object getValueAt(int row, int column) {
			return data[row][column];
		}
		
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		@Override
		// Datentype der Spalten 
		public Class<?> getColumnClass(int column){
			if(column == 0) return Object.class; // Bilder und Text
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
