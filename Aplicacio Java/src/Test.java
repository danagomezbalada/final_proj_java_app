import java.io.IOException;
import java.text.ParseException;

import pujarFitxers.Constants;
import pujarFitxers.TextAXML;

public class Test {

	public static void main(String[] args) {
		try {
			Constants.DIRECTORI = "D:\\Documents\\DAM 2\\M13 Projecte\\Fitxers\\";
			TextAXML.iniciar();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

}
