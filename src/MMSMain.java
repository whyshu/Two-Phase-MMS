import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class MMSMain {

    public static void main(String[] args) throws Exception {
        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter(Constants.INPUT_FILE);
        ArrayList<String> chunkFileList=chunkFileSplitter.execute(1280);

        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
        ArrayList<String> sortedChunkFileList=chunkFileSorter.sort();

        BlockManager bm = new BlockManager(sortedChunkFileList.size());
        for (int i=0; i<1200472; i++) {
          bm.execute();
        }
        //InputBlock ip = new InputBlock(Constants.SORTED_FILE_PREFIX + 19 + ".txt");
        //System.out.println(ip.isDataAvailable());
        //Student student = new Student("40000006Ada       Blair     10600259611247Tannor Pass,Iduwotcet,NB,V8F 4R1");
    }
}