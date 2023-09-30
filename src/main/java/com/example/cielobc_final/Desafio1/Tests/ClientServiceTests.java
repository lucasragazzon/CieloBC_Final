package com.example.cielobc_final.Desafio1.Tests;


import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio1.Repository.InMemoryRepo;
import com.example.cielobc_final.Desafio1.Services.ClientsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceTests {
    @Mock
    private InMemoryRepo inMemoryRepo;

    @InjectMocks
    private ClientsService clientsService;

    private final PhysicalPerson physicalPerson = new PhysicalPerson(
            "1324",
            "46986719817",
            "Lucas Ferreira Ragazzon",
            "lucas_ragazzon@hotmail.com");

    private final LegalPerson legalPerson = new LegalPerson(
            "1423",
            "46986719817",
            "Lucas Ferreira Ragazzon",
            "lucas_ragazzon@enterprise.com.br",
            "12345678000100",
            "Cielo Dev Teste Inc.");

    @Test
    public void getPhysicalList_shouldReturnEmptyList() {

        Mockito.when(inMemoryRepo.findAllPhysicalClients()).thenReturn(new ArrayList<>());
        var result = clientsService.getPhysicalClientList();
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void getPhysicalList_shouldReturnListWithItems() {

        List<PhysicalPerson> registeredClients = new ArrayList<>();
        registeredClients.add(
                new PhysicalPerson(
                        "1324",
                        "46986719817",
                        "Lucas Ferreira Ragazzon",
                        "lucas_ragazzon@hotmail.com")
        );

        Mockito.when(inMemoryRepo.findAllPhysicalClients()).thenReturn(registeredClients);

        var obj = clientsService.getPhysicalClientList();
        Assertions.assertThat(obj).isNotEmpty();
    }

    @Test
    public void findPhysicalClient_shouldReturnNullIfNotFound() {

        Mockito.when(inMemoryRepo.findPhysicalBy(ArgumentMatchers.any())).thenReturn(null);
        var obj = clientsService.findPhysicalPerson(ArgumentMatchers.any());
        Assertions.assertThat(obj).isNull();
    }

    @Test
    public void getLegalList_shouldReturnEmptyList() {

        Mockito.when(inMemoryRepo.findAllLegalClients()).thenReturn(new ArrayList<>());
        var obj = clientsService.getLegalClientList();
        Assertions.assertThat(obj).isEmpty();
    }

    @Test
    public void getLegalList_shouldReturnListWithItems() {

        List<LegalPerson> registeredClients = new ArrayList<>();
        registeredClients.add(
                new LegalPerson(
                        "1423",
                        "46986719817",
                        "Lucas Ferreira Ragazzon",
                        "lucas_ragazzon@enterprise.com.br",
                        "12345678000100",
                        "Cielo Teste Dev Inc.")
        );

        Mockito.when(inMemoryRepo.findAllLegalClients()).thenReturn(registeredClients);

        var obj = clientsService.getLegalClientList();
        Assertions.assertThat(obj).isNotEmpty();
    }

    @Test
    public void findPhysicalClient_shouldReturnFoundClients() {

        Mockito.when(inMemoryRepo.findPhysicalBy(ArgumentMatchers.any())).thenReturn(physicalPerson);
        var obj = clientsService.findPhysicalPerson(ArgumentMatchers.any());
        Assertions.assertThat(obj.getUUID()).isEqualByComparingTo(physicalPerson.getUUID());
    }

    @Test
    public void findLegalClient_shouldReturnFoundClients() {

        LegalPerson legalPerson = new LegalPerson(
                "1423",
                "46986719817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragazzon@enterprise.com.br",
                "12345678000100",
                "Cielo Teste Dev Inc.");

        Mockito.when(inMemoryRepo.findlegalBy(ArgumentMatchers.any())).thenReturn(legalPerson);
        var obj = clientsService.findLegalPerson(ArgumentMatchers.any());
        Assertions.assertThat(obj.getUUID()).isEqualByComparingTo(legalPerson.getUUID());
    }

    @Test
    public void registerPhysical_shouldReturnNullIfExists() {

        inMemoryRepo.add(physicalPerson);

        Mockito.when(inMemoryRepo.add(ArgumentMatchers.any(PhysicalPerson.class))).thenReturn(null);
        var obj = clientsService.registerPhysicalPerson(physicalPerson);
        Assertions.assertThat(obj).isNull();
    }

    @Test
    public void registerPhysical_shouldReturnPersonIfIsSuccesful() {

        Mockito.when(inMemoryRepo.add(ArgumentMatchers.any(PhysicalPerson.class))).thenReturn(physicalPerson);
        var obj = clientsService.registerPhysicalPerson(physicalPerson);
        Assertions.assertThat(obj).isNotNull();
    }

    @Test
    public void updatePhysical_shouldReturnNullIfDoesntExist() {

        Mockito.when(inMemoryRepo.update(ArgumentMatchers.any(), ArgumentMatchers.any(PhysicalPerson.class))).thenReturn(null);
        var obj = clientsService.updatePhysicalPerson(ArgumentMatchers.any(), ArgumentMatchers.any());
        Assertions.assertThat(obj).isNull();
    }

    @Test
    public void updatePhysical_shouldReturnPersonIfSuccessful() {


        Mockito.when(inMemoryRepo.update(ArgumentMatchers.any(), ArgumentMatchers.any(PhysicalPerson.class))).thenReturn(physicalPerson);
        var result = clientsService.updatePhysicalPerson(physicalPerson.getUUID().toString(), physicalPerson);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void registerLegal_shouldReturnNullIfExists() {


        inMemoryRepo.add(legalPerson);

        Mockito.when(inMemoryRepo.add(ArgumentMatchers.any(LegalPerson.class))).thenReturn(null);
        var obj = clientsService.registerLegalPerson(legalPerson);
        Assertions.assertThat(obj).isNull();
    }

    @Test
    public void registerLegal_shouldReturnPersonIfIsSuccessful() {

        Mockito.when(inMemoryRepo.add(ArgumentMatchers.any(LegalPerson.class))).thenReturn(legalPerson);
        var obj = clientsService.registerLegalPerson(legalPerson);
        Assertions.assertThat(obj).isNotNull();
    }


    @Test
    public void updateLegal_shouldReturnNullIfDoesntExist() {

        Mockito.when(inMemoryRepo.update(ArgumentMatchers.any(), ArgumentMatchers.any(LegalPerson.class))).thenReturn(null);
        var obj = clientsService.updateLegalPerson(ArgumentMatchers.any(), ArgumentMatchers.any());
        Assertions.assertThat(obj).isNull();
    }

    @Test
    public void updateLegal_shouldReturnPersonIfIsSuccessful() {

        Mockito.when(inMemoryRepo.update(ArgumentMatchers.any(), ArgumentMatchers.any(LegalPerson.class))).thenReturn(legalPerson);
        var result = clientsService.updateLegalPerson(legalPerson
                .getUUID().toString(), legalPerson);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void deleteClient_shouldReturnFalseIfNotFound() {

        Mockito.when(inMemoryRepo.delete(ArgumentMatchers.any())).thenReturn(false);
        var obj = clientsService.deleteClient(ArgumentMatchers.any());
        Assertions.assertThat(obj).isFalse();
    }

    @Test
    public void deleteClient_shouldReturnTrueIfSuccessful() {

        inMemoryRepo.add(legalPerson);

        Mockito.when(inMemoryRepo.delete(ArgumentMatchers.any())).thenReturn(true);
        var obj = clientsService.deleteClient(legalPerson.getUUID().toString());
        Assertions.assertThat(obj).isTrue();
    }

}
