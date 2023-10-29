import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(args[0]);
        else
            System.out.println("no arguments provided");

        Properties properties = System.getProperties();

        for (String key : properties.stringPropertyNames()) {
            System.out.println(key + ": " + properties.getProperty(key));
        }

        try(BufferedReader reader = new BufferedReader(new FileReader("test.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hello world!");

        PermutationSolver perm = new PermutationSolver();
        int[] nums = {1,2,3,4,5};
        System.out.println(perm.permute(nums));
    }
}