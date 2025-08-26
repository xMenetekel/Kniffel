package main;

import gui.StartmenueGUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import control.WuerfelManagen;
import model.WuerfelFactory;
import model.WuerfelLogik;
import gui.StartmenueGUI;

public class Main {
	public static void main(String[] args) {
	
		System.out.println("Test es funktionert! UwU");
		

		new StartmenueGUI();
		
		
		
		
		
		//Das ist nur ein Test für die Würfel kann später wider gelöscht werden!
		//5 W6 Wuerfel erstellen
		List<WuerfelLogik> wuerfelListe = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			wuerfelListe.add(WuerfelFactory.createWuerfel("W6"));
		}
		
		//Ubergabe an den Manager
		WuerfelManagen manager = new WuerfelManagen(wuerfelListe);
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
			System.out.println("Würfel bahalten 1-5, 0 kein würfel behalten");
			String zeile = scanner.nextLine().trim();
			
			//zuerst alle auf nichtbehalten
			for(int i = 0; i < manager.getWuerfel().size(); i++) {
				manager.setBehalten(i, false);
			}
			
			if(!zeile.equals("O")){
				String[] teile = zeile.split("\\s+"); //nach Leer trennen?
				for(String t : teile) {
					try {
						int index = Integer.parseInt(t);
						if(index >= 1 && index <= manager.getWuerfel().size()) {
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
	
	private static void ausgabe(WuerfelManagen manager) {
		int i = 1;
		for(WuerfelLogik w : manager.getWuerfel()) {
			System.out.println("Wuerfel " + i + ": " + w.getAugen());
			i++;
		}
		System.out.println("Aktuelle Wurfanzahl: " + manager.getWurfAnzahl());
	}
	

	

}
