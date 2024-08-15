package br.com.erivelton.service;

import br.com.erivelton.model.Lutador;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PlanilhaService {

    public List<Lutador> lerPlanilha(MultipartFile file) throws IOException {
        List<Lutador> lutadores = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

       /* while (rows.hasNext()) {
            Row currentRow = rows.next();
            Lutador lutador = new Lutador();
            lutador.setNome(currentRow.getCell(0).getStringCellValue());
            lutador.setAcademia(currentRow.getCell(1).getStringCellValue());
            lutador.setPeso(currentRow.getCell(2).getNumericCellValue());
            lutador.setFaixa(currentRow.getCell(3).getStringCellValue());
            lutador.setIdade((int) currentRow.getCell(4).getNumericCellValue());
            lutador.setGenero(currentRow.getCell(5).getStringCellValue());
            lutadores.add(lutador);
        }*/

        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // Ignorar a primeira linha se for o cabeçalho
            if (currentRow.getRowNum() == 0) {
                continue;
            }

            Lutador lutador = new Lutador();

            Cell nomeCell = currentRow.getCell(0);
            Cell academiaCell = currentRow.getCell(1);
            Cell pesoCell = currentRow.getCell(2);
            Cell faixaCell = currentRow.getCell(3);
            Cell idadeCell = currentRow.getCell(4);
            Cell generoCell = currentRow.getCell(5);

            lutador.setNome(nomeCell != null ? nomeCell.getStringCellValue() : "");
            lutador.setAcademia(academiaCell != null ? academiaCell.getStringCellValue() : "");
            lutador.setPeso(pesoCell != null ? pesoCell.getNumericCellValue() : 0);
            lutador.setFaixa(faixaCell != null ? faixaCell.getStringCellValue() : "");
            lutador.setIdade(idadeCell != null ? (int) idadeCell.getNumericCellValue() : 0);
            lutador.setGenero(generoCell != null ? generoCell.getStringCellValue() : "");

            lutadores.add(lutador);
        }
        workbook.close();
        return lutadores;
    }
}
