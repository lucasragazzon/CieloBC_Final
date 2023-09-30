package com.example.cielobc_final.Desafio1.Tests;

import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio1.Repository.InMemoryRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InMemoryRepoTests {

    @Autowired
    InMemoryRepo inMemoryRepo;

    @Test
    public void findAllPhysical_shouldReturnEmptyListWhenNotFound(){
        inMemoryRepo.clear();
        var obj = inMemoryRepo.findAllPhysicalClients();
        Assertions.assertThat(obj).isEmpty();
    }

    @Test
    public void findAllPhysical_shouldReturnListWithClientsWhenFound() {

        PhysicalPerson physicalPerson = new PhysicalPerson(
                "1324",
                "46986719817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragazzon@hotmail.com"
        );

        inMemoryRepo.add(physicalPerson);

        var obj = inMemoryRepo.findAllPhysicalClients();
        Assertions.assertThat(obj).isNotEmpty();
        inMemoryRepo.clear();
    }

    @Test
    public void findAllLegal_shouldReturnEmptyListWhenNotFound(){

        inMemoryRepo.clear();
        var obj = inMemoryRepo.findAllLegalClients();
        Assertions.assertThat(obj).isEmpty();
    }

    @Test
    public void findAllLegal_shouldReturnWithClientsWhenFound(){

        LegalPerson legalPerson = new LegalPerson(
                "1432",
                "46986719817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragaazzon@enterprise.com.br",
                "12345678000100",
                "Cielo Teste Dev Inc."
        );

        inMemoryRepo.add(legalPerson);
        var obj = inMemoryRepo.findAllLegalClients();
        Assertions.assertThat(obj).isNotEmpty();
        inMemoryRepo.clear();
    }

    @Test
    public void findPhysicalBy_shouldReturnNullIfNotFound(){
        var obj = inMemoryRepo.findPhysicalBy(UUID.randomUUID().toString());
        Assertions.assertThat(obj).isNull();
        inMemoryRepo.clear();
    }

    @Test
    public void add_legalPerson_shouldReturnNullIfExists(){

        LegalPerson legalPerson = new LegalPerson(
                "1432",
                "46986791817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragazzon@enterprise.com.br",
                "12345678000100",
                "Cielo Teste Dev Inc."
        );

        inMemoryRepo.add(legalPerson);
        var obj = inMemoryRepo.add(legalPerson);
        Assertions.assertThat(obj).isNull();
        inMemoryRepo.clear();

    }

    @Test
    public void update_physicalPerson_shouldReturnNullIfNotFound(){

        PhysicalPerson physicalPerson = new PhysicalPerson(
                "1324",
                "46986719817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragazzon@hotmail.com"
        );

        var obj = inMemoryRepo.update(physicalPerson.getUUID().toString(), physicalPerson);
        Assertions.assertThat(obj).isNull();
        inMemoryRepo.clear();
    }

    @Test
    public void update_legalPerson_shouldReturnNullIfNotFound(){
        LegalPerson legalPerson = new LegalPerson(
                "1423",
                "46986719817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragazzon@enterprise.com.br",
                "12345678000100",
                "Cielo Teste Dev Inc."
        );

        var obj = inMemoryRepo.update(legalPerson.getUUID().toString(),legalPerson);
        Assertions.assertThat(obj).isNull();
        inMemoryRepo.clear();
    }

    @Test
    public void update_legalPerson_shouldReturnPersonIfIsSuccess(){

        LegalPerson legalPerson = new LegalPerson(
                "1423",
                "46986719817",
                "Lucas Ferreira Ragazzon",
                "lucas_ragazzon@enterprise.com.br",
                "12345678000100",
                "Cielo Teste Dev Inc."
        );

        var obj = inMemoryRepo.update(legalPerson.getUUID().toString(),legalPerson);
        Assertions.assertThat(obj).isNull();
        inMemoryRepo.clear();
    }

}
