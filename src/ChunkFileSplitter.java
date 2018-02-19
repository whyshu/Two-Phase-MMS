import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ChunkFileSplitter {
    private String _chunkFileName;
    LineNumberReader _reader;
    Scanner _scanner;

    public ChunkFileSplitter(String fileName){
        try {
            _chunkFileName="data";
            String fullPathToFile = Constants.DATA_DIR + fileName;
            File file = new File(fullPathToFile);
            _scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> execute(int blockCount) {
        int chunkCount=0;
        ArrayList<String> chunkFileList=new ArrayList<>();
        BufferedWriter bw = null;
        try {
            while (_scanner.hasNextLine()) {
                String line;
                int lineCounter = 0;
                String currentFileName= Constants.UNSORTED_FILE_PREFIX + chunkCount + ".txt";
                bw = new BufferedWriter(new FileWriter(Constants.DATA_DIR+currentFileName, true));
                while ((lineCounter < blockCount * 40) && _scanner.hasNextLine() && ( line =_scanner.nextLine())!= null ) {
                    //System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                    lineCounter++;
                }
                bw.close();
                chunkFileList.add(currentFileName);
                chunkCount++;
                //Performance start
                Performance.SplitterDiskIO++;
                //Performance end
            }
            _scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chunkFileList;
    }
}
