package model;

public class EndeLogik {
	private SpielLogik spielLogik;
	
	public EndeLogik(SpielLogik spielLogik) {
		this.setSpielLogik(spielLogik);		
	}
	
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

	public SpielLogik getSpielLogik() {
		return spielLogik;
	}

	public void setSpielLogik(SpielLogik spielLogik) {
		this.spielLogik = spielLogik;
	}

}
