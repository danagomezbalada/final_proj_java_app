package firebaseALocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import classes.ActivitatMobil;
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
	
	public static void iniciar(List<ActivitatMobil> activitats, List<ReservaMobil> reserves) {
        try {
        	iniciarConnexio();
        	
			actualitzarActivitats(activitats);
			
			insertarReserves(reserves);
	        
			tancarConnexio();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metode que inicia una connexio amb la BBDD.
	 */
	public static void iniciarConnexio() {
		try {
        	System.out.println("\nConnectant a la BBDD...\n");
            System.out.println("Port: 3306\nBBDD: projecte_escriptori\nUsuari: root\nIntrodueixi la contrasenya: ");
            Scanner s = new Scanner(System.in);
            String password = s.next();
			Class.forName(Constants.MYSQL_DRIVER);
			connexio = DriverManager.getConnection(Constants.MYSQL_URL, Constants.MYSQL_USUARI, password);
	        System.out.println("\nConnexio exitosa!\n");
	        s.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1045)
				System.err.println("Contrasenya incorrecta!");
			e.printStackTrace();
		}
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
	 * Aquesta funcio actualitza les places actuals de les activitats rebudes de firebase.
	 * @param activitats
	 * @throws SQLException
	 */
	private static void actualitzarActivitats(List<ActivitatMobil> activitats) throws SQLException {
		for (ActivitatMobil a : activitats) {
			String sql="UPDATE activitat SET places_actuals="+a.getPlacesActuals()+" WHERE id="+a.getId()+" ";
			Statement statement = connexio.createStatement();
			statement.execute(sql);
			statement.close();
			System.out.println("Activitat " + a.getId() + " actualitzada correctament.");
		}
		
	}
	
	/**
	 * Aquesta funcio afegeix les noves reserves rebudes a la BBDD.
	 * @param reserves
	 * @throws SQLException
	 */
	private static void insertarReserves(List<ReservaMobil> reserves) throws SQLException {
		for (ReservaMobil r : reserves) {
			/*tring sql="INSERT INTO reserves (data,poblacio,temperatura) VALUES ('"+data.getText().toString()+"','"+municipi.getText().toString()+"',"+temperatura.getText()+")";
			Statement statement = connexio.createStatement();
			statement.execute(sql);
			statement.close(); as
			System.out.println("INSERT realitzat correctament.");*/
		}
	}

}
