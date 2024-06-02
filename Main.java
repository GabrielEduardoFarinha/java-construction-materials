public class Main {
    public static void main(String[] args) {
        MaterialRepository materialRepository = new MaterialRepository("materials.txt");
        MaterialService materialService = new MaterialService(materialRepository);
        MaterialController materialController = new MaterialController(materialService);
        materialController.show();
    }
}
//final