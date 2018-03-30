import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class NestedLoopJoin {

    Scanner input1=new Scanner(System.in);
    Scanner input2=new Scanner(System.in);
    int lineCounter = 0;
    int lineCounter2 = 0;
    ArrayList<String> unsortedLines = new ArrayList<String>();


    public NestedLoopJoin(){
        try {
            File file =new File(Constants.DATA_DIR+"R1.txt");
            File file1 =new File(Constants.DATA_DIR+"R2.txt");
            input1=new Scanner(file);
            input2=new Scanner(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getBlock(int linevalue){
        String line;
        ArrayList<String> unsortedLines1 = new ArrayList<String>();
        unsortedLines1.clear();
        // int count=lineCounter2+40;

        while ((linevalue < (linevalue+40)) && input2.hasNextLine() && (line = input2.nextLine()) != null) {
            unsortedLines1.add(line);
            lineCounter2++;
            linevalue++;
        }
        //System.out.println(lineCounter2);
        return unsortedLines1;
    }

    public void execute() {

        try {
            FileWriter writer = new FileWriter(Constants.DATA_DIR + "output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            String line;
            while ((lineCounter < 40) && input1.hasNextLine() && ( line =input1.nextLine())!= null ) {
                unsortedLines.add(line);
                lineCounter++;
            }
            //System.out.println(unsortedLines.size());
            ArrayList<String> blockData=new ArrayList<String>();
            int b=0;
            while(b==0){
                blockData= getBlock(lineCounter2);
                //System.out.println(blockData.size());
                if(blockData.isEmpty()){
                    unsortedLines.clear();
                    //System.out.println(unsortedLines.size());
                    int count=lineCounter+40;
                    //System.out.println(count);
                    //System.out.println(lineCounter);
                    while ((lineCounter < count ) && input1.hasNextLine() && ( line =input1.nextLine())!= null ) {
                        unsortedLines.add(line);
                        lineCounter++;
                    }
                    //System.out.println(unsortedLines.size());
                    if(unsortedLines.isEmpty()){
                        //System.out.println("not found");
                        b=1;
                    }
                    lineCounter2=0;
                    blockData= getBlock(lineCounter2);
                    //System.out.println(blockData.size());
                }

                //System.out.println("abc");
                //System.out.println(blockData.size());
                List<String> join=unsortedLines.stream().filter(blockData::contains).collect(Collectors.toList());
                for (String s : join) {
                    bw.write(s.trim());
                    bw.newLine();
                }
                blockData.clear();
            }
            bw.close();
            input1.close();
            input2.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]){
        NestedLoopJoin a=new NestedLoopJoin();
        a.execute();
    }

}




