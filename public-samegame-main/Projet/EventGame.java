import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 * La classe <code> EventGame </code> regroupe toutes les actions liées au clic et déplacements de la souris.
 * Contient également toute les actions effectuées sur la grille
 *
 */

public class EventGame extends JFrame implements MouseListener {

/**
 * Variable générant la grille d'image visuelle.
 */
  private JLabel [][] grille;

/**
 * Variable générant les tableaux de lettre et d'état de case.
 */

  private boolean tab_etat[][] = new boolean [10][15];
  private char tab_letter[][] = new char [10][15];

/**
 * Variables utilisées pour l'évolution de la grille de lettre.
 */

  public int condition = 0;
  private int score = 0;
  private int fin = 0;
  private int casex, casey;

/**
 * Variables graphiques dessinant la partie droite de l'écran (score + pokemon).
 */
  private JTextArea areascore;
  private JLabel nompok = new JLabel();
  private JPanel paneldroite = new JPanel();
  private JLabel scorepok = new JLabel();
  private JLabel fond = new JLabel();

/**
 * Fenetre graphique dans laquelle le jeu principale se trouve.
 */
  private JFrame jeux;

/**
 * Constante regroupant les différentes images utilisées dans la grille.
 */

  ImageIcon imageRED = new ImageIcon(getClass().getResource("photo/iconeSalam.png"));
  ImageIcon imageGREEN = new ImageIcon(getClass().getResource("photo/iconeBulbi.png"));
  ImageIcon imageBLUE =  new ImageIcon(getClass().getResource("photo/iconeZapper.png"));


/**
 * Constructeur permettant d'initialiser les variables utilisées.
 */

  public EventGame(char [][] tab_letter, JLabel [][] grid, JTextArea textArea, JPanel paneldroite, JFrame jeux) {
    this.tab_letter=tab_letter;
    this.grille=grid;
    this.areascore=textArea;
    this.paneldroite=paneldroite;
    this.jeux=jeux;
    afficheGrille();
  }


/**
 * Méthode qui detecte l'entrée de la souris dans une case.
 */
  @Override
  public void mouseEntered (MouseEvent e) {
    bgdefault();
    setStatTab();
    JLabel b = (JLabel)e.getSource();
    Point p = b.getLocation();
    casex = p.x/(744/14);
    casey = p.y/(626/9);
    groupeIcon(casey,casex);

    bgon(casey, casex);

    
  }

/**
 * Méthode qui detecte la sortie de la souris dans une case.
 */
  @Override
  public void mouseExited (MouseEvent e){
    setStatTab();
    JLabel b = (JLabel)e.getSource();
    Point p = b.getLocation();
    casex = p.x/(744/14);
    casey = p.y/(626/9);
    groupeIcon(casey,casex);

    bgoff(casey, casex);
    bgdefault();

  }
/**
 * Méthode qui detecte un clic de la souris dans une case.
 */
  public void mouseClicked (MouseEvent e) {
    JLabel b = (JLabel)e.getSource();
    Point p = b.getLocation();
    casex = p.x/(744/14);
    casey = p.y/(626/9);
    System.out.println(casey + " " + casex);
    
    setStatTab();
    condition=0;
    groupeIcon(casey,casex);
    condition=0;
    test();
    descenteBoule();
    decaleGauche();
    afficheGrille();
    refreshScore();
    refresh();

    if(score<=500) {
      Salam();
    } else if (500<score && score<=1000) {
      fond.removeAll();
      Reptin();
    } else if (1000<score) {
      fond.removeAll();
      Dracau();
    }
    
    setStatTab();
    bgdefault();
    if(endGame()==true) {
      System.err.println("Fin du jeu");
      jeux.dispose();
      FenetreFin fenetrefin = new FenetreFin(score);
    }

  }
/**
 * Méthode qui detecte un bouton appuyé de la souris
 */
  @Override
  public void mousePressed(MouseEvent e) {

  }
/**
 * Méthode qui detecte un bouton relaché de la souris
 */
  @Override
  public void mouseReleased(MouseEvent e) {

  }

/**
 * Méthode qui remet la background de tout le tableau à la couleur initiale.
 */
  public void bgdefault () {
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        grille[i][j].setBackground(new Color(40,36,47));
      }
    }
  }

/**
 * Méthode qui remet le background des cases à la couleur initiale
 * @param y ordonnée de la case.
 * @param x abscisse de la case.
 */
  public void bgon (int y, int x) {
    groupeIcon(y,x);
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        if(tab_etat[i][j]==true) {
          grille[i][j].setBackground(new Color(124,44,67));
        }
      }
    }
  }
/**
 * Méthode qui remet le background des cases à la couleur initiale.
 * @param y ordonnée de la case.
 * @param x abscisse de la case.
 */
  public void bgoff (int y, int x) {
    groupeIcon(y,x);
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        if(tab_etat[i][j]==true) {
          grille[i][j].setBackground(new Color(40,36,47));
        }
      }
    }
  }

/**
 * Méthode redessine la grille visuelle permettant de voir la descente et le décalage des icones.
 */
  public void refresh () {
    for (int k = 0 ; k < 10 ; k++) {
      for(int i = 0; i < 10 ; i++) {
        for (int j = 0 ; j < 15 ; j++) {

          if(tab_letter[i][j]=='R') {
            //System.out.print("refresh");
            grille[i][j].setIcon(imageRED);
          } else if (tab_letter[i][j]=='B') {
            //System.out.print("refresh");
            grille[i][j].setIcon(imageBLUE);
          } else if (tab_letter[i][j]=='V') {
            //System.out.print("refresh");
            grille[i][j].setIcon(imageGREEN);
          } else if (tab_letter[i][j]==' ') {
            //System.out.print("refresh");
            grille[i][j].setIcon(null);
          }
        }
      }
    }
  }


/**
 * Méthode qui redessine le score.
 */
  public void refreshScore() {

    int scorejoueur = 0;
    scorejoueur=score();
    this.areascore.setText("SCORE : "+ scorejoueur);
    this.areascore.setFont(new Font("Comic Sans",Font.BOLD,40));
        //this.areascore.setEditable(false);
    this.areascore.setBounds(810, 7,423,55);
    this.areascore.setForeground(new Color(124,44,67));
    this.areascore.setBackground(new Color(40,36,47));
    System.out.println("Score : " + scorejoueur);



  }

/**
 * Méthode qui regroupe les cases en fonction de la lettre du tableau de lettre
 * @param y ordonnée de la case.
 * @param x abscisse de la case.
 */

  public void groupeIcon(int y, int x) {
    char cara;
    boolean vide = true;
    cara=tab_letter[y][x];

    //System.out.println("tab_letter " + tab_letter[y][x]);

    tab_etat[y][x]=true;

    if(cara==' ') {
      tab_etat[y][x]=false;
      vide = false;
    }

    if (vide==true) {
      if(y>0) {
        if(cara==tab_letter[y-1][x] && tab_etat[y-1][x] == false) {
          condition+=2;
          groupeIcon(y-1,x);
        }
      }
      if (x>0) {
        if(cara==tab_letter[y][x-1] && tab_etat[y][x-1] == false) {
          condition+=2;
          groupeIcon(y,x-1);

        }
      }
      if (y<9) {
        if(cara==tab_letter[y+1][x] && tab_etat[y+1][x] == false) {
          condition+=2;
          groupeIcon(y+1,x);
        }
      }
      if (x<14) {
        if(cara==tab_letter[y][x+1] && tab_etat[y][x+1] == false) {
          condition+=2;
          groupeIcon(y,x+1);
        }
      }
      if (condition == 0) {
        tab_etat[y][x]=false;
        fin++;
      }
    }
  }

/**
 * Methode qui compte le nombre de true dans le tableau d'état.
 * @return nombre de true dans le tableau d'état.
 */
  public int compteEtat () {
    int cpt_etat = 0;
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        if (tab_etat[i][j]==true) {
          cpt_etat++;
        }
      }
    }

    return cpt_etat;
  }

/**
 * Methode qui compte le score en fonction du nombre de true retourné par compteEtat.
 * @return score du joueur en fonction de la 
 */
  public int score () {
    int cpt_etat = 0;
    cpt_etat = compteEtat();
    if (cpt_etat<=2) {
      score=score;
    } else {
      score += (cpt_etat-2)*(cpt_etat-2);
    }
    return score;
  }

/**
 * Methode qui lis le tableau d'état et vide la case du tableau de lettre correspondante.
 * lorsque la valeur du tableau état vaut true.
 */
  public void test () {
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        if(tab_etat[i][j]==true) {
          tab_letter[i][j]=' ';
        }
      }
    }
  }
/**
 * Methode qui initialise le tableau d'état à false.
 */
  public void setStatTab () {
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        tab_etat[i][j] = false;
      }
    }
  }

/**
 * Methode qui detecte la fin du jeu en fonction du nombre de true dans le tableau d'état.
 * @return true si il y a aucun true dans le tableau d'état. Sinon false.
 */
  public boolean endGame () {
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        if(tab_letter[i][j] != ' ') {
          groupeIcon(i,j);
          if(compteEtat() > 1) {
            return false;
          }
        }
      }
    }
    return true;
  }
/**
 * Methode aidant au developpement permettant d'afficher le tableau état sur la console.
 */
  public void afficheEtat() {
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        System.out.print(" " + tab_etat[i][j]);
      }
      System.out.println("");
    }
  }
/**
 * Methode qui affiche le tableau de lettre sur la console.
 */
  public void afficheGrille(){
    for (int i = 0 ; i < 10 ; i++) {
      for (int j = 0 ; j < 15 ; j++) {
        System.out.print(tab_letter[i][j]);
      }
      System.out.println("");
    }
  }
/**
 * Methode qui vide une case du tableau de lettre.
 * @param y ordonnée de la case.
 * @param x abscisse de la case.
 */
  public void upgradeTab(int y, int x) {
    tab_letter[y][x] = ' ';
  }
/**
 * Methode qui fait descendre les lettres ayant du vide en dessous d'elles.
 */
  public void descenteBoule() {
    for (int k = 0 ; k < 10 ; k++) {
      for (int i = 0 ; i < 9 ; i++) {
        for (int j = 0 ; j < 15 ; j++) {
          if ((tab_letter[i][j] != ' ') && (tab_letter[i+1][j] == ' ')) {
            tab_letter[i+1][j] = tab_letter[i][j];
            tab_letter[i][j] = ' ';
          }
        }
      }
    }
  }
/**
 * Methode qui decale les colonnes à gauche quand une colonne se vide.
 */
  public void decaleGauche() {
    for (int k = 0 ; k < 10 ; k++) {
      for (int j = 0 ; j < 14 ; j++) {
        if (etatCln(j)) {
          for (int i = 0 ; i < 10 ; i++) {
            tab_letter[i][j] = tab_letter[i][j+1];
            tab_letter[i][j+1] = ' ';
          }
        }
      }
    }
  }
/**
 * Methode qui renvoie l'état d'une colonne (true si elle est vide ou false si elle ne l'est pas)
 * @param j numero de la colonne à vérifier.
 * @return true si la colonne est vide. Sinon false.
 */
  public boolean etatCln(int j) {
    for (int i = 0 ; i < 10 ; i++) {
      if (tab_letter[i][j] != ' ') {
        return false;
      }
    }
    return true;
  }
/**
 * Methode permettant de dessiner le pokemon en fonction du score.
 */
  public void Reptin(){

    ImageIcon dracauf = new ImageIcon("photo/nameReptin.png");
    this.nompok.setIcon(dracauf);
    this.nompok.setBounds(76,10,270,46);
    this.paneldroite.add(nompok);

    ImageIcon salame = new ImageIcon("photo/imgReptin.png");
    this.scorepok.setIcon(salame);
    this.paneldroite.setLayout(null);
    this.scorepok.setBounds(0,20,500,700);
    this.paneldroite.add(scorepok);

    ImageIcon fondre = new ImageIcon("photo/arr.jpg");
    this.fond.setIcon(fondre);
    this.fond.setBounds(0,-190,500,900);
    this.paneldroite.add(fond);   
  }
/**
 * Methode permettant de dessiner le pokemon en fonction du score.
 */
  public void Salam(){

    ImageIcon dracauf = new ImageIcon("photo/nameSalam.png");
    this.nompok.setIcon(dracauf);
    this.nompok.setBounds(13,10,396,63);
    this.paneldroite.add(nompok);

    ImageIcon salame = new ImageIcon("photo/imgSalam.png");
    this.scorepok.setIcon(salame);
    this.paneldroite.setLayout(null);
    this.scorepok.setBounds(0,100,475,475);
    this.paneldroite.add(scorepok);

    ImageIcon fondre = new ImageIcon("photo/arr.jpg");
    this.fond.setIcon(fondre);
    this.fond.setBounds(0,-190,500,900);
    this.paneldroite.add(fond);
  }
/**
 * Methode permettant de dessiner le pokemon en fonction du score.
 */
  public void Dracau(){
    ImageIcon dracauf = new ImageIcon("photo/nameDracau.png");
    this.nompok.setIcon(dracauf);
    this.nompok.setBounds(26,10,370,55);
    this.paneldroite.add(nompok);

    ImageIcon salame = new ImageIcon("photo/imgDracau.png");
    this.scorepok.setIcon(salame);
    this.paneldroite.setLayout(null);
    this.scorepok.setBounds(0,50,475,475);
    this.paneldroite.add(scorepok);

    ImageIcon fondre = new ImageIcon("photo/arr.jpg");
    this.fond.setIcon(fondre);
    this.fond.setBounds(0,-190,500,900);
    this.paneldroite.add(fond);
  }
}