package classes;

public class Ubicacio {
	private int id;
	private String nom;
	
	public Ubicacio() {}

	public Ubicacio(int id, String nom) {
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
		return "Ubicacio [id=" + id + ", nom=" + nom + "]";
	}
	
}
