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
public class Bilhete {
    private String id;
    private String idViagem;
    private String idUsuario;
    private String codigo;
    private String status; // Ex.: "ativo", "cancelado"

    public Bilhete(String idViagem, String idUsuario) {
        this.id = UUID.randomUUID().toString();
        this.idViagem = idViagem;
        this.idUsuario = idUsuario;
        this.codigo = UUID.randomUUID().toString().substring(0, 8); // Código único
        this.status = "ativo";
    }

    public void cancelarBilhete() {
        if ("ativo".equals(this.status)) {
            this.status = "cancelado";
        } else {
            System.out.println("Bilhete já está cancelado.");
        }
    }

    // Getters e Setters omitidos para brevidade
}
