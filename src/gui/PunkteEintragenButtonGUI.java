package gui;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PunkteEintragenButtonGUI extends AbstractGUI{

	

	private static final double X = 0.12;
	private static final double Y = 0.678;	
	private static final double B = 0.25;	
	private static final double H = 0.07;	
	
	
	public PunkteEintragenButtonGUI(Container eimer){
		super(X, Y, B, H, eimer);
		knopf = new JButton();
		
		bildIcon = new ImageIcon("src/Other/punkteButton.jpg");
		
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				knopf.setSize(panel.getSize());
			}
		});
		setzeNeueGroesse((int)(B * eimer.getWidth()), (int)(H * eimer.getHeight()));
		
		knopf.setIcon(bild.getIcon());
		
		panel.add(knopf);
		
	}
	
	
	

}