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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @InjectMocks
    EmployeService employeService;

    @Mock
    EmployeRepository employeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this.getClass());
    }

    @Test
    public void testEmbaucheEmployeSave() throws EmployeException{
        // given
        String nom = "Doe";
        String prenom = "Jane";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00345");
        Mockito.when(employeRepository.findByMatricule("T00346")).thenReturn(null);

        // when
        employeService.embaucheEmploye(nom, prenom,poste,niveauEtude,tempsPartiel);

        // then
        Mockito.verify(employeRepository).findByMatricule("T00346");
        ArgumentCaptor<Employe> employeCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository, Mockito.times(1)).save(employeCaptor.capture());
        Employe e1 = employeCaptor.getValue();
        Assertions.assertThat(e1.getNom()).isEqualTo(nom);
        Assertions.assertThat(e1.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(e1.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(e1.getPerformance()).isEqualTo(Entreprise.PERFORMANCE_BASE);
        Assertions.assertThat(e1.getMatricule()).isEqualTo("T00346");
        // 1521.22 * 1.2
        Assertions.assertThat(e1.getSalaire()).isEqualTo(1825.46);

    }


    @Test
    public void testEmbaucheEmployeExceptionMatricule() {
        // given
        String nom = "Doe";
        String prenom = "Jane";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99999");
        //Mockito.when(employeRepository.findByMatricule("T00346")).thenReturn(null);

        // when
        Throwable thrown = catchThrowable(() -> employeService.embaucheEmploye(nom, prenom,poste,niveauEtude,tempsPartiel));
        // then
        Assertions.assertThat(thrown).isInstanceOf(EmployeException.class)
                .hasMessageContaining("Limite des 100000 matricules atteinte !");
    }

    @Test
    public void testEmbaucheEmployeExceptionEmployePresent() {
        // given
        String nom = "Doe";
        String prenom = "Jane";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        Employe e1 = new Employe(nom, prenom, "T00346", LocalDate.now(), 1826.45, 1, tempsPartiel);

        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00345");
        Mockito.when(employeRepository.findByMatricule("T00346")).thenReturn(e1);

        // when
        Throwable thrown = catchThrowable(() -> employeService.embaucheEmploye(nom, prenom,poste,niveauEtude,tempsPartiel));
        // then
        Assertions.assertThat(thrown).isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("L'employé de matricule T00346 existe déjà en BDD");
    }

}
