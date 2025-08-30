package gui;

import javax.swing.JButton;
import model.*;

import java.awt.Container;

import javax.swing.*;

public class WuerfelGUI {
	JButton wuerfel;
	WuerfelLogik model;
	int xPos;
	int yPos;
	int startX;
	int startY;
	double prozentXAufFeld;
	double prozentYAufFeld;
	Container Eimer;
	

	public WuerfelGUI(WuerfelLogik model, int startX, int startY, Container Eimer){
		this.model = model;
		this.wuerfel = new JButton();
		this.startX = startX;
		this.startY = startY;
		wuerfel.addActionListener(klick -> { boolean neuerZustand = !model.istBehalten();										
											model.setBehalten(neuerZustand);
											
											model.wuerfelAngeklickt(this, neuerZustand);
											} );
		this.Eimer = Eimer;
		this.xPos = startX;
		this.yPos = startY;
		fresherButton();
		this.prozentXAufFeld = startX * 1.0 / Eimer.getWidth();
		this.prozentYAufFeld = startY * 1.0 / Eimer.getHeight();
		
		
	}
	public void fresherButton() {
		ImageIcon bildi = new ImageIcon("Other/" + model.getAugen() + ":50.png" );
		wuerfel.setIcon(bildi);
		wuerfel.setVisible(true);
		wuerfel.setBounds(this.xPos, this.yPos, 50, 50);
	
	}
	
	
	public JButton getButton() {
		return this.wuerfel;
	}
	
	public WuerfelLogik getLogik() {
		return this.model;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public double getProzentXAufFeld() {
		return prozentXAufFeld;
	}
	
	public void setProzentXAufFeld(double prozentXAufFeld) {
		this.prozentXAufFeld = prozentXAufFeld;
	}
	
	public double getProzentYAufFeld() {
		return prozentYAufFeld;
	}
	
	public void setProzentYAufFeld(double prozentYAufFeld) {
		this.prozentYAufFeld = prozentYAufFeld;
	}
	
	public Container getEimer() {
		return Eimer;
	}
	
	public void setEimer(Container eimer) {
		Eimer = eimer;
	}
}







	/*JButton wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5;
	JPanel alleWuerfel;
	int wuerfellIndex = 0;
	
	
	public WuerfelGUI() { //DAS MUSS ALLES WOANDERS HIN AHAHAHA ICH HAB KEINE AHNUNG WO 
		wuerfel1 = new JButton("1");
		wuerfel2 = new JButton("2");
		wuerfel3 = new JButton("3");
		wuerfel4 = new JButton("4");
		wuerfel5 = new JButton("5");
		
		wuerfel1.setBounds(0,0, 50, 50);
		ImageIcon eins = new ImageIcon("Other/eins.jpg");
		wuerfel1.setIcon(eins);		
		wuerfel1.setVisible(true);
		wuerfel1.addActionListener(klick -> wuerfelAngeklickt(wuerfel1));
		
		wuerfel2.setBounds(55, 0, 50, 50);		
		ImageIcon zwei = new ImageIcon("Other/zwei.jpg");
		wuerfel2.setIcon(zwei);		
		wuerfel2.setVisible(true);
		wuerfel2.addActionListener(klick -> wuerfelAngeklickt(wuerfel2));
		
		wuerfel3.setBounds(110, 0, 50, 50);
		ImageIcon drei = new ImageIcon("Other/drei.jpg");
		wuerfel3.setIcon(drei);
		wuerfel3.setVisible(true);
		wuerfel3.addActionListener(klick -> wuerfelAngeklickt(wuerfel3));
		
		wuerfel4.setBounds(165, 0, 50, 50);
		ImageIcon vier = new ImageIcon("Other/vier.jpg");
		wuerfel4.setIcon(vier);
		wuerfel4.setVisible(true);
		wuerfel4.addActionListener(klick -> wuerfelAngeklickt(wuerfel4));
		
		wuerfel5.setBounds(220, 0, 50, 50);
		ImageIcon fuenf = new ImageIcon("Other/fuenf.jpg");
		wuerfel5.setIcon(fuenf);
		wuerfel5.setVisible(true);
		wuerfel5.addActionListener(klick -> wuerfelAngeklickt(wuerfel5));
		
		alleWuerfel = new JPanel();
		
		alleWuerfel.add(wuerfel1);
		alleWuerfel.add(wuerfel2);
		alleWuerfel.add(wuerfel3);
		alleWuerfel.add(wuerfel4);
		alleWuerfel.add(wuerfel5);
		
		alleWuerfel.setVisible(true);
		alleWuerfel.setLayout(null);
		alleWuerfel.setBounds(0, 0, 270, 50);

		
	}

	public JPanel getPanel() {
		return this.alleWuerfel;
	}
}
*/
