import java.util.Scanner;

public class Main {

    public static final short MAX_PTS_VIE = 100;
    public static final short PTS_BOUCLIER = 25;
    public static final short MAX_ATTAQUE_ENNEMI = 5;
    public static final short MAX_VIE_ENNEMI = 30;
    public static final short MAX_ATTAQUE_JOUEUR = 5;
    public static final short REGENARATION_BOUCLIER_PAR_TOUR = 10;


    public static String nomPersonnage;
    public static short ptsDeVie;
    public static short ptsBouclier;
    public static short nbEnnemisTues;
    public static boolean bouclierActif = true;

    public static void main(String[] args)
    {
        initPersonnage();
        short ennemi = 5;
        ennemi = attaqueJoueur(ennemi);
        System.out.println(" il reste " + ennemi + " point de vie à l'ennemi");
    }

    public static void initPersonnage(){

        System.out.println("Saisir le nom de votre personnage");
        Scanner scanner = new Scanner(System.in);
        nomPersonnage = scanner.nextLine();
        System.out.println("OK " + Util.color(nomPersonnage, Color.GREEN) + " ! C'est parti !");
        ptsDeVie= MAX_PTS_VIE;
        ptsBouclier = bouclierActif ? PTS_BOUCLIER : 0;
        scanner.close();
    }
    public static boolean hasard(double pourcentage){
        return pourcentage < Math.random();
    }
    public static short nombreAuHasard(short max){
        return (short) Math.round(Math.random()* max);

    }
    public static short attaqueJoueur(short ptsVieEnnemi) {
        short forceAttaque = nombreAuHasard(MAX_ATTAQUE_JOUEUR);
        ptsVieEnnemi -= forceAttaque;
        System.out.print(Util.color(nomPersonnage, Color.GREEN)
                + " attaque l'" + Util.color("ennemi" , Color.YELLOW) + " ! Il lui fait perdre "
                + Util.color(forceAttaque, Color.PURPLE) + " points de dommage");
        return ptsVieEnnemi;
    }

    }

