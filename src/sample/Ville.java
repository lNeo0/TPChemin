package sample;
import java.lang.Math;

public class Ville{
    private String nom;
    private int x;
    private int y;


    public Ville() {
    }

    public Ville(String nom, int x, int y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public static double getDist(Ville depart, Ville arrivee) {
        return Math.sqrt(((arrivee.getX()*-depart.getX())*(arrivee.getX()*-depart.getX()))+ (arrivee.getY()- depart.getY())*(arrivee.getY()- depart.getY()));
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
