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
	
	private SpielfeldPresenter presenter;
	private WuerfelnButtonGUI wuerfelnButton;
	private PunkteEintragenButtonGUI punkteEintragenButton;
	private PunkteTabelleGUI punkteTabelle;
	private StartmenueLogik menueDaten;
		
	private PunkteEintragenButtonGUI punkteEintragen;	
	private Kategorie ausgewaehlteKategorie = null; 
		
	public SpielfeldGUI(Container Eimer, SpielfeldPresenter presenter, StartmenueLogik menueDaten) {
		super(0,0,1,1, Eimer);
		this.presenter = presenter;
		this.menueDaten = menueDaten;
		
		
		Eimer.removeAll();
		Eimer.setLayout(null);
		bildIcon = new ImageIcon("src/Other/hintergrund.jpg");		
		bild.setBounds(0,0, Eimer.getWidth(), Eimer.getHeight());
		bild.setIcon(bildIcon);
		panel.add(bild);
		setzeNeueGroesse(Eimer.getHeight(),Eimer.getWidth());
		
		Eimer.revalidate();
		Eimer.repaint();
		
		// Listener damit es funktioniert 
		Eimer.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				presenter.rearrangiereAlles(Eimer);				
			}
		});
	}
	
	public WuerfelnButtonGUI getWuerfelnButton() {
		return wuerfelnButton;
	}
	
	public PunkteEintragenButtonGUI getPunkteEintragenButton() {
		return punkteEintragenButton;
	}
	
	public PunkteTabelleGUI getPunkteTabelle() {
		return punkteTabelle;
	}
	
}
