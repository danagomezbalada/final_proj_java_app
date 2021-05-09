package pujarFitxers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import classes.Activitat;
import classes.Categoria;
import classes.Departament;
import classes.Esdeveniment;
import classes.Ponent;
import classes.Reserva;
import classes.Ubicacio;

/**
 * Classe encarregada de la conversio del fitxer de text a XML.
 * @author Sergi Tor
 * @author Dana Gomez
 * @author Javier Garcia
 */
public class TextAXML {
	
	static String tipus = "";
	static List<Categoria> categories = new ArrayList<Categoria>();
	static List<Activitat> activitats = new ArrayList<Activitat>();
	static List<Ubicacio> ubicacions = new ArrayList<Ubicacio>();
	static List<Departament> departaments = new ArrayList<Departament>();
	static List<Esdeveniment> esdeveniments = new ArrayList<Esdeveniment>();
	static List<Ponent> ponents = new ArrayList<Ponent>();
	static List<Reserva> reserves = new ArrayList<Reserva>();
	
	public static void iniciar() {
		llegirFitxerICrearObjectes();
		
		crearXML();
		
		actualitzarVersio();
	}	
	
	/**
	 * Aquest metode s'encarrega de llegir el fitxer que conte les dades de cada objecte, 
	 * i crear els objectes corresponents, afegint-los a les llistes de cada tipus d'objecte.
	 */
	private static void llegirFitxerICrearObjectes() {
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
	 * Metode encarregat de crear varis fitxers XML per activitats, categories, esdeveniments i reserves.
	 */
	private static void crearXML() {
		try {
			//-------------Creacio activitats---------------------
			File xml = new File (Constants.DIRECTORI+Constants.XML_ACTIVITATS);
		    PrintStream escriptor = new PrintStream(xml);
			String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<activitats>";
			Iterator<Activitat> it = activitats.iterator();
			while (it.hasNext()) {
				Activitat a = it.next();
				aux += "\n\t<activitat>";
				aux += "\n\t\t<id>" + a.getId() + "</id>";
				aux += "\n\t\t<idEsdeveniment>" + a.getEsdeveniment().getId() + "</idEsdeveniment>";
				aux += "\n\t\t<titol>" + a.getTitol() + "</titol>";
				DateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
				String data = formatData.format(a.getData());
				aux += "\n\t\t<data>" + data + "</data>";
				aux += "\n\t\t<ubicacio>" + a.getUbicacio().getNom() + "</ubicacio>";
				aux += "\n\t\t<descripcio>" + a.getDescripcio() + "</descripcio>";
				aux += "\n\t\t<departament>" + a.getDepartament().getNom() + "</departament>";
				aux += "\n\t\t<ponents>";
				int i=0;
				for (Ponent p : a.getPonents()) {
					if (i>0)
						aux += ", ";
					aux += p.getNom() + " " + p.getCognoms();
					i++;
				}
					
				aux += "</ponents>";
				aux += "\n\t\t<preu>" + a.getPreu() + "</preu>";
				aux += "\n\t\t<placesTotals>" + a.getPlacesTotals() + "</placesTotals>";
				aux += "\n\t\t<placesActuals>" + a.getPlacesActuals() + "</placesActuals>";
				data = formatData.format(a.getDataIniciMostra());
				aux += "\n\t\t<dataIniciMostra>" + data + "</dataIniciMostra>";
				data = formatData.format(a.getDataFiMostra());
				aux += "\n\t\t<dataFiMostra>" + data + "</dataFiMostra>";
				aux += "\n\t</activitat>";
			}
			aux += "\n</activitats>";
			escriptor.print(aux);
			escriptor.close();
			System.out.println("Fitxer " + Constants.XML_ACTIVITATS + " creat correctament.");
			
			//-------------Creacio categories---------------------
			xml = new File(Constants.DIRECTORI+Constants.XML_CATEGORIES);
			escriptor = new PrintStream(xml);
			aux = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<categories>";
			Iterator<Categoria> it1 = categories.iterator();
			while (it1.hasNext()) {
				Categoria c = it1.next();
				aux += "\n\t<categoria>";
				aux += "\n\t\t<id>" + c.getId() + "</id>";
				aux += "\n\t\t<nom>" + c.getNom() + "</nom>";
				aux += "\n\t</categoria>";
			}
			aux += "\n</categories>";
			escriptor.print(aux);
			escriptor.close();
			System.out.println("Fitxer " + Constants.XML_CATEGORIES + " creat correctament.");
			
			//-------------Creacio esdeveniments---------------------
			xml = new File(Constants.DIRECTORI+Constants.XML_ESDEVENIMENTS);
			escriptor = new PrintStream(xml);
			aux = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<esdeveniments>";
			Iterator<Esdeveniment> it2 = esdeveniments.iterator();
			while (it2.hasNext()) {
				Esdeveniment e = it2.next();
				aux += "\n\t<esdeveniment>";
				aux += "\n\t\t<id>" + e.getId() + "</id>";
				aux += "\n\t\t<any>" + e.getAny() + "</any>";
				aux += "\n\t\t<nom>" + e.getNom() + "</nom>";
				aux += "\n\t\t<descripcio>" + e.getDescripcio() + "</descripcio>";
				aux += "\n\t\t<actiu>" + e.isActiu() + "</actiu>";
				aux += "\n\t</esdeveniment>";
			}
			aux += "\n</esdeveniments>";
			escriptor.print(aux);
			escriptor.close();
			System.out.println("Fitxer " + Constants.XML_ESDEVENIMENTS + " creat correctament.");
			
			//-------------Creacio reserves---------------------
			xml = new File(Constants.DIRECTORI+Constants.XML_RESERVES);
			escriptor = new PrintStream(xml);
			aux = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<reserves>";
			Iterator<Reserva> it3 = reserves.iterator();
			while (it3.hasNext()) {
				Reserva r = it3.next();
				aux += "\n\t<reserva>";
				aux += "\n\t\t<id>" + r.getId() + "</id>";
				aux += "\n\t\t<email>" + r.getEmail() + "</email>";
				aux += "\n\t\t<idActivitat>" + r.getActivitat().getId() + "</idActivitat>";
				DateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
				String data = formatData.format(r.getData());
				aux += "\n\t\t<data>" + data + "</data>";
				aux += "\n\t\t<codiTransaccio>" + r.getCodiTransaccio() + "</codiTransaccio>";
				aux += "\n\t\t<estat>" + r.getEstat() + "</estat>";
				aux += "\n\t</reserva>";
			}
			aux += "\n</reserves>";
			escriptor.print(aux);
			escriptor.close();
			System.out.println("Fitxer " + Constants.XML_RESERVES + " creat correctament.");
			
			//-------------Creacio activitat_categoria---------------------
			xml = new File(Constants.DIRECTORI+Constants.XML_ACTIVITAT_CATEGORIA);
			escriptor = new PrintStream(xml);
			aux = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<activitat_categories>";
			Iterator<Activitat> it4 = activitats.iterator();
			while (it4.hasNext()) {
				Activitat a = it4.next();
				for (Categoria c : a.getCategories()) {
					aux += "\n\t<activitat_categoria>";
					aux += "\n\t\t<id_activitat>" + a.getId() + "</id_activitat>";
					aux += "\n\t\t<id_categoria>" + c.getId() + "</id_categoria>";
					aux += "\n\t</activitat_categoria>";
				}
			}
			aux += "\n</activitat_categories>";
			escriptor.print(aux);
			escriptor.close();
			System.out.println("Fitxer " + Constants.XML_ACTIVITAT_CATEGORIA + " creat correctament.");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metode que actualitza el fitxer de versio sumant 0.3 al valor d'aquest.
	 */
	public static void actualitzarVersio() {
		String fitxer = Constants.DIRECTORI+Constants.FITXER_VERSIO;
		String s = "0";
		double numVersio = 0;
		try (BufferedReader lector = new BufferedReader(new FileReader(fitxer));) {
			while ((s=lector.readLine()) != null)
				numVersio = Double.valueOf(s);
		} catch (IOException e) {
			System.err.println("Error al llegir el fitxer de versio.");
			e.printStackTrace();
		}
		numVersio += 0.3;
		s = Double.toString(numVersio);
		try (BufferedWriter escriptor = new BufferedWriter(new FileWriter(fitxer));){
			escriptor.write(s);
			System.out.println("Fitxer " + Constants.FITXER_VERSIO + " actualitzat correctament! (Versio " + s + ")");
		} catch (IOException e) {
			System.err.println("Error al actualitzar el fitxer de versio.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Segons el tipus d'objecte a crear, crida el metode corresponent.
	 * @param valors - Les dades de l'objecte a ser creat.
	 */
	private static void afegirValors(String [] valors) {
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
			case "R":
				insertarReserva(valors);
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
	
	/**
	 * Crea un objecte ubicacio amb els valors rebuts.
	 * @param valors
	 */
	private static void insertarUbicacio(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		String nom = valors[1];
		
		Ubicacio ubicacio = new Ubicacio(id, nom);
		ubicacions.add(ubicacio);
	}
	
	/**
	 * Crea un objecte departament amb els valors rebuts.
	 * @param valors
	 */
	private static void insertarDepartament(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		String nom = valors[1];
		
		Departament departament = new Departament(id, nom);
		departaments.add(departament);
	}
	
	/**
	 * Crea un objecte esdeveniment amb els valors rebuts.
	 * @param valors
	 */
	private static void insertarEsdeveniment(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		int any = Integer.parseInt(valors[1]);
		String nom = valors[2];
		String descripcio = valors[3];
		boolean actiu = Boolean.parseBoolean(valors[4].toLowerCase());
		
		Esdeveniment esdeveniment = new Esdeveniment(id, any, nom, descripcio, actiu);
		esdeveniments.add(esdeveniment);
	}
	
	/**
	 * Crea un objecte ponent amb els valors rebuts.
	 * @param valors
	 */
	private static void insertarPonent(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		String nom = valors[1];
		String cognoms = valors[2];
		int telefon = Integer.parseInt(valors[3]);
		String email = valors[4];
		
		Ponent ponent = new Ponent(id, nom, cognoms, telefon, email);
		ponents.add(ponent);
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
		
		//Afegir esdeveniment
		Esdeveniment esdeveniment = null;
		boolean trobat = false;
		Iterator<Esdeveniment> itEsdeveniments = esdeveniments.iterator();
		while (itEsdeveniments.hasNext() && !trobat) {
			esdeveniment = itEsdeveniments.next();
			if (esdeveniment.getId() == Integer.parseInt(valors[7]))
				trobat = true;
		}
		
		Date dataIniciMostra = stringADate(valors[8]);
		Date dataFiMostra = stringADate(valors[9]);
		
		//Afegir ubicacio
		Ubicacio ubicacio = null;
		trobat = false;
		Iterator<Ubicacio> itUbicacions = ubicacions.iterator();
		while (itUbicacions.hasNext() && !trobat) {
			ubicacio = itUbicacions.next();
			if (ubicacio.getId() == Integer.parseInt(valors[10]))
				trobat = true;
		}
		
		//Afegir departament
		Departament departament = null;
		trobat = false;
		Iterator<Departament> itDepartaments = departaments.iterator();
		while (itDepartaments.hasNext() && !trobat) {
			departament = itDepartaments.next();
			if (departament.getId() == Integer.parseInt(valors[11]))
				trobat = true;
		}
		
		Activitat activitat = new Activitat(id, esdeveniment, data, titol, descripcio, preu, placesTotals, placesActuals, dataIniciMostra, 
				dataFiMostra, ubicacio, departament);
		activitats.add(activitat);
		
	}
	
	/**
	 * Afegeix l'activitat amb l'id rebut al ponent amb l'id rebut, i viceversa.
	 * @param valors
	 */
	private static void insertarRelacioActivitatPonent(String[] valors) {
		boolean trobat = false;
		Ponent p = null;
		Activitat a = null;
		
		Iterator<Activitat> itActivitats = activitats.iterator();
		while (itActivitats.hasNext() && !trobat) {
			a = itActivitats.next();
			if (a.getId() == Integer.parseInt(valors[0]))
				trobat = true;
		}
		trobat = false;
		Iterator<Ponent> itPonents = ponents.iterator();
		while (itPonents.hasNext() && !trobat) {
			p = itPonents.next();
			if (p.getId() == Integer.parseInt(valors[1]))
				trobat = true;
		}
		if (a != null && p != null) {
			p.afegirActivitat(a);
			a.afegirPonent(p);
		}
		
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
		}
	}
	
	/**
	 * Crea un objecte reserva amb els valors rebuts, i afegeix la reserva a la llista de reserves de l'usuari(constructor).
	 * @param valors
	 */
	private static void insertarReserva(String[] valors) {
		int id = Integer.parseInt(valors[0]);
		String email = valors[1];
		
		//Afegir activitat
		Activitat activitat = null;
		boolean trobat = false;
		Iterator<Activitat> itActivitats = activitats.iterator();
		while (itActivitats.hasNext() && !trobat) {
			activitat = itActivitats.next();
			if (activitat.getId() == Integer.parseInt(valors[2]))
				trobat = true;
		}
		
		Date data = stringADate(valors[3]);
		String codiTransaccio = valors[4];
		int estat = Integer.parseInt(valors[5]);
		
		Reserva reserva = new Reserva(id, email, activitat, data, codiTransaccio, estat);
		reserves.add(reserva);
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
