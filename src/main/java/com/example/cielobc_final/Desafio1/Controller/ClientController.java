package com.example.cielobc_final.Desafio1.Controller;

import com.example.cielobc_final.Desafio1.Model.LegalPerson;
import com.example.cielobc_final.Desafio1.Model.PhysicalPerson;
import com.example.cielobc_final.Desafio1.Services.ClientsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Clients", description = ("Register, Update, Query and Delete clients"))
@RestController
@RequestMapping(value = "/api/D1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientsService clientService;

    @GetMapping("/physical_Person")
    public ResponseEntity<List<PhysicalPerson>> indexAllPhysicalClients(){
        return new ResponseEntity<>(clientService.getPhysicalClientList(), HttpStatus.OK);
    }

    @GetMapping("/legal_Person")
    public ResponseEntity<List<LegalPerson>> indexAllLegalPerson(){
        return new ResponseEntity<>(clientService.getLegalClientList(), HttpStatus.OK);
    }

    @GetMapping("/physical_Person/{UUID}")
    public ResponseEntity<Object> showPhysicalClient(@PathVariable("UUID") String UUID){
        PhysicalPerson clients = clientService.findPhysicalPerson(UUID);
        if (clients == null){
            return new ResponseEntity<>("This person was not found in our system", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/legal_Person/{UUID}")
    public ResponseEntity<Object> showLegalClient(@PathVariable("UUID") String UUID){
        LegalPerson clients = clientService.findLegalPerson(UUID);
        if (clients == null){
            return new ResponseEntity<>("This company was not found in our system", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/physical_Person/register")
    public ResponseEntity<String> registerPhysicalPerson(@Valid @RequestBody PhysicalPerson payload){
        var obj = clientService.registerPhysicalPerson(payload);
        if(obj == null){
             return new ResponseEntity<>("This person is already registered in our system, try a new one", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Registration Completed", HttpStatus.CREATED);
    }

    @PostMapping("/legal_Person/register")
    public ResponseEntity<String> registerLegalPerson(@Valid @RequestBody LegalPerson payload){
        var obj = clientService.registerLegalPerson(payload);
        if(obj == null){
            return new ResponseEntity<>("This company is already registered in our system, try a new one", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Registration Completed", HttpStatus.CREATED);
    }

    @PutMapping("physical_Person/update/{UUID}")
    public ResponseEntity<String> updatePhysicalPerson( @PathVariable("UUID") String UUID, @Valid @RequestBody PhysicalPerson payload){
        var obj = clientService.updatePhysicalPerson(UUID, payload);
        if(obj == null){
            return new ResponseEntity<>("Person not found in the system, update failed", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Updated person successfully", HttpStatus.OK);
    }

    @PutMapping("legal_Person/update/{UUID}")
    public ResponseEntity<String> updateLegalPerson( @PathVariable("UUID") String UUID, @Valid @RequestBody LegalPerson payload){
        var obj = clientService.updateLegalPerson(UUID, payload);
        if(obj == null){
            return new ResponseEntity<>("Company not found in the system, update failed", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Updated company successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{UUID}")
    public ResponseEntity<String>  deleteClients (@PathVariable("UUID") String UUID){
        boolean result = clientService.deleteClient(UUID);
        if (!result){
            return new ResponseEntity<>("Registry not found, nothing was deleted", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Client was successfully removed", HttpStatus.OK);
    }
}
