package Cetas.resgate.Service;

import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Resgate entity = resg.orElseThrow(() -> new RuntimeException("Entity not found"));
        return new ResgateDto(entity);
    }

    public ResgateDto insert(ResgateDto dto) {
        Resgate obj = new Resgate();
        obj.setApplicant(dto.applicant());
        obj.setPhoneApplicant(dto.phoneApplicant());
        obj.setSpecie(dto.specie());
        obj.setAddress(dto.address());
        obj.setCity(dto.city());
        obj.setData(dto.data());
        obj.setAnimalSituation(dto.animalSituation());
        obj.setAnimalDestination(dto.animalDestination());
        repo.save(obj);
        return new ResgateDto(obj);
    }
}
