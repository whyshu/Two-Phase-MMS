package tpmms;

import java.io.*;

public class check {
    public static void test() throws IOException {
//        byte[] temp = new byte[Constants.BLOCKSIZE];
//        OutputStream op=null;
//        File cFilePtr=new File("bag1.txt");
//        File oFilePtr=new File("op.txt");
//        FileInputStream stream = FileOperation.getFileStream(cFilePtr);
//        op=new BufferedOutputStream(new FileOutputStream(oFilePtr,true));
//        long fileSize=cFilePtr.length();
//        int totalBytesRead=1;
//        while(stream.is) {
//            //read each file and keep inserting the first block of each file
//            int bytesRead = stream.read(temp);
//            String tuplesBlk = new String(temp, "UTF-8");
//            op.write(temp);
//            totalBytesRead = bytesRead;
//        }

        File file=new File("bag1.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        OutputStream op=null;
        File oFilePtr=new File("op.txt");
        op=new BufferedOutputStream(new FileOutputStream(oFilePtr,true));
        byte[] chunk=new byte[Constants.BLOCKSIZE];
        int chunkLen=0;
        while((chunkLen=fileInputStream.read(chunk))!=-1){
            System.out.println(chunk.length);
            op.write(chunk);
        }
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        // Seek to the end of file
        raf.seek(oFilePtr.length());
        // Read it out.
        raf.read(chunk, 0, (int)(file.length()-oFilePtr.length()));
    }

    //refill the output buffer
    public static byte[] refill(){
        byte[] opBuffer=new byte[Constants.BLOCKSIZE];
        return opBuffer;
    }
}
