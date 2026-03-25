import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class K_NN {

    private FlowerRepository DataSet = new FlowerRepository();
    private Decider Decider = new Decider(DataSet);

    public void LoadData(FlowerRepository DataSet, String FileName) {
        try(Scanner in = new Scanner(new File(FileName))){
            while (in.hasNextLine()) {
                Scanner line = new Scanner(in.nextLine());
                List<Double> vector = new ArrayList<Double>();
                String name ="";
                while (line.hasNextDouble()) {
                    vector.add(line.nextDouble());
                }
                name = line.next();
                DataSet.AddFlower(new Flower(name, vector));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception occurred during Loading from File" + e);
        }
    }

    public void Test(String FileName, int k) {

        if (k > DataSet.GetFlowers().size()) {
            System.out.println("K must not be greater than the number of Flowers in DataSet");
            return;
        }

        FlowerRepository TestSet = new FlowerRepository();
        //loading from test file
        LoadData(TestSet, FileName);

        //Classifying
        int CorrectGuesses = 0;
        int counter = 1;
        for (Flower flower : TestSet.GetFlowers()) {
            System.out.println("=======================");
            System.out.println("Guess: " + counter);
            String guess = Decider.Classify(flower, k);
            if (guess.equals(flower.getName())) {
                CorrectGuesses++;
            }
            counter++;
        }

        //Result
        System.out.println("=======================");
        System.out.println("Correct Guesses: " + CorrectGuesses + " out of " + TestSet.GetFlowers().size());
        double percentage = (double)CorrectGuesses/TestSet.GetFlowers().size()*100;
        System.out.printf("Percentage of correct guesses: %.2f%%\n", percentage);

    }

    public void Classify(List<Double> FlowerVector, int k) {

        if (k > DataSet.GetFlowers().size()) {
            System.out.println("K must not be greater than the number of Flowers in DataSet");
            return;
        }

        Flower NewFlower = new Flower("", FlowerVector);
        System.out.println("=======================");
        String decision = Decider.Classify(NewFlower, k);
    }


    public FlowerRepository GetDataSet() {
        return DataSet;
    }
}
