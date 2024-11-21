import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class USSDTransportesSystem extends JFrame {

    private JTextArea display;
    private JTextField inputField;
    private JPanel panel;
    private String currentMenu = "main";
    private HashMap<String, String> patientData = new HashMap<>();

    public USSDTransportesSystem() {
      
        
        // Configuração da janela simulando um celular
        setTitle("Simulação USSD - Sistema Hospitalar Moçambique");
        setSize(300, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configura painel com layout de celular
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Área de exibição (Simulando a tela do celular)
        display = new JTextArea();
        display.setEditable(false);  // Para impedir a edição do texto pelo usuário
        display.setFont(new Font("Monospaced", Font.PLAIN, 14));
        display.setMargin(new Insets(10, 10, 10, 10));
        display.setText("Bem-vindo ao Sistema de Consultas Hospitalares!\n" +
                        "1. Marcar Consulta\n" +
                        "2. Ver Hospitais Próximos\n" +
                        "3. Cadastro de Paciente\n" +
                        "4. Sair\n");
        JScrollPane scrollPane = new JScrollPane(display);

        // Campo de entrada para inserir opções
        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.addActionListener(new InputHandler()); // Ação quando o usuário pressiona Enter

        // Adiciona componentes ao painel
        panel.add(scrollPane, BorderLayout.CENTER);  // Área de exibição no centro
        panel.add(inputField, BorderLayout.SOUTH);   // Campo de entrada na parte inferior

        // Adiciona painel à janela
        add(panel);
    }

    // Manipulador para capturar a entrada do usuário
    private class InputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = inputField.getText();
            inputField.setText("");  // Limpa o campo de input após a entrada

            // Gerencia o menu atual
            switch (currentMenu) {
                case "main":
                    handleMainMenu(userInput);
                    break;
                case "marcarConsulta":
                    handleConsultaMenu(userInput);
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
                display.setText("Cadastro de Paciente:\n" +
                                "Digite o nome completo:\n");
                break;
            case "4":
                currentMenu = "confirmarSaida";
                display.setText("Tem certeza que deseja sair?\n" +
                                "1. Sim\n" +
                                "2. Não\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    // Manipulador para marcar consultas
    private void handleConsultaMenu(String input) {
        switch (input) {
            case "1":
                display.setText("Clínica Geral selecionada.\n" +
                                "Escolha o horário:\n" +
                                "1. 08:00 - 10:00\n" +
                                "2. 10:00 - 12:00\n" +
                                "3. 14:00 - 16:00\n" +
                                "4. Voltar\n");
                break;
            case "2":
                display.setText("Pediatria selecionada.\n" +
                                "Escolha o horário:\n" +
                                "1. 08:00 - 10:00\n" +
                                "2. 10:00 - 12:00\n" +
                                "3. 14:00 - 16:00\n" +
                                "4. Voltar\n");
                break;
            case "3":
                display.setText("Cardiologia selecionada.\n" +
                                "Escolha o horário:\n" +
                                "1. 08:00 - 10:00\n" +
                                "2. 10:00 - 12:00\n" +
                                "3. 14:00 - 16:00\n" +
                                "4. Voltar\n");
                break;
            case "4":
                currentMenu = "main";
                display.setText("Bem-vindo ao Sistema de Consultas Hospitalares!\n" +
                                "1. Marcar Consulta\n" +
                                "2. Ver Hospitais Próximos\n" +
                                "3. Cadastro de Paciente\n" +
                                "4. Sair\n");
                break;
            default:
                display.append("Opção inválida. Tente novamente.\n");
                break;
        }
    }

    // Manipulador para visualizar hospitais próximos
    private void handleHospitaisMenu() {
        display.setText("Hospitais Próximos:\n" +
                        "1. Hospital Central de Maputo\n" +
                        "2. Hospital Provincial de Gaza\n" +
                        "3. Hospital Geral de Nampula\n" +
                        "4. Hospital Central da Beira\n" +
                        "Pressione qualquer tecla para voltar ao menu principal.\n");
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
        display.append("\n1. Marcar Consulta\n" +
                       "2. Ver Hospitais Próximos\n" +
                       "3. Cadastro de Paciente\n" +
                       "4. Sair\n");
    }

    // Manipulador para confirmar a saída
    private void handleSairMenu(String input) {
        if ("1".equals(input)) {
            display.setText("Você saiu do sistema. Obrigado!\n");
            inputField.setEnabled(false); // Desativa o campo de entrada
        } else if ("2".equals(input)) {
            currentMenu = "main";
            display.setText("Bem-vindo ao Sistema de Consultas Hospitalares!\n" +
                            "1. Marcar Consulta\n" +
                            "2. Ver Hospitais Próximos\n" +
                            "3. Cadastro de Paciente\n" +
                            "4. Sair\n");
        } else {
            display.append("Opção inválida. Tente novamente.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            USSDTransportesSystem frame = new USSDTransportesSystem();
            frame.setVisible(true);
        });
    }
}