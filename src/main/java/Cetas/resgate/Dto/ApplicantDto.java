package Cetas.resgate.Dto;

import Cetas.resgate.Entities.Resgate;

public record ApplicantDto(
        Long id,
        String applicant,
        String phoneApplicant,
        String address
) {

    public ApplicantDto(Resgate applicant){
        this(
                applicant.getId(),
                applicant.getApplicant(),
                applicant.getPhoneApplicant(),
                applicant.getAddress()
        );
    }
}
