/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author steli
 */
import java.util.HashMap;
import java.util.Map;
import Model.Viagem;

public class ViagemController {
    private Map<String, Viagem> viagens = new HashMap<>();

    public Viagem criarViagem(String rota, String horario, double preco, int capacidade) {
        Viagem viagem = new Viagem(rota, horario, preco, capacidade);
        viagens.put(viagem.getId(), viagem);
        return viagem;
    }

    public Viagem buscarViagem(String id) {
        return viagens.get(id);
    }

    public boolean verificarDisponibilidade(String id) {
        Viagem viagem = viagens.get(id);
        return viagem != null && viagem.verificarDisponibilidade();
    }

    public boolean atualizarLugaresDisponiveis(String id, int qtd) {
        Viagem viagem = viagens.get(id);
        if (viagem != null) {
            viagem.atualizarLugaresDisponiveis(qtd);
            return true;
        }
        return false;
    }
}
