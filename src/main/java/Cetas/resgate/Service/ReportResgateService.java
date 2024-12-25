package Cetas.resgate.Service;

import Cetas.resgate.Dto.ResgateDto;
import Cetas.resgate.Entities.Resgate;
import Cetas.resgate.Repositories.ResgateRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportResgateService {

    private final ResgateRepository resgateRepository;

    public ReportResgateService(ResgateRepository resgateRepository){
        this.resgateRepository = resgateRepository;
    }


    public List<ResgateDto> findRescuePerSpecieBetweenDates(
            String specie,
            LocalDate startDate,
            LocalDate endDate) {

        List<Resgate> resgates = resgateRepository.findRescuesBySpecieAndDateRange(specie, startDate, endDate);

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


    public ByteArrayOutputStream generateExcelAnimalsPerNameBetweenDatesReport(List<ResgateDto> resgate) throws IOException {
        // Criar um novo workbook e uma nova planilha
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Relatório animail");

            CellStyle dateCellStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            dateCellStyle.setDataFormat(format.getFormat("dd-MM-yyyy"));


            // Criacao do cabeçalho
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Espécie");
            headerRow.createCell(2).setCellValue("Data resgate");
            headerRow.createCell(3).setCellValue("Situação");
            headerRow.createCell(4).setCellValue("Destinação");
            headerRow.createCell(5).setCellValue("Nome");
            headerRow.createCell(6).setCellValue("Telefone");
            headerRow.createCell(7).setCellValue("Endereço");
            headerRow.createCell(8).setCellValue("Cidade");


            // Preenchimento das linhas com os dados da entidade
            int rowIdx = 1;
            for (ResgateDto entity : resgate) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entity.id());
                row.createCell(1).setCellValue(entity.specie());

                Cell dateCell = row.createCell(2);
                row.createCell(2).setCellValue(entity.data());
                dateCell.setCellStyle(dateCellStyle);


                row.createCell(3).setCellValue(entity.animalSituation());
                row.createCell(4).setCellValue(entity.animalDestination());
                row.createCell(5).setCellValue(entity.applicant());
                row.createCell(6).setCellValue(entity.phoneApplicant());
                row.createCell(7).setCellValue(entity.address());
                row.createCell(8).setCellValue(entity.city());
            }

            // Auto ajuste das colunas
            for (int i = 0; i < 9; i++){
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out;
        }
    }

    public List<ResgateDto> findRescueByDateRange (LocalDate startDate, LocalDate endDate){

        List<Resgate> resgates = resgateRepository.findRescueByDateRange(startDate, endDate);

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
