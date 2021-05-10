package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Categoria {
	private int id;
	private String nom;
	private List<Activitat> activitats;
	
	public Categoria() {}

	public Categoria(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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

	public List<Activitat> getActivitats() {
		return activitats;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nom=" + nom + "\nActivitats:" + mostrarActivitats() + "]";
	}

}
