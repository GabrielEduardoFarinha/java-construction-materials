import java.io.Serial;
import java.io.Serializable;

public class Material implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String name;
    private final double value;
    private final String sizeUnit;
    private final double size;

    public Material(String id, String name, double value, String sizeUnit, double size) throws MaterialCreationException {
        if (id == null || id.isEmpty()) {
            throw new MaterialCreationException("ID cannot be null or empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new MaterialCreationException("Name cannot be null or empty.");
        }
        if (value <= 0) {
            throw new MaterialCreationException("Value must be greater than zero.");
        }
        if (sizeUnit == null || sizeUnit.isEmpty()) {
            throw new MaterialCreationException("Size unit cannot be null or empty.");
        }
        if (size <= 0) {
            throw new MaterialCreationException("Size must be greater than zero.");
        }

        this.id = id;
        this.name = name;
        this.value = value;
        this.sizeUnit = sizeUnit;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Material{" +
                "codigo='" + id + '\'' +
                ", nome do material='" + name + '\'' +
                ", valor=" + value +
                ", unidade de medida='" + sizeUnit + '\'' +
                ", tamanho=" + size +
                '}';
    }
}
//final