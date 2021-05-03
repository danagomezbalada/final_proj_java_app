package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Activitat {
	private int id;
	private Esdeveniment esdeveniment;
	private Date data;
	private String titol;
	private String descripcio;
	private double preu;
	private int placesTotals;
	private int placesActuals;
	private Date dataIniciMostra;
	private Date dataFiMostra;
	private Ubicacio ubicacio;
	private Departament departament;
	private List<Categoria> categories;
	private List<Ponent> ponents;
	
	public Activitat() {}

	public Activitat(int id, Esdeveniment esdeveniment, Date data, String titol, String descripcio, double preu,
			int placesTotals, int placesActuals, Date dataIniciMostra, Date dataFiMostra, Ubicacio ubicacio,
			Departament departament) {
		super();
		this.id = id;
		this.esdeveniment = esdeveniment;
		this.data = data;
		this.titol = titol;
		this.descripcio = descripcio;
		this.preu = preu;
		this.placesTotals = placesTotals;
		this.placesActuals = placesActuals;
		this.dataIniciMostra = dataIniciMostra;
		this.dataFiMostra = dataFiMostra;
		this.ubicacio = ubicacio;
		this.departament = departament;
		this.categories = new ArrayList<Categoria>();
		this.ponents = new ArrayList<Ponent>();
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
	
	public void afegirPonent(Ponent p) {
		this.ponents.add(p);
	}
	
	public String mostrarPonents() {
		Iterator<Ponent> it = ponents.iterator();
		String s = "";
		while (it.hasNext()) {
			Ponent p = it.next();
			s += "\nId: " + p.getId() + " Nom: " + p.getNom();
		}
		return s;
	}

	public int getId() {
		return id;
	}

	public Esdeveniment getEsdeveniment() {
		return esdeveniment;
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

	public Ubicacio getUbicacio() {
		return ubicacio;
	}

	public Departament getDepartament() {
		return departament;
	}

	public List<Categoria> getCategories() {
		return categories;
	}

	public List<Ponent> getPonents() {
		return ponents;
	}

	@Override
	public String toString() {
		return "Activitat [id=" + id + ", esdeveniment=" + esdeveniment + ", data=" + data + ", titol=" + titol
				+ ", descripcio=" + descripcio + ", preu=" + preu + ", placesTotals=" + placesTotals
				+ ", placesActuals=" + placesActuals + ", dataIniciMostra=" + dataIniciMostra + ", dataFiMostra="
				+ dataFiMostra + ", ubicacio=" + ubicacio + ", departament=" + departament + "\nCategories:"
				+ mostrarCategories() + "\nPonents:" + mostrarPonents() + "]";
	}
}
