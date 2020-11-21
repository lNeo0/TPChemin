package sample;

import java.time.LocalTime;

public class Train {

    private Ville origine;
    private Ville destination;
    private Moyen means;
    private LocalTime departureTime;
    private int duration;
    private int financial;
    private int cost;
    private int co2;
    private int confort;
    private int nbRepetitions;
    private int frequence;


    public Train() {
    }

    public Train(Ville origine, Ville destination, Moyen means, LocalTime departureTime, int duration, int financial, int cost, int co2, int confort, int nbRepetitions, int frequence) {
        this.origine = origine;
        this.destination = destination;
        this.means = means;
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
        return origine;
    }

    public void setOrigine(Ville origine) {
        this.origine = origine;
    }

    public Ville getDestination() {
        return destination;
    }

    public void setDestination(Ville destination) {
        this.destination = destination;
    }

    public Moyen getMeans() {
        return means;
    }

    public void setMeans(Moyen means) {
        this.means = means;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
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
