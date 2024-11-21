package ussdapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class App extends JFrame {

    private JTextArea display;
    private JTextField inputField;
    private JPanel panel;
    private String currentMenu = "main";
    private HashMap<String, String> ticketData = new HashMap<>();
    private String selectedOrigin = "";
    private String selectedDestination = "";
    private String selectedTrip = "";

    public App() {
        // Window configuration simulating a mobile phone
        setTitle("USSD - Compra de Bilhetes de Transporte");
        setSize(350, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel configuration
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Display area
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setMargin(new Insets(10, 10, 10, 10));
        display.setText("Pag Express Moz!\n" +
                        "1) Comprar bilhete\n" +
                        "2) Consultar horários\n" +
                        "3) Ver bilhetes comprados\n" +
                        "4) Ajuda\n" +
                        "0) Sair\n");
        JScrollPane scrollPane = new JScrollPane(display);

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.addActionListener(new InputHandler());

        // Add components to panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);

        // Add panel to window
        add(panel);
    }

    private class InputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = inputField.getText();
            inputField.setText("");

            // Manage current menu
            switch (currentMenu) {
                case "main":
                    handleMainMenu(userInput);
                    break;
                case "origem":
                    handleOrigemMenu(userInput);
                    break;
                case "destino":
                    handleDestinoMenu(userInput);
                    break;
                case "viagens":
                    handleViagensMenu(userInput);
                    break;
                case "detalhes-viagem":
                    handleDetalhesViagemMenu(userInput);
                    break;
                case "pagamento":
                    handlePagamentoMenu(userInput);
                    break;
                case "pin":
                    handlePinMenu(userInput);
                    break;
            }
        }
    }

    private void handleMainMenu(String input) {
        switch (input) {
            case "1":
                currentMenu = "origem";
                display.setText("Escolher a origem:\n" +
                                "1) Maputo\n" +
                                "2) Xai-Xai\n" +
                                "3) Marracuene\n" +
                                "0) Voltar\n");
                break;
            case "2":
                display.setText("Consulta de Horários \n" +
                                "Funcionalidade em desenvolvimento\n"+
                                "Pressione qualquer tecla para voltar.\n");
                currentMenu = "main";
                break;
            case "3":
                display.setText("Bilhetes Comprados \n" +
                                  "Funcionalidade em desenvolvimento\n"+
                                "Pressione qualquer tecla para voltar.\n");
                currentMenu = "main";
                break;
            case "4":
                display.setText("Ajuda:\n" +
                                "Sistema de compra de bilhetes interprovincias.\n" +
                                "Navegue usando os números das opções.\n" +
                                "Pressione qualquer tecla para voltar.\n");
                currentMenu = "main";
                break;
            case "0":
                display.setText("Obrigado por utilizar nossos serviços. Até logo!\n");
                inputField.setEnabled(false);
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    private void handleOrigemMenu(String input) {
        switch (input) {
            case "1":
                selectedOrigin = "Maputo";
                currentMenu = "destino";
                display.setText("Escolher o Destino:\n" +
                                "1) Maxixe Velha\n" +
                                "2) Manhiça\n" +
                                "3) Magude\n" +
                                "0) Voltar\n");
                break;
            case "2":
                selectedOrigin = "Xai-Xai";
                currentMenu = "destino";
                display.setText("Escolher o Destino:\n" +
                                "1) Maputo\n" +
                                "2) Inhambane\n" +
                                "3) Chokwé\n" +
                                "0) Voltar\n");
                break;
            case "3":
                selectedOrigin = "Marracuene";
                currentMenu = "destino";
                display.setText("Escolher o Destino:\n" +
                                "1) Maputo\n" +
                                "2) Boane\n" +
                                "3) Matola\n" +
                                "0) Voltar\n");
                break;
            case "0":
                currentMenu = "main";
                display.setText("Bem-vindo ao Sistema de Compra de Bilhetes!\n" +
                                "1) Comprar bilhete\n" +
                                "2) Consultar horários\n" +
                                "3) Ver bilhetes comprados\n" +
                                "4) Ajuda\n" +
                                "0) Sair\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    private void handleDestinoMenu(String input) {
        switch (input) {
            case "1":
                selectedDestination = input.equals("1") ? getDestinationName(selectedOrigin, 1) : "";
                currentMenu = "viagens";
                display.setText("Selecione a viagem:\n" +
                                "1) X Data: 21/10/2024, Horário: 08:00\n" +
                                "2) Y Data: 19/10/2024, Horário: 15:00\n" +
                                "3) Z Data: 18/10/2024, Horário: 13:00\n" +
                                "0) Voltar\n");
                break;
            case "2":
                selectedDestination = input.equals("2") ? getDestinationName(selectedOrigin, 2) : "";
                currentMenu = "viagens";
                display.setText("Selecione a viagem:\n" +
                                "1) X Data: 22/10/2024, Horário: 09:30\n" +
                                "2) Y Data: 20/10/2024, Horário: 16:45\n" +
                                "3) Z Data: 19/10/2024, Horário: 14:15\n" +
                                "0) Voltar\n");
                break;
            case "3":
                selectedDestination = input.equals("3") ? getDestinationName(selectedOrigin, 3) : "";
                currentMenu = "viagens";
                display.setText("Selecione a viagem:\n" +
                                "1) X Data: 23/10/2024, Horário: 10:00\n" +
                                "2) Y Data: 21/10/2024, Horário: 17:30\n" +
                                "3) Z Data: 20/10/2024, Horário: 15:45\n" +
                                "0) Voltar\n");
                break;
            case "0":
                currentMenu = "origem";
                display.setText("Escolher a origem:\n" +
                                "1) Maputo\n" +
                                "2) Xai-Xai\n" +
                                "3) Marracuene\n" +
                                "0) Voltar\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    private String getDestinationName(String origin, int option) {
        switch (origin) {
            case "Maputo":
                return option == 1 ? "Maxixe Velha" : 
                       option == 2 ? "Manhiça" : "Magude";
            case "Xai-Xai":
                return option == 1 ? "Maputo" : 
                       option == 2 ? "Inhambane" : "Chokwé";
            case "Marracuene":
                return option == 1 ? "Maputo" : 
                       option == 2 ? "Boane" : "Matola";
            default:
                return "";
        }
    }

    private void handleViagensMenu(String input) {
        switch (input) {
            case "1":
            case "2":
            case "3":
                selectedTrip = "Viagem " + input;
                currentMenu = "detalhes-viagem";
                display.setText("Detalhes da viagem selecionada:\n" +
                                "1) Efectuar Pagamento\n" +
                                "2) Ver detalhes da Viagem\n" +
                                "0) Voltar\n");
                break;
            case "0":
                currentMenu = "destino";
                display.setText("Escolher o Destino:\n" +
                                "1) " + getDestinationName(selectedOrigin, 1) + "\n" +
                                "2) " + getDestinationName(selectedOrigin, 2) + "\n" +
                                "3) " + getDestinationName(selectedOrigin, 3) + "\n" +
                                "0) Voltar\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    private void handleDetalhesViagemMenu(String input) {
        switch (input) {
            case "1":
                currentMenu = "pagamento";
                display.setText("Detalhes da viagem selecionada\n" +
                                "Valor: 750 MT\n" +
                                "1) Efectuar Pagamento\n" +
                                "2) Ver detalhes da Viagem\n" +
                                "0) Voltar\n");
                break;
            case "2":
                display.setText("Detalhes da Viagem:\n" +
                                "Origem: " + selectedOrigin + "\n" +
                                "Destino: " + selectedDestination + "\n" +
                                "Data: 21/10/2024\n" +
                                "Horário: 08:00\n" +
                                "Valor: 750 MT\n" +
                                "Pressione qualquer tecla para voltar.\n");
                break;
            case "0":
                currentMenu = "viagens";
                display.setText("Selecione a viagem:\n" +
                                "1) Lagoon Data: 21/10/2024, Horário: 08:00\n" +
                                "2) MozCab Data: 19/10/2024, Horário: 15:00\n" +
                                "3) Mango Data: 18/10/2024, Horário: 13:00\n" +
                                "0) Voltar\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    private void handlePagamentoMenu(String input) {
        switch (input) {
            case "1":
                currentMenu = "pin";
                display.setText("Digite o PIN para confirmar o pagamento:\n");
                break;
            case "0":
                currentMenu = "detalhes-viagem";
                display.setText("Detalhes da viagem selecionada:\n" +
                                "1) Efectuar Pagamento\n" +
                                "2) Ver detalhes da Viagem\n" +
                                "0) Voltar\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    private void handlePinMenu(String input) {
        if (input.length() == 4 && input.matches("\\d+")) {
            String referencia = generateReference();
            display.setText("Pagamento Confirmado! Referência: " + referencia + "\n" +
                            "Você efetuou a compra de um bilhete no valor de 750MT\n" +
                            "para a viagem de " + selectedOrigin + " a " + selectedDestination + "\n" +
                            "no dia 12/01/2024 às 10:34.\n" +
                            "O pagamento foi realizado sem taxas adicionais 0MT.\n" +
                            "Consulte os serviços da sua operadora\n" +
                            "para verificar o seu novo saldo.\n" +
                            "Obrigado por utilizar nossos serviços. Boa viagem!\n");
            currentMenu = "main";
        } else {
            display.append("PIN inválido. Tente novamente.\n");
        }
    }

    private String generateReference() {
        Random random = new Random();
        return "BISA" + (100000 + random.nextInt(900000));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App frame = new App();
            frame.setVisible(true);
        });
    }
}