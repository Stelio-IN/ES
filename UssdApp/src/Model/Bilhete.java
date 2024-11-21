package Model;

import java.io.Serializable;

public class Bilhete implements Serializable {
    private String origem;
    private String destino;
    private String data;
    private String horario;
    private String referencia;
    private double valor;

    public Bilhete(String origem, String destino, String data, String horario, String referencia, double valor) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.horario = horario;
        this.referencia = referencia;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Bilhete:\n" +
               "Origem: " + origem + "\n" +
               "Destino: " + destino + "\n" +
               "Data: " + data + "\n" +
               "Horário: " + horario + "\n" +
               "Referência: " + referencia + "\n" +
               "Valor: " + valor + " MT\n";
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
