package main;

import java.awt.Container;

import javax.swing.JFrame;

import presenter.StartmenuePresenter;

public class Main {
	public static void main(String[] args) {	

		JFrame hauptfenster = new JFrame("Kniffel");
		hauptfenster.setSize(1000,825);
		hauptfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hauptfenster.setVisible(true);
		Container Eimer = hauptfenster.getContentPane();
		
		
		new StartmenuePresenter(hauptfenster, Eimer);
		
	}
}
		
		
	


