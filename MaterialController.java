
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;
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
        inputPanel.add(new JLabel("tamanho ou peso:"));
        sizeField = new JTextField();
        inputPanel.add(sizeField);
        JButton addButton = new JButton("adicionar");
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
        JButton refreshButton = new JButton("mostrar materiais ja cadastrados");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMaterials();
            }
        });
        mainPanel.add(refreshButton, BorderLayout.SOUTH);
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
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double value = Double.parseDouble(valueText);
            double size = Double.parseDouble(sizeText);
            // Generate unique ID
            String id = UUID.randomUUID().toString();
            Material material = new Material(id, name, value, sizeUnit, size);
            materialService.addMaterial(material);
            JOptionPane.showMessageDialog(frame, "Material added.");
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid value for price or size.", "Input Error", JOptionPane.ERROR_MESSAGE);
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
}
 