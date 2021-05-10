package classes;

public class Departament {
	private int id;
	private String nom;
	
	public Departament() {}

	public Departament(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return "Departament [id=" + id + ", nom=" + nom + "]";
	}
	
}
