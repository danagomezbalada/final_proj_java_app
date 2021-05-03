package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Activitat {
	private int id;
	private int idEsdeveniment;
	private Date data;
	private String titol;
	private String descripcio;
	private double preu;
	private int placesTotals;
	private int placesActuals;
	private Date dataIniciMostra;
	private Date dataFiMostra;
	private int idUbicacio;
	private int idDepartament;
	private List<Categoria> categories;
	
	public Activitat() {}

	public Activitat(int id, int idEsdeveniment, Date data, String titol, String descripcio, double preu,
			int placesTotals, int placesActuals, Date dataIniciMostra, Date dataFiMostra, int idUbicacio,
			int idDepartament) {
		super();
		this.id = id;
		this.idEsdeveniment = idEsdeveniment;
		this.data = data;
		this.titol = titol;
		this.descripcio = descripcio;
		this.preu = preu;
		this.placesTotals = placesTotals;
		this.placesActuals = placesActuals;
		this.dataIniciMostra = dataIniciMostra;
		this.dataFiMostra = dataFiMostra;
		this.idUbicacio = idUbicacio;
		this.idDepartament = idDepartament;
		this.categories = new ArrayList<Categoria>();
	}
	
	public void afegirCategoria(Categoria c) {
		this.categories.add(c);
	}
	
	public String mostrarCategories() {
		Iterator<Categoria> it = categories.iterator();
		String s = "";
		while (it.hasNext()) {
			Categoria c = it.next();
			s += "\nId: " + c.getId() + " Nom: " + c.getNom();
		}
		return s;
	}

	public int getId() {
		return id;
	}

	public int getIdEsdeveniment() {
		return idEsdeveniment;
	}

	public Date getData() {
		return data;
	}

	public String getTitol() {
		return titol;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public double getPreu() {
		return preu;
	}

	public int getPlacesTotals() {
		return placesTotals;
	}

	public int getPlacesActuals() {
		return placesActuals;
	}

	public Date getDataIniciMostra() {
		return dataIniciMostra;
	}

	public Date getDataFiMostra() {
		return dataFiMostra;
	}

	public int getIdUbicacio() {
		return idUbicacio;
	}

	public int getIdDepartament() {
		return idDepartament;
	}

	public List<Categoria> getCategories() {
		return categories;
	}

	@Override
	public String toString() {
		return "Activitat [id=" + id + ", idEsdeveniment=" + idEsdeveniment + ", data=" + data + ", titol=" + titol
				+ ", descripcio=" + descripcio + ", preu=" + preu + ", placesTotals=" + placesTotals
				+ ", placesActuals=" + placesActuals + ", dataIniciMostra=" + dataIniciMostra + ", dataFiMostra="
				+ dataFiMostra + ", idUbicacio=" + idUbicacio + ", idDepartament=" + idDepartament + "\nCategories:"
				+ mostrarCategories() + "]";
	}
}
