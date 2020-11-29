/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author gauthierpous
 */
public class FFSSMTest {
    Club passionKite;
    Site Porquerolles, PortCros;
    Moniteur moniteurGreg;
    Plongee Porq, Portc;
    Plongeur bernard, lisa;
    Licence licenceBernard, licenceLisa;
    
    
    @BeforeEach
    public void setUp(){
        passionKite = new Club(moniteurGreg, "PassionKite", "0607080910");
        Porquerolles = new Site("Porquerolles", "Découvrir le nord de l'ile et ses fonds marins");
        PortCros = new Site("Port-Cros", "Découvrir le parc naturel et ses fonds marins incroyables");
        moniteurGreg = new Moniteur("1234", "Slide", "Greg", "23 rue de la vague", "0701010101", LocalDate.of(1985, 5, 17), 5, 7777);
        Porq = new Plongee(Porquerolles, moniteurGreg, LocalDate.of(2020, 7, 27), 12, 20);
        Portc = new Plongee(PortCros, moniteurGreg, LocalDate.of(2020, 7, 25), 20, 30);
        bernard = new Plongeur("9876", "Laporte", "Bernard", "33 rue du Rugby", "0660066066", LocalDate.of(1964, 1, 7), 2);
        lisa = new Plongeur("6382", "Estebe", "Lisa", "192 rue du vide", "0770771707", LocalDate.of(2000, 2, 13), 3);
        licenceBernard = new Licence(bernard, "1566129", LocalDate.of(2020, 2, 21), passionKite);
        licenceLisa = new Licence(lisa, "5191265", LocalDate.of(2019, 7, 27), passionKite);
    }
    
    @Test
    public void testOrganisePlongee(){
        //Ajoute la plongée à la liste 
        passionKite.organisePlongee(Porq);
        //Vérifie sa présence
        assertEquals(Porq, passionKite.plongeeOragnisees.get(0), 
                "La plongée n'a pas été ajoutée");
    }
    
    
    @Test
    public void testAjouteParticipant(){
        //Ajoute un particpant à la plongée
        Porq.ajouteParticipant(bernard);
        //Vérifie l'ajout
        assertEquals(bernard.licence, Porq.listeParticipants.get(0),
                "Le plongeur n'a pas été ajouté à la plongée");
    }
    
    /*
    @Test
    public void testEstValide(){
        //
    }
    /*
    @Test
    public void testplongeesNonConformes(){
        //Ajoute un particpant avec une licence valide à la plongée Porquerolle
        Porq.ajouteParticipant(bernard);
        //Ajoute un participant avec une licence non-valide à la plongée Port-Cros
        Portc.ajouteParticipant(lisa);
        
        //Ajoute les plongées à la liste du club
        passionKite.organisePlongee(Porq);
        passionKite.organisePlongee(Portc);
        
        //Vérifie que les plongées sont conformes
        assertEquals(passionKite.plongeesNonConformes(), Portc, 
                "La plongée Port-Cros ne doit pas être conforme");
    }
    /*
    Cannot invoke "FFSSM.Licence.estValide(java.time.LocalDate)" because the return value of "java.util.ArrayList.get(int)" is null
	at FFSSM.FFSSMTest.testplongeesNonConformes(FFSSMTest.java:71)
    
    Liste des test à faire : 
    plongeeNonCoformes
    terminerEmbauche
    estValide
    employeurActuel
    nouvelleEmbauche + emplois
    estConforme
    ajouteLicence
    
    */
}
