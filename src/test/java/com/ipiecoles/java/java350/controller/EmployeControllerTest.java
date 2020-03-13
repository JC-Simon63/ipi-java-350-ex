package com.ipiecoles.java.java350.controller;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

@WebMvcTest
public class EmployeControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EmployeRepository employeRepository;
//
//    @Test
//    public void testGetEmploye()throws Exception{
////        given
//        Employe employe = new Employe("Doe", "John", "T00001", LocalDate.now(), 1500.0, 1, 1.0);
//        employe.setId(5L);
//        Mockito.when(employeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employe));
//
////        when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/employes/5"));
//
////        then
//        result.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("{'id': 5, 'nom': 'Doe', 'prenom': 'John', 'matricule': 'T00001', " +
//                                                                            "'dateEmbauche': '2020-03-13', 'salaire': 1500.0, 'performance': 1, 'tempsPartiel': 1.0}"));
//    }
//
//    @Test
//    public void testGetEmployeNotFound()throws Exception{
////        given
//        Mockito.when(employeRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
//
////        when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/employes/5"));
//
////        then
//        result.andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().string("Employé 5 introuvable."));
//    }
}
