package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;



public class WuerfelnButtonGUI implements GUIProzente{
	private JPanel sammelpanel;
	private JButton wuerfeln;


	private final double prozentFlaecheX = 0.2;
	private final double prozentFlaecheY = 0.719457014;	
	private final double prozentFlaecheBreite = 0.15;	
	private final double prozentFlaecheHoehe = 0.030165913;
	
	
	public WuerfelnButtonGUI(){
		this.wuerfeln = new JButton("Wuerfeln");
		this.sammelpanel = new JPanel();
		sammelpanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				wuerfeln.setSize(sammelpanel.getSize());
			}
		});
		sammelpanel.setLayout(null);
		sammelpanel.add(wuerfeln);
		
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
		return wuerfeln;
	}
	public void setButton(JButton wuerfeln) {
		this.wuerfeln = wuerfeln;
	}
	

}