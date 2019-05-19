package it.akademija.institutions.service;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


/**
 * Sitoje klaseje aprasome visoms institucijoms bendrus laukus
 * Ja reikia pazymeti "abstract" kad nebandytume jos kurti. Ja galima naudoti tik paveldint.
 * lombok.Data anotacija duos mums getterius ir setterius
 */
@Data
abstract class InstitutionServiceObject {
    @NotNull
    @Length(min = 1, max = 200)
    private String name;

    @NotNull
    @Length(min = 1, max = 200)
    private String code;

}
