package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import presenter.StartmenuePresenter;

/**
 * Initialisiert Hauptfenster und startet 
 * den Startmenue Presenter
 */
public class Main {
	public static void main(String[] args) {	

		JFrame hauptfenster = new JFrame("Kniffel");
		hauptfenster.setSize(1000,825);
		hauptfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hauptfenster.setMinimumSize(new Dimension(740, 700));
		hauptfenster.setMaximizedBounds(new Rectangle(0,0,1100,900));
		
		Container eimer = hauptfenster.getContentPane();
	
		new StartmenuePresenter(hauptfenster, eimer);
	
		hauptfenster.setVisible(true);
		eimer.revalidate();				
		eimer.repaint();
		
	}
}
		
		
	


