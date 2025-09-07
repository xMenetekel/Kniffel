package gui;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PunkteEintragenButtonGUI extends AbstractGUI{

	private JButton punkteEintragen;

	private static final double X = 0.12;
	private static final double Y = 0.678;	
	private static final double B = 0.25;	
	private static final double H = 0.07;	
	
	
	public PunkteEintragenButtonGUI(Container Eimer){
		super(X, Y, B, H, Eimer);
		this.punkteEintragen = new JButton();
		
		bildIcon = new ImageIcon("Other/punkteButton.jpg");
		
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				punkteEintragen.setSize(panel.getSize());
			}
		});
		setzeneueGroesse((int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight()));
		
		punkteEintragen.setIcon(bild.getIcon());
		
		panel.add(punkteEintragen);
		
	}
	
	public JButton getButton() {
		return punkteEintragen;
	}
	public void setButton(JButton punkteButton) {
		this.punkteEintragen = punkteButton;
	}
	

}