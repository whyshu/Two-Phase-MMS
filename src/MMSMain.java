import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class MMSMain {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
//      InputBlock ip = new InputBlock("3.txt");
//        OutputBlock op = new OutputBlock();
//        for (int i =0; i< 120; i++) {
//            op.add(String.valueOf(i));
//        }
//        BlockManager bm = new BlockManager(3);
//        for (int i=0; i<240; i++) {
//            bm.execute();
//        }
        ChunkFileSplitter chunkFileSplitter=new ChunkFileSplitter("D:\\Study\\Two-Phase-MMS\\data\\input.txt");
        chunkFileSplitter.execute(2);
//        LineNumberReader reader =  new LineNumberReader(new InputStreamReader(new FileInputStream("D:\\Study\\Two-Phase-MMS\\data\\1.txt"), "UTF-8"));
//        System.out.println(getBlock(reader).size());
//        System.out.println(getBlock(reader).size());
    }


    public static ArrayList<String> getBlock(LineNumberReader _reader) throws Exception  {
        ArrayList<String> lines = new ArrayList<>();
        int lineCounter = 0;
        try{
            String line;
            while ( lineCounter < 40  && ((line = _reader.readLine()) != null)) {
                lines.add(line);
                lineCounter = lineCounter + 1;
            }
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
        System.out.println("LC " + lineCounter);
        return lines;
    }
}