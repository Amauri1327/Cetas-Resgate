package Cetas.resgate.Resources;


import Cetas.resgate.Dto.ApplicantDto;
import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Service.ResgateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<ResgateDto> insert(@RequestBody ResgateDto dto){
        dto = service.insert(dto);
        return ResponseEntity.ok().body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResgateDto> update(@PathVariable Long id, @RequestBody ResgateDto dto){
        dto = service.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResgateDto> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // retornar dados apenas do solicitante
    @GetMapping("/report/{id}")
    public ResponseEntity<ApplicantDto> applicant(@PathVariable Long id){
        ApplicantDto dto = service.applicantReport(id);
        return ResponseEntity.ok().body(dto);
    }


}
