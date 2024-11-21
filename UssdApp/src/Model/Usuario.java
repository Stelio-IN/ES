/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author steli
 */
import java.util.UUID;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public Usuario(String nome, String email, String senha, String telefone) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.senha = senha; // Aqui você pode aplicar hashing
        this.telefone = telefone;
    }

    public boolean verificarSenha(String senha) {
        // Aqui, você deve implementar a verificação com hash
        return this.senha.equals(senha);
    }

    public String gerarToken() {
        // Gerar token (simulação)
        return UUID.randomUUID().toString();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
