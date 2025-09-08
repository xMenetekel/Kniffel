package main;

import java.awt.Container;

import javax.swing.JFrame;

import presenter.StartmenuePresenter;

public class Main {
	public static void main(String[] args) {	

		JFrame hauptfenster = new JFrame("Kniffel");
		hauptfenster.setSize(1000,825);
		hauptfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container Eimer = hauptfenster.getContentPane();
	
		new StartmenuePresenter(hauptfenster, Eimer);
		
		hauptfenster.setVisible(true); //aus irgendeinem grund muss das hier sein damit das fenster sich nicht random entscheidet einfach nicht zu erscheinen.
		Eimer.revalidate();				//nach allem anderen. java warum
		Eimer.repaint();
		
	}
}
		
		
	


