package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PunkteEintragenButtonGUI extends AbstractGUI{

	private JButton punkteEintragen;

	private static final double X = 0.145;
	private static final double Y = 0.678;	
	private static final double B = 0.1875;	
	private static final double H = 0.030165913;
	
	
	public PunkteEintragenButtonGUI(){
		super(X, Y, B, H);
		this.punkteEintragen = new JButton("Punkte eintragen");
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				punkteEintragen.setSize(panel.getSize());
			}
		});
		panel.add(punkteEintragen);
		
	}
	
	public JButton getButton() {
		return punkteEintragen;
	}
	public void setButton(JButton punkteButton) {
		this.punkteEintragen = punkteButton;
	}
	

}