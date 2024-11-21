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
import Model.Transportadora;
import Model.Viagem;

public class TransportadoraController {
    private Map<String, Transportadora> transportadoras = new HashMap<>();

    public Transportadora cadastrarTransportadora(String nome, String cnpj, String endereco) {
        Transportadora transportadora = new Transportadora(nome, cnpj, endereco);
        transportadoras.put(transportadora.getId(), transportadora);
        return transportadora;
    }

    public Transportadora buscarTransportadora(String id) {
        return transportadoras.get(id);
    }

    public void adicionarViagem(String idTransportadora, Viagem viagem) {
        Transportadora transportadora = transportadoras.get(idTransportadora);
        if (transportadora != null) {
            transportadora.adicionarViagem(viagem);
        }
    }

    public void listarViagens(String idTransportadora) {
        Transportadora transportadora = transportadoras.get(idTransportadora);
        if (transportadora != null) {
            for (Viagem viagem : transportadora.listarViagens()) {
                System.out.println(viagem);
            }
        }
    }
}

