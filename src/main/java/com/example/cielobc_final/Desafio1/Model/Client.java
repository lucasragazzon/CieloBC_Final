package com.example.cielobc_final.Desafio1.Model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Getter
@Setter
@ToString
public abstract class Client {

    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private UUID UUID;

    @Schema(example = "1234")
    @Pattern(regexp = "\\d{4}", message = "MCC field may only contain 4 digits")
    @NotNull (message = "The MCC may not be null")
    private String MCC;

    @Schema(example = "45989711513")
    @Pattern( regexp = "\\d{11}", message =  "Please insert an valid CPF")
    @NotNull (message = "The CPF may not be blank")
    private String CPF;

    @Schema(example = "Your Name")
    @Size(max = 50, message = "Please type a name, it may have less than 50 characters")
    @NotNull (message = "The Name field cannot be empty")
    private String Name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)[^.]@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "Not an valid E-mail") //Regex está diferente do proposto no Desáfio devido a erros do regex proposto
    @NotNull (message = "The e-mail field cannot be empty")
    @Schema (example = "validemail@gmail.com")
    private String Email;

    public Client(String MCC, String CPF, String Name, String Email){
        this.UUID = java.util.UUID.randomUUID();
        this.MCC = MCC;
        this.CPF = CPF;
        this.Name = Name;
        this.Email = Email;
    }
}
