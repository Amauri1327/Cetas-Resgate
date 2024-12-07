package Cetas.resgate.Service;

import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    private final ResgateRepository resgateRepository;

    public ReportService(ResgateRepository resgateRepository){
        this.resgateRepository = resgateRepository;
    }

    public ByteArrayOutputStream generateExcelReport(List<ResgateDto> resgate) throws IOException {
        // Criar um novo workbook e uma nova planilha
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Relatório");

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

    public List<ResgateDto> buscarResgatesPorEspecieEIntervaloDeDatas(
            String especie,
            LocalDate dataInicio,
            LocalDate dataFim) {

        List<Resgate> resgates = resgateRepository.findRescuesBySpecieAndDateRange(especie, dataInicio, dataFim);

        return resgates.stream()
                .map(resgate -> new ResgateDto(
                  resgate.getId(),
                  resgate.getApplicant(),
                    resgate.getPhoneApplicant(),
                    resgate.getSpecie(),
                    resgate.getAddress(),
                    resgate.getNeighborhood(),
                    resgate.getCity(),
                    resgate.getData(),
                    resgate.getAnimalSituation(),
                        resgate.getAnimalDestination()))
                .toList();

    }
}
