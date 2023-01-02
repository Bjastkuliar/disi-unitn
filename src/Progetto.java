// Scheletro di codice per il progetto 2022

// In questo file NON ci deve essere nessuna dichiarazione "package"
// come per esempio
//
//    package progetto;

// Potete aggiungere qui altri "import" per usare le librerie
// standard di Java (ad es. lo Scanner).

import java.io.*;
import java.util.Scanner;

// NON modificate in nessun modo la linea seguente
public class Progetto {

	// Qui potete liberamente aggiungere altre funzioni / procedure.
	
	// Se volete, qui potete anche inserire variabili globali.
	// Se decidete di inserirle, dovete fare in modo che la funzione
	//     determinante
	// inizializzi tali variabili **ogni volta** che viene chiamata. 
	// Inizializzarle una volta sola nel main NON basta, visto che la
	// funzione verra` chiamate piu` volte.
	
	
	// NON modificate in nessun modo la linea seguente
	public static long[] determinante(String fileName) {
		// Qui potete inserire il vostro codice, modificando lo stub esistente.
		long[] risultato = new long[2];
		risultato[0] = 1;
		risultato[1] = 1;
		return risultato;
	}

	// Potete modificare il main liberamente. Potete per esempio eseguire
	// qualche test sulla funzione sopra, come quelli suggeriti sotto.
	// Ricordatevi che chi corregge il progetto NON eseguira` il vostro
	// main, ma i propri test.
	// 
	public static void main(String[] args) {
		// Eseguiamo un test
		long[] d;
		d = determinante("test.txt");
		System.out.println("determinante = " + d[0] + "/" + d[1]);
	}
}

