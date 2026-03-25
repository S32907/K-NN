import java.util.List;

public class Flower {

    private List<Double> vector;
    private String name;

    public Flower(String name, List<Double> vector) {
        this.name = name;
        this.vector = vector;
    }

    public String getName() {
        return name;
    }

    public List<Double> getVector() {
        return vector;
    }

    public String toString() {
        return name + ": " + vector.toString();
    }

}
