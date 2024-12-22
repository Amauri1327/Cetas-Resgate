package Cetas.resgate.Service;

import Cetas.resgate.Dto.ApplicantDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import Cetas.resgate.Service.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantReportService {

    private ResgateRepository repository;

    public ApplicantReportService(ResgateRepository resgateRepository) {
        this.repository = resgateRepository;
    }

    public ApplicantDto applicantReport(Long id) {
        Optional<Resgate> resg = repository.findById(id);
        Resgate applicant = resg.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ApplicantDto(applicant);
    }

    public List<ApplicantDto> applicantReportList() {
        List<Resgate> resg = repository.findAll();
        return resg.stream().map(ApplicantDto::new).collect(Collectors.toList());
    }


}
