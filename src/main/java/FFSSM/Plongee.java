/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

    public Site lieu;

    public Moniteur chefDePalanquee;

    public LocalDate date;

    public int profondeur;

    public int duree;

    public boolean conforme;

    public ArrayList<Licence> listeParticipants = new ArrayList<>();

    public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        this.lieu = lieu;
        this.chefDePalanquee = chefDePalanquee;
        this.date = date;
        this.profondeur = profondeur;
        this.duree = duree;
    }

    public void ajouteParticipant(Plongeur participant) {
        this.listeParticipants.add(participant.licence);
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Détermine si la plongée est conforme. Une plongée est conforme si tous
     * les plongeurs de la palanquée ont une licence valide à la date de la
     * plongée
     *
     * @return vrai si la plongée est conforme
     */
    public boolean estConforme() {

        /*
        for (int i = 0; i < this.listeParticipants.size(); i++) {
            if (!this.listeParticipants.get(i).estValide(date)) {
                return false;
            }
        }
        return true;
        */
        this.conforme = true;
        this.listeParticipants.forEach((p) -> {
            if (!p.estValide(date)) {
                this.conforme = false;
            }
        });
        return this.conforme;
    }
    

}
