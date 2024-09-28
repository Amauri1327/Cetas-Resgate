package Cetas.resgate.Service;

import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ResgateService {

    @Autowired
    private ResgateRepository repo;

    public List<ResgateDto> findAll(){
        List<Resgate> resgate = repo.findAll();
        return resgate.stream().map(ResgateDto:: new).collect(Collectors.toList());
    }

}
