import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

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
            ftpClient.connect(server, port);
            respostaServidor(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return;
            }
            boolean success = ftpClient.login(user, pass);
            respostaServidor(ftpClient);
            if (!success) {
                System.out.println("Could not login to the server");
                return;
            } else {
                System.out.println("LOGGED IN SERVER");
            }
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
        }
    }
    
    private static void respostaServidor(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

}
