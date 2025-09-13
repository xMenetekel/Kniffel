package model;

public class EndeLogik {
	private SpielLogik spielLogik;
	private StartmenueLogik menueDaten;
	
	public EndeLogik(SpielLogik spielLogik, StartmenueLogik menueDaten) {
		this.setSpielLogik(spielLogik);
		this.setMenueDaten(menueDaten);
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

	public StartmenueLogik getMenueDaten() {
		return menueDaten;
	}

	public void setMenueDaten(StartmenueLogik menueDaten) {
		this.menueDaten = menueDaten;
	}

}
