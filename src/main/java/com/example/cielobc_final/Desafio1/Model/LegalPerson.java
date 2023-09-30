package com.example.cielobc_final.Desafio1.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class LegalPerson extends Client {


    @Pattern(regexp = "\\d{14}", message = "Invalid format for CNPJ")
    @NotNull(message = "The CNPJ may not be empty")
    @Schema(example = "12345678000100")
    private String CNPJ;

    @Size( max = 50, message = "The Company name may not contain over 50 digits")
    @NotNull (message = "Company name may not be null")
    @Schema(example = "Testing Inc.")
    private String LegalName;

    public LegalPerson(
            String MCC,
            String CPF,
            String Name,
            String Email,
            String CNPJ,
            String LegalName ) {
                super(MCC,CPF, Name, Email);
                this.CNPJ = CNPJ;
                this.LegalName = LegalName;
    }
}

