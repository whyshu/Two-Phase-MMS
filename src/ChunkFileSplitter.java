import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by prasanth on 2/14/2018.
 */
public class ChunkFileSplitter {
    private String _chunkFileName;
    LineNumberReader _reader;
    Scanner _scanner;

    public ChunkFileSplitter(String fullPathToFile){
        try {
            _chunkFileName="data";
            //_reader = new LineNumberReader(new InputStreamReader(new FileInputStream(fullPathToFile), "UTF-8"));
            File file = new File(fullPathToFile);
            _scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void execute(int blockCount) {
        int chunkCount=0;
        BufferedWriter bw = null;
        int k = 0;
        try {
            while (_scanner.hasNextLine()) {
                String line;
                int lineCounter = 0;
                bw = new BufferedWriter(new FileWriter(_chunkFileName + chunkCount + ".txt", true));
                while ((lineCounter < blockCount * 40) && ( line =_scanner.nextLine())!= null ) {
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                    lineCounter++;
                }
                bw.close();
                chunkCount++;
                k++;
            }
            _scanner.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
