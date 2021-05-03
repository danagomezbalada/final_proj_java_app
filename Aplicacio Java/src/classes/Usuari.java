package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuari {
	
	private int id;
	private String email;
	private List<Reserva> reserves;
	
	public Usuari() {}

	public Usuari(int id, String email) {
		super();
		this.id = id;
		this.email = email;
		this.reserves = new ArrayList<Reserva>();
	}
	
	public void afegirReserva(Reserva r) {
		this.reserves.add(r);
	}
	
	public String mostrarReserves() {
		Iterator<Reserva> it = reserves.iterator();
		String s = "";
		while (it.hasNext()) {
			Reserva a = it.next();
			s += "\nId: " + a.getId() + " Codi: " + a.getCodiTransaccio() + " Estat: " + a.getEstat();
		}
		return s;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public List<Reserva> getReserves() {
		return reserves;
	}

	@Override
	public String toString() {
		return "Usuari [id=" + id + ", email=" + email + "\nReserves:" + mostrarReserves() + "]";
	}

}
