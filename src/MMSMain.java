import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class MMSMain {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE);
        chunkFileSplitter.execute(2);

        ArrayList<String> chunkFileList=new ArrayList<>();
        chunkFileList.add("unsorted_chunk_0.txt");
        chunkFileList.add("unsorted_chunk_1.txt");
        chunkFileList.add("unsorted_chunk_2.txt");
        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
        chunkFileSorter.sort();

        BlockManager bm = new BlockManager(3);
        for (int i=0; i<240; i++) {
          bm.execute();
        }
    }

}