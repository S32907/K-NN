import java.util.ArrayList;
import java.util.List;

public class FlowerRepository {

    private List<Flower> flowers = new ArrayList<Flower>();

    public void AddFlower(Flower flower) {
        flowers.add(flower);
    }

    public List<Flower> GetFlowers() {
        return flowers;
    }

}
