package gui;

import javax.swing.JButton;


import java.util.Random;

import javax.swing.*;

public class WuerfelGUI {
	JButton wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5;
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
	
	private Object wuerfelAngeklickt(JButton wuerfel) {
		int zielX = (new Random().nextInt(350))+50;  //die werte später anpassen auf werte zum skalieren!!!!!!!!!! TODO TODO TODO 
		int zielY = (new Random().nextInt(200))+200; //lol die werte schon jetzt anpassen, die werden nämlich immer unter dem würfelpanel sein weil das als 0,0 zählt. irgendwie müssen die aus dem panel rausgeholt werden. flag probably boolean für ob unten oder oben problem für später
		
		Timer timer = new Timer(6, e -> {
			int xJetzt = wuerfel.getX();
			int yJetzt = wuerfel.getY();
			
			if (xJetzt > zielX) xJetzt--;
			if (xJetzt < zielX) xJetzt++;
			if (yJetzt > zielY) yJetzt--;		//lol
			if (yJetzt < zielY) yJetzt++;
			
			wuerfel.setLocation(xJetzt, yJetzt);
			
			if(xJetzt == zielX && yJetzt == zielY) {
				((Timer)e.getSource()).stop();
			}
			
		});
		timer.start();
		
		return null;
	}

	public void setBehalten() {
		
	}

	public JPanel getPanel() {
		return this.alleWuerfel;
	}
}
