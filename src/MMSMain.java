import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MMSMain {

    public static void main(String[] args) throws Exception {  
//    	for(int fileCount=1;fileCount<=Constants.FILE_COUNT;fileCount++){
//	        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE+fileCount+".txt");
//	        ArrayList<String> chunkFileList=chunkFileSplitter.execute(Constants.BLOCK_COUNT);
//	
//	        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
//	        ArrayList<String> sortedChunkFileList=chunkFileSorter.sort();
//	
//	        LineCounter lineCounter=new LineCounter();
//	        int lineCount=lineCounter.count(Constants.INPUT_FILE+fileCount+".txt");
//	        
//	        System.out.println(lineCount);
//	        BlockManager bm = new BlockManager(sortedChunkFileList.size());
//	        for (int i=0; i<lineCount; i++) {
//	          bm.execute();
//	        }
//	        bm.finish();
//	        
//	        CleanUp cleanup=new CleanUp();
//	        cleanup.renameOutputFile(Constants.OUTPUT_FILE,fileCount);
//	        cleanup.deleteChunks();    
//    	}
    	
    	
        
        BagDifference bagDifference = new BagDifference();
        HashMap<String, Integer> bag1 = bagDifference.readFile(Constants.DATA_DIR + Constants.OUTPUT_FILE+"1"+".txt", "output1.txt");
        HashMap<String, Integer> bag2 = bagDifference.readFile(Constants.DATA_DIR + Constants.OUTPUT_FILE+"2"+".txt","output2.txt");
       //bagDifference.comapareTupple(bag1, bag2);        
    }
}