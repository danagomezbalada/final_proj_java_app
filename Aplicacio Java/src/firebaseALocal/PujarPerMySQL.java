package firebaseALocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import classes.ReservaMobil;
import pujarFitxers.Constants;

/**
 * Classe encarregada de pujar les dades obtingudes de Firebase a la BBDD local MySQL.
 * @author Sergi Tor
 * @author Dana Gomez
 * @author Javier Garcia
 */
public class PujarPerMySQL {
	static Connection connexio;
	
	public static void iniciar(List<ReservaMobil> reserves, String password) throws ClassNotFoundException, SQLException {

		iniciarConnexio(password);
			
		insertarReserves(reserves);
	        
		tancarConnexio();
	}

	/**
	 * Metode que inicia una connexio amb la BBDD.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void iniciarConnexio(String password) throws ClassNotFoundException, SQLException {
		System.out.println("\nConnectant a la BBDD...\n");
		Class.forName(Constants.MYSQL_DRIVER);
		connexio = DriverManager.getConnection(Constants.MYSQL_URL, Constants.MYSQL_USUARI, password);
        System.out.println("\nConnexio exitosa!\n");
	}
	
	/**
	 * Metode que tanca la connexio a la BBDD.
	 * @throws SQLException 
	 */
	public static void tancarConnexio() throws SQLException {
		if (connexio != null && !connexio.isClosed()) {
			connexio.close();
			System.out.println("\nConnexio a la BBDD tancada.");
		}
	}
	
	/**
	 * Aquesta funcio afegeix les noves reserves rebudes a la BBDD si no existeixen.
	 * @param reserves
	 * @throws SQLException
	 */
	private static void insertarReserves(List<ReservaMobil> reserves) throws SQLException {
		for (ReservaMobil r : reserves) {
			PreparedStatement stmt = connexio.prepareStatement("SELECT * FROM reserva WHERE id = ?");
			    stmt.setInt(1, r.getId());
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst() ) {    
				String s = new SimpleDateFormat("yyyy-MM-dd").format(r.getData());
				String sql="INSERT INTO reserva (id,email,id_activitat,data,codi_transaccio,estat) VALUES ("
						+r.getId()+",'"+r.getEmail()+"',"+r.getIdActivitat()+",'"
						+s+"','"+r.getCodiTransaccio()+"',"+r.getEstat()+")";
				Statement statement = connexio.createStatement();
				statement.execute(sql);
				statement.close();
				System.out.println("Reserva " + r.getId() + " insertada correctament.");
			}
			stmt.close();
			rs.close();
		}
	}

}
