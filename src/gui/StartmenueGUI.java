package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartmenueGUI extends AbstractGUI{
		
		private JButton starteSpiel;
		private JButton einstellungen;
		private JButton exit;
		
		private List<JButton> iconButtons = new ArrayList<>();
		
			
	
	
	public StartmenueGUI(Container Eimer) {
		super(1,1,1,1, Eimer);
		this.Eimer.setLayout(null); //Hab erstmal den Layout Manager ausgemacht um es manuell anzuordnen, können wir später aber noch ändern : famous last words, i am so sorry
		
		this.starteSpiel = new JButton("Starte Spiel");
		this.einstellungen = new JButton("Einstellungen");
		this.exit = new JButton("Schließen");
		
		erstelleButtons();	
		
		this.Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiereFelder(Eimer, starteSpiel, einstellungen, exit);
				Eimer.revalidate();
				Eimer.repaint();
			}			
		});
		
		this.Eimer.add(starteSpiel);
		this.Eimer.add(exit);
		this.Eimer.add(einstellungen); 
		Eimer.revalidate();
		Eimer.repaint();
		
		
		
	}
	private void rearrangiereFelder(Container eimer, JButton starteSpiel, JButton einstellungen, JButton exit) {
		int breite = eimer.getWidth();
		int hoehe = eimer.getHeight();
		
		starteSpiel.setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.14285), (int)Math.floor(breite*0.75),(int)Math.floor(hoehe*0.071));
		einstellungen.setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.28571), (int)Math.floor(breite*0.75),(int)Math.floor(hoehe*0.071));
		exit.setBounds((int)Math.floor(breite*0.125), (int)Math.floor(hoehe*0.42857), (int)Math.floor(breite*0.75),(int)Math.floor(hoehe*0.071));
		
		
		
	}
	
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
		
		this.iconButtons.add(bebi);
		this.iconButtons.add(kiki);
		this.iconButtons.add(bubu);
		this.iconButtons.add(crystal);
		this.iconButtons.add(schrinke);
		this.iconButtons.add(transistor);
		this.iconButtons.add(integral);
		
		iconButtons.forEach(button -> {button.setIcon(new ImageIcon("Other/" + button.getName() + "klein.jpg"));
										button.setPreferredSize(new Dimension(50,50));
										
										});
		
		
	}
	
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
	

	public Container getEimer() {
		return Eimer;
	}


	
	
}
