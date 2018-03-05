import java.util.ArrayList;
public class MMSMain {

	/**
	 * Loads the Input files
	 * 
	 * Split the input file into Small unsorted chunk files
	 * 
	 * Sort each Chunk file
	 * 
	 * Merge the Sorted Chunk file and create output file
	 * 
	 * Compute the Bag difference between the Sorted output files
	 * 
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception {  
    	for(int fileCount = 1;fileCount<=Constants.FILE_COUNT;fileCount++){
    		long startTimeSplit = System.nanoTime();		
	        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE+fileCount+".txt");
	        ArrayList<String> chunkFileList=chunkFileSplitter.execute(Constants.BLOCK_COUNT);
    		long endTimeSplit   = System.nanoTime();
    		Performance.SplittingTime+=calcTotalTime(startTimeSplit,endTimeSplit);
//    		
//    		
////    		long startTimeSort = System.nanoTime();	
////	        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
////	        ArrayList<String> sortedChunkFileList=chunkFileSorter.sort();
////    		long endTimeSort   = System.nanoTime();
////    		Performance.SortingTime+=calcTotalTime(startTimeSort,endTimeSort);
//
	        LineCounter lineCounter=new LineCounter();
	        int lineCount=lineCounter.count(Constants.INPUT_FILE+fileCount+".txt");
	        
	        long startTimeMerge = System.nanoTime();	
	        BlockManager bm = new BlockManager(chunkFileList.size());
	        for (int i=0; i<lineCount; i++) {
	          bm.execute();
	        }
	        bm.finish();
	        long endTimeMerge  = System.nanoTime();
	        Performance.MergingTime+=calcTotalTime(startTimeMerge,endTimeMerge);
	       
	        
	        CleanUp cleanup=new CleanUp();
	        cleanup.renameOutputFile(Constants.OUTPUT_FILE,fileCount);
	        cleanup.deleteChunks();    
    	}	

    	long startTimeDiff = System.nanoTime();	
        BagDifference bagDifference = new BagDifference();
        bagDifference.readFile(Constants.DATA_DIR + Constants.OUTPUT_FILE+"1"+".txt", "output1.txt");
        bagDifference.readFile(Constants.DATA_DIR + Constants.OUTPUT_FILE+"2"+".txt","output2.txt");
        bagDifference.compareTuple("output1.txt","output2.txt");
        long endTimeDiff  = System.nanoTime();
        Performance.BagDifferenceTime+=calcTotalTime(startTimeDiff,endTimeDiff);
        printPerformance();
    }
    
    /**
     * Compute the difference between start and end time
     * @param startTime
     * @param endTime
     * @return
     */
    public static long calcTotalTime(long startTime,long endTime){
    	long totalTime = endTime - startTime;
    	return totalTime;
    }
    
    /**
     * Prints the performance metrics
     */
    public  static void printPerformance(){
    	    System.out.println("Disk I/O Performance with RAM size :: "+(Runtime.getRuntime().totalMemory())/(1024*1024)+"MB");
    	 	System.out.println("Disk I/O for Splitting and Sorting files :: "+Performance.SplitterDiskIO);
	        System.out.println("Disk I/O for reading files during Merge :: "+ Performance.MergeReadDiskIO);
	        System.out.println("Disk I/O for writing files during Merge :: "+Performance.MergeWriteDiskIO);
	        System.out.println("Disk I/O for finding Bag difference :: "+Performance.BagDifferenceDiskIO);
	        
	        System.out.println("Time taken by Chunk File Splitter and Sorter :: "+Performance.SplittingTime/1000000000+"seconds");
	        System.out.println("Time taken by Chunk File Merger :: "+Performance.MergingTime/1000000000+"seconds");
	        System.out.println("Time taken for Bag Difference  :: "+Performance.BagDifferenceTime/1000000000+"seconds");
    }
}