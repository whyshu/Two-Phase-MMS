import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class MMSMain {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter("data\\input.txt");
        chunkFileSplitter.execute(2);

        ArrayList<String> chunkFileList=new ArrayList<>();
        chunkFileList.add("data\\data0.txt");
        chunkFileList.add("data\\data1.txt");
        chunkFileList.add("data\\data2.txt");
        ChunkFileSorter chunkFileSorter=new ChunkFileSorter(chunkFileList);
        chunkFileSorter.sort();
        
        BlockManager bm = new BlockManager(3);
        for (int i=0; i<240; i++) {
          bm.execute();
        }
    }

}