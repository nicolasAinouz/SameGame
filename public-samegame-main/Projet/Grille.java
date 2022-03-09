import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.awt.*;


/**
 * La classe <code> Grille </code> permet de dessiner la grille sur la fenetre et génère une grille
 */

public class Grille extends JFrame{
    
    
    private int ligne;
    private int colonne;
    private JLabel[][] grid;

    private JFrame jeux = new  JFrame();

    private JPanel panelgauche = new JPanel();
    private JPanel paneldroite = new JPanel();

    private ImageIcon image1;

    private JLabel scorepok = new JLabel();
    private JLabel nompok = new JLabel();
    private JLabel fond = new JLabel();

    JTextArea textArea = new JTextArea();

    private RemplirCase casej;
    private char tab_letter[][];

    /**
     * Constructeur de la classe Grille appelé par la bouton "jouer".
     */
    public Grille(int nbligne, int nbcolonne){
        // Musique
        Thread playWave=new Son("lib/pokemo.wav");
        playWave.start();
        
        Image icone = Toolkit.getDefaultToolkit().getImage("photo/poke.png"); 
        this.ligne = nbligne;
        this.colonne = nbcolonne;
        this.grid = new JLabel[nbligne][nbcolonne];
        
        // Fenetre de jeux
        this.jeux.setIconImage(icone);
        this.jeux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jeux.setSize(1250,750);
        this.jeux.setLayout(null);
        this.jeux.setLocationRelativeTo(null);
        this.jeux.setResizable(true);
        this.jeux.add(panelgauche);
        this.jeux.setTitle("PokemonAventure");
        this.jeux.add(paneldroite);
        this.jeux.getContentPane().setBackground(new Color(40,36,47));
        this.jeux.add(textArea);
        
        //Partie gauche de la fenetre (grille)
        this.panelgauche.setBounds(5,5,800,700);
        this.panelgauche.setLayout(new GridLayout(nbligne, nbcolonne)); 
        this.panelgauche.setBackground(new Color(40,36,47));
        this.panelgauche.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(124,44,67)));
        
        //partie droite de la fenetre (score + pokemon)
        this.paneldroite.setBounds(805,65,423,639);
        this.paneldroite.setBackground(new Color(40,36,47));
        dessinScoreInit();
        
        casej=new RemplirCase();
        tab_letter = casej.randomGrille();
        fillGrille();

    }

    /**
     * Constructeur de la classe Grille appelé par la bouton "importer".
     */
    public Grille(int nbligne, int nbcolonne, String s){
        // Son
        Thread playWave=new Son("lib/pokemo.wav");
        playWave.start();
        
        Image icone = Toolkit.getDefaultToolkit().getImage("photo/poke.png"); 
        this.ligne = nbligne;
        this.colonne = nbcolonne;
        this.grid = new JLabel[nbligne][nbcolonne];
        
        // Fenetre de jeux
        this.jeux.setIconImage(icone);
        this.jeux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jeux.setSize(1250,750);
        this.jeux.setLayout(null);
        this.jeux.setLocationRelativeTo(null);
        this.jeux.setResizable(true);
        this.jeux.add(panelgauche);
        this.jeux.setTitle("PokemonAventure");
        this.jeux.add(paneldroite);
        this.jeux.getContentPane().setBackground(new Color(40,36,47));
        this.jeux.add(textArea);
        
        //Partie gauche de la fenetre (grille)
        this.panelgauche.setBounds(5,5,800,700);
        this.panelgauche.setLayout(new GridLayout(nbligne, nbcolonne)); 
        this.panelgauche.setBackground(new Color(40,36,47));
        this.panelgauche.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(124,44,67)));
        
        //partie droite de la fenetre (score + pokemon)
        this.paneldroite.setBounds(805,65,423,639);
        this.paneldroite.setBackground(new Color(40,36,47));
        dessinScoreInit();
        casej=new RemplirCase();
        tab_letter = casej.fileGrille(s);
        fillGrille();
    }
    

    /**
     * Methode qui affiche les images en fonction du tableau de lettres.
     */
    public void fillGrille(){
        char lettre;
        EventGame eventgame = new EventGame(tab_letter, grid, textArea, paneldroite, jeux);
        
        for(int i = 0; i < 10 ; i++) {
            for (int j = 0 ; j < 15 ; j++) {
                lettre=tab_letter[i][j];
                
                if(lettre=='R') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    grid[i][j].setIcon(new ImageIcon(getClass().getResource("photo/iconeSalam.png")));
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre=='B') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    grid[i][j].setIcon(new ImageIcon(getClass().getResource("photo/iconeZapper.png")));
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre=='V') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    grid[i][j].setIcon(new ImageIcon(getClass().getResource("photo/iconeBulbi.png")));
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre!= ' ') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    grid[i][j].setIcon(new ImageIcon(getClass().getResource("photo/iconeBulbi.png")));
                    this.panelgauche.add(grid[i][j]);
                } else if (lettre==' ') {
                    grid[i][j] = new JLabel();
                    grid[i][j].setOpaque(true);
                    grid[i][j].setBackground(new Color(40,36,47));
                    grid[i][j].setIcon(new ImageIcon());
                    this.panelgauche.add(grid[i][j]);
                }
                grid[i][j].addMouseListener(eventgame);
            }
        }
        
        this.jeux.setResizable(false);
        this.jeux.setVisible(true);
    }

    /**
     * Methode qui dessine le score.
     */
    public void dessinScoreInit () {

        int scorejoueur = 0;
        this.textArea.setText("SCORE : "+ scorejoueur);
        this.textArea.setFont(new Font("Comic Sans",Font.BOLD,40));
        this.textArea.setEditable(false);
        this.textArea.setBounds(810, 7,423,55);
        this.textArea.setForeground(new Color(124,44,67));
        this.textArea.setBackground(new Color(40,36,47));
    }
}
