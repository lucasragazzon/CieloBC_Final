package com.example.cielobc_final.Desafio1.Tests;

import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio1.Services.ClientsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientsService clientsService;

    private final PhysicalPerson physicalPerson = new PhysicalPerson(
            "1324",
            "46986719817",
            "Lucas Ferreira Ragazzon",
            "lucas_ragazzon@hotmail.com"
    );


    private final LegalPerson legalPerson = new LegalPerson(
            "1432",
            "46986719817",
            "Lucas Ferreira Ragazzon",
            "lucas_ragazzon@organizacao.com.br",
            "12345678000100",
            "Cielo Tests Enterprise"
    );

    @Test
    public void getPhysical_shouldReturn200WithOrWithoutClients() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/D1/clients/physical_Person").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getLegal_shouldReturn200WithOrWithoutClients() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/D1/clients/legal_Person").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void showPhysicalClient_shouldReturn404IfNotFound() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/D1/clients/physical_Person/{UUID}",
                                        physicalPerson.getUUID())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void show_physicalClient() throws Exception {

        Mockito.when(clientsService.findPhysicalPerson(ArgumentMatchers.any())).thenReturn(physicalPerson);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/D1/clients/physical_Person/{UUID}", physicalPerson.getUUID())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.UUID").value(physicalPerson.getUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void showLegalPerson_shouldReturn404IfNotFound() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/D1/clients/legal_Person/{UUID}", legalPerson.getUUID())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void show_legalPerson() throws Exception {

        Mockito.when(clientsService.findLegalPerson(ArgumentMatchers.any())).thenReturn(legalPerson);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/D1/clients/legal_Person/{UUID}", legalPerson.getUUID())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.UUID").value(legalPerson.getUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void post_legalPerson_shouldReturn400IfExists() throws Exception {

        Mockito.when(clientsService.registerLegalPerson(ArgumentMatchers.any())).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/D1/clients/legal_Person/register")
                                .content(objectMapper.writeValueAsString(legalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void post_legalPerson_shouldReturn201WhenCreated() throws Exception {

        Mockito.when(clientsService.registerLegalPerson(ArgumentMatchers.any())).thenReturn(legalPerson);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/D1/clients/legal_Person/register")
                                .content(objectMapper.writeValueAsString(legalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void put_legalPerson_shouldReturn404IfDoesntExist() throws Exception {

        Mockito.when(clientsService.updateLegalPerson(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/D1/clients/legal_Person/{UUID}",
                                        legalPerson.getUUID())
                                .content(objectMapper.writeValueAsString(legalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void put_legalPerson_shouldReturn200IfUpdated() throws Exception {

        Mockito.when(clientsService.updateLegalPerson(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(legalPerson);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/D1/clients/legal_Person/update/{UUID}",
                                        legalPerson.getUUID())
                                .content(objectMapper.writeValueAsString(legalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void post_physicalPerson_shouldReturn400IfExists() throws Exception {

        Mockito.when(clientsService.registerPhysicalPerson(ArgumentMatchers.any())).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/D1/clients/physical_Person/register")
                                .content(objectMapper.writeValueAsString(physicalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void post_physicalPerson_shouldReturn201WhenCreated() throws Exception {

        Mockito.when(clientsService.registerPhysicalPerson(ArgumentMatchers.any())).thenReturn(physicalPerson);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/D1/clients/physical_Person/register")
                                .content(objectMapper.writeValueAsString(physicalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void put_physicalPerson_shouldReturn404IfDoesntExist() throws Exception {

        Mockito.when(clientsService.updatePhysicalPerson(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/D1/clients/physical_Person/update/{UUID}",
                                        physicalPerson.getUUID())
                                .content(objectMapper.writeValueAsString(physicalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void put_physicalPerson_shouldReturn200IfUpdated() throws Exception {

        Mockito.when(clientsService.updatePhysicalPerson(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(physicalPerson);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/D1/clients/physical_Person/update/{UUID}",
                                        physicalPerson.getUUID())
                                .content(objectMapper.writeValueAsString(physicalPerson))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete_shouldReturn404ifDoesntExist() throws Exception {

        Mockito.when(clientsService.deleteClient(ArgumentMatchers.any())).thenReturn(false);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("api/D1/clients/physical_Person/{UUID}",
                                        physicalPerson.getUUID())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void delete_shouldReturn200ifDeleted() throws Exception {

        Mockito.when(clientsService.deleteClient(ArgumentMatchers.any())).thenReturn(true);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/D1/clients/physical_Person/{UUID}",
                                        physicalPerson.getUUID())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}