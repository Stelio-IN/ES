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
public class Pagamento {
    private String id;
    private String idUsuario;
    private String idBilhete;
    private double valor;
    private String metodo; // Ex.: "M-Pesa", "USSD"

    public Pagamento(String idUsuario, String idBilhete, double valor, String metodo) {
        this.id = UUID.randomUUID().toString();
        this.idUsuario = idUsuario;
        this.idBilhete = idBilhete;
        this.valor = valor;
        this.metodo = metodo;
    }

    public boolean validarPagamento() {
        // Simulação de validação (ex.: verificar com gateway de pagamento)
        return valor > 0 && (metodo.equals("M-Pesa") || metodo.equals("USSD"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdBilhete() {
        return idBilhete;
    }

    public void setIdBilhete(String idBilhete) {
        this.idBilhete = idBilhete;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

   
}

