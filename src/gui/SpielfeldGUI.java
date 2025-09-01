package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import control.WuerfelControl;

public class SpielfeldGUI {
	
	private WuerfelflaecheGUI wuerfelflaeche;
	private WuerfelControl wuerfel;
	private PunkteTabelleGUI punktetabelle;
	private SpielbankGUI spielbank;
	private JButton wuerfeln;

	// Konstruktor
	// Fläche und sichbarkeit müssen noch gemacht werden
	// Fläche mit ...setSize() und sichtbarkeit mit ...setVisible(true)
	public SpielfeldGUI (Container Eimer) {
		Eimer.removeAll();		
		
		Eimer.setLayout(null);
		
		wuerfel = new WuerfelControl(Eimer);
		wuerfelflaeche = new WuerfelflaecheGUI();
		punktetabelle = new PunkteTabelleGUI();
		spielbank = new SpielbankGUI();
		wuerfeln = new JButton ("Würfeln");
		
		wuerfelflaeche.getPanel().setBounds(50, 200, 350, 200);
		punktetabelle.getPanel().setBounds(440, 50, 300, 550);
		spielbank.getPanel().setBounds(50, 500, 350, 100);
		wuerfeln.setBounds(160, 475, 120, 20);
		wuerfeln.addActionListener(klick -> {wuerfel.wuerfeln();});
		

		
		
		Eimer.addComponentListener(new ComponentAdapter() { //der ganze spaß hier ist dafür da das beim neu ziehen alles prozentual angepasst wird
			@Override
			public void componentResized(ComponentEvent e) {
				rearrangiere(Eimer, wuerfelflaeche);
				rearrangiereWuerfel(Eimer, wuerfel);
				rearrangiere(Eimer, punktetabelle);
				rearrangiere(Eimer, spielbank);
			}

			
		});
		
		wuerfelflaeche.getPanel().setBackground(Color.RED);		
		punktetabelle.getPanel().setBackground(Color.CYAN);
		spielbank.getPanel().setBackground(Color.GREEN);		


		for (WuerfelGUI wuerfelGUI : wuerfel.getAlleWuerfelGUI()) {
	        Eimer.add(wuerfelGUI.getButton());
	    }
		
		Eimer.add(wuerfelflaeche.getPanel());
		Eimer.add(punktetabelle.getPanel());
		Eimer.add(spielbank.getPanel());
		Eimer.add(wuerfeln);
		
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
	
	public void rearrangiere(Component e, AbstractGUI wf) {
		int breite = e.getWidth();
		int hoehe = e.getHeight();
		
		wf.getPanel().setBounds((int)Math.floor(breite*wf.getProzentFlaecheX()), 
								(int)Math.floor(hoehe*wf.getProzentFlaecheY()), 
								(int)Math.floor(breite*wf.getProzentFlaecheBreite()),
								(int)Math.floor(hoehe*wf.getProzentFlaecheHoehe()));
		
	}
	
	public void aktualisieren() {
		
	}

}
