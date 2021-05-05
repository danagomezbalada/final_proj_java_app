import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Classe encarregada de connectar al servidor per FTP i pujar els fitxers.
 * @author Sergi Tor
 * @author Dana Gomez
 * @author Javier Garcia
 */
public class PujarPerFTP {
	
	static String server = Constants.SERVIDOR_FTP;
    static int port = Constants.PORT_FTP;
    static String user = Constants.USUARI_FTP;
    static String pass = Constants.CONTRASENYA_FTP;
	
    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
        	//Connecta al servidor FTP
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Connexio correcta al servidor " + server + ".");
 
            //Puja el fitxer de versio
            File fitxerVersioLocal = new File(Constants.DIRECTORI+Constants.FITXER_VERSIO);
            String fitxerVersioRemot = Constants.FITXER_VERSIO;
            InputStream inputStream = new FileInputStream(fitxerVersioLocal);
            boolean correcte = ftpClient.storeFile(fitxerVersioRemot, inputStream);
            if (correcte) 
                System.out.println("Fitxer " + Constants.FITXER_VERSIO + " pujat correctament.");
            inputStream.close();
            
            //Puja els XML de dades
           //Activitats
            File fitxerActivitatsLocal = new File(Constants.DIRECTORI+Constants.XML_ACTIVITATS);
            String fitxerActivitatsRemot = Constants.XML_ACTIVITATS;
            inputStream = new FileInputStream(fitxerActivitatsLocal);
            correcte = ftpClient.storeFile(fitxerActivitatsRemot, inputStream);
            if (correcte) 
                System.out.println("Fitxer " + Constants.XML_ACTIVITATS + " pujat correctament.");
            inputStream.close();
           //Categories
            File fitxerCategoriesLocal = new File(Constants.DIRECTORI+Constants.XML_CATEGORIES);
            String fitxerCategoriesRemot = Constants.XML_CATEGORIES;
            inputStream = new FileInputStream(fitxerCategoriesLocal);
            correcte = ftpClient.storeFile(fitxerCategoriesRemot, inputStream);
            if (correcte) 
                System.out.println("Fitxer " + Constants.XML_CATEGORIES + " pujat correctament.");
            inputStream.close();
          //Esdeveniments
            File fitxerEsdevenimentsLocal = new File(Constants.DIRECTORI+Constants.XML_ESDEVENIMENTS);
            String fitxerEsdevenimentsRemot = Constants.XML_ESDEVENIMENTS;
            inputStream = new FileInputStream(fitxerEsdevenimentsLocal);
            correcte = ftpClient.storeFile(fitxerEsdevenimentsRemot, inputStream);
            if (correcte) 
                System.out.println("Fitxer " + Constants.XML_ESDEVENIMENTS + " pujat correctament.");
            inputStream.close();
            
           //Reserves
            File fitxerReservesLocal = new File(Constants.DIRECTORI+Constants.XML_RESERVES);
            String fitxerReservesRemot = Constants.XML_RESERVES;
            inputStream = new FileInputStream(fitxerReservesLocal);
            correcte = ftpClient.storeFile(fitxerReservesRemot, inputStream);
            if (correcte) 
                System.out.println("Fitxer " + Constants.XML_RESERVES + " pujat correctament.");
            inputStream.close();
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
