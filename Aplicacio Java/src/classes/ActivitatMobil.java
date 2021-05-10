package classes;

public class ActivitatMobil {
	
	private int id;
	private int placesActuals;
	
	public ActivitatMobil(int id) {
		super();
		this.id = id;
		this.placesActuals = 0;
	}
	
	public ActivitatMobil(int id, int placesActuals) {
		super();
		this.id = id;
		this.placesActuals = placesActuals;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlacesActuals() {
		return placesActuals;
	}
	public void setPlacesActuals(int placesActuals) {
		this.placesActuals = placesActuals;
	}
	
	@Override
	public String toString() {
		return "ActivitatMobil [id=" + id + ", placesActuals=" + placesActuals + "]";
	}
}
