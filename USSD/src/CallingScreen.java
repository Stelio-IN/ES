import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class CallingScreen extends Screen {

    private final ScreenChangeListener listener;
    private JTextArea display;  // Área de exibição do menu
    private JTextField inputField;  // Campo de entrada
    private String currentMenu = "main";  // Controle do menu atual
    private String selectedService = "";  // Serviço médico selecionado
    private HashMap<String, String> patientData = new HashMap<>();  // Armazenamento de dados do paciente

    public CallingScreen(ScreenChangeListener listener) {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setLayout(new GridBagLayout());  // Utilizando o GridBagLayout

        this.listener = listener;

        // Configuração do display do menu USSD
        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setMargin(new Insets(10, 10, 10, 10));
        display.setText("Pag express MOZ!\n" +
                        "1. Marcar Consulta\n" +
                        "2. Ver Hospitais Próximos\n" +
                        "3. Cadastro de Paciente\n" +
                        "4. Sair\n");
        JScrollPane scrollPane = new JScrollPane(display);  // Adicionando a área de exibição em um JScrollPane

        // Campo de entrada para o usuário
        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.addActionListener(new InputHandler());  // Processa o input ao pressionar Enter

        // Configurando o layout dos componentes usando GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);  // Adicionando a área de exibição

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(10, 0, 0, 0);  // Pequeno espaço entre os elementos
        add(inputField, gbc);  // Adicionando o campo de entrada
    }

    // Classe interna para gerenciar as entradas do usuário
    private class InputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = inputField.getText();
            inputField.setText("");  // Limpa o campo de input após a entrada

            // Gerenciamento do menu atual com base na entrada
            switch (currentMenu) {
                case "main":
                    handleMainMenu(userInput);
                    break;
                case "marcarConsulta":
                    handleConsultaMenu(userInput);
                    break;
                case "selecionarUnidade":
                    handleUnidadeSanitaria(userInput);
                    break;
                case "hospitaisProximos":
                    handleHospitaisMenu();
                    break;
                case "cadastroPacienteNome":
                    handleCadastroNome(userInput);
                    break;
                case "cadastroPacienteIdade":
                    handleCadastroIdade(userInput);
                    break;
                case "confirmarSaida":
                    handleSairMenu(userInput);
                    break;
                default:
                    display.append("\nOpção inválida. Tente novamente.");
            }
        }
    }

    // Manipulador do menu principal
    private void handleMainMenu(String input) {
        switch (input) {
            case "1":
                currentMenu = "marcarConsulta";
                display.setText("Marcar Consulta:\n" +
                                "Escolha a especialidade:\n" +
                                "1. Clínica Geral\n" +
                                "2. Pediatria\n" +
                                "3. Cardiologia\n" +
                                "4. Voltar\n");
                break;
            case "2":
                currentMenu = "hospitaisProximos";
                handleHospitaisMenu();
                break;
            case "3":
                currentMenu = "cadastroPacienteNome";
                display.setText("Cadastro de Paciente:\nDigite o nome completo:\n");
                break;
            case "4":
                currentMenu = "confirmarSaida";
                display.setText("Tem certeza que deseja sair?\n1. Sim\n2. Não\n");
                break;
            default:
                display.append("\nOpção inválida. Tente novamente.");
        }
    }

    // Manipulador para marcar consultas
    private void handleConsultaMenu(String input) {
        switch (input) {
            case "1":
                selectedService = "Clínica Geral";
                currentMenu = "selecionarUnidade";
                display.setText("Clínica Geral selecionada.\n" +
                                "Unidades sanitárias com este serviço num raio de 50 km:\n" +
                                "1. Hospital Central de Maputo (HCM)\n" +
                                "2. Hospital Geral José Macamo\n" +
                                "3. Hospital Provincial da Matola\n" +
                                "Escolha o hospital para prosseguir:\n");
                break;
            case "2":
                selectedService = "Pediatria";
                currentMenu = "selecionarUnidade";
                display.setText("Pediatria selecionada.\n" +
                                "Unidades sanitárias com este serviço num raio de 50 km:\n" +
                                "1. Hospital Central de Maputo (HCM)\n" +
                                "2. Hospital Geral José Macamo\n" +
                                "3. Hospital Provincial da Matola\n" +
                                "Escolha o hospital para prosseguir:\n");
                break;
            case "3":
                selectedService = "Cardiologia";
                currentMenu = "selecionarUnidade";
                display.setText("Cardiologia selecionada.\n" +
                                "Unidades sanitárias com este serviço num raio de 50 km:\n" +
                                "1. Hospital Central de Maputo (HCM)\n" +
                                "2. Hospital Geral José Macamo\n" +
                                "3. Hospital Provincial da Matola\n" +
                                "Escolha o hospital para prosseguir:\n");
                break;
            case "4":
                currentMenu = "main";
                display.setText("Bem-vindo ao Sistema de Consultas Hospitalares!\n" +
                                "1. Marcar Consulta\n2. Ver Hospitais Próximos\n" +
                                "3. Cadastro de Paciente\n4. Sair\n");
                break;
            default:
                display.append("\nOpção inválida. Tente novamente.");
        }
    }

    // Manipulador para seleção da unidade sanitária
    private void handleUnidadeSanitaria(String input) {
        String hospitalSelecionado = "";
        switch (input) {
            case "1":
                hospitalSelecionado = "Hospital Central de Maputo (HCM)";
                break;
            case "2":
                hospitalSelecionado = "Hospital Geral José Macamo";
                break;
            case "3":
                hospitalSelecionado = "Hospital Provincial da Matola";
                break;
            default:
                display.append("\nOpção inválida. Tente novamente.");
                return;
        }

        display.setText(selectedService + " selecionada no " + hospitalSelecionado + ".\n" +
                        "Consulta marcada com sucesso!\n" +
                        "Pressione qualquer tecla para voltar ao menu principal.");
        currentMenu = "main";
    }

    // Manipulador para visualizar hospitais próximos
    private void handleHospitaisMenu() {
        display.setText("Hospitais Próximos:\n" +
                        "1. Hospital Central de Maputo\n" +
                        "2. Hospital Provincial de Gaza\n" +
                        "3. Hospital Geral de Nampula\n" +
                        "4. Hospital Central da Beira\n" +
                        "Pressione qualquer tecla para voltar ao menu principal.");
        currentMenu = "main";
    }

    // Manipulador para cadastro de paciente (Nome)
    private void handleCadastroNome(String nome) {
        patientData.put("nome", nome);
        currentMenu = "cadastroPacienteIdade";
        display.setText("Digite a sua idade:\n");
    }

    // Manipulador para cadastro de paciente (Idade)
    private void handleCadastroIdade(String idade) {
        patientData.put("idade", idade);
        display.setText("Cadastro concluído com sucesso!\n" +
                        "Nome: " + patientData.get("nome") + "\n" +
                        "Idade: " + patientData.get("idade") + "\n");
        currentMenu = "main";
        display.append("\n1. Marcar Consulta\n2. Ver Hospitais Próximos\n3. Cadastro de Paciente\n4. Sair\n");
    }

    // Manipulador para confirmar a saída
    private void handleSairMenu(String input) {
        if ("1".equals(input)) {
            display.setText("Você saiu do sistema. Obrigado!\n");
            inputField.setEnabled(false);  // Desativa o campo de entrada
        } else if ("2".equals(input)) {
            currentMenu = "main";
            display.setText("Bem-vindo ao Sistema de Consultas Hospitalares!\n" +
                            "1. Marcar Consulta\n" +
                            "2. Ver Hospitais Próximos\n" +
                            "3. Cadastro de Paciente\n" +
                            "4. Sair\n");
        } else {
            display.append("\nOpção inválida. Tente novamente.");
        }
    }
}
