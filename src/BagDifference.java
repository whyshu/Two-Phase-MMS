import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BagDifference {
    public HashMap<String, Integer> readFile(String filename, String outputFilename) {

        HashMap<String, Integer> map = new LinkedHashMap<String, Integer>();

        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(filename));
            String line = null;
            String prev = "s";
            int counter = 0;
            while ((line = inputFile.readLine()) != null) {
                if (prev.equals(line)) {
                    map.put(line, map.get(line) + 1);
                } else {
                	if (map.size()>10000){
                		writeFile(outputFilename,map);
                		map.clear();
                	}
                	counter++;
                	System.out.println("Memory :: "+Runtime.getRuntime().freeMemory()+"Map size :: "+map.size() + " Counter " + counter);
                    map.put(line, 1);
                }
                prev = line;
            }
            writeFile(outputFilename, map);
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File error");
        }
        return map;
    }

    
    public void writeFile(String fileName,HashMap<String,Integer> tupleList){
		BufferedWriter bw=null;
        try {
            try {
            	String fullPathToFile = Constants.DATA_DIR + fileName;
                bw = new BufferedWriter(new FileWriter(fullPathToFile,true));
                for (Map.Entry<String, Integer> entry : tupleList.entrySet()){
                    bw.write(entry.getKey().toString()+"###"+entry.getValue().toString());
                    bw.newLine();
                }
            }finally {
                bw.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
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
}


