// Scheletro di codice per il progetto 2022

// In questo file NON ci deve essere nessuna dichiarazione "package"
// come per esempio
//
//package progetto;

// Potete aggiungere qui altri "import" per usare le librerie
// standard di Java (ad es. lo Scanner).

import java.util.Arrays;

// NON modificate in nessun modo la linea seguente
public class Progetto {

	// Qui potete liberamente aggiungere altre funzioni / procedure.

    /* Se volete, qui potete anche inserire variabili globali.
	 Se decidete d'inserirle, dovete fare in modo che la funzione
	 determinante
	 inizializzi tali variabili **ogni volta** che viene chiamata.
	 Inizializzarle una volta sola nel main NON basta, visto che la
	 funzione verrà chiamate piu` volte.*/

	// NON modificate in nessun modo la linea seguente
	public static long[] determinante(String fileName) {
		// Qui potete inserire il vostro codice, modificando lo stub esistente.
		long[][][] matrix = Utils.readMatrix("test/mat/matrice-001.txt");
		long[] risulato = new long[2];
		/*if(matrix!=null) {
			Utils.printMatrix(matrix);
			Useless.determinant(matrix);
			//risulato = Utils.determinant(matrix);
			System.out.println(Arrays.toString(risulato));
			System.out.print("Soluzione: ");
			Utils.printSampleSolution("test/det/det_matrice-001.txt");
		}*/
		double risultato2 = 0;
		double[][] matrix2 = Test.readMatrix("test/mat/matrice-001.txt");
		if(matrix2!=null) {
			Test.printMatrix(matrix2);
			risultato2 = Test.determinant(matrix2);
			System.out.println(risultato2);
			Test.printSampleSolution("test/det/det_matrice-001.txt");
		}
		return risulato;
	}


    /* Potete modificare il main liberamente. Potete per esempio eseguire
	 qualche test sulla funzione sopra, come quelli suggeriti sotto.
	 Ricordatevi che chi corregge il progetto NON eseguirà il vostro
	 main, ma i propri test.*/

		public static void main(String[] args) {
			// Eseguiamo un test
			long[] d;
			d = determinante("test.txt");
			System.out.println("determinante = " + d[0] + "/" + d[1]);
		}
}

