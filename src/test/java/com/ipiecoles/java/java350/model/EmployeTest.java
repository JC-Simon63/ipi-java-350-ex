package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


public class EmployeTest {

    @Autowired
    private Employe employe;

    @Test
    public void testgetNombreAnneeAncienneteDateEmbaucheNull() {
        int result;

//        Given employe
        employe = new Employe();

//        When date embauche is null
        employe.setDateEmbauche(null);
        result = employe.getNombreAnneeAnciennete();

//        Then return 0
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void testgetNombreAnneeAncienneteDateEmbaucheToday() {
        int result;

//        Given employe
        employe = new Employe();

//        When date embauche is today
        employe.setDateEmbauche(LocalDate.now());
        result = employe.getNombreAnneeAnciennete();

//        Then return 0
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void testgetNombreAnneeAncienneteDateEmbaucheMinus2Years() {
        int result;

//        Given employe
        employe = new Employe();

//        When date embauche is today - 2 years
        employe.setDateEmbauche(LocalDate.now().minusYears(2));
        result = employe.getNombreAnneeAnciennete();

//        Then return 2
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void testgetNombreAnneeAncienneteDateEmbauchePlus2Years() {
        int result;

//        Given employe
        employe = new Employe();

//        When date embauche is today + 2 years
        employe.setDateEmbauche(LocalDate.now().plusYears(2));
        result = employe.getNombreAnneeAnciennete();

//        Then return 0
        Assertions.assertThat(result).isEqualTo(0);
    }


    @Test
    public void testgetPrimeAnnuelleMatriculeBeginWithMAndTempsPartiel1(){
        double result;
        double compare;

//        Given employe
        employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));

//        When Matricule begin with M and Temps partiel is 1.0
        employe.setMatricule("M00001");
        employe.setTempsPartiel(1.0);
        compare = (Entreprise.primeAnnuelleBase() * Entreprise.INDICE_PRIME_MANAGER + Entreprise.PRIME_ANCIENNETE * employe.getNombreAnneeAnciennete()) * employe.getTempsPartiel();
        result = employe.getPrimeAnnuelle();

//        Then return 0
        Assertions.assertThat(result).isEqualTo(compare);
    }
}
