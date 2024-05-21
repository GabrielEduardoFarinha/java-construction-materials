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

    public Material(String id, String name, double value, String sizeUnit, double size) {
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", sizeUnit='" + sizeUnit + '\'' +
                ", size=" + size +
                '}';
    }
}
