package sample;

import java.time.LocalTime;

public class TrajetSimple implements  Cloneable {

    /** origine */
    Ville depart;
    /** destination */
    Ville arrive;
    /** moyen de transport*/
    Moyen moyen;
    /** duress du voyage en minutes */
    int duree;
    /** longueur du parcours en km */
    double distance;
    /** date de depart */
    LocalTime dateDepart;
    /** date d'arrivee */
    LocalTime dateArrivee;
    /** cout */
    double cout;
    /* CO2 */
    int co2;
    /*Confort*/
    int confort;

    public TrajetSimple(){
    }

    public TrajetSimple(Ville depart, Ville arrive, Moyen moyen, int cout, int duree, int co2, int confort) {
        this.depart = depart;
        this.arrive = arrive;
        this.moyen = moyen;
        this.cout = cout;
        this.duree = duree;
        this.co2 = co2;
        this.confort = confort;
    }


    public TrajetSimple(String _depart, String _arrivee, int _dateDepart, String _moyen) {

        moyen = Moyen.valueOf(_moyen.toUpperCase());
        int hh= _dateDepart / 100;
        int mm = _dateDepart - hh*100;
        dateDepart = LocalTime.of(hh, mm);
        calcule();
    }


    public TrajetSimple(Ville _depart, Ville _arrivee,  LocalTime _dateDepart, Moyen _moyen) {
        this.depart = _depart;
        this.arrive = _arrivee;
        this.moyen = _moyen;
        dateDepart = _dateDepart;
    }

    /**calcule la durée, la date d'arrivée, et le coût en fonction des villes
     * et du moyen de transport (si distance==-1 (car aucun trajet direct possible
     * entre les villes), cout=duree=-1 et date d'arrivee = null))*/
    private void calcule() {
        duree = (distance==-1?-1:(int) (60d*distance / moyen.v));
        dateArrivee = (distance==-1?null:dateDepart.plusMinutes((int)duree));
        cout = (distance==-1?-1:distance*moyen.cout);
    }

    /**change le moyen de transport utilisé*/
    public void setMoyen(Moyen _moyen)
    { moyen = _moyen; calcule(); }
    /**retourne le cout du voyage*/
    public double getCout() { return cout; }
    /**retourne la ville de depart*/
    public String getDepart() { return depart.getNom(); }
    public String getArrive() { return arrive.getNom(); }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("trajet de ");
        sb.append(depart.getNom()).append(" à ").append(arrive.getNom());
        sb.append(" par ").append(moyen);
        sb.append(", depart: ").append(dateDepart);
        sb.append(", arrivee: ").append(dateArrivee);
        sb.append(", cout = ").append(cout);
        sb.append(", distance = ").append(distance);
        return sb.toString();
    }

    @Override
    protected TrajetSimple clone() {
        TrajetSimple clone=null;
        try { clone = (TrajetSimple) super.clone(); }
        catch (CloneNotSupportedException e) { e.printStackTrace();}
        return (TrajetSimple)clone;
    }

    public void setDepart(Ville depart) {
        this.depart = depart;
    }

    public void setArrive(Ville arrive) {
        this.arrive = arrive;
    }

    public Moyen getMoyen() {
        return moyen;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public LocalTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalTime dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalTime dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setCout(double cout) {
        this.cout = cout;
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
}
