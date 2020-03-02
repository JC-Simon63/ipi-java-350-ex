package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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


    @ParameterizedTest(name = "Matricule : {0}, performance : {1}, tempsPartiel : {2}, Le resultat devrait être : {3}")
    @CsvSource({
            "'Mtest', 1, 1.0, 1900d",
            "'test', 1, 1.0, 1200d",
            ", 1, 1.0, 1200d",
            "'test', , 1.0, 1200d",
            "'test', 0, 1.0, 500d",
            "'test', 2, 1.0, 2500d",
            "'Mtest', 1, 0, 0d"
    })
    public void getPrimeAnnuellePourManagerParamTest (String matricule, Integer performance, Double tempsPartiel, Double result) {
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));
        employe.setMatricule(matricule);
        employe.setPerformance(performance);
        employe.setTempsPartiel(tempsPartiel);
        Double Prime = employe.getPrimeAnnuelle();
        Assertions.assertThat(Prime).isEqualTo(result);
    }


}
