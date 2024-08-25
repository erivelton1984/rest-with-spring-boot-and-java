package br.com.erivelton.service;

import br.com.erivelton.model.Lutador;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public ByteArrayInputStream exportLutasToExcel(List<List<Lutador>> chaves) throws IOException {
        String[] columns = {"Lutador 1", "Academia 1", "Peso 1", "Faixa 1", "Idade 1", "Gênero 1", "Lutador 2", "Academia 2", "Peso 2", "Faixa 2", "Idade 2", "Gênero 2"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Lutas");

            // Criação do cabeçalho
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Preenchendo os dados
            int rowIdx = 1;
            for (List<Lutador> chave : chaves) {
                Row row = sheet.createRow(rowIdx++);

                Lutador lutador1 = chave.get(0);
                Lutador lutador2 = chave.size() > 1 ? chave.get(1) : null;

                row.createCell(0).setCellValue(lutador1.getNome());
                row.createCell(1).setCellValue(lutador1.getAcademia());
                row.createCell(2).setCellValue(lutador1.getPeso());
                row.createCell(3).setCellValue(lutador1.getFaixa());
                row.createCell(4).setCellValue(lutador1.getKeyade());
                row.createCell(5).setCellValue(lutador1.getGenero());

                if (lutador2 != null) {
                    row.createCell(6).setCellValue(lutador2.getNome());
                    row.createCell(7).setCellValue(lutador2.getAcademia());
                    row.createCell(8).setCellValue(lutador2.getPeso());
                    row.createCell(9).setCellValue(lutador2.getFaixa());
                    row.createCell(10).setCellValue(lutador2.getKeyade());
                    row.createCell(11).setCellValue(lutador2.getGenero());
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}