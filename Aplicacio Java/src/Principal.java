
import firebaseALocal.ObtenirDadesFirebase;
import firebaseALocal.PujarPerMySQL;
import pujarFitxers.PujarPerFTP;
import pujarFitxers.TextAXML;

public class Principal {

	public static void main(String[] args) {
		System.out.println("===========================================");
		//TextAXML.iniciar();
		System.out.println("===========================================");
		//PujarPerFTP.iniciar();
		System.out.println("===========================================");
		ObtenirDadesFirebase.iniciar();
		System.out.println("===========================================");
		PujarPerMySQL.iniciar(ObtenirDadesFirebase.getActivitats(), ObtenirDadesFirebase.getReserves());
		System.out.println("===========================================");
	}

}
