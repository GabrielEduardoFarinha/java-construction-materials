import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MaterialController {
    private final MaterialService materialService;
    private final JFrame frame;
    private final JTextField nameField;
    private final JTextField valueField;
    private final JTextField sizeUnitField;
    private final JTextField sizeField;
    private final JTextArea displayArea;

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMaterial();
            }
        });
        inputPanel.add(addButton);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(); // Panel to hold the buttons
        JButton showMaterialsButton = new JButton("Mostrar Materiais Cadastrados");
        showMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMaterials();
            }
        });
        buttonPanel.add(showMaterialsButton);

        // New buttons for selecting materials and multiplying values
        JButton selectMaterialsButton = new JButton("Selecionar Materiais");
        selectMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMaterials();
            }
        });
        buttonPanel.add(selectMaterialsButton);

        JButton multiplyValuesButton = new JButton("Multiplicar Valores");
        multiplyValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplyValues();
            }
        });
        buttonPanel.add(multiplyValuesButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
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

    // Method to handle selecting materials
    private void selectMaterials() {
        // Add your logic here to handle selecting materials
        JOptionPane.showMessageDialog(frame, "Função de seleção de materiais ainda não implementada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to handle multiplying values
    private void multiplyValues() {
        // Add your logic here to handle multiplying values
        JOptionPane.showMessageDialog(frame, "Função de multiplicação de valores ainda não implementada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}
