package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WuerfelGUI {
	JButton wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5;
	JPanel alleWuerfel;
	int wuerfellIndex = 0;
	
	public WuerfelGUI() {
		wuerfel1 = new JButton("1");
		wuerfel2 = new JButton("2");
		wuerfel3 = new JButton("3");
		wuerfel4 = new JButton("4");
		wuerfel5 = new JButton("5");
		
		wuerfel1.setBounds(0,0, 50, 50);
		wuerfel1.setVisible(true);
		
		wuerfel2.setBounds(55, 0, 50, 50);
		wuerfel2.setVisible(true);
		
		wuerfel3.setBounds(110, 0, 50, 50);
		wuerfel3.setVisible(true);
		
		wuerfel4.setBounds(165, 0, 50, 50);
		wuerfel4.setVisible(true);
		
		wuerfel5.setBounds(220, 0, 50, 50);
		wuerfel5.setVisible(true);
		
		alleWuerfel = new JPanel();
		
		alleWuerfel.add(wuerfel1);
		alleWuerfel.add(wuerfel2);
		alleWuerfel.add(wuerfel3);
		alleWuerfel.add(wuerfel4);
		alleWuerfel.add(wuerfel5);
		
		alleWuerfel.setVisible(true);
		alleWuerfel.setLayout(null);
		alleWuerfel.setBounds(0, 0, 120, 40);

		
	}
	
	public void setBehalten() {
		
	}

	public JPanel getPanel() {
		return this.alleWuerfel;
	}
}
