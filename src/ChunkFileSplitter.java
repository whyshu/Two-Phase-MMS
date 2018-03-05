import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class has the functionalities to split the unsorted input file
 * into small chunks of files.
 *
 */
public class ChunkFileSplitter {
    private String _chunkFileName;
    LineNumberReader _reader;
    Scanner _scanner;

    /**
     * @param fileName Input File Name that has to be splitted
     */
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
    
    /**
     * Reads set of blocks (Based on available ram memory) from the input file
     * and dumps to a chunk file (for eg: Unsorted_Chunk_1.txt)
     * 
     * Repeats the process till the whole input file is read
     * 
     * @param blockCount
     * @return List of names of the chunk files that has been created
     */
    public ArrayList<String> execute(int blockCount) {
        int chunkCount=0;
        ArrayList<String> chunkFileList=new ArrayList<>();
        BufferedWriter bw = null;
        try {
            while (_scanner.hasNextLine()) {
                String line;
                int lineCounter = 0;
                String currentFileName= Constants.SORTED_FILE_PREFIX + chunkCount + ".txt";
                
                ArrayList<String> unsortedLines = new ArrayList<String>();
                while ((lineCounter < blockCount * 40) && _scanner.hasNextLine() && ( line =_scanner.nextLine())!= null ) {
                    //System.out.println(line);
                	unsortedLines.add(line);
                    lineCounter++;
                }
                
                unsortedLines.sort(Comparator.comparing(s -> Integer.parseInt(s.substring(0, 8))));
                bw = new BufferedWriter(new FileWriter(Constants.DATA_DIR+currentFileName, true));
                for(String s: unsortedLines) {
                    bw.write(s);
                    bw.newLine();
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