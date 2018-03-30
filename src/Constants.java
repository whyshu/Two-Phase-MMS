import java.lang.Runtime;

public class Constants {
    public static String DATA_DIR = "C:\\Users\\Ani\\Documents\\GitHub\\Two-Phase-MMS\\data\\";
    public static String UNSORTED_FILE_PREFIX = "unsorted_chunk_";
    public static String SORTED_FILE_PREFIX = "sorted_chunk_";
    public static String INPUT_FILE = "bag";
    public static String OUTPUT_FILE = "bagoutput.txt";
    public static String BAG_OUTPUT_FILE = "bag_difference.txt";
    public static int RAM_SIZE=(int)Runtime.getRuntime().freeMemory();
    public static int BLOCK_SIZE=4096;
    public static int BLOCK_COUNT=(RAM_SIZE/BLOCK_SIZE)/2;
    public static int FILE_COUNT=2;
    
    public static double getMultiplier(){
    	if(RAM_SIZE>8*1024*1024){
    		return 4;
    	}else{
    		return 2;
    	}
    }
    
    public static int getBagDiffMultiplier(){
    	if(RAM_SIZE>8*1024*1024){
    		return 2;
    	}else{
    		return 1;
    	}
    }
    
}
