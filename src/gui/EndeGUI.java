package gui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import model.SpielLogik;
import presenter.EndePresenter;

public class EndeGUI extends AbstractGUI {

	public EndeGUI(Container eimer, EndePresenter presenter) {
		super(0,0,1,1, eimer);
		
		this.eimer.setLayout(null); 
		bildIcon = new ImageIcon("src/Other/startHintergrund.jpg");	
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);
		
		 JTextArea endeNachricht = new JTextArea();
			endeNachricht.setBounds((int)(eimer.getWidth() * 0.333), (int)(eimer.getHeight() * 0.45), (int)(eimer.getWidth() * 0.3),100);
			endeNachricht.setVisible(true);
			endeNachricht.setEditable(false);
			endeNachricht.setOpaque(false);
			endeNachricht.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			endeNachricht.setText("Der Gewinner ist " + presenter.getGewinner() + " mit " +  presenter.getGewinnerPunkte() + " Yay!");
		
		
		
		
		panel.add(endeNachricht);
		
		panel.add(bild);
		eimer.revalidate();
		eimer.repaint();
	}

}
