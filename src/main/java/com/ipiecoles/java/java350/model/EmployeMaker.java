package com.ipiecoles.java.java350.model;

import java.time.LocalDate;

public class EmployeMaker {

    public static EmployeBuilder technicienAPleinTemps() {
        return EmployeBuilder.anEmploye()
                .withMatricule("T12345")
                .withNom("Doe")
                .withPrenom("Jane")
                .withDateEmbauche(LocalDate.now())
                .withPerformance(Entreprise.PERFORMANCE_BASE)
                .withSalaire(Entreprise.SALAIRE_BASE)
                .withTempsPartiel(1.0);
    }

    public static EmployeBuilder technicienATempsPartiel() {
        return EmployeBuilder.anEmploye()
                .withMatricule("T12345")
                .withNom("Doe")
                .withPrenom("Jane")
                .withDateEmbauche(LocalDate.now())
                .withPerformance(Entreprise.PERFORMANCE_BASE)
                .withSalaire(Entreprise.SALAIRE_BASE)
                .withTempsPartiel(0.5);
    }
}
