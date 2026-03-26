import java.util.*;

public class Decider {

    FlowerRepository DataSet;

    public Decider(FlowerRepository FlowerRepository) {
        this.DataSet = FlowerRepository;
    }

    public double CalculateDistance(Flower flower1, Flower flower2) {
        List<Double> vector1 = flower1.getVector();
        List<Double> vector2 = flower2.getVector();
        double distance = 0;
        for (int i = 0; i < vector1.size(); i++) {
            double diff = vector1.get(i) - vector2.get(i);
            distance += diff * diff;
        }
        return Math.sqrt(distance);
    }

    public String Classify(Flower NewFlower, int k) {


        Map<Flower, Double> distances = new HashMap<Flower, Double>();

        //Calculating distances
        for (Flower f: DataSet.GetFlowers()){
            distances.put(f, CalculateDistance(f, NewFlower));
        }
        //sorting
        ArrayList<Map.Entry<Flower, Double>> DistancesAsAList = new ArrayList<>(distances.entrySet());
        DistancesAsAList.sort(Map.Entry.comparingByValue());

        //scanning k nearest neighbours
        Map<String, Integer> neighbours = new HashMap<String,Integer>();
        for (int i = 0; i < k; i++) {
            Flower temp = DistancesAsAList.get(i).getKey();
            String ClassName = temp.getName();

            neighbours.put(ClassName, neighbours.getOrDefault(ClassName, 0) + 1);
        }

        System.out.println("Neighbours: " );
        for (Map.Entry<String,Integer> entry : neighbours.entrySet()) {
            System.out.println(entry.getKey() + " Quantity: " + entry.getValue());
        }

        String Classified = "";
        int MostCommon =0;
        for (Map.Entry<String, Integer> entry : neighbours.entrySet()) {
            if (entry.getValue() > MostCommon) {
                MostCommon = entry.getValue();
                Classified = entry.getKey();
            }
            else if (entry.getValue() == MostCommon) {
                if (Math.random() < 0.5) {
                    Classified = entry.getKey();
                }
            }
        }
        System.out.println("Decision: " + Classified);
        if (Classified.equals(NewFlower.getName())){
            System.out.println("Correct decision");
        }
        else if (NewFlower.getName().equals("")){

        }
        else {
            System.out.println("Wrong decision");
        }
        return Classified;
    }

}
