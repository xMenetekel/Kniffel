package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;



public class WuerfelnButtonGUI extends AbstractGUI{
	
	private JButton wuerfeln;

	private static final double X = 0.156;
	private static final double Y = 0.55;	
	private static final double B = 0.15;	
	private static final double H = 0.030165913;	
	
	public WuerfelnButtonGUI(){
		super(X, Y, B, H);
		this.wuerfeln = new JButton("Wuerfeln");
		
		
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				wuerfeln.setSize(panel.getSize());
			}
		});
		
		panel.add(wuerfeln);
		
	}
	

	public JButton getButton() {
		return wuerfeln;
	}
	public void setButton(JButton wuerfeln) {
		this.wuerfeln = wuerfeln;
	}
	

}