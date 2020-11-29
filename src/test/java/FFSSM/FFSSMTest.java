/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
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
        Porq = new Plongee(Porquerolles, moniteurGreg, LocalDate.of(2020, 11, 29), 12, 20);
        Portc = new Plongee(PortCros, moniteurGreg, LocalDate.of(2020, 11, 30), 20, 30);
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
        assertEquals(bernard, Porq.listeParticipants.get(0),
                "Le plongeur n'a pas été ajouté à la plongée");
        
        
    }
    
    
    @Test
    public void testEstValide(){
        //Test de la validité de la licence d'un plongeur
        assertTrue(licenceBernard.estValide(LocalDate.now()), "La licence n'est pas valide");
    }
    
    
    @Test
    public void testEmployeurActuel(){
        //Vérifie que Greg est bien employé par le club
        moniteurGreg.nouvelleEmbauche(passionKite, LocalDate.of(2020, 1, 1));
        
        //Vérifie l'employeur actuel
        assertEquals(moniteurGreg.employeurActuel().get() , passionKite, "L'employeur actuel est le club Passion Kite");
    }
    
    
    @Test
    public void testTerminerEmbauche(){
        //Création d'une embauche
        moniteurGreg.nouvelleEmbauche(passionKite, LocalDate.of(2020, 1, 1));
        
        //Fin de l'embauche
        moniteurGreg.embauche.terminer(LocalDate.now());
        
        //Vérification de la fin de l'embauche
        assertTrue(moniteurGreg.embauche.estTerminee(), "L'embauche de Greg doit être terminée");
    }
    @Test
    public void testEstConforme(){
        //Aout d'une licence à Bernard et à Lisa
        bernard.ajouteLicence("5191265", LocalDate.of(2020, 2, 21), passionKite);
        lisa.ajouteLicence("1566129", LocalDate.of(2019, 7, 27), passionKite);
        
        //On ajoute deux participants à une plongée
        Porq.ajouteParticipant(bernard);
        Porq.ajouteParticipant(lisa);
        
        
        //Vérifie la conformité de la plongée
        assertFalse(Porq.estConforme(),  "La plongée n'est pas conforme car la licence de lisa ne l'est pas");
    }
    
    
    @Test
    public void testAjouteLicence(){
        //Ajout d'une licence à Lisa
        lisa.ajouteLicence("5191265", LocalDate.now(), passionKite);
        
        //Vérification que la licence ajoutée appartient bien à Lisa
        assertEquals(lisa.licence.getDelivrance(), LocalDate.now(), "L'attribution de la licence a rencontré une erreur");
    }
    
    
    
    @Test
    public void testplongeesNonConformes(){
        //Aout d'une licence à Bernard et à Lisa
        bernard.ajouteLicence("5191265", LocalDate.of(2020, 2, 21), passionKite);
        lisa.ajouteLicence("1566129", LocalDate.of(2019, 7, 27), passionKite);
        
        //Ajoute un particpant avec une licence valide à la plongée Porquerolle
        Porq.ajouteParticipant(bernard);
        //Ajoute un participant avec une licence non-valide à la plongée Port-Cros
        Portc.ajouteParticipant(lisa);
        
        //Ajoute les plongées à la liste du club
        passionKite.organisePlongee(Porq);
        passionKite.organisePlongee(Portc);
        
        //Vérifie que les plongées sont conformes
        assertTrue(passionKite.plongeesNonConformes().contains(Portc), 
                "La plongée Port-Cros ne doit pas être conforme");
    }
    
    
    @Test
    public void testNouvelleEmbauche(){
        //Crée une nouvelle embauche pour le moniteur Greg
        moniteurGreg.nouvelleEmbauche(passionKite, LocalDate.of(2020, 1, 1));
        
        //Vérifie que l'embauche a été ajouté à la liste
        assertTrue(moniteurGreg.emplois().contains(moniteurGreg.embauche));
    }

}
