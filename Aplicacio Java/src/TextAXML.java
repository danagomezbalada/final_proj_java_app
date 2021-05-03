
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import classes.Activitat;
import classes.Categoria;

public class TextAXML {
	
	static String tipus = "";
	static List<Categoria> categories = new ArrayList<Categoria>();
	static List<Activitat> activitats = new ArrayList<Activitat>();
	

	public static void main(String[] args) {
		int lectura;
		char ch;
		StringBuilder s = new StringBuilder();
		String [] valors;
		
		try (FileReader lector = new FileReader(Constants.DIRECTORI+Constants.FITXER_DADES);) {
			while ((lectura = lector.read())!=-1) {
				ch = (char) lectura;
				if (ch == '#'){
					valors = s.toString().split("#");
					s.setLength(0);
					tipus = valors[0];
				}else if (ch == '\n') {
					valors = s.toString().replaceAll("(\\r)", "").split(";");
					s.setLength(0);
					afegirValors(valors);
				}else {
					s.append(ch);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Segons el tipus d'objecte a crear, crida el metode corresponent.
	 * @param valors - Les dades de l'objecte a ser creat.
	 */
	public static void afegirValors(String [] valors) {
		switch (tipus) {
			case "C":
				insertarCategoria(valors);
				break;
			case "U":
				insertarUbicacio(valors);
				break;
			case "D":
				insertarDepartament(valors);
				break;
			case "E":
				insertarEsdeveniment(valors);
				break;
			case "P":
				insertarPonent(valors);
				break;
			case "A":
				insertarActivitat(valors);
				break;
			case "AP":
				insertarRelacioActivitatPonent(valors);
				break;
			case "AC":
				insertarRelacioActivitatCategoria(valors);
				break;
			default:
		}
				
			
	}	
	
	/**
	 * Crea un objecte categoria amb els valors rebuts.
	 * @param valors
	 */
	private static void insertarCategoria(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		String nom = valors[1];
		
		Categoria categoria = new Categoria(id, nom);
		categories.add(categoria);
	}
	
	private static void insertarUbicacio(String[] valors) {
		
	}
	
	private static void insertarDepartament(String[] valors) {
		
	}
	
	private static void insertarEsdeveniment(String[] valors) {
		
	}
	
	private static void insertarPonent(String[] valors) {
		
	}
	
	/**
	 * Crea un objecte activitat amb els valors rebuts.
	 * @param valors
	 */
	private static void insertarActivitat(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		String titol = valors[1];
		Date data = stringADate(valors[2]);
		String descripcio = valors[3];
		double preu = Double.parseDouble(valors[4]);
		int placesTotals = Integer.parseInt(valors[5]);
		int placesActuals = Integer.parseInt(valors[6]);
		int idEsdeveniment = Integer.parseInt(valors[7]);
		Date dataIniciMostra = stringADate(valors[8]);
		Date dataFiMostra = stringADate(valors[9]);
		int idUbicacio = Integer.parseInt(valors[10]);
		int idDepartament = Integer.parseInt(valors[11]);
		
		Activitat activitat = new Activitat(id, idEsdeveniment, data, titol, descripcio, preu, placesTotals, placesActuals, dataIniciMostra, 
				dataFiMostra, idUbicacio, idDepartament);
		activitats.add(activitat);
		
	}
	
	private static void insertarRelacioActivitatPonent(String[] valors) {
		
	}
	
	/**
	 * Afegeix l'activitat amb l'id rebut a la categoria amb l'id rebut, i viceversa.
	 * @param valors
	 */
	private static void insertarRelacioActivitatCategoria(String[] valors) {
		boolean trobat = false;
		Categoria c = null;
		Activitat a = null;
		
		Iterator<Activitat> itActivitats = activitats.iterator();
		while (itActivitats.hasNext() && !trobat) {
			a = itActivitats.next();
			if (a.getId() == Integer.parseInt(valors[0]))
				trobat = true;
		}
		trobat = false;
		Iterator<Categoria> itCategories = categories.iterator();
		while (itCategories.hasNext() && !trobat) {
			c = itCategories.next();
			if (c.getId() == Integer.parseInt(valors[1]))
				trobat = true;
		}
		if (a != null && c != null) {
			c.afegirActivitat(a);
			a.afegirCategoria(c);
			System.out.println(a + "\n" + c);
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
	
}
