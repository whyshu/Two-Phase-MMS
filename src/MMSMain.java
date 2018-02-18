import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class MMSMain {

    public static void main(String[] args) throws Exception {
        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE);
        System.out.println("Splitting files");
        ArrayList<String> chunkFileList=chunkFileSplitter.execute(Constants.BLOCK_COUNT);

        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
        ArrayList<String> sortedChunkFileList=chunkFileSorter.sort();

        BlockManager bm = new BlockManager(sortedChunkFileList.size());
        for (int i=0; i<1200472; i++) {
          bm.execute();
        }
    }
}