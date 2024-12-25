package Cetas.resgate.Service;

import Cetas.resgate.Dto.ApplicantDto;
import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import Cetas.resgate.Service.Exceptions.ResourceNotFoundException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
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

    public ByteArrayOutputStream generateExcelApplicantReport(List<ResgateDto> resgate) throws IOException {
        // Criar um novo workbook e uma nova planilha
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Relatório de todos os solicitantes");

            // Criacao do cabeçalho
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nome");
            headerRow.createCell(2).setCellValue("Telefone");
            headerRow.createCell(3).setCellValue("Endereço");

            // Preenchimento das linhas com os dados da entidade
            int rowIdx = 1;
            for (ResgateDto entity : resgate) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entity.id());
                row.createCell(1).setCellValue(entity.applicant());
                row.createCell(2).setCellValue(entity.phoneApplicant());
                row.createCell(3).setCellValue(entity.address());
            }
            // Auto ajuste das colunas
            for (int i = 0; i < 4; i++){
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out;
        }
    }

    public List<ApplicantDto> findApplicantByDateRange (LocalDate startDate, LocalDate endDate) {

        List<Resgate> resg = repository.findAll();

        List<ApplicantDto> applicants = resg.stream()
                .filter(resgate -> resgate.getData() != null &&
                        !resgate.getData().isBefore(startDate) &&
                        !resgate.getData().isAfter(endDate))
                .map(resgate -> new ApplicantDto(resgate.getId(), resgate.getApplicant(), resgate.getPhoneApplicant(), resgate.getAddress()))
                .collect(Collectors.toList());

        return applicants;
    }


}
