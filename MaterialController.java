import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaterialController {
    private final MaterialService materialService;
    private final JFrame frame;
    private final JTextField nameField;
    private final JTextField valueField;
    private final JTextField sizeUnitField;
    private final JTextField sizeField;
    private final JTextArea displayArea;
    private final Map<Material, Integer> selectedMaterialsWithQuantities;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
        frame = new JFrame("Calculadora P&G");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Tipo do material:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Valor:"));
        valueField = new JTextField();
        inputPanel.add(valueField);
        inputPanel.add(new JLabel("Qual a unidade de medida:"));
        sizeUnitField = new JTextField();
        inputPanel.add(sizeUnitField);
        inputPanel.add(new JLabel("Tamanho ou peso:"));
        sizeField = new JTextField();
        inputPanel.add(sizeField);
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> addMaterial());
        inputPanel.add(addButton);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(); // Panel to hold the buttons
        JButton showMaterialsButton = new JButton("Mostrar Materiais Cadastrados");
        showMaterialsButton.addActionListener(e -> displayMaterials());
        buttonPanel.add(showMaterialsButton);

        JButton selectMaterialsButton = new JButton("Selecionar Materiais");
        selectMaterialsButton.addActionListener(e -> selectMaterials());
        buttonPanel.add(selectMaterialsButton);

        JButton calculatorButton = new JButton("Calculadora");
        calculatorButton.addActionListener(e -> calculator());
        buttonPanel.add(calculatorButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        selectedMaterialsWithQuantities = new HashMap<>();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void addMaterial() {
        String name = nameField.getText();
        String valueText = valueField.getText();
        String sizeUnit = sizeUnitField.getText();
        String sizeText = sizeField.getText();
        // Validate input
        if (name.isEmpty() || valueText.isEmpty() || sizeUnit.isEmpty() || sizeText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double value = Double.parseDouble(valueText.replace(",", "."));
            double size = Double.parseDouble(sizeText);
            // Generate unique ID
            String id = java.util.UUID.randomUUID().toString();
            Material material = new Material(id, name, value, sizeUnit, size);
            materialService.addMaterial(material);
            JOptionPane.showMessageDialog(frame, "Material adicionado.");
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Valor inválido para preço ou tamanho.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar material: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayMaterials() {
        try {
            ArrayList<Material> materials = materialService.loadMaterials();
            String allMaterials = materialService.getAllMaterials(materials);
            displayArea.setText(allMaterials);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar materiais: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        nameField.setText("");
        valueField.setText("");
        sizeUnitField.setText("");
        sizeField.setText("");
    }

    private void selectMaterials() {
        try {
            ArrayList<Material> materials = materialService.loadMaterials();
            String[] options = new String[materials.size()];
            for (int i = 0; i < materials.size(); i++) {
                options[i] = materials.get(i).toString();
            }
            String selectedMaterialString = (String) JOptionPane.showInputDialog(frame, "Selecione um material:", "Selecionar Materiais",
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (selectedMaterialString != null) {
                for (Material material : materials) {
                    if (material.toString().equals(selectedMaterialString)) {
                        String quantityText = JOptionPane.showInputDialog(frame, "Quantos " + material.getName() + " o senhor(a) deseja?", "Quantidade", JOptionPane.PLAIN_MESSAGE);
                        int quantity = Integer.parseInt(quantityText);
                        selectedMaterialsWithQuantities.put(material, quantity);
                        JOptionPane.showMessageDialog(frame, "Material selecionado: " + material.getName() + " - Quantidade: " + quantity, "Seleção Concluída", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Quantidade inválida.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao selecionar materiais: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculator() {
        try {
            if (selectedMaterialsWithQuantities.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum material selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double totalCost = 0;
            for (Map.Entry<Material, Integer> entry : selectedMaterialsWithQuantities.entrySet()) {
                totalCost += entry.getKey().getValue() * entry.getValue();
            }
            double engineerCost = 2200;
            double taxCost = 0.035 * (totalCost + engineerCost);
            double finalCost = totalCost + engineerCost + taxCost;

            String resultMessage = String.format(
                    "Custo total dos materiais: %.2f\nCusto do engenheiro: %.2f\nCusto de impostos: %.2f\nValor final: %.2f",
                    totalCost, engineerCost, taxCost, finalCost
            );
            JOptionPane.showMessageDialog(frame, resultMessage, "Cálculo Concluído", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao calcular custo total: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
