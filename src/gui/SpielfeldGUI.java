package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import control.WuerfelControl;

public class SpielfeldGUI {
	
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelControl wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private WuerfelBankGUI wuerfelbankGUI;
	private SpielbankGUI spielbank;
	private JButton wuerfeln;
	private JLabel hintergrund;

	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI (Container Eimer) {
		Eimer.removeAll();		
		
		Eimer.setLayout(null);
		
		
		wuerfelflaeche = new WuerfelflaecheGUI();
		wuerfel = new WuerfelControl(Eimer, wuerfelflaeche);
		punktetabelle = new PunkteTabelleGUI();
		spielbank = new SpielbankGUI();
		wuerfeln = new JButton ("Würfeln");
		wuerfelbankGUI = new WuerfelBankGUI();
		hintergrund = new JLabel();
		
		wuerfelflaeche.getPanel().setBounds(50, 200, 350, 200);
		punktetabelle.getPanel().setBounds(440, 50, 300, 550);
		spielbank.getPanel().setBounds(50, 500, 350, 100);
		wuerfelbankGUI.getPanel().setBounds(75,415,280,60);
		wuerfeln.setBounds(160, 477, 120, 20);
		wuerfeln.addActionListener(klick -> {wuerfel.wuerfeln();});
		ImageIcon bildi = new ImageIcon("Other/hintergrund.jpg" );
		hintergrund.setIcon(bildi);
		hintergrund.setBounds(0,0,800,700);
		hintergrund.setVisible(true);

		
		
		Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiere(Eimer, wuerfelflaeche);
				rearrangiereWuerfel(Eimer, wuerfel);
				rearrangiere(Eimer, punktetabelle);
				rearrangiere(Eimer, spielbank);
				rearrangiere(Eimer, wuerfelbankGUI);
			}

			
		});
		
			


		for (WuerfelGUI wuerfelGUI : wuerfel.getAlleWuerfelGUI()) {
	        Eimer.add(wuerfelGUI.getButton());
	    }
		
		Eimer.add(wuerfelflaeche.getPanel());
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
		Eimer.add(wuerfeln);
		Eimer.add(wuerfelbankGUI.getPanel());
		Eimer.add(hintergrund);
		
		Eimer.revalidate();
		Eimer.repaint();
		
		
		
	}
	
	/**
	 * junge ich hab das selber ohne KI geschrieben ich werde nie wieder so einen höhenflug erleben wie grade
	 * jedenfalls resized das alle würfel auf ihre prozentualen werte auf dem bildschirm wenn man das spielfeld größer oder kleinerzieht
	 * @param e
	 * @param w
	 */
	protected void rearrangiereWuerfel(Component e, WuerfelControl w) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		w.getAlleWuerfelGUI().forEach(t -> { t.setxPos( (int) Math.round(t.prozentXAufFeld * breite)  );
											 t.setyPos( (int) Math.round(t.prozentYAufFeld * hoehe));
											 t.setProzentXAufFeld(t.getxPos() *1.0 / breite);
											 t.setProzentYAufFeld(t.getyPos() *1.0 / hoehe);
											 t.getButton().setBounds(t.getxPos(), t.getyPos(), 50, 50);
											 });
			
	}
	
	public void rearrangiere(Component e, GUIProzente gui) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		gui.getPanel().setBounds((int)Math.floor(breite*gui.getProzentFlaecheX()), 
								(int)Math.floor(hoehe*gui.getProzentFlaecheY()), 
								(int)Math.floor(breite*gui.getProzentFlaecheBreite()),
								(int)Math.floor(hoehe*gui.getProzentFlaecheHoehe()));
		
	}
	
	public void aktualisieren() {
		
	}

}
