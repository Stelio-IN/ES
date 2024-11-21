/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author steli
 */
import Model.Usuario;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public Usuario cadastrarUsuario(String nome, String email, String senha, String telefone) {
        Usuario usuario = new Usuario(nome, email, senha, telefone);
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public Usuario buscarUsuario(String id) {
        return usuarios.get(id);
    }

    public boolean verificarSenha(String id, String senha) {
        Usuario usuario = usuarios.get(id);
        if (usuario != null) {
            return usuario.verificarSenha(senha);
        }
        return false;
    }

    public String gerarToken(String id) {
        Usuario usuario = usuarios.get(id);
        if (usuario != null) {
            return usuario.gerarToken();
        }
        return null;
    }
}
