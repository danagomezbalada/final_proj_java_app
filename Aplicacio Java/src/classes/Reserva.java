package classes;

import java.util.Date;

public class Reserva {
	
	private int id;
	private Usuari usuari;
	private Activitat activitat;
	private Date data;
	private String codiTransaccio;
	private int estat;
	
	public Reserva() {}

	public Reserva(int id, Usuari usuari, Activitat activitat, Date data, String codiTransaccio, int estat) {
		super();
		this.id = id;
		this.usuari = usuari;
		this.activitat = activitat;
		this.data = data;
		this.codiTransaccio = codiTransaccio;
		this.estat = estat;
		usuari.afegirReserva(this);
	}

	public int getId() {
		return id;
	}

	public Usuari getUsuari() {
		return usuari;
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
		return "Reserva [id=" + id + ", usuari=" + usuari + ", activitat=" + activitat + ", data=" + data
				+ ", codiTransaccio=" + codiTransaccio + ", estat=" + estat + "]";
	}

}
