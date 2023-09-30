package com.example.cielobc_final.Desafio2.Tests;

import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio2.Repository.InMemoryRepo2;
import com.example.cielobc_final.Desafio2.Service.ClientsService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceTests {

    private final LegalPerson legalPerson = new LegalPerson(
            "1423",
            "46986719817",
            "Lucas Ferreira Ragazzon",
            "lucas_ragazzon@enterprise.com.br",
            "12345678000100",
            "Cielo Teste Dev Inc.");
    private final PhysicalPerson physicalPerson = new PhysicalPerson(
            "1324",
            "46986719817",
            "Lucas Ferreira Ragazzon",
            "lucas_ragazzon@gmail.com");
    @Mock
    private InMemoryRepo2 inMemoryRepo;
    @InjectMocks
    private ClientsService2 clientsService;

    @Test
    public void getPhysicalList_shouldReturnEmptyList() {

        when(inMemoryRepo.findAllPhysicalClients()).thenReturn(new ArrayList<>());
        var obj = clientsService.getPhysicalClientList();
        assertThat(obj).isEmpty();
    }

    @Test
    public void getPhysicalList_shouldReturnListWithItems() {

        List<PhysicalPerson> registeredClients = new ArrayList<>();
        registeredClients.add(
                physicalPerson
        );

        when(inMemoryRepo.findAllPhysicalClients()).thenReturn(registeredClients);

        var obj = clientsService.getPhysicalClientList();
        assertThat(obj).isNotEmpty();
    }

    @Test
    public void findPhysicalClient_shouldReturnNullIfNotFound() {

        when(inMemoryRepo.findPhysicalBy(any())).thenReturn(null);
        var obj = clientsService.findPhysicalPerson(any());
        assertThat(obj).isNull();
    }

    @Test
    public void getLegalList_shouldReturnEmptyList() {

        when(inMemoryRepo.findAllLegalClients()).thenReturn(new ArrayList<>());
        var obj = clientsService.getLegalClientList();
        assertThat(obj).isEmpty();
    }

    @Test
    public void getLegalList_shouldReturnListWithItems() {

        List<LegalPerson> registeredClients = new ArrayList<>();
        registeredClients.add(
                legalPerson
        );

        when(inMemoryRepo.findAllLegalClients()).thenReturn(registeredClients);

        var obj = clientsService.getLegalClientList();
        assertThat(obj).isNotEmpty();
    }

    @Test
    public void findPhysicalClient_shouldReturnFoundClient() {

        when(inMemoryRepo.findPhysicalBy(any())).thenReturn(physicalPerson);
        var obj = clientsService.findPhysicalPerson(any());
        assertThat(obj.getUUID()).isEqualByComparingTo(physicalPerson.getUUID());
    }

    @Test
    public void findLegalClient_shouldReturnFoundClient() {

        when(inMemoryRepo.findlegalBy(any())).thenReturn(legalPerson);
        var obj = clientsService.findLegalPerson(any());
        assertThat(obj.getUUID()).isEqualByComparingTo(legalPerson.getUUID());
    }

    @Test
    public void registerPhysical_shouldReturnNullIfExists() {

        inMemoryRepo.add(physicalPerson);

        when(inMemoryRepo.add(any(PhysicalPerson.class))).thenReturn(null);
        var obj = clientsService.registerPhysicalPerson(physicalPerson);
        assertThat(obj).isNull();
    }

    @Test
    public void registerPhysical_shouldReturnPersonIfIsSuccessful() {

        when(inMemoryRepo.add(any(PhysicalPerson.class))).thenReturn(physicalPerson);
        var obj = clientsService.registerPhysicalPerson(physicalPerson);
        assertThat(obj).isNotNull();
    }

    @Test
    public void updatePhysical_shouldReturnNullIfDoesntExist() {

        when(inMemoryRepo.update(any(), any(PhysicalPerson.class))).thenReturn(null);
        var obj = clientsService.updatePhysicalPerson(any(), any());
        assertThat(obj).isNull();
    }

    @Test
    public void updatePhysical_shouldReturnPersonIfIsSuccessful() {

        when(inMemoryRepo.update(any(), any(PhysicalPerson.class))).thenReturn(physicalPerson);
        var obj = clientsService.updatePhysicalPerson(physicalPerson.getUUID().toString(), physicalPerson);
        assertThat(obj).isNotNull();
    }

    @Test
    public void registerLegal_shouldReturnNullIfExists() {

        inMemoryRepo.add(legalPerson);

        when(inMemoryRepo.add(any(LegalPerson.class))).thenReturn(null);
        var obj = clientsService.registerLegalPerson(legalPerson);
        assertThat(obj).isNull();
    }

    @Test
    public void registerLegal_shouldReturnPersonIfSuccessful() {

        when(inMemoryRepo.add(any(LegalPerson.class))).thenReturn(legalPerson);
        var obj = clientsService.registerLegalPerson(legalPerson);
        assertThat(obj).isNotNull();
    }


    @Test
    public void updateLegal_shouldReturnNullIfDoesntExist() {

        when(inMemoryRepo.update(any(), any(LegalPerson.class))).thenReturn(null);
        var obj = clientsService.updateLegalPerson(any(), any());
        assertThat(obj).isNull();
    }

    @Test
    public void updateLegal_shouldReturnPersonIfIsSuccessful() {

        when(inMemoryRepo.update(any(), any(LegalPerson.class))).thenReturn(legalPerson);
        var result = clientsService.updateLegalPerson(legalPerson
                .getUUID().toString(), legalPerson);
        assertThat(result).isNotNull();
    }

    @Test
    public void deleteClient_shouldReturnFalseIfNotFound() {

        when(inMemoryRepo.delete(any())).thenReturn(false);
        var obj = clientsService.deleteClient(any());
        assertThat(obj).isFalse();
    }

    @Test
    public void deleteClient_shouldReturnTrueIfSuccessful() {

        inMemoryRepo.add(legalPerson);

        when(inMemoryRepo.delete(any())).thenReturn(true);
        var obj = clientsService.deleteClient(legalPerson.getUUID().toString());
        assertThat(obj).isTrue();
    }

    @Test
    public void getClientFromServiceQueue_shouldReturnNullIfEmpty() {

        when(inMemoryRepo.fromQueue()).thenReturn(null);
        var obj = clientsService.getClientFromQueue();
        assertThat(obj).isNull();
    }

    @Test
    public void getClientFromServiceQueue_shouldReturnClientIfNotEmpty() {

        when(inMemoryRepo.fromQueue()).thenReturn(physicalPerson);
        var obj = clientsService.getClientFromQueue();
        assertThat(obj).isNotNull();
    }

}
