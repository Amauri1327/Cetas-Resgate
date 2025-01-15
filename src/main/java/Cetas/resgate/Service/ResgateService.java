package Cetas.resgate.Service;

import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import Cetas.resgate.Service.Exceptions.DatabaseException;
import Cetas.resgate.Service.Exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResgateService {

    @Autowired
    private ResgateRepository repository;

    public List<ResgateDto> findAll(){
        List<Resgate> resgate = repository.findAll();
        return resgate.stream().map(ResgateDto:: new).collect(Collectors.toList());
    }

    public Page<ResgateDto> findByIdPageable(Pageable pageable) {
        Page<Resgate> resgates = repository.findAll(pageable);
        return resgates.map(ResgateDto::new);
    }

    public ResgateDto findById(Long id) {
        Optional<Resgate> resg = repository.findById(id);
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
        obj.setAnimalQuantity(dto.animalQuantity());
        obj.setOrigin(dto.origin());
        repository.save(obj);
        return new ResgateDto(obj);
    }

    @Transactional
    public ResgateDto update(ResgateDto dto, Long id) {
        try {
            Resgate obj = repository.getReferenceById(id);
            obj.setApplicant(dto.applicant());
            obj.setPhoneApplicant(dto.phoneApplicant());
            obj.setSpecie(dto.specie());
            obj.setAddress(dto.address());
            obj.setNeighborhood(dto.neighborhood());
            obj.setCity(dto.city());
            obj.setData(dto.data());
            obj.setAnimalSituation(dto.animalSituation());
            obj.setAnimalDestination(dto.animalDestination());
            obj.setAnimalQuantity(dto.animalQuantity());
            obj.setOrigin(dto.origin());
            repository.save(obj);
            return new ResgateDto(obj);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

}
