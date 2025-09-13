package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import presenter.StartmenuePresenter;

public class Main {
	public static void main(String[] args) {	

		JFrame hauptfenster = new JFrame("Kniffel");
		hauptfenster.setSize(1000,825);
		hauptfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hauptfenster.setMinimumSize(new Dimension(500, 600)); //set maximum size geht nicht :(
		hauptfenster.setMaximizedBounds(new Rectangle(0,0,1100,1000));
		
		Container eimer = hauptfenster.getContentPane();
	
		new StartmenuePresenter(hauptfenster, eimer);
		
		hauptfenster.setVisible(true); //aus irgendeinem grund muss das hier sein damit das fenster sich nicht random entscheidet einfach nicht zu erscheinen.
		eimer.revalidate();				//nach allem anderen. java warum
		eimer.repaint();
		
	}
}
		
		
	


