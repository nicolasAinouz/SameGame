import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code> Menu </code> dessine tout le menu du jeu.
 */
public class Menu extends JPanel {

/**
 * Variables des boutons du menu
 */
	public JButton jouer = new JButton("jouer");
	public JButton importer = new JButton("importer");
	
/**
 * Variables des images
 */
	private Image arrierepl;
	private Image dresseure;
	private Image text;
	private Image hyper;
	private Image superb;
	private Image pokeav;

/**
 * Constructeur de la classe Menu qui initialise toute les variables.
 */
	public Menu() {

	super();
	this.arrierepl = Toolkit.getDefaultToolkit().getImage("photo/arr.jpg");
	this.dresseure = Toolkit.getDefaultToolkit().getImage("photo/dresseur.png");
	this.text = Toolkit.getDefaultToolkit().getImage("photo/text.png");
	this.hyper = Toolkit.getDefaultToolkit().getImage("photo/hyperball.png");
	this.superb = Toolkit.getDefaultToolkit().getImage("photo/superball.png");
	this.pokeav = Toolkit.getDefaultToolkit().getImage("photo/pokeav.png");


	this.add(jouer);
	//this.importer.setFont(new Font("Helvetica", Font.BOLD, 20));
	this.add(importer);

	}

	/**
	 * Redefinition de la méthode paintComponent pour dessiner le menu
	 */
	@Override
	protected void paintComponent(Graphics pinceau) {
		//Arrière plan 
		Graphics pinceau1 = pinceau.create();
		if (this.isOpaque()) {
			pinceau1.setColor(this.getBackground());
			pinceau1.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		//arrière plan
		pinceau1.drawImage(this.arrierepl,0,0,this.getWidth(),this.getHeight(),this);

		//bouton jouer
		double l_jouer = (this.getWidth()/12);
		int largeur_jouer = (int)l_jouer;

		double h_jouer = (this.getHeight()/1.55);
		int hauteur_jouer = (int)h_jouer;

		this.jouer.setBounds(largeur_jouer-10,hauteur_jouer+80,100,50);

		//bouton importer

		double l_importer = (this.getWidth()/1.2);
		int largeur_importer = (int)l_importer;

		double h_importer = (this.getHeight()/1.55);
		int hauteur_importer = (int)h_importer;

		this.importer.setBounds(largeur_importer-10,hauteur_importer+80,100,50);
		//this.importer.setFont(new Font("Arial", Font.BOLD, 15));

		//Sasha 

		double l_sasha = (this.getWidth()/7.5);
		int largeur_sasha = (int)l_sasha;

		double h_sasha = (this.getHeight()/2.69);
		int hauteur_sasha = (int) h_sasha;

		pinceau1.drawImage(this.dresseure,largeur_sasha,hauteur_sasha,280,400,this);		

		//Pokeball 1

		double l_pokeball1 = (this.getWidth()/12);
		int largeur_pokeball1 = (int)l_pokeball1;

		double h_pokeball1 = (this.getHeight()/1.55);
		int hauteur_pokeball1 = (int) h_pokeball1;

		pinceau1.drawImage(this.hyper,largeur_pokeball1,hauteur_pokeball1,80,80,this);


		//Pokeball 2

		double l_pokeball2 = (this.getWidth()/1.2);
		int largeur_pokeball2 = (int)l_pokeball2;

		double h_pokeball2 = (this.getHeight()/1.55);
		int hauteur_pokeball2 = (int) h_pokeball2;

		pinceau1.drawImage(this.superb,largeur_pokeball2,hauteur_pokeball2,80,80,this);

		// titre
		pinceau1.drawImage(this.pokeav,100,100,1000,200,this);


		//Règles

		double l_regles = (this.getWidth()/4.8);
		int largeur_regles = (int)l_regles;

		double h_regles = (this.getHeight()/1.75);
		int hauteur_regles = (int)h_regles;

		pinceau1.drawImage(this.text,largeur_regles,hauteur_regles,700,200,this);


		//modification des JBUTTON
		this.importer.setBackground(new Color(255,200,0));
		this.jouer.setBackground(new Color(255,200,0));
        this.jouer.setForeground(Color.BLACK);
        this.importer.setForeground(Color.BLACK);
        this.jouer.setBorder(BorderFactory.createLineBorder(new Color(50,64,90), 1));
        this.importer.setBorder(BorderFactory.createLineBorder(new Color(50,64,90), 1));
	}
}