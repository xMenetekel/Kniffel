package gui;

import java.awt.Container;
import java.awt.Image;
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
	
	private List<ImageIcon> icons = new ArrayList<>();
		
	// Konstruktor erstellt GUI, Layout und fuegt Buttons Hintergrund hinzu ohne Layout Manager
	/**
	 * 
	 * @param eimer
	 */
	public StartmenueGUI(Container eimer) {
		super(0,0,1,1, eimer);
		this.eimer.setLayout(null); 
		
		this.starteSpiel = new JButton();
		this.einstellungen = new JButton();
		this.exit = new JButton();
		
		starteSpiel.setIcon(new ImageIcon("src/other/starteSpiel.jpg"));
		einstellungen.setIcon(new ImageIcon("src/other/einstellungen.jpg"));
		exit.setIcon(new ImageIcon("src/other/schliessen.jpg"));
		
		erstelleBilder();	
		
		// Listener groeßenanderungen anpassen 
		this.eimer.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizing();
			}			
		});
		
		bildIcon = new ImageIcon("src/other/startHintergrund.jpg");	
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);
		
		resizing();
		
		panel.add(starteSpiel);
		panel.add(einstellungen);
		panel.add(exit);
		panel.add(bild);		
		
		eimer.add(panel);
		eimer.revalidate();
		eimer.repaint();	
		
	
	}
	
	/**
	 * Hilfsfunktion zum resizen der Komponenten auf dem Feld
	 */
	private void resizing() {
		rearrangiereFelder();
		setzeNeueGroesse(eimer.getWidth(), eimer.getHeight());
		setzeNeueButtonBildGroesse(starteSpiel, einstellungen, exit);
		eimer.revalidate();
		eimer.repaint();
	}
	
	
	/**
	 * Skaliert die Groesse der Bilder der Buttons. Da es voreingestellte Icons in Images umwandelt, sie dann skaliert und wieder zu Icons verwandelt
	 * entsteht hier der wunderschöne Effekt das der Button blurry wird wenn man das Menü zu oft groß- und kleinzieht. Ich finde das so lustig, das ich es
	 * drinnenlasse.
	 * @param buttons die Buttons dessen Bilder geklaut und geändert werden sollen
	 */
	public void setzeNeueButtonBildGroesse(JButton... buttons) {
		for(JButton button : buttons) {		
			ImageIcon bildneu = (ImageIcon) button.getIcon();
			Image programmierenIstWeird = bildneu.getImage();
			Image skaliertesBild = programmierenIstWeird.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(skaliertesBild));
		}
	}
	
	/**
	 * rearrangiert die Positionierung der Buttons anhand der übergeordneten Containergroesse
	 */
	private void rearrangiereFelder() {
		int breite = eimer.getWidth();
		int hoehe = eimer.getHeight();
		
		starteSpiel.setBounds(	(int)Math.floor(breite*0.33), (int)Math.floor(hoehe*0.33), (int)Math.floor(breite*0.33),(int)Math.floor(hoehe*0.1));
		einstellungen.setBounds((int)Math.floor(breite*0.33), (int)Math.floor(hoehe*0.44), (int)Math.floor(breite*0.33),(int)Math.floor(hoehe*0.1));
		exit.setBounds(			(int)Math.floor(breite*0.33), (int)Math.floor(hoehe*0.55), (int)Math.floor(breite*0.33),(int)Math.floor(hoehe*0.1));		
	}
	
	/**
	 * erstellt die ImageIcons für das Icon Auswahlfenster im Startmenue bei Spiel starten. 
	 * Wenn man ein neues Bild einfügen soll, dann einfach den namen hier im Array hinten dran setzen. Im src/Other/ ordner muss es dann NAMEklein.jpg heißen
	 */
	private void erstelleBilder(){
		String[] namen = new String[] {"bebi", "kiki", "bubu", "crystal", "schrinke", "transistor", "integral", "fey"};
		for(String name : namen) {
			icons.add(new ImageIcon("src/other/" + name + "klein.jpg"));
		}
	
	}
	
	//Getter und Setter
	
	public JButton getStarteSpiel() {
		return starteSpiel;
	}

	public List<ImageIcon> getIcons() {
		return icons;
	}

	public void setIcons(List<ImageIcon> icons) {
		this.icons = icons;
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
	
}
