import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bag_difference {
    public HashMap<String, Integer> readFile(String filename) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(filename));
            String line = null;
            String prev = "s";
            while ((line = inputFile.readLine()) != null) {
                if (prev.equals(line)) {
                    map.put(line, map.get(line) + 1);
                } else {
                    map.put(line, 1);
                }

                prev = line;
//                if (map.containsKey(line)) {
//                    map.put(line, map.get(line) + 1);
//                } else {
//                    int value = 1;
//                    map.put(line, value);
//                }
            }
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File error");
        }
        return map;
    }


    public void comapareTupple(HashMap<String, Integer> L1, HashMap<String, Integer> L2) {

        for (Map.Entry<String, Integer> File1 : L1.entrySet()) {
            if (L2.containsKey(File1.getKey()) && File1.getValue() > L2.get(File1.getKey())) {

                System.out.println(File1.getKey() + "," + (File1.getValue()- L2.get(File1.getKey())));
             } else {
                 System.out.println(File1.getKey() + "," + 0);
             }

        }

    }

    public static void main(String[] args) {
        Bag_difference File1 = new Bag_difference();
        HashMap<String, Integer> File1_value = File1.readFile("src/Bag1.txt");
        HashMap<String, Integer> File2_value = File1.readFile("src/Bag2.txt");
        File1.comapareTupple(File1_value, File2_value);
        System.out.println(File1_value.size());
        System.out.println(File2_value.size());
    }
}


