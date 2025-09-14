package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import presenter.EndePresenter;

public class EndeGUI extends AbstractGUI {
	JButton neustart;
	JTextArea endeNachricht;

	/**
	 * erstellt den Endbildschirm mit einem Hintergrund, einer Gewinnernachricht und einem neustart Button, der das Spiel wieder im Startmenue einsteigen lässt.
	 * 
	 * @param eimer			uebergeordneter Container
	 * @param presenter		zuegehoeriger Presenter um die Daten abzurufen
	 */
	public EndeGUI(Container eimer, EndePresenter presenter) {
		super(0,0,1,1, eimer);
		this.eimer.removeAll();		
		
		eimer.setLayout(null); 
		
		bildIcon = new ImageIcon("src/other/startHintergrund.jpg");	
		bild.setBounds(0,0, eimer.getWidth(), eimer.getHeight());
		bild.setIcon(bildIcon);			

		endeNachricht = new JTextArea();		
		endeNachricht.setVisible(true);
		endeNachricht.setEditable(false);
		endeNachricht.setOpaque(false);
		endeNachricht.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));		
		endeNachricht.setText("Der Gewinner ist\n" 
				+ presenter.getGewinner() + "\nmit "
				+  presenter.getGewinnerPunkte() + " Punkten\nYay!");
		
		neustart = new JButton("Neustart!");				
		
		panel.add(neustart);
		panel.add(endeNachricht);	
		panel.add(bild);
		resizing();
		
		eimer.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizing();
				
			}			
		});			
		
		eimer.add(panel);		
		eimer.revalidate();
		eimer.repaint();
	}
	
	/**
	 * Hilfsfunktion, damit alle Elemente richtig an den Eimer angepasst und gesetzt werden. Und das sie im Component listener aufgerufen werden kann.
	 */
	private void resizing() {
		setzeNeueGroesse(eimer.getWidth(), eimer.getHeight());
		neustart.setBounds((int)(eimer.getWidth() * 0.43),(int) (eimer.getHeight() * 0.8),(int) (eimer.getWidth() * 0.4), 60);
		endeNachricht.setBounds(
				(int)(eimer.getWidth() * 0.333), 
				(int)(eimer.getHeight() * 0.35), 
				(int)(eimer.getWidth() * 0.4),
				400);
		
		eimer.revalidate();
		eimer.repaint();
	}
	
	
	//Settzen und Gettzen

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
