import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        K_NN k1 = new K_NN();
        k1.LoadData(k1.GetDataSet(), "iris_training.txt");

        System.out.println("Enter k that will be used for testing.");
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        input.nextLine();
        k1.Test("iris_test.txt", k);


        while(true) {
            System.out.println("=======================");
            System.out.println("Enter \"Continue\" if you would like to test new flower.");
            System.out.println("Enter \"Exit\" if you would like to exit");
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("Exit")) {
                break;
            }
            else if(choice.equalsIgnoreCase("Continue")) {
                System.out.println("Enter data for new flower according to File format.");
                String data = input.nextLine();
                Scanner dataAnalyzer = new Scanner(data);
                List<Double> NewFlowerVector = new ArrayList<Double>();
                while(dataAnalyzer.hasNextDouble()) {
                    NewFlowerVector.add(dataAnalyzer.nextDouble());
                }
                //check if input is valid
                if(NewFlowerVector.size() != k1.GetDataSet().GetFlowers().get(1).getVector().size()) {
                    System.out.println("Does not match the format");
                    continue;
                }
                System.out.println("Enter k that will be used for new flower.");
                k = input.nextInt();
                input.nextLine();
                k1.Classify(NewFlowerVector,k);

            }
            else {
                System.out.println("Command is not recognized.");
            }
        }
    }
}
