
import java.io.Serializable;
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private double value;
    private String sizeUnit;
    private double size;
    public Material(String id, String name, double value, String sizeUnit, double size) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.sizeUnit = sizeUnit;
        this.size = size;
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
 