import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class MMSMain {

    public static void main(String[] args) throws Exception {
        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE);
        ArrayList<String> chunkFileList=chunkFileSplitter.execute(2);

        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
        chunkFileSorter.sort();

        BlockManager bm = new BlockManager(chunkFileList.size());
        for (int i=0; i<240; i++) {
          bm.execute();
        }
    }

}