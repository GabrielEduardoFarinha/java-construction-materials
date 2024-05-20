
import java.util.ArrayList;
public class MaterialService {
    private final MaterialRepository materialRepository;
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }
    public void addMaterial(Material material) {
        ArrayList<Material> materials = loadMaterials();
        materials.add(material);
        materialRepository.saveMaterials(materials);
    }
    public ArrayList<Material> loadMaterials() {
        return materialRepository.loadMaterials();
    }
    public String getAllMaterials(ArrayList<Material> materials) {
        StringBuilder builder = new StringBuilder();
        for (Material material : materials) {
            builder.append(material.toString()).append("\n");
        }
        return builder.toString();
    }
    public double calculateTotalValue(ArrayList<Material> materials) {
        double totalValue = 0;
        for (Material material : materials) {
            totalValue += material.getValue();
        }
        return totalValue;
    }
    public double calculateTotalValueWithOperation(ArrayList<Material> materials, String operation) {
        double totalValue = materials.get(0).getValue();
        for (int i = 1; i < materials.size(); i++) {
            double materialValue = materials.get(i).getValue();
            switch (operation) {
                case "add":
                    totalValue += materialValue;
                    break;
                case "subtract":
                    totalValue -= materialValue;
                    break;
                case "multiply":
                    totalValue *= materialValue;
                    break;
                default:
                    // Handle invalid operation
                    break;
            }
        }
        return totalValue;
    }
}
 