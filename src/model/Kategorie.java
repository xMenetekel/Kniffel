package model;

public enum Kategorie {
	EINSER("Einser",1,0,true,"Other/1:20.png"),
	ZWEIER("Zweier",2,1,true,"Other/2:20.png"),
	DREIER("Dreier",3,2,true,"Other/3:20.png"),
	VIERER("Vierer",4,3,true,"Other/4:20.png"),
	FUENFER("Fuenfer",5,4,true,"Other/5:20.png"),
	SECHSER("Sechser",6,5,true,"Other/6:20.png"),
	GESAMT_OBEN("Gesamt Oben",-1,6,false, null),
	BONUS("Bonus",-1,7,false,null),
	GESAMT_OBEN_BONUS("Gesamt Oben Bonus",-1,8,false,null),
	DREIER_PASCH("Dreierpasch",-1,9,false,null),
	VIERER_PASCH("Viererpasch",-1,10,false,null),
	FULL_HOUSE("Full House",-1,11,false,null),
	KLEINE_STRASSE("Kleine Strasse",-1,12,false,null),
	GROSSE_STRASSE("Grosse Strasse",-1,13,false,null),
	KNIFFEL("Kniffel",-1,14,false,null),
	CHANCE("Chance",-1,15,false,null),
	GESAMT_UNTEN("Gesamt Unten",-1,16,false,null),
	GESAMT_OBEN_ZWEI("Gesamt Oben",-1,17,false,null),
	ENDSUMME("Endsumme",-1,18,false,null);
	
    private final String anzeigeName;
    private final int augenZahl;
    private final int tabellenIndex;
    private final boolean hatBild;
    private final String bildDatei;
    
    Kategorie(String anzeigeName, int augenZahl, int tabellenIndex, boolean hatBild, String bildDatei) {
        this.anzeigeName = anzeigeName;
        this.augenZahl = augenZahl;
        this.tabellenIndex = tabellenIndex;
        this.hatBild = hatBild;
        this.bildDatei = bildDatei;
    }
    
    public String getAnzeigeName() { return anzeigeName; }
    public int getAugenZahl() { return augenZahl; }
    public int getTabellenIndex() { return tabellenIndex; }
    public boolean hatBild() { return hatBild; }
    public String getBildDatei() { return bildDatei; }
    
    private static final Kategorie[] NACH_INDEX = new Kategorie[19];
    static {
        Kategorie[] alleKategorien = values();
        for (int i = 0; i < alleKategorien.length; i++) {
            Kategorie k = alleKategorien[i];
            if (k.tabellenIndex >= 0 && k.tabellenIndex < NACH_INDEX.length) {
                NACH_INDEX[k.tabellenIndex] = k;
            }
        }
    }
    
    public static Kategorie fromTabellenIndex(int index) {
        if (index >= 0 && index < NACH_INDEX.length) {
            return NACH_INDEX[index];
        }
        return null;
    }
}
