/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ussdapp;

/**
 *
 * @author steli
 */

import Model.Bilhete;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaBilhetes {

    private static final String FILE_NAME = "bilhetes.dat";

    public static void salvarBilhete(Bilhete bilhete) {
        List<Bilhete> bilhetes = carregarBilhetes();
        bilhetes.add(bilhete);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bilhetes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Bilhete> carregarBilhetes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Bilhete>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

