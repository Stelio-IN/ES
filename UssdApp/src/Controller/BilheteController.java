/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author steli
 */
import Model.Bilhete;
import java.util.HashMap;
import java.util.Map;

public class BilheteController {
    private Map<String, Bilhete> bilhetes = new HashMap<>();

    /*public Bilhete emitirBilhete(String idViagem, String idUsuario) {
        Bilhete bilhete = new Bilhete(idViagem, idUsuario);
        bilhetes.put(bilhete.getId(), bilhete);
        return bilhete;
    }*/

    public Bilhete buscarBilhete(String id) {
        return bilhetes.get(id);
    }
/*
    public boolean cancelarBilhete(String id) {
        Bilhete bilhete = bilhetes.get(id);
        if (bilhete != null) {
            bilhete.cancelarBilhete();
            return true;
        }
        return false;
    }*/
}
