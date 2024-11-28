package Cetas.resgate.Service;

import Cetas.resgate.Dto.ApplicantDto;
import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import Cetas.resgate.Service.Exceptions.DatabaseException;
import Cetas.resgate.Service.Exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResgateService {

    @Autowired
    private ResgateRepository repo;

    public List<ResgateDto> findAll(){
        List<Resgate> resgate = repo.findAll();
        return resgate.stream().map(ResgateDto:: new).collect(Collectors.toList());
    }

    public ResgateDto findById(Long id) {
        Optional<Resgate> resg = repo.findById(id);
        Resgate entity = resg.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ResgateDto(entity);
    }

    public ResgateDto insert(ResgateDto dto) {
        Resgate obj = new Resgate();
        obj.setApplicant(dto.applicant());
        obj.setPhoneApplicant(dto.phoneApplicant());
        obj.setSpecie(dto.specie());
        obj.setAddress(dto.address());
        obj.setNeighborhood(dto.neighborhood());
        obj.setCity(dto.city());
        obj.setData(dto.data());
        obj.setAnimalSituation(dto.animalSituation());
        obj.setAnimalDestination(dto.animalDestination());
        repo.save(obj);
        return new ResgateDto(obj);
    }

    @Transactional
    public ResgateDto update(ResgateDto dto, Long id) {
        try {
            Resgate obj = repo.getReferenceById(id);
            obj.setApplicant(dto.applicant());
            obj.setPhoneApplicant(dto.phoneApplicant());
            obj.setSpecie(dto.specie());
            obj.setAddress(dto.address());
            obj.setNeighborhood(dto.neighborhood());
            obj.setCity(dto.city());
            obj.setData(dto.data());
            obj.setAnimalSituation(dto.animalSituation());
            obj.setAnimalDestination(dto.animalDestination());
            repo.save(obj);
            return new ResgateDto(obj);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        if(!repo.existsById(id)){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    public ApplicantDto applicantReport(Long id) {
        Optional<Resgate> resg = repo.findById(id);
        Resgate applicant = resg.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ApplicantDto(applicant);
    }

    public List<ApplicantDto> applicantReportList() {
        List<Resgate> resg = repo.findAll();
        return resg.stream().map(ApplicantDto::new).collect(Collectors.toList());
    }
}
