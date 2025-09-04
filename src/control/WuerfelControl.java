package control;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import gui.WuerfelGUI;
import gui.WuerfelflaecheGUI;
import gui.WuerfelBankGUI;
import gui.PunkteTabelleGUI;
import model.Kategorie;
import model.WuerfelLogik;

public class WuerfelControl  {
    private List<WuerfelGUI> alleWuerfelGUI = new ArrayList<>();
    private List<WuerfelLogik> alleWuerfelLogik = new ArrayList<>();
    private Container container;
    private MoeglicheZuege moeglicheZuege = new MoeglicheZuege();
    private PunkteTabelleGUI punkteTabelle;
    private WuerfelflaecheGUI flaeche;
    private WuerfelBankGUI bankGUI;
    
    private List<int[]> freiePlaetze;
    
    int wurfAnzahl = 0;
    int maxWuerfe = 3;
    
    public WuerfelControl(Container Eimer, WuerfelflaecheGUI flaeche, WuerfelBankGUI bankGUI, PunkteTabelleGUI punkteTabelle){
        this.container = Eimer;
        this.punkteTabelle = punkteTabelle;
        this.flaeche = flaeche;
        this.bankGUI = bankGUI;
        
        for(int i = 0; i < 5; i++) {
            WuerfelLogik model = new WuerfelLogik(6);
            WuerfelGUI view = new WuerfelGUI(model, i*55+80, 420, Eimer);
            model.setGui(view);
            view.getButton().addActionListener(klick -> { 
                boolean neuerZustand = !model.istBehalten();
                model.setBehalten(neuerZustand);
                hochRunterhochRunter(model);
            });
            alleWuerfelGUI.add(view);
            alleWuerfelLogik.add(model);        
            container.add(view.getButton());
        }
        container.revalidate();
        container.repaint();    
    }
    
    // ORIGINALE WUERFELN METHODE
    public void wuerfeln() {
        if(wurfAnzahl >= maxWuerfe) {
            return;
        }
        
        // ORIGINALER BEWEGUNGSCODE
        for(int i = 0; i < alleWuerfelLogik.size(); i++) {
            WuerfelLogik logik = alleWuerfelLogik.get(i);
            WuerfelGUI gui = alleWuerfelGUI.get(i);
            
            if(!logik.istBehalten()) {
                logik.wuerfeln();
                gui.fresherButton();
                
                int x = getValidesX(flaeche);
                int y = getValidesY(flaeche);
                int[] platz = korrigierteKollision(gui, x, y);
                gui.zeichneLinie(gui.getButton(), platz[0], platz[1]);
               
            }
        }
        
        wurfAnzahl++;
        
        // Würfelwerte für Punkteberechnung speichern
        speichereWuerfelWerte();
        
        if(wurfAnzahl == maxWuerfe) {
            aktualisierePunkteTabelle();
        }
    }
    
    private void aktualisierePunkteTabelle() {
        Map<Kategorie, Integer> moeglichePunkte = moeglicheZuege.berechneAlleMoeglichenPunkte();
        System.out.println("Mögliche Punkte: " + moeglichePunkte); // DEBUG
        if (punkteTabelle != null) {
            punkteTabelle.zeigeMoeglichePunkte(moeglichePunkte);
        }
    }
    
    private void speichereWuerfelWerte() {
        List<Integer> werte = new ArrayList<>();
        for (int i = 0; i < alleWuerfelLogik.size(); i++) {
            werte.add(alleWuerfelLogik.get(i).getAugen());
        }
        moeglicheZuege.setWuerfelWerte(werte);
        System.out.println("Würfelwerte gespeichert: " + werte); // DEBUG
    }
    
    // ORIGINALE BEWEGUNGSMETHODEN
    private void hochRunterhochRunter(WuerfelLogik wuerfel) { 
        if(wuerfel.istBehalten()) {
            this.runter(wuerfel.getGui());
        } else {
            this.hoch(wuerfel.getGui());
        }
    }
    
    private void runter(WuerfelGUI gui) {
        int x = (int)(container.getWidth() * bankGUI.getProzentFlaecheX());
        int y = (int)(container.getHeight() * bankGUI.getProzentFlaecheY());
        x = (x + 5) * zaehleBankFuelle();
        y = y + 5;
        gui.zeichneLinie(gui.getButton(), x, y);
    }
    
    private void hoch(WuerfelGUI gui) {
        int x = getValidesX(flaeche);
        int y = getValidesY(flaeche);
        int xy[] = korrigierteKollision(gui, x, y);
        gui.zeichneLinie(gui.getButton(), xy[0], xy[1]);
    }
    
    private int zaehleBankFuelle() {
        int bank = 0;
        for (WuerfelLogik wuerfel : alleWuerfelLogik) {
            if (wuerfel.istBehalten()) { bank++; }            
        }        
        return bank;
    }
    
    // ORIGINALE KOLLISIONSMETHODEN
    private int[] korrigierteKollision(WuerfelGUI aktuellerWuerfel, int zielX, int zielY) {
        final int WUERFEL_GROESSE = 50;            
        boolean kollisionGefunden;
        do {
            kollisionGefunden = false;
            for(WuerfelGUI andererWuerfel : alleWuerfelGUI) {
                if(andererWuerfel == aktuellerWuerfel || andererWuerfel.getLogik().istBehalten()) { continue; }                
                int andererX = andererWuerfel.getxPos();
                int andererY = andererWuerfel.getyPos();                        
                boolean kollisionX = (zielX < andererX + WUERFEL_GROESSE) && (zielX + WUERFEL_GROESSE > andererX);
                boolean kollisionY = (zielY < andererY + WUERFEL_GROESSE) && (zielY + WUERFEL_GROESSE > andererY);                                        
                if(kollisionX && kollisionY) {
                    kollisionGefunden = true;
                    zielX = getValidesX(flaeche);
                    zielY = getValidesY(flaeche);
                    break;
                }
            }                    
        } while (kollisionGefunden);
        return new int[] {zielX, zielY};
    }
    
    public int getValidesX(WuerfelflaecheGUI flaeche) {
        int validesX;
        int xVon = (int) (flaeche.getProzentFlaecheX() * this.container.getWidth());
        int xBis = (int) ((flaeche.getProzentFlaecheBreite() + flaeche.getProzentFlaecheX()) * this.container.getWidth());
        validesX = new Random().nextInt(xBis-xVon)+xVon-50;        
        return validesX;
    }
    
    public int getValidesY(WuerfelflaecheGUI flaeche) {
        int validesY;
        int YVon = (int) (flaeche.getProzentFlaecheY() * this.container.getHeight());
        int YBis = (int) ((flaeche.getProzentFlaecheHoehe() + flaeche.getProzentFlaecheY()) * this.container.getHeight());
        validesY = new Random().nextInt(YBis-YVon)+YVon;        
        return validesY;
    }
    
    // GETTER und SETTER
    public MoeglicheZuege getMoeglicheZuege() {
        return moeglicheZuege;
    }
    
    public void neueRunde() {
        wurfAnzahl = 0;
        for (WuerfelLogik wuerfel : alleWuerfelLogik) {
            wuerfel.setBehalten(false);
            wuerfel.wuerfeln();
        }
        for (WuerfelGUI gui : alleWuerfelGUI) {
            gui.fresherButton();
        }
        if (punkteTabelle != null) {
        	punkteTabelle.zeigeMoeglichePunkte(new HashMap<>());
        }
    }
    
    public List<WuerfelGUI> getAlleWuerfelGUI() {
        return alleWuerfelGUI;
    }
    
    public List<WuerfelLogik> getAlleWuerfelLogik() {
        return alleWuerfelLogik;
    }
    
    public int getWurfAnzahl() {
        return wurfAnzahl;
    }
    
    public int getMaxWuerfe() {
        return maxWuerfe;
    }
}