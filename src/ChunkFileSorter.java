import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ChunkFileSorter {
	private ArrayList<String> _chunkFileList;
	public ChunkFileSorter(ArrayList<String> chunkFileList){
		_chunkFileList=chunkFileList;
	}
	public void sort(){
		try {
			for(String fileName:_chunkFileList){
				//Open the file
				File file=new File(fileName);
				Scanner sortScan=new Scanner(file);
			    List<Integer> L = new ArrayList<Integer>();
				while(sortScan.hasNextLine()){
					L.add(Integer.parseInt(sortScan.nextLine()));
				}
				file.delete();
				Collections.sort(L);
				writeFile("sorted_"+fileName,L);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeFile(String fileName,List sortedLines){
		BufferedWriter bw=null;
        try {
            try {
                bw = new BufferedWriter(new FileWriter(fileName,true));
                for (Object value : sortedLines) {
                	System.out.println("Sorted::"+value.toString()+"File name :: "+fileName);
                    bw.write(value.toString());
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
