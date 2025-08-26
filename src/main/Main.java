package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import control.StartmenueControl;
import control.WuerfelControl;
import model.WuerfelFactory;
import model.WuerfelLogik;


public class Main {
	public static void main(String[] args) {
	
		System.out.println("Test es funktionert! UwU");
		

		new StartmenueControl();
		
		
		
		
		
		//Das ist nur ein Test für die Würfel kann später wider gelöscht werden!
		//5 W6 Wuerfel erstellen
		List<WuerfelLogik> wuerfelListe = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			wuerfelListe.add(WuerfelFactory.createWuerfel("W6"));
		}
		
		//Ubergabe an den Manager
		WuerfelControl manager = new WuerfelControl();
		Scanner scanner = new Scanner(System.in);
				
		//Wurfabfrage nochmal Würfeln
		while(true) {
			manager.wuerfeln();
			ausgabe(manager);
			
			if(manager.getWurfAnzahl() >= manager.getMaxWuerfe()) {
				System.out.println("Maximale Wuerfe erreicht!");
				break;
			}
			
			//Meherer Wuerfel behalten
			System.out.println("Würfel behalten 1-5, 0 kein Würfel behalten");
			String zeile = scanner.nextLine().trim();
			
			//zuerst alle auf nichtbehalten
			for(int i = 0; i < manager.getAlleWuerfelLogik().size(); i++) {
				manager.setBehalten(i, false);
			}
			
			if(!zeile.equals("O")){
				String[] teile = zeile.split("\\s+"); //nach Leer trennen?
				for(String t : teile) {
					try {
						int index = Integer.parseInt(t);
						if(index >= 1 && index <= manager.getAlleWuerfelLogik().size()) {
							manager.setBehalten(index-1, true);
							System.out.println("Wuerfel " + index + "behalten.");
						}
					}catch (NumberFormatException e) {
						
					}
				}
			}
			
			//nochmal würfeln mx 3x
			System.out.println("Nochmal würfeln? J/N");
			String eingabe = scanner.nextLine().trim().toUpperCase();
			
			if(!eingabe.equals("J")) {
				System.out.println("Beendet nach " + manager.getWurfAnzahl() + "Wuerfen.");
				break;
			}
		}
		scanner.close();
		

	}
	
	private static void ausgabe(WuerfelControl manager) {
		int i = 1;
		for(WuerfelLogik w : manager.getAlleWuerfelLogik()) {
			System.out.println("Wuerfel " + i + ": " + w.getAugen());
			i++;
		}
		System.out.println("Aktuelle Wurfanzahl: " + manager.getWurfAnzahl());
	}
	

	

}
