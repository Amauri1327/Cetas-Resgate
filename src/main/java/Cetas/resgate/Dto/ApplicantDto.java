package Cetas.resgate.Dto;

import Cetas.resgate.Entities.Resgate;

import java.time.LocalDate;

public record ApplicantDto(
        Long id,
        String applicant,
        String phoneApplicant,
        String address,
        LocalDate data
) {

    public ApplicantDto(Resgate applicant){
        this(
                applicant.getId(),
                applicant.getApplicant(),
                applicant.getPhoneApplicant(),
                applicant.getAddress(),
                applicant.getData()
        );
    }
}
