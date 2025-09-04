package gui;

import javax.swing.JPanel;

public interface GUIProzente {
	
	public abstract double getProzentFlaecheX();
	public abstract double getProzentFlaecheHoehe();
	public abstract double getProzentFlaecheY();
	public abstract double getProzentFlaecheBreite();
	public abstract JPanel getPanel();
}


 /* kopierbar:
  
  
	public double getProzentFlaecheX() {
		return this.prozentFlaecheX;
	}
	
	public double getProzentFlaecheHoehe() {
		return this.prozentFlaecheHoehe;
	}
	
	public double getProzentFlaecheY() {
		return this.prozentFlaecheY;
	}
	
	public double getProzentFlaecheBreite() {
		return this.prozentFlaecheBreite;
	}
	
	public JPanel getPanel() {
		return this.getPanel();
	}
}
*/