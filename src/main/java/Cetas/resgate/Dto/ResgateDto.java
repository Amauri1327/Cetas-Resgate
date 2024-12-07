package Cetas.resgate.Dto;

import Cetas.resgate.Entities.Resgate;

import java.time.LocalDate;

public record ResgateDto(

        Long id,
        String applicant,
        String phoneApplicant,
        String specie,
        String address,
        String neighborhood,
        String city,
        LocalDate data,
        String animalSituation,
        String animalDestination)
{
    public ResgateDto (Resgate reg){
        this(
                reg.getId(),
                reg.getApplicant(),
                reg.getPhoneApplicant(),
                reg.getSpecie(),
                reg.getAddress(),
                reg.getNeighborhood(),
                reg.getCity(),
                reg.getData(),
                reg.getAnimalSituation(),
                reg.getAnimalDestination()
        );
    }
}
