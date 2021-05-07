package firebaseALocal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import classes.ActivitatMobil;
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
	static List<ActivitatMobil> activitats;
	static List<ReservaMobil> reserves;

	public static void iniciar() {
		connectarICrearFitxer();
		
		obtenirDades();
	}
	
	/**
	 * Aquest metode s'encarrega d'establir una connexio amb la BBDD de Firebase i 
	 * obtenir totes les seves dades en format JSON.
	 * Guarda les dades obtingudes en un fitxer JSON.
	 */
	public static void connectarICrearFitxer() {
		try {
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
			
		} catch (FirebaseException e) {
			System.err.println("Error connectant a Firebase.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obte les dades del fitxer JSON creat anteriorment. 
	 * Guarda aquestes dades mitjancant mapes per obtenir les ID i els valors de cada objecte, i
	 * posteriorment crea objectes del tipus corresponent i els guarda en una llista.
	 */
	public static void obtenirDades() {
		activitats = new ArrayList<ActivitatMobil>();
		reserves = new ArrayList<ReservaMobil>();
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    System.out.println("Obtenint dades de " + Constants.FITXER_JSON);
		    
		    //Creem el primer mapa que agafara els valors de A i R (totes les activitats i totes les reserves)
		    Map<?, ?> map = mapper.readValue(Paths.get(Constants.DIRECTORI+"firebase.json").toFile(), Map.class);
		    for (Map.Entry<?, ?> entry : map.entrySet()) {
		    	
		        //Amb un segon mapa obtenim la ID de cada Activitat i Reserva
			    Map<?, ?> map2 = (Map<?, ?>) map.get(entry.getKey());
			    for (Map.Entry<?, ?> entry2 : map2.entrySet()) {
			    	String key = entry2.getKey().toString();
			    	//ACTIVITATS
			    	if (key.startsWith("A")) {
				    	String id = key.substring(1);
				    	ActivitatMobil a = new ActivitatMobil(Integer.parseInt(id));
				        
				        //Amb un tercer mapa obtenim les places actuals de cada activitat
					    Map<?, ?> map3 = (Map<?, ?>) map2.get("A"+a.getId());
					    for (Map.Entry<?, ?> entry3 : map3.entrySet()) {
					    	String value = entry3.getValue().toString();
					    	int places = Integer.parseInt(value);
					    	a.setPlacesActuals(places);
					    }
					    activitats.add(a);
			    	}
			    	//RESERVES 
			    	else if (key.startsWith("R")) {
			    		String id = key.substring(1);
			    		ReservaMobil r = new ReservaMobil(Integer.parseInt(id));
			    		
			    		//Amb un tercer mapa obtenim la resta de dades de la reserva
			    		Map<?, ?> map3 = (Map<?, ?>) map2.get("R" + r.getId());
			    		for (Map.Entry<?, ?> entry3 : map3.entrySet()) {
			    			String value = entry3.getValue().toString();
			    			String key2 = entry3.getKey().toString();
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
			    				case ("id_usuari"):
			    					r.setUsuari(Integer.parseInt(value));
			    					break;
			    				default:
			    			}
			    		}
			    		reserves.add(r);
			    	}
			    }
		    }
		    System.out.println("Dades obtingudes correctament.\n");

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	/**
	 * Converteix un string a Date.
	 * @param string
	 * @return Date
	 */
	private static Date stringADate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static List<ActivitatMobil> getActivitats() {
		return activitats;
	}

	public static List<ReservaMobil> getReserves() {
		return reserves;
	}
	
	

}
