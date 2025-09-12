package gui;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;



public class WuerfelnButtonGUI extends AbstractGUI{
	
	private static final double X = 0.12;
	private static final double Y = 0.51;	
	private static final double B = 0.25;	
	private static final double H = 0.07;	
	
	public WuerfelnButtonGUI(Container Eimer){
		super(X, Y, B, H, Eimer);
		knopf = new JButton();
		
		bildIcon = new ImageIcon("src/Other/wuerfelnButton.jpg");		
		
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				knopf.setSize(panel.getSize());
			}
		});
		
		setzeNeueGroesse(
			(int)(B * Eimer.getWidth()), 
			(int)(H * Eimer.getHeight())
		);

		knopf.setIcon(bild.getIcon());
		knopf.setVisible(true);
		panel.add(knopf);
		
	}
}