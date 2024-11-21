package ussdapp;

import Model.Bilhete;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class App extends JFrame {
    private JTextArea display;
    private JTextField inputField;
    private JPanel panel;
    private String currentMenu = "main";
    private HashMap<String, List<String>> routeMap = new HashMap<>();
    private String selectedOrigin = "";
    private String selectedDestination = "";
    private String selectedTrip = "";
    private String selectedReference = "";
    private List<Bilhete> bilhetesComprados = new ArrayList<>();
    private static final String FILE_PATH = "bilhetes.dat";
    private static final double TICKET_PRICE = 750.0;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public App() {
        initializeRoutes();
        setupUI();
        loadBilhetes();
    }

    private void initializeRoutes() {
        routeMap.put("Maputo", List.of("Maxixe Velha", "Manhiça", "Magude"));
        routeMap.put("Xai-Xai", List.of("Maputo", "Inhambane", "Chokwé"));
        routeMap.put("Marracuene", List.of("Maputo", "Boane", "Matola"));
    }

private void setupUI() {
    // Definindo o título e a janela
    setTitle("Pag Express Moz - Bilhetes de Transporte");
    setSize(400, 600);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Criando o painel principal com um Layout simples
    panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(245, 245, 245)); // Cor de fundo mais clara

    // Área de exibição (Texto)
    display = new JTextArea();
    display.setEditable(false);
    display.setFont(new Font("Arial", Font.PLAIN, 14));
    display.setMargin(new Insets(10, 10, 10, 10));
    display.setBackground(new Color(255, 255, 255)); // Cor de fundo do texto
    display.setForeground(new Color(0, 0, 0)); // Cor do texto
    display.setLineWrap(true);
    display.setWrapStyleWord(true); // Quebra de linha automática
    showMainMenu();

    // Adicionando um painel de rolagem ao JTextArea
    JScrollPane scrollPane = new JScrollPane(display);
    scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Removendo borda do JScrollPane

    // Campo de entrada
    inputField = new JTextField();
    inputField.setFont(new Font("Arial", Font.PLAIN, 14));
    inputField.setForeground(new Color(50, 50, 50)); // Cor do texto
    inputField.setBackground(new Color(255, 255, 255)); // Cor de fundo
    inputField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borda sutil
    inputField.addActionListener(new InputHandler());

    // Adicionando os componentes no painel principal
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(inputField, BorderLayout.SOUTH);

    // Adicionando o painel ao JFrame
    add(panel);
}


    private void showMainMenu() {
        display.setText("Pag Express Moz!\n" +
                        "1) Comprar bilhete\n" +
                        "2) Consultar horários\n" +
                        "3) Ver bilhetes comprados\n" +
                        "4) Cancelar viagem\n" +
                        "5) Ajuda\n" +
                        "0) Sair\n");
    }

  private class InputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = inputField.getText().trim();
            inputField.setText("");

            try {
                switch (currentMenu) {
                    case "main" -> handleMainMenu(userInput);
                    case "origem" -> handleOrigemMenu(userInput);
                    case "destino" -> handleDestinoMenu(userInput);
                    case "viagens" -> handleViagensMenu(userInput);
                    case "detalhes-viagem" -> handleDetalhesViagemMenu(userInput);
                    case "pagamento" -> handlePagamentoMenu(userInput);
                    case "pin" -> handlePinMenu(userInput);
                    case "cancelar" -> handleCancelMenu(userInput);
                    case "confirmar-cancelamento" -> handleConfirmCancellation(userInput);
                    case "listar-viagens" -> handleListarViagens(userInput);
                }
            } catch (Exception ex) {
                display.append("Erro: " + ex.getMessage() + "\n");
                showMainMenu();
                currentMenu = "main";
            }
        }
        private void handleListarViagens(String input) {
            showMainMenu();
            currentMenu = "main";
        }
          private void handleViagensMenu(String userInput) {
            switch (userInput) {
                case "1":
                    selectedTrip = "10:00";
                    currentMenu = "detalhes-viagem";
                    display.setText("Detalhes da Viagem:\n" +
                                   "Horário: 10:00\n" +
                                   "Preço: 8500.0 MZN\n" +
                                   "Pressione qualquer tecla para continuar.");
                    break;
                case "2":
                    selectedTrip = "15:00";
                    currentMenu = "detalhes-viagem";
                    display.setText("Detalhes da Viagem:\n" +
                                   "Horário: 15:00\n" +
                                   "Preço: 750.0 MZN\n" +
                                   "Pressione qualquer tecla para continuar.");
                    break;
                case "0":
                    currentMenu = "destino";
                    display.setText("Escolher o Destino:\n" +
                                   "1) Maxixe Velha\n" +
                                   "2) Manhiça\n" +
                                   "3) Magude\n" +
                                   "0) Voltar\n");
                    break;
                default:
                    display.append("Opção inválida. Tente novamente.\n");
                    break;
            }
        }
             private void handleDetalhesViagemMenu(String userInput) {
            currentMenu = "pagamento";
            display.setText("Confirmação de Pagamento:\n" +
                            "Horário: " + selectedTrip + "\n" +
                            "Preço: 950.0 MZN\n" +
                            "Pressione: \n"
                                    + "1 - pagar \n"
                                    + "0 - voltar. \n");
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
                    display.setText("Pag Express Moz!\n" +
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

            private void handlePagamentoMenu(String input) {
            if (input.equals("1")) {
                currentMenu = "pin";
                display.setText("Digite o PIN para confirmar o pagamento:\n");
            } else {
                display.append("Opção inválida. Tente novamente.\n");
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
                    currentMenu = "listar-viagens";
                    displayAvailableTrips();
                    break;
                case "3":
                    displayBilhetesComprados();
                    break;
                case "4":
                    currentMenu = "cancelar";
                    showCancelMenu();
                    break;
                case "5":
                    display.setText("Ajuda:\n" +
                                    "Sistema de compra de bilhetes interprovincias.\n" +
                                    "Navegue usando os números das opções.\n" +
                                    "Para cancelar uma viagem, use a opção 4 do menu principal.\n" +
                                    "Pressione qualquer tecla para voltar.\n");
                    currentMenu = "main";
                    break;
                case "0":
                    display.setText("Obrigado por utilizar nossos serviços. Até logo!\n");
                    inputField.setEnabled(false);
                    break;
                default:
                    display.append("Opção inválida. Tente novamente.\n");
                    showMainMenu();
                    break;
            }
        }
        
        private void displayAvailableTrips() {
            StringBuilder trips = new StringBuilder("Viagens Disponíveis:\n\n");
            
            for (String origin : routeMap.keySet()) {
                trips.append(origin).append(":\n");
                List<String> destinations = routeMap.get(origin);
                for (String destination : destinations) {
                    trips.append("  → ").append(destination).append("\n")
                         .append("     Horários: 10:00, 15:00\n")
                         .append("     Preço: ").append(TICKET_PRICE).append(" MZN\n\n");
                }
            }
            
            trips.append("\nPressione qualquer tecla para voltar ao menu principal.");
            display.setText(trips.toString());
        }

        private void showCancelMenu() {
        if (bilhetesComprados.isEmpty()) {
            display.setText("Não há bilhetes disponíveis para cancelamento.\n" +
                          "Pressione qualquer tecla para voltar ao menu principal.\n");
            currentMenu = "main";
            return;
        }

        StringBuilder menu = new StringBuilder("Selecione o bilhete para cancelar:\n\n");
        for (int i = 0; i < bilhetesComprados.size(); i++) {
            Bilhete bilhete = bilhetesComprados.get(i);
            menu.append(String.format("%d) %s -> %s (Ref: %s)\n",
                    i + 1,
                    bilhete.getOrigem(),
                    bilhete.getDestino(),
                    bilhete.getReferencia()));
        }
        menu.append("\n0) Voltar ao menu principal\n");
        display.setText(menu.toString());
        currentMenu = "cancelar";
    }

         private void handleCancelMenu(String input) {
            if (bilhetesComprados.isEmpty()) {
                display.setText("Não há bilhetes disponíveis para cancelamento.\n" +
                              "Pressione qualquer tecla para voltar ao menu principal.\n");
                currentMenu = "main";
                return;
            }

            if (input.equals("0")) {
                showMainMenu();
                currentMenu = "main";
                return;
            }

            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < bilhetesComprados.size()) {
                    Bilhete selectedBilhete = bilhetesComprados.get(index);
                    selectedReference = selectedBilhete.getReferencia();
                    display.setText("Confirmar cancelamento do bilhete:\n\n" +
                                  "Origem: " + selectedBilhete.getOrigem() + "\n" +
                                  "Destino: " + selectedBilhete.getDestino() + "\n" +
                                  "Data: " + selectedBilhete.getData() + "\n" +
                                  "Hora: " + selectedBilhete.getHorario() + "\n" +
                                  "Referência: " + selectedBilhete.getReferencia() + "\n\n" +
                                  "1) Confirmar cancelamento\n" +
                                  "0) Voltar\n");
                    currentMenu = "confirmar-cancelamento";
                } else {
                    display.setText("Opção inválida. Tente novamente.\n");
                    showCancelMenu();
                }
            } catch (NumberFormatException e) {
                if (!input.equals("0")) {
                    display.setText("Opção inválida. Tente novamente.\n");
                    showCancelMenu();
                }
            }
        }


    private void handleConfirmCancellation(String input) {
            switch (input) {
                case "1":
                    // Remove the ticket and save
                    bilhetesComprados.removeIf(bilhete -> 
                        bilhete.getReferencia().equals(selectedReference));
                    saveBilhetes();
                    display.setText("Bilhete cancelado com sucesso!\n" +
                                  "Referência: " + selectedReference + "\n\n" +
                                  "Pressione qualquer tecla para voltar ao menu principal.\n");
                    currentMenu = "main";
                    break;
                case "0":
                    showCancelMenu();
                    break;
                default:
                    currentMenu = "main";
                    showMainMenu();
                    break;
            }
        }

        // Existing handlers remain the same...
        private void handleDestinoMenu(String userInput) {
            List<String> destinations = routeMap.get(selectedOrigin);
            if (destinations == null) {
                display.append("Origem inválida. Tente novamente.\n");
                showMainMenu();
                currentMenu = "main";
                return;
            }

            switch (userInput) {
                case "0":
                    currentMenu = "origem";
                    display.setText("Escolher a origem:\n" +
                                    "1) Maputo\n" +
                                    "2) Xai-Xai\n" +
                                    "3) Marracuene\n" +
                                    "0) Voltar\n");
                    break;
                case "1", "2", "3":
                    int index = Integer.parseInt(userInput) - 1;
                    if (index < destinations.size()) {
                        selectedDestination = destinations.get(index);
                        currentMenu = "viagens";
                        display.setText("Escolher horário de viagem para " + selectedDestination + ":\n" +
                                        "1) 10:00\n" +
                                        "2) 15:00\n" +
                                        "0) Voltar\n");
                    } else {
                        display.append("Opção inválida. Tente novamente.\n");
                    }
                    break;
                default:
                    display.append("Opção inválida. Tente novamente.\n");
                    break;
            }
        }

        private void handlePinMenu(String input) {
            if (isValidPin(input)) {
                String referencia = generateReference();
                Bilhete bilhete = new Bilhete(
                    selectedOrigin, 
                    selectedDestination, 
                    LocalDate.now().format(DATE_FORMATTER), 
                    LocalTime.now().format(TIME_FORMATTER), 
                    referencia, 
                    TICKET_PRICE
                );
                bilhetesComprados.add(bilhete);
                saveBilhetes();

                display.setText("Pagamento Confirmado! Referência: " + referencia + "\n" +
                                "Bilhete comprado com sucesso.\n" +
                                "Obrigado por utilizar nossos serviços. Boa viagem!\n");
                currentMenu = "main";
            } else {
                display.append("PIN inválido. Deve conter 4 dígitos numéricos.\n");
            }
        }
    }

    private boolean isValidPin(String pin) {
        return pin != null && 
               pin.length() == 4 && 
               Pattern.matches("\\d{4}", pin);
    }

    private String generateReference() {
        Random random = new Random();
        return "BISA" + (100000 + random.nextInt(900000));
    }

    private void displayBilhetesComprados() {
        if (bilhetesComprados.isEmpty()) {
            display.setText("Nenhum bilhete comprado ainda.\n");
        } else {
            StringBuilder builder = new StringBuilder("Bilhetes Comprados:\n\n");
            for (Bilhete bilhete : bilhetesComprados) {
                builder.append(bilhete).append("\n");
            }
            display.setText(builder.toString());
        }
    }

    private void saveBilhetes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(bilhetesComprados);
        } catch (IOException e) {
            System.err.println("Erro ao salvar bilhetes: " + e.getMessage());
        }
    }

    private void loadBilhetes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            bilhetesComprados = (List<Bilhete>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            bilhetesComprados = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App frame = new App();
            frame.setVisible(true);
        });
    }
}