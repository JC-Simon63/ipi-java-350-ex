package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class) //Junit 5
@SpringBootTest //ou @DataJpaTest
public class EmployeServiceIntegrationTest {

    @Autowired
    EmployeService employeService;

    @Autowired
    EmployeRepository employeRepository;

    @BeforeEach
    public void setup() {
        employeRepository.deleteAll();
    }

    @Test
    public void testEmbaucheEmployeIntegration() throws EmployeException {
        // Given
        String nom = "Doe";
        String prenom = "Jane";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        // When
        employeService.embaucheEmploye(nom, prenom,poste,niveauEtude,tempsPartiel);

        // Then
        Employe e1 = employeRepository.findAll().get(0);
        Assertions.assertThat(e1.getNom()).isEqualTo(nom);
        Assertions.assertThat(e1.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(e1.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(e1.getPerformance()).isEqualTo(Entreprise.PERFORMANCE_BASE);
        Assertions.assertThat(e1.getMatricule()).isEqualTo("T00001");
        // 1521.22 * 1.2
        Assertions.assertThat(e1.getSalaire()).isEqualTo(1825.46);
    }
}
