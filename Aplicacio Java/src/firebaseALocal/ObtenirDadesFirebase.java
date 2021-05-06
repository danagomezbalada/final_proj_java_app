package firebaseALocal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import firebase4j.Firebase;
import firebase4j.FirebaseException;
import firebase4j.FirebaseResponse;
import pujarFitxers.Constants;

public class ObtenirDadesFirebase {
	
	static Firebase firebase;

	public static void main(String[] args) {
		try {
			System.out.println("Connectant a Firebase...");
			firebase = new Firebase(Constants.BBDD_FIREBASE);
			//FirebaseResponse response = firebase.delete();
			
			System.out.println("\n\n=====================");
			System.out.println("Connectat a Firebase ");
			System.out.println("=====================\n\n");
			
			FirebaseResponse response = firebase.get();
			String json = response.getRawBody();
			System.out.println( "\n\nResultat de GET:\n" + json + "\n");
			
			/*File file = new File(Constants.DIRECTORI+"firebase.json");
			try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
			    out.print(response.getRawBody());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}*/ 
			
		} catch (FirebaseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}

}
