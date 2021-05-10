import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import firebase4j.FirebaseException;
import firebaseALocal.ObtenirDadesFirebase;
import firebaseALocal.PujarPerMySQL;
import pujarFitxers.Constants;
import pujarFitxers.PujarPerFTP;
import pujarFitxers.TextAXML;

/**
 * Classe que mostra una interficie visual per interactuar amb els elements de l'aplicació.
 * @author Sergi Tor
 * @author Dana Gomez
 * @author Javier Garcia
 */
public class Aplicacio {
	
	static JLabel display;
	static JFrame principal;
	static Container contenidor;
	static JPanel esquerraTop;
	static JPanel dretaTop;
	static JLabel sortida;
	static JFileChooser chooser;
	static boolean fitxerSeleccionat;

	public static void main(String[] args) {
		do {
			JOptionPane.showMessageDialog(principal,"Siusplau, seleccioni el directori amb el qual treballar.","Seleccio",JOptionPane.INFORMATION_MESSAGE);
			demanar_directori();
			
			if (fitxerSeleccionat) {
				Constants.DIRECTORI = chooser.getSelectedFile().toString() + "\\";
				System.out.println(Constants.DIRECTORI);
				crear_panells();
				crear_controls_esquerra();
				crear_controls_dreta();
				
				principal.setVisible(true);
				
				principal.addWindowListener(new WindowAdapter() {
				@Override
			        public void windowClosing(WindowEvent e) {
						System.exit(0);
			        }
			    });
			} else {
				int dialogResult = JOptionPane.showConfirmDialog(principal,"Vol sortir del programa?","Avis",JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION){
					fitxerSeleccionat = true;
				}
			}
		} while (!fitxerSeleccionat);
		

	}
	
	private static void demanar_directori() {
		chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File(Constants.DIRECTORI));
	    chooser.setDialogTitle("Escollir directori de treball");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    
	    if (chooser.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION)
	    	fitxerSeleccionat = true;
	    else
	    	fitxerSeleccionat = false;
		
	}

	public static void crear_panells() {
		principal= new JFrame();
		principal.setSize(500, 400);
		principal.setResizable(false);
		
		contenidor=principal.getContentPane();
		contenidor.setBackground(new Color(130, 155, 179));
		contenidor.setLayout(null);
		
		esquerraTop=new JPanel();
		esquerraTop.setLayout(null);
		esquerraTop.setBounds(5, 5, 235, 355);
		esquerraTop.setBackground(new Color(176, 217, 255));
		
		dretaTop=new JPanel();
		dretaTop.setLayout(null);
		dretaTop.setBounds(245, 5, 235, 355);
		dretaTop.setBackground(new Color(145, 202, 255));
		
		contenidor.add(esquerraTop);
		contenidor.add(dretaTop);
	}
	
	public static void crear_controls_esquerra() {
		JLabel data=new JLabel("Passar fitxers de text a XML");
		JLabel data2 = new JLabel("i pujar-los per FTP.");
		data.setBounds(30, 100, 400, 20);
		data2.setBounds(30, 120, 400, 20);
		esquerraTop.add(data);
		esquerraTop.add(data2);
		
		JButton add = new JButton("Pujar dades");
		add.setBounds(30, 200, 150, 50);
		esquerraTop.add(add);
		 
		add.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					TextAXML.iniciar();
				} catch(IOException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error de fitxer",JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error amb la data",JOptionPane.ERROR_MESSAGE);
				}
				try {
					PujarPerFTP.iniciar();
					JOptionPane.showMessageDialog(principal,"Dades pujades correctament al servidor FTP!\n("+Constants.SERVIDOR_FTP+")","Dades pujades",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error pujant per FTP",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public static void crear_controls_dreta() {
		JLabel data=new JLabel("Obtenir dades de Firebase");
		JLabel data2 = new JLabel("i actualitzar la BBDD local.");
		data.setBounds(30, 100, 400, 20);
		dretaTop.add(data);
		data2.setBounds(30, 120, 400, 20);
		dretaTop.add(data2);
		
		JButton add = new JButton("Actualitzar BBDD");
		add.setBounds(25, 200, 180, 50);
		dretaTop.add(add);
		 
		add.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObtenirDadesFirebase.iniciar();
				} catch (FirebaseException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error connectant a Firebase",JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error obtenint dades",JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error amb la data",JOptionPane.ERROR_MESSAGE);
				} 
				try {
					String aux = JOptionPane.showInputDialog(principal,"Port: 3306\nBBDD: projecte_escriptori\nUsuari: root\nIntrodueixi la contrasenya: ");
					if ((aux != null) && (aux.length() > 0)) {
						PujarPerMySQL.iniciar(ObtenirDadesFirebase.getActivitats(), ObtenirDadesFirebase.getReserves(), aux);
						JOptionPane.showMessageDialog(principal,"BBDD local actualitzada correctament!","Dades pujades",JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(principal,"No es pot deixar el camp buit!","Contrasenya incorrecta! ",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					if (e.getErrorCode() == 1045)
						JOptionPane.showMessageDialog(principal,e.getMessage(),"Contrasenya incorrecta! ",JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(principal,e.getMessage(),"Error al pujar les dades a la BBDD ",JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(principal,e.getMessage(),"Error al pujar les dades a la BBDD ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
