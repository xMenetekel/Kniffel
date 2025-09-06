package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Kategorie;

public class MoeglicheZuege {

	private List<Integer>letzteWuerfelWerte = new ArrayList<>();
	private Map<Kategorie,Integer>aktiveMoeglichePunkte = new HashMap<>();
	private Map<Kategorie,Integer>einzelWerte = new HashMap<>();
	
	public void setWuerfelWerte(List<Integer> wuerfel) {
		letzteWuerfelWerte.clear();
		for(int i=0; i< wuerfel.size(); i++) {
			letzteWuerfelWerte.add(wuerfel.get(i));
		}
		Collections.sort(letzteWuerfelWerte);
		berechneEinzelWerte();
		berechneKombinationen();
		
	}

	private void berechneEinzelWerte() {
		einzelWerte.clear();
		for (int augenzahl = 1; augenzahl <= 6; augenzahl++) {
			int punkte = berechneEinzelwert(augenzahl);
			if(punkte>0) {
				Kategorie kategorie = getKategorieFuerAugenzahl(augenzahl);
				einzelWerte.put(kategorie,punkte);
			}
		}
	}
	
	private void berechneKombinationen() {
		aktiveMoeglichePunkte.clear();
		aktiveMoeglichePunkte.putAll(einzelWerte);
		Kategorie[] kombinationen = {
				Kategorie.DREIER_PASCH,Kategorie.VIERER_PASCH,
				Kategorie.FULL_HOUSE,Kategorie.KLEINE_STRASSE,
				Kategorie.GROSSE_STRASSE,Kategorie.KNIFFEL, Kategorie.CHANCE
		};
		for(Kategorie kategorie : kombinationen) {
			int punkte = berechnePunkte(kategorie);
			if(punkte >0) {
				aktiveMoeglichePunkte.put(kategorie, punkte);
			}
		}
	}
	
	private Kategorie getKategorieFuerAugenzahl(int augenzahl) {
		switch(augenzahl) {
		case 1: return Kategorie.EINSER;
		case 2: return Kategorie.ZWEIER;
		case 3: return Kategorie.DREIER;
		case 4: return Kategorie.VIERER;
		case 5: return Kategorie.FUENFER;
		case 6: return Kategorie.SECHSER;
		default: return null;
		}
	}
	
	public Map<Kategorie,Integer> getAktiveMoeglichePunkte(){
		return new HashMap<>(aktiveMoeglichePunkte);
	}
	
	  public int berechnePunkte(Kategorie kategorie) {
	        if (letzteWuerfelWerte.isEmpty()) return 0;
	        
	        switch(kategorie) {
	            case EINSER: return berechneEinzelwert(1);
	            case ZWEIER: return berechneEinzelwert(2);
	            case DREIER: return berechneEinzelwert(3);
	            case VIERER: return berechneEinzelwert(4);
	            case FUENFER: return berechneEinzelwert(5);
	            case SECHSER: return berechneEinzelwert(6);
	            case DREIER_PASCH: return berechneDreierPasch();
	            case VIERER_PASCH: return berechneViererPasch();
	            case FULL_HOUSE: return berechneFullHouse();
	            case KLEINE_STRASSE: return berechneKleineStrasse();
	            case GROSSE_STRASSE: return berechneGrosseStrasse();
	            case KNIFFEL: return berechneKniffel();
	            case CHANCE: return berechneChance();
	            default: return 0;
	        }
	    }
	    
	  	public void entferneKategorie(Kategorie kategorie) {
	  		aktiveMoeglichePunkte.remove(kategorie);

	  	}
	
	    public List<Kategorie> filterMoeglichkeiten() {
	        List<Kategorie> moegliche = new ArrayList<>();
	        for (Kategorie kat : Kategorie.values()) {
	            if (kat.getAugenZahl() != -1 && berechnePunkte(kat) > 0) {
	                moegliche.add(kat);
	            }
	        }
	        return moegliche;
	    }
	    
	    private int berechneEinzelwert(int augenzahl) {
	        int summe = 0;
	        for (int i = 0; i < letzteWuerfelWerte.size(); i++) {
	            int wert = letzteWuerfelWerte.get(i);
	            if (wert == augenzahl) summe += augenzahl;
	        }
	        return summe;
	    }
	    
	    private int berechneDreierPasch() {
	        return hatMindestensXGleiche(3) ? summiereAlle() : 0;
	    }
	    
	    private int berechneViererPasch() {
	        return hatMindestensXGleiche(4) ? summiereAlle() : 0;
	    }
	    
	    private int berechneFullHouse() {
	        Map<Integer, Integer> haeufigkeiten = getHaeufigkeiten();
	        return (haeufigkeiten.containsValue(3) && haeufigkeiten.containsValue(2)) ? 25 : 0;
	    }
	    
	    private int berechneKleineStrasse() {
	        Set<Integer> unique = new HashSet<>(letzteWuerfelWerte);
	        return (unique.containsAll(Arrays.asList(1,2,3,4)) ||
	                unique.containsAll(Arrays.asList(2,3,4,5)) ||
	                unique.containsAll(Arrays.asList(3,4,5,6))) ? 30 : 0;
	    }
	    
	    private int berechneGrosseStrasse() {
	        Set<Integer> unique = new HashSet<>(letzteWuerfelWerte);
	        return (unique.containsAll(Arrays.asList(1,2,3,4,5)) ||
	                unique.containsAll(Arrays.asList(2,3,4,5,6))) ? 40 : 0;
	    }
	    
	    private int berechneKniffel() {
	        return hatMindestensXGleiche(5) ? 50 : 0;
	    }
	    
	    private int berechneChance() {
	        return summiereAlle();
	    }
	    
	    private boolean hatMindestensXGleiche(int x) {
	        Map<Integer, Integer> haeufigkeiten = getHaeufigkeiten();
	        for (Integer count : haeufigkeiten.values()) {
	            if (count >= x) return true;
	        }
	        return false;
	    }
	    
	    private Map<Integer, Integer> getHaeufigkeiten() {
	        Map<Integer, Integer> counts = new HashMap<>();
	        for (int i = 0; i < letzteWuerfelWerte.size(); i++) {
	            int wert = letzteWuerfelWerte.get(i);
	            counts.put(wert, counts.getOrDefault(wert, 0) + 1);
	        }
	        return counts;
	    }
	    
	    private int summiereAlle() {
	        int summe = 0;
	        for (int i = 0; i < letzteWuerfelWerte.size(); i++) {
	            summe += letzteWuerfelWerte.get(i);
	        }
	        return summe;
	    }
	    
	    public Map<Kategorie, Integer> berechneAlleMoeglichenPunkte() {
	        Map<Kategorie, Integer> allePunkte = new HashMap<>();
	        Kategorie[] alleKategorien = Kategorie.values();
	        
	        for (int i = 0; i < alleKategorien.length; i++) {
	            Kategorie kat = alleKategorien[i];
	            if (kat.getAugenZahl() != -1 
	            		|| kat == Kategorie.DREIER_PASCH 
	            		|| kat == Kategorie.VIERER_PASCH 
	            		|| kat == Kategorie.FULL_HOUSE
	            		|| kat == Kategorie.KLEINE_STRASSE
	            		|| kat == Kategorie.GROSSE_STRASSE
	            		|| kat == Kategorie.KNIFFEL
	            		|| kat == Kategorie.CHANCE)
	            		{
	                int punkte = berechnePunkte(kat);
	                if (punkte > 0) {
	                    allePunkte.put(kat, punkte);
	                    System.out.println(" "+ kat + ":" + punkte + "punkte");
	                }
	            }
	        }
	        return allePunkte;
	    }
	}
	

