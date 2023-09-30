package com.example.cielobc_final.Desafio2.Repository;

import com.example.cielobc_final.Desafio1.Model.Client;
import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio2.ClientQueue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryRepo2 {

     private final ClientQueue<Client> serviceQueue = new ClientQueue<>(100);
     private final List<PhysicalPerson> registeredPhysicalClients = new ArrayList<>();
     private final List<LegalPerson> registeredLegalClients = new ArrayList<>();

     public void clear() {
         registeredPhysicalClients.clear();
         registeredLegalClients.clear();
     }

     public Client fromQueue(){
         return serviceQueue.dequeue();
     }
     public List<PhysicalPerson> findAllPhysicalClients(){
         return registeredPhysicalClients;
     }

     public List<LegalPerson> findAllLegalClients(){
         return registeredLegalClients;
     }



    public PhysicalPerson findPhysicalBy(String UUID){
         List<PhysicalPerson> foundClient = registeredPhysicalClients.stream().filter((obj) ->
                 obj.getUUID().equals(java.util.UUID.fromString(UUID))).toList();

         if (!foundClient.isEmpty()){
             return null;
         }

         return foundClient.get(0);
     }

    public LegalPerson findlegalBy(String UUID){
        List<LegalPerson> foundClient = registeredLegalClients.stream().filter((obj) ->
                obj.getUUID().equals(java.util.UUID.fromString(UUID))).toList();

        if (!foundClient.isEmpty()){
            return null;
        }

        return foundClient.get(0);
    }

     public PhysicalPerson add(PhysicalPerson payload) {
         List<PhysicalPerson> foundClient = registeredPhysicalClients.stream().filter((obj) ->
                 obj.getCPF().equals(payload.getCPF())).toList();

         if (!foundClient.isEmpty()){
             return null;
         }

         var PhysicalPerson = new PhysicalPerson(
                 payload.getMCC(),
                 payload.getCPF(),
                 payload.getEmail(),
                 payload.getName());

         registeredPhysicalClients.add(PhysicalPerson);
         return PhysicalPerson;
     }

     public LegalPerson add (LegalPerson payload) {
         for (var Clients : registeredLegalClients){
             if(Clients.getCNPJ().equals(payload.getCNPJ())){
                 return null;
             }
         }

         payload.setUUID(UUID.randomUUID());
         registeredLegalClients.add(payload);
         return payload;
     }

     public PhysicalPerson update(String UUID, PhysicalPerson payload){
         List<PhysicalPerson> foundClient = registeredPhysicalClients.stream().filter((obj)-> obj.getUUID().equals(java.util.UUID.fromString(UUID))).toList();

         if (foundClient.isEmpty()){
             return null;
         }

         var client = foundClient.get(0);
         client.setMCC(payload.getMCC());
         client.setCPF(payload.getCPF());
         client.setName(payload.getName());
         client.setEmail(payload.getEmail());

         registeredPhysicalClients.remove(client);
         registeredPhysicalClients.add(client);
         return client;
     }


     public LegalPerson update(String UUID, LegalPerson payload){
         List<LegalPerson> clientFound = registeredLegalClients.stream().filter((obj)->
                 obj.getUUID().equals(java.util.UUID.fromString(UUID))).toList();

         if(clientFound.isEmpty()){
             return null;
         }

         var client = clientFound.get(0);
         client.setMCC(payload.getMCC());
         client.setCPF(payload.getCPF());
         client.setEmail(payload.getEmail());
         client.setName(payload.getName());
         client.setCNPJ(payload.getCNPJ());
         client.setLegalName(payload.getLegalName());

         registeredLegalClients.remove(client);
         registeredLegalClients.add(client);
         serviceQueue.enqueue(client);
         return client;
     }

     public boolean delete(String UUID){
         List<PhysicalPerson> foundPhysicalPerson = registeredPhysicalClients.stream().filter((obj) ->
                 obj.getUUID().equals(java.util.UUID.fromString(UUID))).toList();

         List<LegalPerson> foundLegalPerson = registeredLegalClients.stream().filter((obj) ->
                 obj.getUUID().equals(java.util.UUID.fromString(UUID))).toList();


         if(!foundPhysicalPerson.isEmpty()){
             registeredPhysicalClients.remove(foundPhysicalPerson.get(0));
             return true;
         } else if (!foundLegalPerson.isEmpty()) {
             registeredLegalClients.remove(foundLegalPerson.get(0));
             return true;
         }
         return false;
     }




}
