import java.util.ArrayList;
public class MMSMain {

    public static void main(String[] args) throws Exception {  
    	for(int fileCount=1;fileCount<=Constants.FILE_COUNT;fileCount++){
	        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE+fileCount+".txt");
	        ArrayList<String> chunkFileList=chunkFileSplitter.execute(Constants.BLOCK_COUNT);
	
	        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
	        ArrayList<String> sortedChunkFileList=chunkFileSorter.sort();
	
	        LineCounter lineCounter=new LineCounter();
	        int lineCount=lineCounter.count(Constants.INPUT_FILE+fileCount+".txt");
	        
	        BlockManager bm = new BlockManager(sortedChunkFileList.size());
	        for (int i=0; i<lineCount; i++) {
	          bm.execute();
	        }
	        bm.finish();
	        
	        CleanUp cleanup=new CleanUp();
	        cleanup.renameOutputFile(Constants.OUTPUT_FILE,fileCount);
	        cleanup.deleteChunks();    
    	}	
        
        BagDifference bagDifference = new BagDifference();
        bagDifference.readFile(Constants.DATA_DIR + Constants.OUTPUT_FILE+"1"+".txt", "output1.txt");
        bagDifference.readFile(Constants.DATA_DIR + Constants.OUTPUT_FILE+"2"+".txt","output2.txt");
        bagDifference.compareTuple("output1.txt","output2.txt");
    }
}