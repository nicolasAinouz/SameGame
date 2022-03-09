import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * La classe <code>Fenetre</code> Initialise la première fenêtre du jeux et contient les listener des boutons du menu
 */

public class Fenetre extends JFrame implements ActionListener {
	
	/**
	* Appel le constructeur Menu qui est un Panel.
	*/
	private Menu menu = new Menu();
	
	/**
	* Chaîne de caractère qui permet de choisir le fichier.
	*/
	private String fichier_choix;


/**
 * Constructeur de la classe Fenetre qui affiche le menu.
 */
	public Fenetre() {
		super();
		Image icone = Toolkit.getDefaultToolkit().getImage("photo/poke.png"); 
		setIconImage(icone);
		this.setTitle("SameGame");
		this.setSize(1200,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		menu.jouer.addActionListener(this);
		menu.importer.addActionListener(this);
		this.getContentPane().add(this.menu);
	}

/**
 * Listener des boutons de menu.
 */
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(menu.jouer)) {
			System.out.println("jouer");
			Grille grille = new Grille(10,15);
			setVisible(false);			
		}
		
		if(event.getSource().equals(menu.importer)) {
			System.out.println("import");
			JFileChooser choix = new JFileChooser();
			choix.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			if (choix.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
				File file = choix.getSelectedFile();
				String s = "" + file;
				fichier_choix = s;
				Grille grille = new Grille(10,15,fichier_choix);
				setVisible(false);
			}
		}
	}
}