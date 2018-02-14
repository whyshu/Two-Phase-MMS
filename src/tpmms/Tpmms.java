package tpmms;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tpmms {

    public static void main(String []args){
        //Split the file as chunk files
        //ArrayList chunkFileList=splitFileAsChunks("bag1.txt");

        //Sort seperate chunk files
        //TODO sort(chunkFileList);

        //Take one block from each sorted chunk file
        //ArrayList filePtr=getFilePtr(chunkFileList);

        //Populate k-1 blocks in main memory at once
        //and kth buffer for main memory
        //Merge using minimum heap data structure
        //merge(chunkFileList,filePtr);
        //sampleMerge();
        try {
            check.test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sampleMerge(){
        Stream<String> totLines = null;
        try {
            totLines = Files.lines(Paths.get("bag1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List result;
        long startLine=0;
        long endLine=startLine+40;
        final long skip = startLine;
        final long limit = endLine;
        totLines.skip(startLine).limit(endLine).forEach((s)->{
            System.out.println(s);
        });
    }

    public static ArrayList<String> splitFileAsChunks(String fileName){
        File inFilePtr=new File(fileName);
        long totalBytesRead=0,chunkCount=0;
        ArrayList<String> chunkFileList = new ArrayList<String> ();
        InputStream in=FileOperation.getFileStream(inFilePtr);
        FileOperation.writeChunkFiles(in,inFilePtr,chunkFileList);
        return chunkFileList;
    }

    public static void reFill(){
        byte[] opBuffer=new byte[Constants.BLOCKSIZE];
    }

    public static ArrayList getFilePtr(ArrayList chunkFileList){
        ArrayList<File> filePtr=new ArrayList<File>();
        //Input is a set of sorted chunk files
        Iterator fileIter=chunkFileList.iterator();
        int cnt=0;
        while(fileIter.hasNext()){
            File cFilePtr=new File(fileIter.next().toString());
            filePtr.add(cFilePtr);
            cnt++;
        }
        return filePtr;
    }

    public static void merge(ArrayList chunkFileList,ArrayList filePtr) {
        //Create a minimum heap data structure to occupy blocks for merging
        MinHeap minHeapInst = new MinHeap(Constants.RAMSIZE);
        //Declare variables
        int bytesRead = 0;
        boolean flag = true;
        int writeOffset = 0;
        long totTupleLen = 0;
        byte[] temp = new byte[Constants.BLOCKSIZE];
        //Allocate one output block of size 4K
        ByteBuffer mmOpBuffer = ByteBuffer.allocate(Constants.BLOCKSIZE);
        String writeFileName = "output.txt";
        flag=true;
        int []totalBytesRead=new int[chunkFileList.size()];
        while (flag) {
            Iterator fileIter = filePtr.iterator();
            int i=0;
            while (fileIter.hasNext()) {
                File currFilePtr=(File)fileIter.next();
                InputStream stream = FileOperation.getFileStream(currFilePtr);
                int currFileSize = (int) currFilePtr.length();
                try {
                    //read each file and keep inserting the first block of each file
                    bytesRead = stream.read(temp,0, Constants.BLOCKSIZE);
                    String tuplesBlk = new String(temp, "UTF-8");
                    String[] tuples = tuplesBlk.split("\\r?\\n");
                    totTupleLen =totTupleLen+ tuples.length;
                    System.out.println(totTupleLen);
                    for (String tuple : tuples) {
                        if (tuple != null && !tuple.isEmpty()) {
                            MinHeap.insert(tuple);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }
}