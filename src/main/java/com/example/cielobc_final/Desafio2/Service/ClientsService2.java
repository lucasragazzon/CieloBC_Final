package com.example.cielobc_final.Desafio2.Service;

import com.example.cielobc_final.Desafio1.Model.Client;
import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio2.Repository.InMemoryRepo2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsService2 {

    private final InMemoryRepo2 inMemoryRepo;

    public ClientsService2(InMemoryRepo2 inMemoryRepo){
        this.inMemoryRepo = inMemoryRepo;
    }

    public List<PhysicalPerson> getPhysicalClientList(){
         return inMemoryRepo.findAllPhysicalClients();
    }

    public List<LegalPerson> getLegalClientList(){
        return inMemoryRepo.findAllLegalClients();
    }

    public PhysicalPerson findPhysicalPerson(String UUID) {
        return inMemoryRepo.findPhysicalBy(UUID);
    }

    public LegalPerson findLegalPerson(String UUID) {
        return inMemoryRepo.findlegalBy(UUID);
    }

    public PhysicalPerson registerPhysicalPerson (PhysicalPerson payload) {
        return  inMemoryRepo.add(payload);
    }

    public LegalPerson registerLegalPerson (LegalPerson payload){
        return inMemoryRepo.add(payload);
    }

    public PhysicalPerson updatePhysicalPerson(String UUID, PhysicalPerson payload){
        return inMemoryRepo.update(UUID, payload);
    }

    public LegalPerson updateLegalPerson(String UUID, LegalPerson payload){
        return inMemoryRepo.update(UUID, payload);
    }

    public boolean deleteClient(String UUID) {
        return inMemoryRepo.delete(UUID);
    }

    public Client getClientFromQueue() {
        return inMemoryRepo.fromQueue();
    }
}
