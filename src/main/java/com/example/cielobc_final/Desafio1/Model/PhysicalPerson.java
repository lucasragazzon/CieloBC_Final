package com.example.cielobc_final.Desafio1.Model;

public class PhysicalPerson extends Client {

    public PhysicalPerson(
            String MCC,
            String CPF,
            String Name,
            String Email ) {
        super(MCC, CPF, Name, Email);
    }

    public String toString(){
        return "PhysicalPerson{} " + super.toString();
    }
}
