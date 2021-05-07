<<<<<<< HEAD
package classes;
=======
package firebaseALocal;
>>>>>>> refs/remotes/origin/Dana

import java.util.Date;

public class ReservaMobil {
	
	private int id;
	private String codiTransaccio;
	private Date data;
	private int estat;
	private int idActivitat;
	private String usuari;
	
	public ReservaMobil(int id) {
		super();
		this.id = id;
		this.codiTransaccio = "";
		this.data = null;
		this.estat = 0;
		this.idActivitat = 0;
		this.usuari = "";
	}
	
	public ReservaMobil(int id, String codiTransaccio, Date data, int estat, int idActivitat, String usuari) {
		super();
		this.id = id;
		this.codiTransaccio = codiTransaccio;
		this.data = data;
		this.estat = estat;
		this.idActivitat = idActivitat;
		this.usuari = usuari;
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


	public String getUsuari() {
		return usuari;
	}


	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	@Override
	public String toString() {
		return "ReservaMobil [id=" + id + ", codiTransaccio=" + codiTransaccio + ", data=" + data + ", estat=" + estat
				+ ", idActivitat=" + idActivitat + ", usuari=" + usuari + "]";
	}
	
}
