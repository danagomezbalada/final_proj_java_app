package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ponent {
	
	private int id;
	private String nom;
	private String cognoms;
	private int telefon;
	private String email;
	private List<Activitat> activitats;
	
	public Ponent() {}

	public Ponent(int id, String nom, String cognoms, int telefon, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.cognoms = cognoms;
		this.telefon = telefon;
		this.email = email;
		this.activitats = new ArrayList<Activitat>();
	}
	
	public void afegirActivitat(Activitat a) {
		this.activitats.add(a);
	}
	
	public String mostrarActivitats() {
		Iterator<Activitat> it = activitats.iterator();
		String s = "";
		while (it.hasNext()) {
			Activitat a = it.next();
			s += "\nId: " + a.getId() + " Titol: " + a.getTitol();
		}
		return s;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public int getTelefon() {
		return telefon;
	}

	public String getEmail() {
		return email;
	}

	public List<Activitat> getActivitats() {
		return activitats;
	}

	@Override
	public String toString() {
		return "Ponent [id=" + id + ", nom=" + nom + ", cognoms=" + cognoms + ", telefon=" + telefon + ", email="
				+ email + "\nActivitats: " + mostrarActivitats() + "]";
	}

}
