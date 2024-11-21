/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author steli
 */
import Model.Pagamento;
import java.util.HashMap;
import java.util.Map;

public class PagamentoController {
    private Map<String, Pagamento> pagamentos = new HashMap<>();

    public Pagamento registrarPagamento(String idUsuario, String idBilhete, double valor, String metodo) {
        Pagamento pagamento = new Pagamento(idUsuario, idBilhete, valor, metodo);
        if (pagamento.validarPagamento()) {
            pagamentos.put(pagamento.getId(), pagamento);
            return pagamento;
        }
        return null;
    }

    public Pagamento buscarPagamento(String id) {
        return pagamentos.get(id);
    }
}

