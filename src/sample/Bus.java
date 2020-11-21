package sample;

public class Bus {

    private Ville Origine;
    private Ville Destination;
    private Moyen moyen;
    private int departureTime;
    private int duration;
    private int financial;
    private int cost;
    private int co2;
    private int confort;
    private int nbRepetitions;
    private int frequence;


    public Bus() {
    }

    public Bus(Ville origine, Ville destination, Moyen moyen, int departureTime, int duration, int financial, int cost, int co2, int confort, int nbRepetitions, int frequence) {
        this.Origine = origine;
        this.Destination = destination;
        this.moyen = moyen;
        this.departureTime = departureTime;
        this.duration = duration;
        this.financial = financial;
        this.cost = cost;
        this.co2 = co2;
        this.confort = confort;
        this.nbRepetitions = nbRepetitions;
        this.frequence = frequence;
    }

    public Ville getOrigine() {
        return Origine;
    }

    public void setOrigine(Ville origine) {
        this.Origine = origine;
    }

    public Ville getDestination() {
        return Destination;
    }

    public void setDestination(Ville destination) {
        this.Destination = destination;
    }

    public Moyen getMoyen() {
        return moyen;
    }

    public void setMoyen(Moyen moyen) {
        this.moyen = moyen;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFinancial() {
        return financial;
    }

    public void setFinancial(int financial) {
        this.financial = financial;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getConfort() {
        return confort;
    }

    public void setConfort(int confort) {
        this.confort = confort;
    }

    public int getNbRepetitions() {
        return nbRepetitions;
    }

    public void setNbRepetitions(int nbRepetitions) {
        this.nbRepetitions = nbRepetitions;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
}
