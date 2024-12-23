package Cetas.resgate.Resources;


import Cetas.resgate.Dto.ApplicantDto;
import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Service.ApplicantReportService;
import Cetas.resgate.Service.ReportResgateService;
import Cetas.resgate.Service.ResgateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value="/resgates")
@CrossOrigin(origins = "http://localhost:5173") //Permite o acesso apenas do frontend
public class ResgateResource {

    @Autowired
    private ResgateService service;
    @Autowired
    private ReportResgateService reportService;
    @Autowired
    private ApplicantReportService applicantReportService;

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


    @GetMapping("/report/applicant")
    public ResponseEntity<List<ApplicantDto>> allApplicant(){
        List<ApplicantDto> listDtos = applicantReportService.applicantReportList();
        return ResponseEntity.ok(listDtos);
    }

    // retornar dados apenas do solicitante
    @GetMapping("/report/applicant/{id}")
    public ResponseEntity<ApplicantDto> applicant(@PathVariable Long id){
        ApplicantDto dto = applicantReportService.applicantReport(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/report/applicant/excel")
    public ResponseEntity<byte[]> downloadExcelReport() throws IOException {
        List<ResgateDto> entities = service.findAll();

        ByteArrayOutputStream out = applicantReportService.generateExcelReport(entities);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=relatorio-todos-solicitantes.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(out.toByteArray());
    }
    @GetMapping("/report/applicant-between-dates")
    public ResponseEntity<List<ApplicantDto>> findApplicantByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){

        List<ApplicantDto> listApplicants = applicantReportService.findApplicantByDateRange(startDate, endDate);

        return ResponseEntity.ok(listApplicants);
    }

    @GetMapping("/list-animalsName-between-dates")
    public List<ResgateDto> buscarResgates(
            @RequestParam String especie,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {

        return reportService.buscarResgatesPorEspecieEIntervaloDeDatas(especie, dataInicio, dataFim);
    }

    @GetMapping("/list-animalsName-between-dates/export")
    public ResponseEntity<byte[]> downloadExcelAnimalReport( @RequestParam String especie, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim ) throws IOException {

        List<ResgateDto> resgates = reportService.buscarResgatesPorEspecieEIntervaloDeDatas(especie, dataInicio, dataFim);

        ByteArrayOutputStream out = reportService.generateExcelAnimalsPerNameBetweenDatesReport(resgates);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=relatorio animal data " + dataInicio + " e " + dataFim +".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(out.toByteArray());
    }

    @GetMapping("/list-rescue-between-dates")
    public List<ResgateDto> findRescueByDateRange (
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        
        return reportService.findRescueByDateRange(startDate, endDate);
    }

}
