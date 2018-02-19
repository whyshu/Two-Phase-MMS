import java.lang.Runtime;

public class Constants {
    public static String DATA_DIR = "data\\";
    public static String UNSORTED_FILE_PREFIX = "unsorted_chunk_";
    public static String SORTED_FILE_PREFIX = "sorted_chunk_";
    public static String INPUT_FILE = "bag";
    public static String OUTPUT_FILE = "bagoutput.txt";
    public static int RAM_SIZE=(int)Runtime.getRuntime().freeMemory();
    public static int BLOCK_SIZE=4096;
    public static int BLOCK_COUNT=(RAM_SIZE/BLOCK_SIZE)/4;
    public static int FILE_COUNT=2;
}
