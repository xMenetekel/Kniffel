package gui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import presenter.EndePresenter;

public class EndeGUI extends AbstractGUI {
	JButton neustart;
	JTextArea endeNachricht;

	/**
	 * erstellt den Endbildschirm
	 * @param eimer			uebergeordneter Container
	 * @param presenter		zuegehoeriger Presenter liefert gewinner Daten
	 */
	public EndeGUI(Container eimer, EndePresenter presenter) {
		super(0,0,1,1, eimer);
		
		this.eimer.setLayout(null); 
		bildIcon = new ImageIcon("src/Other/startHintergrund.jpg");	
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);
		

		endeNachricht = new JTextArea();
		endeNachricht.setBounds(
				(int)(eimer.getWidth() * 0.333), 
				(int)(eimer.getHeight() * 0.45), 
				(int)(eimer.getWidth() * 0.3),
				100);
		
		endeNachricht.setVisible(true);
		endeNachricht.setEditable(false);
		endeNachricht.setOpaque(false);
		endeNachricht.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		
		endeNachricht.setText("Der Gewinner ist " 
				+ presenter.getGewinner() + " mit "
				+  presenter.getGewinnerPunkte() + " Yay!");
		
		neustart = new JButton("Neustart!");
		neustart.setBounds((int)(eimer.getWidth() * 0.333),(int) (eimer.getHeight() * 0.8),(int) (eimer.getWidth() * 0.5), 80);
		
		
		panel.add(bild);
		panel.add(neustart);
		panel.add(endeNachricht);		
		
		
		eimer.revalidate();
		eimer.repaint();
	}

	public JButton getNeustart() {
		return neustart;
	}

	public void setNeustart(JButton neustart) {
		this.neustart = neustart;
	}

	public JTextArea getEndeNachricht() {
		return endeNachricht;
	}

	public void setEndeNachricht(JTextArea endeNachricht) {
		this.endeNachricht = endeNachricht;
	}

}
