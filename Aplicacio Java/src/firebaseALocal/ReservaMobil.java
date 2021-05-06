package firebaseALocal;

import java.util.Date;

public class ReservaMobil {
	
	private int id;
	private String codiTransaccio;
	private Date data;
	private int estat;
	private int idActivitat;
	private int idUsuari;
	
	public ReservaMobil(int id, String codiTransaccio, Date data, int estat, int idActivitat, int idUsuari) {
		super();
		this.id = id;
		this.codiTransaccio = codiTransaccio;
		this.data = data;
		this.estat = estat;
		this.idActivitat = idActivitat;
		this.idUsuari = idUsuari;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCodiTransaccio() {
		return codiTransaccio;
	}


	public void setCodiTransaccio(String codiTransaccio) {
		this.codiTransaccio = codiTransaccio;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public int getEstat() {
		return estat;
	}


	public void setEstat(int estat) {
		this.estat = estat;
	}


	public int getIdActivitat() {
		return idActivitat;
	}


	public void setIdActivitat(int idActivitat) {
		this.idActivitat = idActivitat;
	}


	public int getIdUsuari() {
		return idUsuari;
	}


	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	@Override
	public String toString() {
		return "ReservaMobil [id=" + id + ", codiTransaccio=" + codiTransaccio + ", data=" + data + ", estat=" + estat
				+ ", idActivitat=" + idActivitat + ", idUsuari=" + idUsuari + "]";
	}
	
}
