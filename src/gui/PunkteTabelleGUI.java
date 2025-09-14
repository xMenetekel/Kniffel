package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import model.Kategorie;
import model.StartmenueLogik;
import presenter.PunktetabellePresenter;

public class PunkteTabelleGUI extends AbstractGUI {
	 
	private JTable table;
	private static final double X = 0.5;
	private static final double Y = 0.0;	
	private static final double B = 0.5;	
	private static final double H = 1.0;
	private AbstractTableModel tableModel;
	private final int spielerAnzahl;
	private String[] spielerNamen;
	private Object[][] currentTableData;
	private String[] columnNames;
	private DefaultTableCellRenderer symbolRenderer;
	private DefaultTableCellRenderer textRenderer;
	private DefaultTableCellRenderer punkteRenderer;
	private PunktetabellePresenter presenter;

	public PunkteTabelleGUI(Container eimer, StartmenueLogik menueDaten) {
		super(X, Y, B, H, eimer);
		this.spielerAnzahl = menueDaten.getSpieleranzahl();
		
		spielerNamen = new String[spielerAnzahl];
		for(int i = 0; i < spielerAnzahl; i++) {
			spielerNamen[i] = menueDaten.getSpieler(i).getName();
		}
		
		initializeComponents();
		setupEventHandlers();
		
		this.eimer.add(panel);
	}
	
	public void setPresenter(PunktetabellePresenter presenter) {
		this.presenter = presenter;
	}
	
	private void initializeComponents() {
		panel = new JPanel(new BorderLayout ());
		tableModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;	 
			@Override
			public int getRowCount() {
				return currentTableData != null ? currentTableData.length : 19;
			} 
			
			@Override
			public int getColumnCount() {
				return currentTableData != null ? currentTableData[0].length : 2 + spielerAnzahl;
			}
			
			@Override
			public Object getValueAt(int row, int column) {
				if(currentTableData == null) {
					if (column == 0) return getDefaultKategorie(row);
					if (column == 1) return getDefaultBeschreibung(row);
					return null;
				}
				return currentTableData[row][column];
			}
			
			@Override
			public String getColumnName(int column) {
				if(columnNames != null && column < columnNames.length) {
				return columnNames[column];
				}
				if (column == 0) return "Kategorie";
				if (column == 1) return "Kombinationen";
				if (column >= 2 && column < 2 + spielerAnzahl) {
					return spielerNamen[column -2];
				}
				return "";
			}
			
			@Override 
			public Class <?> getColumnClass(int column){
				if (column == 0) return Object.class;
				if (column >= 2) return Integer.class;
				return String.class;
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
		
		table = new JTable(tableModel);
		setupTable();
		
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll, BorderLayout.CENTER);
	}
	
	private void setupTable() {
		macheTabelleNichtEditierbar();
		table.setRowHeight(40);
		Color mischmasch = new Color (150, 190,140);
		table.setBackground(mischmasch);
		
		symbolRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(mischmasch);
				setHorizontalAlignment(CENTER);
				setVerticalAlignment(CENTER);
				
				Kategorie kategorie = Kategorie.fromTabellenIndex(row);
				if(kategorie != null && kategorie.hatBild()) {
					setIcon(new ImageIcon(kategorie.getBildDatei()));
					setText("");
				}else {
					setIcon(null);
					setText(value != null ? value.toString() : "");
				}
				return c;
			}
		};
		
		textRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(mischmasch);
				setHorizontalAlignment(LEFT);
				return c;
			}
		};
		
		punkteRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				Kategorie kategorie = Kategorie.fromTabellenIndex(row);
				int spielerIndex = column -2;
				c.setBackground(mischmasch);
				
				if (value != null) {
					if(presenter != null && presenter.getAusgewaehlteKategorie() == kategorie && spielerIndex == presenter.getAktiverSpielerIndex()) {
						c.setBackground(new Color (255,255,150));
					}else if (presenter != null && presenter.istPunktEndgueltig(kategorie, spielerIndex)) {
						c.setBackground(new Color (200,255,200));
					}else {
						c.setBackground(new Color (255,200,200));
					} 
				}else if (presenter != null && presenter.getAusgewaehlteKategorie() == kategorie && spielerIndex == presenter.getAktiverSpielerIndex() && presenter.istNullwurfMoeglich()) {
					c.setBackground(new Color(255,255,150));
				}
				setHorizontalAlignment(CENTER);
				return c;
				}
		};
		table.getColumnModel().getColumn(0).setCellRenderer(symbolRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(textRenderer);
		
		for (int i = 2; i<table.getColumnCount();i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(punkteRenderer);
		}
	}
	
	
		
	private void setupEventHandlers() {
		table.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			e.consume();
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_F2||
				e.getKeyCode()==KeyEvent.VK_ENTER||
				e.getKeyCode() == KeyEvent.VK_TAB||
				e.getKeyCode() == KeyEvent.VK_SPACE) {
				e.consume();
			}
		}	
	});
		
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent evt) {
				handleMouseClick(evt);
			}
		});
	}
	
	private void handleMouseClick(MouseEvent evt) {
		int row = table.rowAtPoint(evt.getPoint());
		int col = table.columnAtPoint(evt.getPoint());
		
		if(col>= 2 && (row < 6 || (row > 8 && row < 16) || row > 18)) {
			Kategorie kategorie =Kategorie.fromTabellenIndex(row);
			
			if (kategorie != null && presenter != null) {
				presenter.onKategorieSelected(kategorie);
				System.out.println("Kategorie in GUI ausgewählt"+ kategorie);
				table.repaint();
			}
		}else if (col >= 2) {
			System.out.println("Berechnungsfeld - kann nicht ausgegeben werden");
		}
	}
	
	public void macheTabelleNichtEditierbar() {
		if(table != null) {
			table.setEnabled(true);
			table.setFocusable(true);
			
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowSelectionAllowed(true);
			table.setColumnSelectionAllowed(false);
			table.setCellSelectionEnabled(true);
			
			table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0), "none");
			table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "none");
		}
	}
	
	public void updateTableData(Object[][] data, String[] columnNames) {
		this.currentTableData= data;
		this.columnNames =columnNames;
		tableModel.fireTableDataChanged();
		table.repaint();
	}
	
	public JTable getTable() {
		return this.table;
	}
	
	private Object getDefaultKategorie(int row) {
		Kategorie[] kategorien = {
				Kategorie.EINSER,
				Kategorie.ZWEIER,
				Kategorie.DREIER,
				Kategorie.VIERER,
				Kategorie.FUENFER,
				Kategorie.SECHSER,
				Kategorie.GESAMT_OBEN,
				Kategorie.BONUS,
				Kategorie.GESAMT_OBEN_BONUS,
				Kategorie.DREIER_PASCH,
				Kategorie.VIERER_PASCH,
				Kategorie.KLEINE_STRASSE,
				Kategorie.GROSSE_STRASSE,
				Kategorie.KNIFFEL,
				Kategorie.CHANCE,
				Kategorie.GESAMT_OBEN_ZWEI,
				Kategorie.ENDSUMME
		};
		return row < kategorien.length ? kategorien[row] : null;
	}
	
	private String getDefaultBeschreibung(int row) {
		String[] beschreibungen = {
				"Nur Einser zaehlen",
				"Nur Zweier zaehlen",
				"Nur Dreier zaehlen",
				"Nur Vierer zaehlen",
				"Nur Fuenfer zaehlen",
				"Nur Sechser zaehlen",
				"--->", 
				"plus 35",
				"--->",
				"Alle Augen zaehlen",
				"Alle Augen zaehlen",
				"25 Punkte",
				"30 Punkte",
				"40 Punkte",
				"50 Punkte",
				"--->",
				"--->",
				"--->",
		};
		return row<beschreibungen.length ? beschreibungen[row]: "";
	}
	
}


