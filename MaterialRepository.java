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
        } catch (FileNotFoundException e) {
            System.err.println("Error: The specified file '" + fileName + "' could not be found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred while saving materials to the file '" + fileName + "'.");
            e.printStackTrace();
        }
    }

    public ArrayList<Material> loadMaterials() {
        ArrayList<Material> materials = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            materials = (ArrayList<Material>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Warning: The specified file '" + fileName + "' could not be found. Returning an empty list.");
        } catch (EOFException e) {
            System.err.println("Warning: The file '" + fileName + "' is empty. Returning an empty list.");
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred while loading materials from the file '" + fileName + "'.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: The class definition for the objects in the file '" + fileName + "' was not found.");
            e.printStackTrace();
        }
        return materials;
    }
}
//final
