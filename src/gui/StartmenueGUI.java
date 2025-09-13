package gui;

import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StartmenueGUI extends AbstractGUI{
		
	private JButton starteSpiel;
	private JButton einstellungen;
	private JButton exit;
	
	
	private List<JButton> iconButtons = new ArrayList<>();
		
	// Konstruktor erstellt GUI, Layout und fuegt Buttons Hintergrund hinzu ohne Layout Manager
	public StartmenueGUI(Container eimer) {
		super(0,0,1,1, eimer);
		this.eimer.setLayout(null); 
		
		this.starteSpiel = new JButton();
		this.einstellungen = new JButton();
		this.exit = new JButton();
		
		starteSpiel.setIcon(new ImageIcon("src/Other/starteSpiel.jpg"));
		einstellungen.setIcon(new ImageIcon("src/Other/einstellungen.jpg"));
		exit.setIcon(new ImageIcon("src/Other/schliessen.jpg"));
		
		erstelleButtons();	
		
		// Listener groeßenanderungen anpassen 
		this.eimer.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiereFelder(eimer, starteSpiel, einstellungen, exit);
				setzeNeueGroesse(eimer.getWidth(), eimer.getHeight());
				eimer.revalidate();
				eimer.repaint();
			}			
		});
		
		bildIcon = new ImageIcon("src/Other/startHintergrund.jpg");	
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);
		panel.add(bild);
				
		this.eimer.add(starteSpiel);
		this.eimer.add(exit);
		this.eimer.add(einstellungen); 
		this.eimer.add(panel);
		eimer.revalidate();
		eimer.repaint();		
	}
	
	
	private void rearrangiereFelder(Container eimer, JButton starteSpiel, JButton einstellungen, JButton exit) {
		int breite = eimer.getWidth();
		int hoehe = eimer.getHeight();
		
		starteSpiel.setBounds(	(int)Math.floor(breite*0.33), (int)Math.floor(hoehe*0.33), (int)Math.floor(breite*0.33),(int)Math.floor(hoehe*0.1));
		einstellungen.setBounds((int)Math.floor(breite*0.33), (int)Math.floor(hoehe*0.44), (int)Math.floor(breite*0.33),(int)Math.floor(hoehe*0.1));
		exit.setBounds(			(int)Math.floor(breite*0.33), (int)Math.floor(hoehe*0.55), (int)Math.floor(breite*0.33),(int)Math.floor(hoehe*0.1));		
	}
	
	// Icon Bilder erstellen weil wir Buttons lieben  TODO umschreiben das es nur die bilder sind
	private void erstelleButtons(){
		JButton bebi = new JButton();
		bebi.setName("bebi");
		JButton kiki = new JButton();
		kiki.setName("kiki");
		JButton bubu = new JButton();
		bubu.setName("bubu");
		JButton crystal = new JButton();
		crystal.setName("crystal");
		JButton schrinke = new JButton();
		schrinke.setName("schrinke");
		JButton transistor = new JButton();
		transistor.setName("transistor");
		JButton integral = new JButton();
		integral.setName("integral");
		JButton fey = new JButton();
		fey.setName("fey");
		
		this.iconButtons.add(bebi);
		this.iconButtons.add(kiki);
		this.iconButtons.add(bubu);
		this.iconButtons.add(crystal);
		this.iconButtons.add(schrinke);
		this.iconButtons.add(transistor);
		this.iconButtons.add(integral);
		this.iconButtons.add(fey);
		
		iconButtons.forEach(button -> {
			button.setIcon(new ImageIcon("src/Other/" + button.getName() + "klein.jpg"));
			button.setPreferredSize(new Dimension(50,50));
			});		
	}
	
	// Getter und Setter
	public List<JButton> getIconButtons() {
		return iconButtons;
	}
	
	public void setIconButtons(List<JButton> iconButtons) {
		this.iconButtons = iconButtons;
	}
	
	public JButton getStarteSpiel() {
		return starteSpiel;
	}

	public void setStarteSpiel(JButton starteSpiel) {
		this.starteSpiel = starteSpiel;
	}

	public JButton getEinstellungen() {
		return einstellungen;
	}

	public void setEinstellungen(JButton einstellungen) {
		this.einstellungen = einstellungen;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public void moeglicheSpielModi() {
		
	}
	
	public void hauptmenue() {
		
	}
	
	public void einstellungen() {
		
	}
	
	public void auswahlSpiel() {
	
	}
	
	public void debugging() {
		
	}
	
	public void exit() {
		
	}	
	
}
