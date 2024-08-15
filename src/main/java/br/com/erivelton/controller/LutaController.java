package br.com.erivelton.controller;

import br.com.erivelton.model.Lutador;
import br.com.erivelton.service.ExcelService;
import br.com.erivelton.service.LutaService;
import br.com.erivelton.service.PlanilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lutas")
@CrossOrigin(origins = "*")  // Permitir CORS para desenvolvimento local
public class LutaController {

    @Autowired
    private PlanilhaService planilhaService;
    @Autowired
    private LutaService lutaService;
    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public List<List<Lutador>> uploadPlanilha(@RequestParam("file") MultipartFile file) throws IOException {
        List<Lutador> lutadores = planilhaService.lerPlanilha(file);
        return lutaService.gerarChaves(lutadores);
    }

   /* @GetMapping("/export")
    public ResponseEntity<byte[]> exportLutasToExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<Lutador> lutadores = planilhaService.lerPlanilha(file);
        List<List<Lutador>> chaves = lutaService.gerarChaves(lutadores);

        ByteArrayInputStream in = excelService.exportLutasToExcel(chaves);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=lutas.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(in.readAllBytes());
    }*/
}