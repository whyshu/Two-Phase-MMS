package tpmms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileOperation {
    public FileOperation(String fileName){


    }
    public static InputStream getFileStream(File inFilePtr){
        try{
            InputStream in=null;
            try{
                in=new BufferedInputStream(new FileInputStream(inFilePtr));
                return in;
            }finally{
                //in.close();
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public  static void writeChunkFiles(InputStream in,File inFilePtr,ArrayList chunkFileList){
        int totalBytesRead=0,chunkCount=0;
        long fileSize=inFilePtr.length();
        System.out.println(fileSize);
        while(totalBytesRead<fileSize){
            String chunkFileName="F:\\data"+chunkCount;
                byte[] temp=new byte[Constants.RAMSIZE];
                int bytesRead;
                try {
                    bytesRead = in.read(temp,0,Constants.RAMSIZE);
                    if(bytesRead>0){
                        totalBytesRead+=bytesRead;
                        chunkCount++;
                    }
                    System.out.println("Total bytes read :: "+totalBytesRead);
                    writeFile(temp,chunkFileName,0);
                    chunkFileList.add(chunkFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    public static void writeFile(byte[] temp,String fileName,int writeOffset){
        File file=new File(fileName);
        try{
            OutputStream op=null;
            try{
                op=new BufferedOutputStream(new FileOutputStream(file,true));
                op.write(temp, writeOffset, temp.length);
            }finally{
                op.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
