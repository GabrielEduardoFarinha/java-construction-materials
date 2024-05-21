import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MaterialController {
    private final MaterialService materialService;
    private final JFrame frame;
    private final JTextField nameField;
    private final JTextField valueField;
    private final JTextField sizeUnitField;
    private final JTextField sizeField;
    private final JTextArea displayArea;
    private final HashSet<Material> selectedMaterials;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
        frame = new JFrame("Construction Material Frontend");
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

        selectedMaterials = new HashSet<>();
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
            double value = Double.parseDouble(valueText);
            double size = Double.parseDouble(sizeText);
            // Generate unique ID
            String id = java.util.UUID.randomUUID().toString();
            Material material = new Material(id, name, value, sizeUnit, size);
            materialService.addMaterial(material);
            JOptionPane.showMessageDialog(frame, "Material adicionado.");
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Valor inválido para preço ou tamanho.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayMaterials() {
        ArrayList<Material> materials = materialService.loadMaterials();
        String allMaterials = materialService.getAllMaterials(materials);
        displayArea.setText(allMaterials);
    }

    private void clearInputFields() {
        nameField.setText("");
        valueField.setText("");
        sizeUnitField.setText("");
        sizeField.setText("");
    }

    private void selectMaterials() {
        ArrayList<Material> materials = materialService.loadMaterials();
        String[] options = new String[materials.size()];
        for (int i = 0; i < materials.size(); i++) {
            options[i] = materials.get(i).toString();
        }
        String selectedMaterial = (String) JOptionPane.showInputDialog(frame, "Selecione um material:", "Selecionar Materiais",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selectedMaterial != null) {
            for (Material material : materials) {
                if (material.toString().equals(selectedMaterial)) {
                    selectedMaterials.add(material);
                    break;
                }
            }
            JOptionPane.showMessageDialog(frame, "Material selecionado: " + selectedMaterial, "Seleção Concluída", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calculator() {
        if (selectedMaterials.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum material selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double totalCost = materialService.calculateTotalValue(new ArrayList<>(selectedMaterials));
        JOptionPane.showMessageDialog(frame, "Custo total dos materiais selecionados: " + totalCost, "Cálculo Concluído", JOptionPane.INFORMATION_MESSAGE);
    }
}
