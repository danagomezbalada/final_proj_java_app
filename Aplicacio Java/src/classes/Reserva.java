package classes;

import java.util.Date;

public class Reserva {
	
	private int id;
	private String email;
	private Activitat activitat;
	private Date data;
	private String codiTransaccio;
	private int estat;
	
	public Reserva() {}

	public Reserva(int id, String email, Activitat activitat, Date data, String codiTransaccio, int estat) {
		super();
		this.id = id;
		this.email = email;
		this.activitat = activitat;
		this.data = data;
		this.codiTransaccio = codiTransaccio;
		this.estat = estat;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Activitat getActivitat() {
		return activitat;
	}

	public Date getData() {
		return data;
	}

	public String getCodiTransaccio() {
		return codiTransaccio;
	}

	public int getEstat() {
		return estat;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", email=" + email + ", activitat=" + activitat + ", data=" + data
				+ ", codiTransaccio=" + codiTransaccio + ", estat=" + estat + "]";
	}

}
