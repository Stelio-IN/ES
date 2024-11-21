/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author steli
 */
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transportadora {
    private String id;
    private String nome;
    private String cnpj;
    private String endereco;
    private List<Viagem> viagens;

    public Transportadora(String nome, String cnpj, String endereco) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.viagens = new ArrayList<>();
    }

    public List<Viagem> listarViagens() {
        return viagens;
    }

    public void adicionarViagem(Viagem viagem) {
        viagens.add(viagem);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }


}
