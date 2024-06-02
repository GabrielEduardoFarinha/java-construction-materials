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

    public void saveMaterials(ArrayList<Material> materials) {
        materialRepository.saveMaterials(materials);
    }

    public String getAllMaterials(ArrayList<Material> materials) {
        StringBuilder builder = new StringBuilder();
        for (Material material : materials) {
            builder.append(material.toString()).append("\n");
        }
        return builder.toString();
    }
}
//final