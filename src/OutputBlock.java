import java.io.*;
import java.util.ArrayList;

public class OutputBlock extends Block{

    private int counter = -1;
    ArrayList<String> _opBuffer=new ArrayList<>();

    public OutputBlock() {

    }

    public void add(String data) {
        counter = counter + 1;
        addToBuffer(counter, data);
        if(counter == BLOCK_MAX_INDEX) {
            writeToFile();
            counter = -1;
        }
    }

    private void addToBuffer(int counter, String data) {
        _opBuffer.add(data);
        System.out.println("Added to output buffer " + data);
    }

    private void writeToFile() {
        System.out.println("Block has been written to File");
        writeFile();
        freeBuffer();
    }

    private void freeBuffer(){
        _opBuffer.clear();
        System.out.println("Output Buffer has been freed");
    }

    private  void writeFile()  {
        BufferedWriter bw=null;
        try {
            try {
                bw = new BufferedWriter(new FileWriter("out.txt",true));
                for (String value : _opBuffer) {
                    bw.write(value);
                    bw.newLine();
                }
            }finally {
                bw.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
