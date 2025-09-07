package gui;

import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;



public class WuerfelnButtonGUI extends AbstractGUI{
	
	private JButton wuerfeln;

	private static final double X = 0.12;
	private static final double Y = 0.51;	
	private static final double B = 0.25;	
	private static final double H = 0.07;	
	
	public WuerfelnButtonGUI(Container Eimer){
		super(X, Y, B, H, Eimer);
		this.wuerfeln = new JButton();
		
		bildIcon = new ImageIcon("Other/wuerfelnButton.jpg");
		
		
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				wuerfeln.setSize(panel.getSize());
			}
		});
		setzeneueGroesse((int)(B * Eimer.getWidth()), (int)(H * Eimer.getHeight()));
		wuerfeln.setIcon(bild.getIcon());
		wuerfeln.setVisible(true);
		panel.add(wuerfeln);
		
	}
	

	public JButton getButton() {
		return wuerfeln;
	}
	public void setButton(JButton wuerfeln) {
		this.wuerfeln = wuerfeln;
	}
	

}