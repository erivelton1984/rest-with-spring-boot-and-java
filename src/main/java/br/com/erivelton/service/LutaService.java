package br.com.erivelton.service;

import br.com.erivelton.model.Lutador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LutaService {

    public List<List<Lutador>> gerarChaves(List<Lutador> lutadores) {
        List<Lutador> lutadoresMasculinos = lutadores.stream()
                .filter(lutador -> "Masculino".equalsIgnoreCase(lutador.getGenero()))
                .collect(Collectors.toList());

        List<Lutador> lutadoresFemininos = lutadores.stream()
                .filter(lutador -> "Feminino".equalsIgnoreCase(lutador.getGenero()))
                .collect(Collectors.toList());

        List<List<Lutador>> chavesMasculinas = gerarChavesPorGenero(lutadoresMasculinos);
        List<List<Lutador>> chavesFemininas = gerarChavesPorGenero(lutadoresFemininos);

        List<List<Lutador>> todasAsChaves = new ArrayList<>();
        todasAsChaves.addAll(chavesMasculinas);
        todasAsChaves.addAll(chavesFemininas);

        return todasAsChaves;
    }

    private List<List<Lutador>> gerarChavesPorGenero(List<Lutador> lutadores) {
        List<List<Lutador>> chaves = new ArrayList<>();

        for (int i = 0; i < lutadores.size(); i++) {
            for (int j = i + 1; j < lutadores.size(); j++) {
                Lutador lutador1 = lutadores.get(i);
                Lutador lutador2 = lutadores.get(j);

                if (Math.abs(lutador1.getPeso() - lutador2.getPeso()) <= 10 &&
                        Math.abs(lutador1.getIdade() - lutador2.getIdade()) <= 5) {
                    List<Lutador> chave = new ArrayList<>();
                    chave.add(lutador1);
                    chave.add(lutador2);
                    chaves.add(chave);
                }
            }
        }

        return chaves;
    }
}
        //List<List<Lutador>> chaves = new ArrayList<>();
        // Implementar a lógica para casar os lutadores
        /*for (int i = 0; i < lutadores.size(); i++) {
            for (int j = i + 1; j < lutadores.size(); j++) {
                Lutador lutador1 = lutadores.get(i);
                Lutador lutador2 = lutadores.get(j);
                if (Math.abs(lutador1.getPeso() - lutador2.getPeso()) <= 5 &&
                    Math.abs(lutador1.getIdade() - lutador2.getIdade()) <= 5){
                    List<Lutador> chave = new ArrayList<>();
                    chave.add(lutador1);
                    chave.add(lutador2);
                    chaves.add(chave);
                }
            }
        }
        return chaves;*/
