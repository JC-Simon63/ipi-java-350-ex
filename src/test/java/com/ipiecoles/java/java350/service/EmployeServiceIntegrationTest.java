package com.ipiecoles.java.java350.service;


import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.*;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeServiceIntegrationTest {

    @Autowired
    EmployeService employeService;

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void setup(){
        employeRepository.deleteAll();
    }

    @Test
    public void integrationEmbaucheEmploye() throws EmployeException {
        //Given
        employeRepository.save(new Employe("Doe", "John", "T12345", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0));
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        Employe employe = employeRepository.findByMatricule("T12346");
        Assertions.assertNotNull(employe);
        Assertions.assertEquals(nom, employe.getNom());
        Assertions.assertEquals(prenom, employe.getPrenom());
        Assertions.assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), employe.getDateEmbauche().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        Assertions.assertEquals("T12346", employe.getMatricule());
        Assertions.assertEquals(1.0, employe.getTempsPartiel().doubleValue());

        //1521.22 * 1.2 * 1.0
        Assertions.assertEquals(1825.46, employe.getSalaire().doubleValue());
    }

    @Test
    public void findEmployeGagnantMoinsQue() {
        employeRepository.save(new Employe("Doe", "John", "T12345", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0));
        employeRepository.save(new Employe("Doe", "Jane", "T23456", LocalDate.now(), Entreprise.SALAIRE_BASE+1000, 1, 1.0));
        employeRepository.save(new Employe("Doe", "Franck", "T34567", LocalDate.now(), Entreprise.SALAIRE_BASE-200, 1, 1.0));
        Employe employe = employeRepository.findByMatricule("T34567");

        List<Employe> employeList = employeService.findEmployeGagnantMoinsQue("T12345");
        Assertions.assertTrue(employeList.size() == 1);
        Assertions.assertEquals(employe, employeList.get(0));
    }

    @Test
    public void integrationCalculPerformanceCommercial() throws EmployeException{
        employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(), Entreprise.SALAIRE_BASE, 4, 1.0));
        Long caTraite = 9L;
        Long objectifCa = 10L;

        employeService.calculPerformanceCommercial("C12345", caTraite, objectifCa);
        Employe employe = employeRepository.findByMatricule("C12345");
        Assertions.assertEquals(employe.getPerformance(), 2);
    }

    @Test
    public void integrationAvgPerformanceWhereMatriculeStartsWith(){
        employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(), Entreprise.SALAIRE_BASE, 4, 1.0));
        employeRepository.save(new Employe("Doe", "Jane", "C23456", LocalDate.now(), Entreprise.SALAIRE_BASE, 8, 1.0));

        Double avgPerformance = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
        Assertions.assertEquals(avgPerformance, 6);
    }

}