package classes;

public class Esdeveniment {
	
	private int id;
	private int any;
	private String nom;
	private String descripcio;
	private boolean actiu;
	
	public Esdeveniment() {}

	public Esdeveniment(int id, int any, String nom, String descripcio, boolean actiu) {
		super();
		this.id = id;
		this.any = any;
		this.nom = nom;
		this.descripcio = descripcio;
		this.actiu = actiu;
	}

	public int getId() {
		return id;
	}

	public int getAny() {
		return any;
	}

	public String getNom() {
		return nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public boolean isActiu() {
		return actiu;
	}

	@Override
	public String toString() {
		return "Esdeveniment [id=" + id + ", any=" + any + ", nom=" + nom + ", descripcio=" + descripcio + ", actiu="
				+ actiu + "]";
	}

}
