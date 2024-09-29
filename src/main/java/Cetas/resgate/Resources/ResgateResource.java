package Cetas.resgate.Resources;


import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Service.ResgateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/resgates")
public class ResgateResource {

    @Autowired
    private ResgateService service;

    @GetMapping()
    public ResponseEntity<List<ResgateDto>> findAll(){
        List<ResgateDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResgateDto> findById(@PathVariable Long id){
        ResgateDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

}
