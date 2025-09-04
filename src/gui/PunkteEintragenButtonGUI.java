package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PunkteEintragenButtonGUI implements GUIProzente{
	private JPanel sammelpanel;
	private JButton punkteEintragen;


	private final double prozentFlaecheX = 0.3625;
	private final double prozentFlaecheY = 0.716440422;	
	private final double prozentFlaecheBreite = 0.1875;	
	private final double prozentFlaecheHoehe = 0.030165913;
	
	
	public PunkteEintragenButtonGUI(){
		this.punkteEintragen = new JButton("Punkte eintragen");
		this.sammelpanel = new JPanel();
		sammelpanel.setLayout(null);
		sammelpanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				punkteEintragen.setSize(sammelpanel.getSize());
			}
		});
		sammelpanel.add(punkteEintragen);
		
	}
	public double getProzentFlaecheX() {
		return this.prozentFlaecheX;
	}
	
	public double getProzentFlaecheHoehe() {
		return this.prozentFlaecheHoehe;
	}
	
	public double getProzentFlaecheY() {
		return this.prozentFlaecheY;
	}
	
	public double getProzentFlaecheBreite() {
		return this.prozentFlaecheBreite;
	}
	
	public JPanel getPanel() {
		return this.sammelpanel;
	}
	public JButton getButton() {
		return punkteEintragen;
	}
	public void setButton(JButton punkteButton) {
		this.punkteEintragen = punkteButton;
	}
	

}