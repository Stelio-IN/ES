/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.UUID;

/**
 *
 * @author steli
 */
public class Viagem {
    private String id;
    private String rota; 
    private String horario;
    private double preco;
    private int capacidade;
    private int lugaresDisponiveis;

    public Viagem(String rota, String horario, double preco, int capacidade) {
        this.id = UUID.randomUUID().toString();
        this.rota = rota;
        this.horario = horario;
        this.preco = preco;
        this.capacidade = capacidade;
        this.lugaresDisponiveis = capacidade;
    }

    public boolean verificarDisponibilidade() {
        return lugaresDisponiveis > 0;
    }

    public void atualizarLugaresDisponiveis(int qtd) {
        if (lugaresDisponiveis - qtd >= 0) {
            lugaresDisponiveis -= qtd;
        } else {
            System.out.println("Não há lugares suficientes disponíveis.");
        }
    }

    // Getters e Setters omitidos para brevidade
}

