package firebaseALocal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import classes.ReservaMobil;
import firebase4j.Firebase;
import firebase4j.FirebaseException;
import firebase4j.FirebaseResponse;
import pujarFitxers.Constants;

/**
 * Classe encarregada de connectar a Firebase i guardar les dades obtingudes.
 * @author Sergi Tor
 * @author Dana Gomez
 * @author Javier Garcia
 */
public class ObtenirDadesFirebase {
	
	static Firebase firebase;
	static List<ReservaMobil> reserves;

	public static void iniciar() throws IOException, ParseException, FirebaseException {
		connectarICrearFitxer();
		
		obtenirDades();
	}
	
	/**
	 * Aquest metode s'encarrega d'establir una connexio amb la BBDD de Firebase i 
	 * obtenir totes les seves dades en format JSON.
	 * Guarda les dades obtingudes en un fitxer JSON.
	 * @throws FirebaseException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void connectarICrearFitxer() throws FirebaseException, UnsupportedEncodingException {
		System.out.println("Connectant a Firebase...\n");
		firebase = new Firebase(Constants.BBDD_FIREBASE);
		System.out.println("\nConnectat a Firebase\n");
		
		FirebaseResponse response = firebase.get();
		File file = new File(Constants.DIRECTORI + Constants.FITXER_JSON);
		try (PrintStream out = new PrintStream(new FileOutputStream(file))) {
		    out.print(response.getRawBody());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("\nFitxer " + Constants.FITXER_JSON + " creat.\n");
		firebase.delete();
	}
	
	/**
	 * Obte les dades del fitxer JSON creat anteriorment. 
	 * Guarda aquestes dades mitjancant mapes per obtenir les ID i els valors de cada objecte, i
	 * posteriorment crea objectes del tipus corresponent i els guarda en una llista.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws ParseException 
	 */
	public static void obtenirDades() throws IOException, ParseException {
		reserves = new ArrayList<ReservaMobil>();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Obtenint dades de " + Constants.FITXER_JSON);
		
		//Creem el primer mapa que agafara els IDs de cada reserva
	    Map<?, ?> map = mapper.readValue(Paths.get(Constants.DIRECTORI+"firebase.json").toFile(), Map.class);
	    for (Map.Entry<?, ?> entry : map.entrySet()) {
	    	String key = entry.getKey().toString();
	    	String id = key.substring(1);
	    	ReservaMobil r = new ReservaMobil(Integer.parseInt(id));
	    	
	        //Amb un segon mapa obtenim les dades de cada camp de la reserva
		    Map<?, ?> map2 = (Map<?, ?>) map.get(entry.getKey());
		    for (Map.Entry<?, ?> entry2 : map2.entrySet()) {
    			String value = entry2.getValue().toString();
    			String key2 = entry2.getKey().toString();
    			switch (key2) {
    				case ("codi_transaccio"):
    					r.setCodiTransaccio(value);
    					break;
    				case ("estat"):
    					r.setEstat(Integer.parseInt(value));
    					break;
    				case ("data"):
    					r.setData(stringADate(value));
    					break;
    				case ("id_activitat"):
    					r.setIdActivitat(Integer.parseInt(value));
    					break;
    				case ("email"):
    					r.setEmail(value);
    					break;
    				default:
    			}
		    }
		    reserves.add(r);
		}
		System.out.println("Dades obtingudes correctament.\n");
	}
	
	/**
	 * Converteix un string a Date.
	 * @param string
	 * @return Date
	 * @throws ParseException 
	 */
	private static Date stringADate(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		date = sdf.parse(string);
		return date;
	}

	public static List<ReservaMobil> getReserves() {
		return reserves;
	}
	
	

}
