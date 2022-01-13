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
        //TODO exercice 11

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
    public static void afficherPersonnage(){
        System.out.print(Util.color(nomPersonnage, Color.GREEN) + " (" + Util.color(ptsDeVie, Color.RED));
        if(bouclierActif){
            System.out.print(" " + Util.color(ptsBouclier, Color.BLUE));
        }
        System.out.print(")");
    }
    public static void attaqueEnnemi() {
        //Le bouclier reçoit en priorité les dommages
        short dommages = nombreAuHasard(MAX_ATTAQUE_ENNEMI);
        System.out.print("L'" + Util.color("ennemi", Color.YELLOW) + " attaque " + Util.color(nomPersonnage, Color.GREEN) + " ! ");
        System.out.print("Il lui fait " + dommages + " points de dommages ! ");
        if (ptsBouclier > 0){
            short dommagesBouclier = (short) Math.min(ptsBouclier, dommages);
            System.out.print("Le bouclier perd " + Util.color(dommagesBouclier, Color.BLUE) + " points. ");
            ptsBouclier -= dommagesBouclier;
            dommages -= dommagesBouclier;
        }
        //Ensuite la vie du joueur
        if (dommages > 0){
            System.out.print(Util.color(nomPersonnage, Color.GREEN) + " perd " + Util.color(dommages, Color.RED) + " points de vie ! ");
            ptsDeVie -= dommages;
        }
        System.out.println();
    }
    static short[] initEnnemis(){
        System.out.println("Combien souhaitez-vous combattre d'ennemis ?");
        Scanner scanner = new Scanner(System.in);
        short nbEnnemis = scanner.nextShort();
        System.out.println("Génération des ennemis...");
        short[] ennemis = new short[nbEnnemis];
        for(short i = 0; i < nbEnnemis; i++){
            ennemis[i] = (short) nombreAuHasard(MAX_VIE_ENNEMI);
            System.out.println("Ennemi numéro " + (i + 1) + " : " + Util.color(ennemis[i], Color.PURPLE));
        }
        return ennemis;
    }
    public static short attaque(short ennemi, boolean joueurAttaque) {
        if (ennemi <= 0 || (ptsBouclier <= 0 && ptsDeVie <= 0)){
            return ennemi;
        }
        if (joueurAttaque){
            ennemi = attaqueJoueur(ennemi);
        } else {
            attaqueEnnemi();
        }
        return ennemi;
    }


    }

