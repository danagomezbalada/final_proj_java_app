
import firebaseALocal.ObtenirDadesFirebase;
import firebaseALocal.PujarPerMySQL;

public class Principal {

	public static void main(String[] args) {
		
		ObtenirDadesFirebase.iniciar();
		
		PujarPerMySQL.iniciar(ObtenirDadesFirebase.getActivitats(), ObtenirDadesFirebase.getReserves());
		
	}

}
