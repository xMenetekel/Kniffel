package model;

public enum Kategorie {
	EINSER("Einser",1,0,true,"Other/EINER.png"),
	ZWEIER("Zweier",2,1,true,"Other/ZWEIER.png"),
	DREIER("Dreier",3,2,true,"Other/DREIER.png"),
	VIERER("Vierer",4,3,true,"Other/VIERER.png"),
	FUENFER("Fuenfer",5,4,true,"Other/FUENFER.png"),
	SECHSER("Sechser",6,5,true,"Other/SECHSER.png"),
	GESAMT_OBEN("Gesamt Oben",-1,6,false, null),
	BONUS("Bonus",-1,7,false,null),
	GESAMT_OBEN_BONUS("Gesamt Oben Bonus",-1,8,false,null),
	DREIER_PASCH("Dreierpasch",-1,9,true,"Other/DREIERPASCH.png"),
	VIERER_PASCH("Viererpasch",-1,10,true,"Other/VIERERPASCH.png"),
	FULL_HOUSE("Full House",-1,11,true,"Other/FULL_HOUSE.png"),
	KLEINE_STRASSE("Kleine Strasse",-1,12,true,"Other/KLEINE_STRASSE.png"),
	GROSSE_STRASSE("Grosse Strasse",-1,13,true,"Other/GROSSE_STRASSE.png"),
	KNIFFEL("Kniffel",-1,14,true,"Other/KNIFFEL.png"),
	CHANCE("Chance",-1,15,true,"Other/CHANCE.png"),
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
