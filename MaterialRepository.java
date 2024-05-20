import java.io.*;
import java.util.ArrayList;
public class MaterialRepository {
    private final String fileName;
    public MaterialRepository(String fileName) {
        this.fileName = fileName;
    }
    public void saveMaterials(ArrayList<Material> materials) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(materials);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Material> loadMaterials() {
        ArrayList<Material> materials = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            materials = (ArrayList<Material>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File might not exist or be empty, no need to print stack trace here
        }
        return materials;
    }
}
 