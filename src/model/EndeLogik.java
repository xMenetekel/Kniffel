package model;

public class EndeLogik {
	private SpielLogik spielLogik;
	
	public EndeLogik(SpielLogik spielLogik) {
		this.setSpielLogik(spielLogik);		
	}
	
	/**
	 * rechnet den Gewinner des Spiels aus
	 * @return gibt den Index des siegenden Spielers zurück
	 */
	public int getGewinner() {
		int mostWantedWerHatDieMeistenPunkte = 0;
		int spielerIndex = -1;
		for(SpielerLogik spieler : spielLogik.getAlleSpieler()) {
			if(spieler.getPunktestand() > mostWantedWerHatDieMeistenPunkte) {
				mostWantedWerHatDieMeistenPunkte = spieler.getPunktestand();
				spielerIndex = spielLogik.getAlleSpieler().indexOf(spieler);
			}
		}		
		
		return spielerIndex;
	}

	
	//Getter Setter
	
	public SpielLogik getSpielLogik() {
		return spielLogik;
	}

	public void setSpielLogik(SpielLogik spielLogik) {
		this.spielLogik = spielLogik;
	}

}
